// --== CS400 File Header Information ==--
// Name: Matthew Donahue
// Email: mtdonahue@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Florian Heimel
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class LoadDataObject {
  public List<LocationObject> attractions;
  private List<LocationObject> bathrooms;
  private List<LocationObject> rides;
  private List<LocationObject> resturants;
  private CS400Graph<LocationObject> parkMap;
  private final String fileName = "Park.txt";
  private File file;

  /**
  * This constructor reads the txt file given, and organizes the locations into separate ArrayLists.
  * It then adds each of the locations to parkMap as a vertex, and then inserts the edges between the vertices into parkMap.
  */
  public LoadDataObject() {
    try {
      bathrooms = new ArrayList<LocationObject>();
      rides = new ArrayList<LocationObject>();
      resturants = new ArrayList<LocationObject>();
      attractions= new ArrayList<LocationObject>();
      String name;
      String type;
      String description;
      file = new File(fileName);
      Scanner parkData = new Scanner(file);
      parkMap = new CS400Graph<LocationObject>();

      while (parkData.hasNext()) {
        String line = parkData.nextLine();
        int nameIndex = line.indexOf(",");
        int typeIndex = line.indexOf(",", nameIndex + 1);
        name = line.substring(0, nameIndex).trim();
        type = line.substring(nameIndex + 1, typeIndex).trim();
        description = line.substring(typeIndex + 1).trim();

        LocationObject attraction = new LocationObject(name, type, description);
        attractions.add(attraction);
      }
      parkData.close();
      
      for (int i = 0; i < attractions.size(); i++) {
        if (i == 0) {
          parkMap.insertVertex(attractions.get(i));
        } else if (i > 0 && i < 9) {
          parkMap.insertVertex(attractions.get(i));
          rides.add(attractions.get(i));
        } else if (i >= 9 && i < 13) {
          parkMap.insertVertex(attractions.get(i));
          resturants.add(attractions.get(i));
        } else {
          parkMap.insertVertex(attractions.get(i));
          bathrooms.add(attractions.get(i));
        }
      }
      
      // entrance edge section
      parkMap.insertEdge(attractions.get(0), attractions.get(11), 20);
      parkMap.insertEdge(attractions.get(0), attractions.get(9), 20);
      parkMap.insertEdge(attractions.get(0), attractions.get(1), 50);
      
      // ZipZapZop edge section
      parkMap.insertEdge(attractions.get(1), attractions.get(0), 50);
      parkMap.insertEdge(attractions.get(1), attractions.get(11), 40);
      parkMap.insertEdge(attractions.get(1), attractions.get(5), 50);
      parkMap.insertEdge(attractions.get(1), attractions.get(15), 20);
      
      // Interstellar Hill edge section
      parkMap.insertEdge(attractions.get(2), attractions.get(9), 20);
      parkMap.insertEdge(attractions.get(2), attractions.get(11), 40);
      parkMap.insertEdge(attractions.get(2), attractions.get(7), 40);
      parkMap.insertEdge(attractions.get(2), attractions.get(13), 30);
      
      // 5 Time Pointy edge section
      parkMap.insertEdge(attractions.get(3), attractions.get(15), 30);
      parkMap.insertEdge(attractions.get(3), attractions.get(5), 40);
      parkMap.insertEdge(attractions.get(3), attractions.get(12), 20);
      
      // Maggot Destruction edge section
      parkMap.insertEdge(attractions.get(4), attractions.get(10), 40);
      parkMap.insertEdge(attractions.get(4), attractions.get(7), 30);
      parkMap.insertEdge(attractions.get(4), attractions.get(13), 30);
      
      // The Wheel edge section
      parkMap.insertEdge(attractions.get(5), attractions.get(1), 50);
      parkMap.insertEdge(attractions.get(5), attractions.get(15), 30);
      parkMap.insertEdge(attractions.get(5), attractions.get(3), 40);
      parkMap.insertEdge(attractions.get(5), attractions.get(12), 80);
      parkMap.insertEdge(attractions.get(5), attractions.get(8), 40);
      parkMap.insertEdge(attractions.get(5), attractions.get(14), 20);
      parkMap.insertEdge(attractions.get(5), attractions.get(11), 30);
      
      // Speeds Hopper edge section
      parkMap.insertEdge(attractions.get(6), attractions.get(12), 20);
      parkMap.insertEdge(attractions.get(6), attractions.get(8), 30);
      parkMap.insertEdge(attractions.get(6), attractions.get(10), 30);
      
      // Perfect Poodles edge section
      parkMap.insertEdge(attractions.get(7), attractions.get(2), 40);
      parkMap.insertEdge(attractions.get(7), attractions.get(11), 30);
      parkMap.insertEdge(attractions.get(7), attractions.get(14), 20);
      parkMap.insertEdge(attractions.get(7), attractions.get(8), 40);
      parkMap.insertEdge(attractions.get(7), attractions.get(10), 70);
      parkMap.insertEdge(attractions.get(7), attractions.get(4), 30);
      parkMap.insertEdge(attractions.get(7), attractions.get(13), 40);
      
      // The 6ft Under edge section
      parkMap.insertEdge(attractions.get(8), attractions.get(5), 40);
      parkMap.insertEdge(attractions.get(8), attractions.get(6), 30);
      parkMap.insertEdge(attractions.get(8), attractions.get(7), 40);
      
      // Underneath Cold Ones edge section
      parkMap.insertEdge(attractions.get(9), attractions.get(0), 20);
      parkMap.insertEdge(attractions.get(9), attractions.get(11), 30);
      parkMap.insertEdge(attractions.get(9), attractions.get(2), 20);
      
      // Jasmine Vignette edge section
      parkMap.insertEdge(attractions.get(10), attractions.get(6), 30);
      parkMap.insertEdge(attractions.get(10), attractions.get(12), 100);
      parkMap.insertEdge(attractions.get(10), attractions.get(4), 40);
      parkMap.insertEdge(attractions.get(10), attractions.get(7), 70);
      
      // The Greasy Fool edge section
      parkMap.insertEdge(attractions.get(11), attractions.get(0), 20);
      parkMap.insertEdge(attractions.get(11), attractions.get(1), 40);
      parkMap.insertEdge(attractions.get(11), attractions.get(5), 30);
      parkMap.insertEdge(attractions.get(11), attractions.get(14), 20);
      parkMap.insertEdge(attractions.get(11), attractions.get(7), 30);
      parkMap.insertEdge(attractions.get(11), attractions.get(2), 40);
      parkMap.insertEdge(attractions.get(11), attractions.get(9), 30);
      
      // Cupcakes of Grand Dessert edge section
      parkMap.insertEdge(attractions.get(12), attractions.get(3), 20);
      parkMap.insertEdge(attractions.get(12), attractions.get(5), 80);
      parkMap.insertEdge(attractions.get(12), attractions.get(6), 20);
      parkMap.insertEdge(attractions.get(12), attractions.get(10), 100);
      
      //The Deuce edge section
      parkMap.insertEdge(attractions.get(13), attractions.get(2), 30);
      parkMap.insertEdge(attractions.get(13), attractions.get(7), 40);
      parkMap.insertEdge(attractions.get(13), attractions.get(4), 30);
      
      // Central Bathrooms edge section
      parkMap.insertEdge(attractions.get(14), attractions.get(5), 20);
      parkMap.insertEdge(attractions.get(14), attractions.get(8), 20);
      parkMap.insertEdge(attractions.get(14), attractions.get(7), 20);
      parkMap.insertEdge(attractions.get(14), attractions.get(11), 20);
      
      // The Trough edge section
      parkMap.insertEdge(attractions.get(15), attractions.get(1), 20);
      parkMap.insertEdge(attractions.get(15), attractions.get(3), 30);
      parkMap.insertEdge(attractions.get(15), attractions.get(5), 30);
      
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }

  /**
  * This is a getter method that returns parkMap object
  */
  public CS400Graph<LocationObject> mapGraph() {
    return parkMap;
  }

  /**
  * This is a getter method that returns the bathrooms list.
  */
  public List<LocationObject> getBathrooms() {
    return bathrooms;
  }

  /**
  * This is a getter method that returns the rides list.
  */
  public List<LocationObject> getRides() {
    return rides;
  }

  /**
  * This is a getter method that returns the resturants list.
  */
  public List<LocationObject> getRestuarnts() {
    return resturants;
  }
  
  /**
  * This is a getter method that returns the entrance vertex.
  */
  public LocationObject getGate() {
    return attractions.get(0);
  }
}
