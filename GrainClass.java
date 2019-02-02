//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018
import java.util.*;
import java.text.SimpleDateFormat;
/******************************************
 * Purpose: This is the class for grain which inherits from FoodClass
 * Author: Aaron Gangemi
 * Date Modified: 21/05/2018
 * **************************************/
public class GrainClass extends FoodClass    //Grain class inherits from FoodClass
{
  private String type;                    //Classfields
  private double volume;
  private Date bestBeforeDate;

  public GrainClass()         //Default Constructor
  {
    String type = "abc";        //Assign classfields to default values
    double volume = 2.0;
    Date bestBeforeDate = new Date();
  }

  public GrainClass(String inName, String inType, double inVolume, double inStorageTemp, Date inBestBeforeDate, String inPackaging)  //Alternate constructor imports all
  {
    super(inName, inStorageTemp, inPackaging);     //send all values to super alternate constructor
    setType(inType);     //setType imports inType
    setVolume(inVolume); //setVolume imports inVolume
    setBestBeforeDate(inBestBeforeDate);  //setBestBeforeDate imports inBestBeforeDate
  }

  public GrainClass(GrainClass inGrain)  //Copy Constructor
  {
    super(inGrain);  //Super imports inGrain
    type = inGrain.getType();                                  //makes copy of type, volume and bestBeforeDate
    volume = inGrain.getVolume();
    bestBeforeDate = inGrain.getBestBeforeDate();
  }

  public GrainClass clone()       //Clone class
  {
    return new GrainClass(this);  //Clone grainClass
  }

  public String getType()        //Getters(Accessors)
  {
    return type;        //export type
  }

  public double getVolume()
  {
    return volume;    //export volume
  }

  public Date getBestBeforeDate()
  {
    return bestBeforeDate;    //export bestBeforeDate
  }

  public void setType(String inType)       //Setters(Mutators)
  {
    if (inType.equals(""))    //Checks if type is an empty string
    {
      throw new IllegalArgumentException("Invalid, no type found");      //Throws exception if no string is found
    } 
    if (inType.equals(null))
    {
      throw new IllegalArgumentException("Invalid, no type found");
    }
    type = inType;  //if validation is okay then set type to inType
  }

  public void setVolume(double inVolume)    
  {
    if(inVolume < 0)    //Check if volume is negative
    {
      throw new IllegalArgumentException("Invalid, volume is negative");  //throws exception if volume is negative
    }
    else if(inVolume < 0.2 || inVolume > 5.0)  //checks if volume is between range
    {
      throw new IllegalArgumentException("Invalid, volume is out of range");    //if volume is out of range - throw exception
    }
    volume = inVolume;  
  }

  public void setBestBeforeDate(Date inBestBeforeDate)
  {
    bestBeforeDate = inBestBeforeDate; //Set best before date to inBestBeforeDate
  } 
  public boolean equals(Object inObject)  //Equals method
  {
    boolean isEqual = false;      //set isEqual to false
    if (inObject instanceof GrainClass)  //validate inObject is an instance of GrainClass
    {
      GrainClass grain = (GrainClass) inObject;
      if (type.equals(grain.getType()))  //validate type is equal to the accessor for type
      {
        if (volume == grain.getVolume()) //validate volume is equal to the accessor for volume
        {
          if (bestBeforeDate.equals(grain.getBestBeforeDate()))  //validate the bestBeforeDate is equal to the accessor for bestBeforeDate
          {
            isEqual = true;        
          }
        }
      }
    }
    return isEqual;         //export either true or false
  }

  public String toString()     //toString method
  {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");   //format date
        //Obtained from manish_s
        //https://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java
        //Accessed 29/05/2018
    String removeTime = dateFormatter.toString().substring(0,10);        //remove time from date
       //End of code obtained from manish_s
    String grainString =  ("Grain, " + super.getName() + "," + type + "," + volume + "," 
                           + super.getStorageTemp() + "," +  dateFormatter.format(bestBeforeDate) + "," + super.getPackaging());         //toString
                     									
    return grainString;    //export grainString
  }
/******************************************
 * Purpose: the purpose of this submodule is to check if the usebyDate is before today's date
 * Import: today
 * Export: isExpired
 * ****************************************/

//Based on code from Alvin Alexandar
////https://alvinalexander.com/java/java-today-get-todays-date-now
////Accessed 29/05/2018
  public boolean calcExpiry(Date today)  
  {
    boolean isExpired = false;
    if (bestBeforeDate.before(today))  //check if date is before 
    {
      isExpired = true;
    }
    return isExpired;    //export true or false, representing whether the product is expired or not
  }
}



