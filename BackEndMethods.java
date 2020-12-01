// --== CS400 File Header Information ==--
// Name: <Zi Hern Wong>
// Email: <zwong4@wisc.edu email address>
// Team: <EG>
// TA: <Keren Chen>
// Lecturer: <Gary>
// Notes to Grader: <optional extra notes>
import java.util.List;

public class BackEndMethods {
	private List<LocationObject> bathrooms; 
	private List<LocationObject> restaurants;  
	private List<LocationObject> attractions; 
	public CS400Graph<LocationObject> parkMap;
	LocationObject here; 
	LocationObject gate; 
	
	public BackEndMethods() {
		LoadDataObject LDO = new LoadDataObject();
		this.bathrooms = LDO.getBathrooms();
		this.restaurants = LDO.getRestuarnts();
		this.parkMap = LDO.mapGraph();
		this.attractions = LDO.getRides();
		this.gate = LDO.getGate();

		// initialize the park visitors start gate
		this.here = this.gate;
	}

	/**
	 * This method returns a locatioObject with the name inputed or null if the name is not an attraction 
	 * @param name
	 * @return LocationObject with the name inputed
	 */
	private LocationObject containsAttraction(String name) {
		for(int i =0;i<this.attractions.size();i++) {
			if(this.attractions.get(i).getName().equals(name)) {
				return this.attractions.get(i);
				}
		}
		return null;
	}
	/**
	 * This method returns a locationObject with the name inputed or null if the name is not a restaurant
	 * @param name
	 * @return LocationObject with the name inputed
	 */
	private LocationObject containsRestaurant(String name) {
		for(int i =0;i<this.restaurants.size();i++) {
			if(this.restaurants.get(i).getName().equals(name)) {
				return this.restaurants.get(i);
				}
		}
		return null;
	}
	/**
	 * This method returns a locationObject with the name inputed or null if the name is not a bathroom
	 * @param name
	 * @return LocationObject with the name inputed
	 */
	private LocationObject containsBathroom(String name) {
		for(int i =0;i<this.bathrooms.size();i++) {
			if(this.bathrooms.get(i).getName().equals(name)) {
				return this.bathrooms.get(i);
				}
		}
		return null;
	}

	/**
	 * This helper method generates the string based output for the five locate* methods (locateClosestBathroom, locateSpecificResaurant)
	 * String should include...
	 * - Name/type/description of the destination
	 * - The path (Maybe just the names)
	 * - The distance of the path
	 * 
	 * @param path - the path being returned to the front end
	 * @return String based representation of the path as described above
	 */
	private String getPathOutput(List<LocationObject> path, int distance) {
		String output = "Destination:\n" + path.get(path.size()-1).toString();
		output += "\nPath:\n";
		for (int i = 0; i < path.size(); i++) {
			output += "\t" + path.get(i).getName() + "\n";
		}
		output += "Distance: " + distance + " meters";
		return output;
	}

	/**
	 * This method checks if the attraction exists and if so, returns a string with the path locations and distance
	 * This method returns an error message if the location doesn't exist
	 * @param name
	 * @return a String representation of either a path or an error message
	 */
	String locateSpecificAttraction(String name) {
		if(containsAttraction(name)!=null) {
			return getPathOutput(this.parkMap.shortestPath(this.here, containsAttraction(name)),
				this.parkMap.getPathCost(this.here, containsAttraction(name)));
		}
		else {
			return "Attraction Does Not Exist";
		}
	}
	
	/**
	 * This method checks if the restaurant exists and if so, returns a string with the path locations and distance
	 * This method returns an error message if the location doesn’t exist
	 * @param name
	 * @return a String representing either a path or an error message
	 */
	String locateSpecificRestaurant(String name) {
		if(containsRestaurant(name)!=null) {
			return getPathOutput(this.parkMap.shortestPath(this.here, containsRestaurant(name)),
				this.parkMap.getPathCost(this.here, containsRestaurant(name)));
		}
		else {
			return "Restaurant Does Not Exist";
		}
	}
	
	/**
	 * This method returns a string with the path locations and distance from current location to main entrance
	 * @return a String representing the path from current location to the gate
	 */
	String pathToGate() {
		return getPathOutput(this.parkMap.shortestPath(this.here, this.gate),
				this.parkMap.getPathCost(this.here, this.gate));
	}
	/**
	 * This methods calculates every bathroom's distance away from the current location in the list
	 * to see which one yields the shortest distance
	 * This then returns the path to the closest bathroom
	 * @return String that represents the shortest path to the nearest bathroom
	 */
	String locateClosestBathroom() {
		int distances [] = new int[this.bathrooms.size()];
		int min =this.parkMap.getPathCost(this.here, this.bathrooms.get(0));//sets the minimum value to the first distance
		int indexOfClosestBathroom =0;
		distances[0] =min;
		//for each bathroom, compute the shortest distance and store it in the array
		for(int i =1;i<this.bathrooms.size();i++) {
			distances[i] = this.parkMap.getPathCost(this.here, this.bathrooms.get(i));
			if(distances[i]<min) {
				min= distances[i];
			}
		}
		//this for loop is used to check which bathroom had the shortest distance
		for(int h =0;h<this.bathrooms.size();h++) {
			if(min==distances[h]) {
				indexOfClosestBathroom = h;
			}
		}
		return getPathOutput(this.parkMap.shortestPath(this.here, this.bathrooms.get(indexOfClosestBathroom)),
			this.parkMap.getPathCost(this.here, this.bathrooms.get(indexOfClosestBathroom)));
	}
	
