//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018
import java.util.*;
import java.text.SimpleDateFormat;

/***************************************
 * Purpose: This is the vegetables class which inherits from the food super class
 * Author: Aaron Gangemi 
 * Date Modified: 21/05/2018
 * **************************************/

public class VegetablesClass extends FoodClass      //vegetables class inherits from food super class
{
  private double weight;                          //classfields assigned to variables                       
  private Date bestBeforeDate;

  public VegetablesClass()                      //Default constructor
  { 
    double weight = 2.0;                        //Assign classfields to default values
    Date bestBeforeDate = new Date();
  }

  public VegetablesClass(String inName, double inWeight, double inStorageTemp, Date inBestBeforeDate, String inPackaging) //Alternate constructor imports all values
  {
    super(inName, inStorageTemp, inPackaging);    //Send all values to alternate supe constructor
    setWeight(inWeight);                           //Setters called in alternate constructor
    setBestBeforeDate(inBestBeforeDate);           //validation done in setters
  }

  public VegetablesClass(VegetablesClass inVegetables)    //Copy constructor
  {
    weight = inVegetables.getWeight();                 //makes copy of weight and bestbeforedate
    bestBeforeDate = inVegetables.getBestBeforeDate();
  }

  public VegetablesClass clone()         //clone class
  {
    return new VegetablesClass(this);     //makes new object called vegetables class with import weight and best before date
  }

  public double getWeight()         //Accessors - getters    
  {
    return weight;       //export weight
  }

  public Date getBestBeforeDate()
  {
    return bestBeforeDate;      //export bestBefore Date
  }

  public void setWeight(double inWeight)          //mutators - setters
  { 
    if (weight < 0)             //validate that weight is not negative
    {
    throw new IllegalArgumentException("Invalid, weight cannot be negative");    //throw exception if weight is negative
    }
    else if (inWeight < 0.2 || inWeight > 5.0)       //check if weight is out of range
    {
      throw new IllegalArgumentException("Invalid, weight is out of range");     //throw exception if weight is out of range
    }
     weight = inWeight;
  }

  public void setBestBeforeDate(Date inBestBeforeDate)
  {
    bestBeforeDate = inBestBeforeDate; 
  }

  public boolean equals(Object inObject)       //equals methods
  {
    boolean isEqual = false;
    if (inObject instanceof VegetablesClass)   //check if inObject is an instance of vegetables class
    {
      VegetablesClass vegetables = (VegetablesClass) inObject;
      if (weight == vegetables.getWeight())      //check if weight equals the accessor for weight
      {
        if (bestBeforeDate  == vegetables.getBestBeforeDate())  //check if bestBeforeDate equals the accessor for bestBeforeDate
        {
          isEqual = true;
        }
      }
    }
    return isEqual;    //returns either true or false depending on whether all conditions are true
  }

  public String toString()  //toString method
  {
     SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
      //Obtained from manish_s
      //https://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java
      //Accessed 29/05/2018
     String removeTime = dateFormatter.toString().substring(0,10);   //remove time from date
     //End of code obtained from manish_s
     String vegetablesString = ("Vegetables ," + super.getName()+  ","  + weight + "," + 
                                super.getStorageTemp() + dateFormatter.format(bestBeforeDate) + super.getPackaging());      //toString
     return vegetablesString;              //export vegetables string 
  }
/***************************************
 * Purpose: the purpose of this submodule is to check if the best before date is less than todays date
 * Import: today
 * Export: isExpired
 * ***********************************/
//Based on code from Alvin Alexandar
//https://alvinalexander.com/java/java-today-get-todays-date-now
//Accessed 29/05/2018
  public boolean calcExpiry(Date today)    
  {      
    boolean isExpired = false;                 
    if (bestBeforeDate.after(today))    //check if bestbefore date is expired
    {
      isExpired = true;
    }
    return false; //export true if bestbefore date is expired, else export false
  }
}

