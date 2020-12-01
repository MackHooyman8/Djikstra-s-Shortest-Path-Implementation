// --== CS400 File Header Information ==--
// Name: Matthew Donahue
// Email: mtdonahue@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Florian Heimel
// Notes to Grader: <optional extra notes>

public class LocationObject {
  private String name;
  private String attractionType;
  private String description;

  /**
   * This constructor creates the LocationObject.
   * @param name
   * @param attractionType
   * @param description
   */
  public LocationObject(String name, String attractionType, String description) {
    this.name = name;
    this.attractionType = attractionType;
    this.description = description;
  }

  /**
   * This getter method returns the name of the object
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * This getter method returns the name of the object
   * @return String attractionType
   */
  public String getAttractionType() {
    return attractionType;
  }

  /**
   * This getter method returns the name of the object
   * @return String description
   */
  public String getDescription() {
    return description;
  }
  /**
   * This toString method returns the object as a readable String
   * @return String attractionType
   */
  public String toString() {
    return "Name: " + name + "\nAttraction Type: " + attractionType + "\n" + description;
  }
}
