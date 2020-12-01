// --== CS400 File Header Information ==--
// Name: Will Wightman
// Email: wwightman@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Florian Heimel
// Notes to Grader: <optional extra notes>

import java.util.Scanner;

public class FrontEndInterface {
    // Error messages that can be returned from BackEndMethods
    private final static String RESTAURANT_ERROR = "Restaurant Does Not Exist";
    private final static String ATTRACTION_ERROR = "Attraction Does Not Exist";
    private final static String LOCATION_ERROR = "Sorry the location you entered does not exist";
    
    // The menu
    private final static String MENU = "Here is a list of available commands!\n"
    + "FR - Find a specific restaurant\n"
    + "FA - Find a specific atrraction\n"
    + "LR - Lists all resaurants in the park\n"
    + "LA - Lists all the attractions in the park\n"
    + "CB - Finds the closest bathroom\n"
    + "CR - Finds the closest restaurant\n"
    + "CA - Finds the closest attraction\n"
    + "SL - Set your current location\n"
    + "GL - Gets your current location\n"
    + "Q - Quit and exit the park";

    public static void main(String[] args) {
        BackEndMethods backEnd = new BackEndMethods(); // COntains the representation of the park and relevant functions
        Scanner input = new Scanner(System.in); // reads user input
        
        boolean done = false; // Indicates if the user wants to leave the park
        boolean searching =  false; // Indicates if the user is still looking for a location
        String command; // The user's commands
        
        // The found locations
        String restaurant;
        String attraction;
        String bathroom;
        String location;
        
        String[] tokens; // an array used to parse the name of a location from the backEnd's output

        // Prints a welcome message when the user enters the park
        System.out.println("Welcome to Dijkstra's Daredevil Adventures!\nThis applcation is your guide to our world renound amusement park!");
        System.out.println("You are currently at the gate!");

        do {
            System.out.println("\nPlease enter a command. (Type \"H\" to list menu options)");
            command = input.nextLine().toUpperCase();

            switch (command) {
                // Finds a specific restaurant
                case "FR":
                    do { // Looks for a specific attraction while the user is still searching
                        System.out.println("\nPlease enter the name of the restaurant you are looking for!");
                        String test = input.nextLine().trim();
                        System.out.println(test);
                        restaurant = backEnd.locateSpecificRestaurant(test);
                        System.out.println(restaurant);
                        searching = false;

                        // If the location cannot be found, asks the user if they wish to keep looking
                        if (restaurant.equals(RESTAURANT_ERROR)) {
                            System.out.println("\nWould you like to keep looking? (Y/N)");
                            if (input.nextLine().trim().toUpperCase().equals("Y")) searching = true;
                        }
                        // If the location exists, asks the user if they would like to go there
                        else {
                            System.out.println("\nWould you like to travel to this location?  (Y/N)");
                            tokens = restaurant.split(":");
                            if (tokens.length >= 3) restaurant = tokens[2].split("\n")[0].trim();
                            if (input.nextLine().trim().toUpperCase().equals("N")) searching = false;
                            else System.out.println(backEnd.setCurrentLocation(restaurant));
                        }

                    } while (searching);
                    break;
                
                // Finds a specific Attraction
                case "FA":
                    searching =  false;

                    do { // Looks for a specific attraction while the user is still searching
                        System.out.println("\nPlease enter the name of the attraction you are looking for!");
                        attraction = backEnd.locateSpecificAttraction(input.nextLine());
                        System.out.println(attraction);
                        searching = false;

                        // If the location cannot be found, asks the user if they wish to keep looking
                        if (attraction.equals(ATTRACTION_ERROR)) {
                            System.out.println("\nWould you like to keep looking? (Y/N)");
                            if (input.nextLine().trim().toUpperCase().equals("Y")) searching = true;
                        }
                        // If the location exists, asks the user if they would like to go there
                        else {
                            System.out.println("\nWould you like to travel to this location? (Y/N)");
                            tokens = attraction.split(":");
                            if (tokens.length >= 3) attraction = tokens[2].split("\n")[0].trim();
                            if (input.nextLine().trim().toUpperCase().equals("N")) searching = false;
                            else System.out.println(backEnd.setCurrentLocation(attraction));
                        }
                    } while (searching);
                    break;

                // Lists all restaurants in the park
                case "LR":
                    System.out.println(backEnd.getAllRestaurants());
                    break;

                // Lists all attractions in the park
                case "LA":
                    System.out.println(backEnd.getAllAttractions());
                    break;
                
                // Finds the path to the closest bathroom
                case "CB":
                    bathroom = backEnd.locateClosestBathroom();
                    System.out.println(bathroom);
                    
                    // Parses the name of the location
                    tokens = bathroom.split(":");
                    if (tokens.length >= 3) bathroom = tokens[2].split("\n")[0].trim();
                    
                    // Asks the user if they would like to travel to that location
                    System.out.println("\nWould you like to travel to this location? (Y/N)");
                    if (input.nextLine().trim().toUpperCase().equals("N")) searching = false;
                    else System.out.println(backEnd.setCurrentLocation(bathroom));
                    break;
                
                // Finds the path to the closest restaurant
                case "CR":
                    restaurant = backEnd.locateClosestRestaurant();
                    System.out.println(restaurant);

                    // Parses the name of the location
                    tokens = restaurant.split(":");
                    if (tokens.length >= 3) restaurant = tokens[2].split("\n")[0].trim();
                    
                    // Asks the user if they would like to travel to that location
                    System.out.println("\nWould you like to travel to this location? (Y/N)");
                    if (input.nextLine().trim().toUpperCase().equals("N")) searching = false;
                    else System.out.println(backEnd.setCurrentLocation(restaurant));
                    break;

                // Finds the path to the closest attraction
                case "CA":
                    attraction = backEnd.locateClosestAttraction();
                    System.out.println(attraction);
                    
                    // Parses the name of the location
                    tokens = attraction.split(":");
                    if (tokens.length >= 3) attraction = tokens[2].split("\n")[0].trim();
                    
                    // Asks the user if they would like to travel to that location
                    System.out.println("\nWould you like to travel to this location? (Y/N)");
                    if (input.nextLine().trim().toUpperCase().equals("N")) searching = false;
                    else System.out.println(backEnd.setCurrentLocation(attraction));
                    break;

                // Sets the park atendee's location
                case "SL":
                    searching =  false;

                    do { // Asks the user to set their location
                        System.out.println("\nPlease enter the name of your current location!");
                        location = backEnd.setCurrentLocation(input.nextLine());
                        System.out.println(location);
                        searching = false;

                        // If the location could not be found, keep searching
                        if (location.equals(LOCATION_ERROR)) {
                            System.out.println("\nWould you like to keep trying? (Y/N)");
                            if (input.nextLine().trim().toUpperCase().equals("Y")) searching = true;
                        }
                    } while (searching);
                    break;

                // Tells the atendee where they are currently located in the park
                case "GL":
                    System.out.println(backEnd.getCurrentLocation());
                    break;

                // Lists the available commands
                case "H":
                    System.out.println(MENU);
                    break;

                case "Q":
                    done = true;
                    break;
                default:
                    System.out.println("\nThat command was unrecognised. Please enter a valid command.");
                    break;
            }

        } while (!done);

        System.out.println("\nThank you for attending Dijkstra's Daredeveil Adventures!\nPlease follow this path to exit the park!\n");
        System.out.println(backEnd.pathToGate());
        System.out.println("\nSee you next time!");
    }
}
