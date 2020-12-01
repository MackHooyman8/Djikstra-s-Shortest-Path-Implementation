// --== CS400 File Header Information ==--
// Name: Mack Hooyman
// Email: mhooyman@wisc.edu
// Team: EG
// Role: Test Engineer 2
// TA: Keren Chen
// Lecturer: Gary Dahl

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Project3Test {

	/**
	 * Tests whether the getCurrentLocation method gets a given LocationObject correctly when run.
	 */
	@Test
	void testGetCurrentLocation() {
		BackEndMethods test = new BackEndMethods();
		
		String expected = "Main Entrance";
		assertTrue(test.getCurrentLocation().contains(expected));
	}

/**
	 * Tests whether the setCurrentLocation method sets a given LocationObject correctly when run.
	 */
	@Test
	void testSetCurrentLocation() {
		BackEndMethods test = new BackEndMethods();
		
		String expected = "Sorry the location you entered does not exist";
		assertTrue(test.setCurrentLocation("not a real location").equals(expected));
		
		String expected2 = "Your location has been successfully set to: The ZipZapZop";
		assertTrue(test.setCurrentLocation("The ZipZapZop").equals(expected2));
		
		String expected3 = "The ZipZapZop";
		assertTrue(test.getCurrentLocation().contains(expected3));
	}
  
	/**
	 * Tests whether the getAllRestaurant method returns all applicable LocationObjects when run. 
	 */
	@Test
	void testGetAllRestaurants() {
		BackEndMethods test = new BackEndMethods();

		assertTrue(test.getAllRestaurants().contains("Cold Ones"));
		assertTrue(test.getAllRestaurants().contains("Jasmine Vignette"));
		assertTrue(test.getAllRestaurants().contains("The Greasy Fool"));
		assertTrue(test.getAllRestaurants().contains("Cupcakes of The Grand Dessert"));
	}
	
	/**
	 * Tests whether the locateClosestRestaurant method returns the closest applicable LocationObject  when run. 
	 */
	@Test
	void testLocateClosestRestaurant() {
		BackEndMethods test = new BackEndMethods();
		
		String expected = "The Greasy Fool";
		assertTrue(test.locateClosestRestaurant().contains(expected));
		
		test.setCurrentLocation("The 6ft Under");
		
		String expected2 = "Cupcakes of The Grand Dessert";
		assertTrue(test.locateClosestRestaurant().contains(expected2));
	}
	
	/**
	 * Tests whether the locateSpecificRestaurant method returns a given LocationObject when run. 
	 */
	@Test
	void testLocateSpecificRestaurant() {
		BackEndMethods test = new BackEndMethods();
		
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("Main Entrance"));
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("The Greasy Fool"));
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("Central Bathrooms"));
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("The 6ft Under"));
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("Speeds Hopper"));
		assertTrue(test.locateSpecificRestaurant("Cupcakes of The Grand Dessert").contains("Cupcakes of The Grand Dessert"));
	}
	
	/**
	 * Tests whether the getAllAttractions method returns all applicable LocationObjects when run. 
	 */
	@Test
	void testGetAllAttractions() {
		BackEndMethods test = new BackEndMethods();
		
		assertTrue(test.getAllAttractions().contains("The ZipZapZop"));
		assertTrue(test.getAllAttractions().contains("Interstellar Hill"));
		assertTrue(test.getAllAttractions().contains("The 5 Times Pointy"));
		assertTrue(test.getAllAttractions().contains("Maggot Destruction"));
		assertTrue(test.getAllAttractions().contains("Speeds Hopper"));
		assertTrue(test.getAllAttractions().contains("Perfect Poodles"));
		assertTrue(test.getAllAttractions().contains("The 6ft Under"));
	}
	
	/**
	 * Tests whether the locateSpecificAttraction method returns a specific LocationObject when run. 
	 */
	@Test
	void testLocateSpecificAttraction() {
		BackEndMethods test = new BackEndMethods();
		
		assertTrue(test.locateSpecificAttraction("Perfect Poodles").contains("Main Entrance"));
		assertTrue(test.locateSpecificAttraction("Perfect Poodles").contains("The Greasy Fool"));
		assertTrue(test.locateSpecificAttraction("Perfect Poodles").contains("Perfect Poodles"));
	}
	
	/**
	 * Tests whether the locateClosestAttraction method returns the closest LocationObject by distance when run. 
	 */
	@Test
	void testLocateClosestAttraction() {
		BackEndMethods test = new BackEndMethods();
		
		String expected = "Interstellar Hill";
		assertTrue(test.locateClosestAttraction().contains(expected));
		
		test.setCurrentLocation("Maggot Destruction");
		
		String expected2 = "Maggot Destruction";
		assertTrue(test.locateClosestAttraction().contains(expected2));
	}
	
	/**
	 * Tests the locateClosestBathroom method to ensure that the closest location object that has type bathroom is returned
	 */
	@Test
	void testLocateClosestBathroom() {
		BackEndMethods test = new BackEndMethods();
		
		String expected = "Central Bathrooms";
		assertTrue(test.locateClosestBathroom().contains(expected));
		
		test.setCurrentLocation("Interstellar Hill");
		
		String expected2 = "The Deuce";
		assertTrue(test.locateClosestBathroom().contains(expected2));
	}
	
	/**
	 * Tests to ensure that the load data object correctly creates the needed data fields
	 */
	@Test
	void testLoadData() {
		LoadDataObject test = new LoadDataObject();
		
		assertTrue(test.getBathrooms() != null);
		assertTrue(test.getRides() != null);
		assertTrue(test.getRestuarnts() != null);
		assertTrue(test.mapGraph() != null);
		assertTrue(test.getBathrooms() != null);
	}
}