	/**
	 * This method calculates every restaurant's distance away from the current location in the list
	 * to see which one yields the shortest distance
	 * this method then returns the path to the closest bathroom in respect to the current location
	 * @return String that represents the shortest path to the nearest restaurant
	 */
	String locateClosestRestaurant() {
		int distances [] = new int[this.restaurants.size()];
		if (this.here == null) System.out.println("Here is null");
		if (this.restaurants == null) System.out.println("The restaurants list is null");
		if (this.restaurants.get(0) == null) System.out.println("The firt entry in the restaurants list is null");
		
		int min =this.parkMap.getPathCost(this.here, this.restaurants.get(0));//sets the minimum value to the first distance
		int indexOfClosestRestaurant =0;
		distances[0] =min;
		//for each restaurant, compute the shortest distance and store it in the array
		for(int i =1;i<this.restaurants.size();i++) {
			distances[i] = this.parkMap.getPathCost(this.here, this.restaurants.get(i));
			if(distances[i]<min) {
				min= distances[i];
			}
		}
		//this for loop is used to check which restaurant had the shortest distance
		for(int h =0;h<this.restaurants.size();h++) {
			if(min==distances[h]) {
				indexOfClosestRestaurant = h;
			}
		}
		return getPathOutput(this.parkMap.shortestPath(this.here, this.restaurants.get(indexOfClosestRestaurant)),
			this.parkMap.getPathCost(this.here, this.restaurants.get(indexOfClosestRestaurant)));
	}
	//Finds the shortest path to the closest Attraction
	String locateClosestAttraction() {
		int distances [] = new int[this.attractions.size()];
		int min =this.parkMap.getPathCost(this.here, this.attractions.get(0));//sets the minimum value to the first distance
		int indexOfClosestAttraction =0;
		distances[0] =min;
		//for each attraction, compute the shortest distance and store it in the array
		for(int i =1;i<this.attractions.size();i++) {
			distances[i] = this.parkMap.getPathCost(this.here, this.attractions.get(i));
			if(distances[i]<min) {
				min= distances[i];
			}
		}
		//this for loop is used to check which attraction had the shortest distance
		for(int h =0;h<this.attractions.size();h++) {
			if(min==distances[h]) {
				indexOfClosestAttraction = h;
			}
		}
		return getPathOutput(this.parkMap.shortestPath(this.here, this.attractions.get(indexOfClosestAttraction)),
			this.parkMap.getPathCost(this.here, this.attractions.get(indexOfClosestAttraction)));
	}
	
	/**
	 * This method returns a string containing the names and descriptions of all LocationObjects in attractions list.
	 * @return a String which contains the names and descriptions of all LocationObjects in the attractions list
	 */
	String getAllAttractions() {
		String toReturn ="";
		for (int i=0;i<this.attractions.size();i++) {
			toReturn +=this.attractions.get(i).toString()+"\n";//adds a new line after each attraction
		}
		return toReturn;
	}

	/**
	 * This method returns a string containing the names and descriptions of all LocationObjects in attractions list.
	 * @return a String which containts the names and descriptions of all LocationObjects in the attractions list
	 */
	String getAllRestaurants() {
		String toReturn ="";
		for (int i=0;i<this.restaurants.size();i++) {
			toReturn +=this.restaurants.get(i).toString()+"\n";
		}
		return toReturn;
		
	}
	
	/**
	 * This method returns a confirmation message if the location exists and an error otherwise
	 * @param name
	 * @return a String representation of a confirmation or error message
	 */
	String setCurrentLocation(String name) {
		String confirmationMessage ="Sorry the location you entered does not exist";
		for (int i =0;i<this.attractions.size();i++) {
			if(name.equals(this.attractions.get(i).getName())) {
				this.here = this.attractions.get(i);
				return "Your location has been successfully set to: " + name;
			}
		}

		// Also loop through restaurants and bathrooms
		for (int i =0;i<this.restaurants.size();i++) {
			if(name.equals(this.restaurants.get(i).getName())) {
				this.here = this.restaurants.get(i);
				return "Your location has been successfully set to: " + name;
			}
		}

		for (int i =0;i<this.bathrooms.size();i++) {
			if(name.equals(this.bathrooms.get(i).getName())) {
				this.here = this.bathrooms.get(i);
				return "Your location has been successfully set to: " + name;
			}
		}

		if(name.equals("Main Entrance")) {
			this.here = this.gate;
			return "Your location has been successfully set to: " + name;
		}

		return confirmationMessage;
	}
	
	/**
	 * This method returns a string representation of the user's current location
	 * @return a String which contains the location of the user's current location
	 */
	String getCurrentLocation() {
		return this.here.toString();
	}

}
