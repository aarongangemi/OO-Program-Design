//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018
import java.util.*;
import java.text.SimpleDateFormat;
/************************************
 * Purpose: This is the class for meat which inherits from FoodClass
 * Author: Aaron Gangemi
 * Date Modified: 30/05/2018
 * ***********************************/
public class MeatClass extends FoodClass          //meatClass inherits from food Super class
{                                                 //Classfields assigned to variables
  private String cut;                               
  private double weight;
  private Date usebyDate;

  public MeatClass()              //Default constructor
  {
    cut = "abc";             //Assign classfields to default values
    weight = 2.0;
    usebyDate = new Date();
  }

  public MeatClass(String inName, String inCut, double inWeight, double inStorageTemp, Date inUsebyDate, String inPackaging) //Alternate constructor imports all
  {
    super(inName, inStorageTemp, inPackaging); //Send all values to alternate super constructor
    setCut(inCut);                     //Call setters here
    setWeight(inWeight);
    setUsebyDate(inUsebyDate);
  }

  public MeatClass(MeatClass inMeat)     //Copy constructor
  {
    super (inMeat);                      //Copy all values using the getters
    cut = inMeat.getCut();              
    weight = inMeat.getWeight();
    usebyDate = inMeat.getUsebyDate();  
  }

  public MeatClass clone()   //clone 
  {
    return new MeatClass(this); //export new object called meatClass with all imports
  }

  public String getCut()           //Getters (Accessors)
  {
    return cut;                    //export cut
  }

  public double getWeight()
  {
    return weight;                //export weight
  }

  public Date getUsebyDate()
  {
    return usebyDate;        //export usebyDate
  }

  public void setCut(String inCut)              //setters (mutators)
  {
    if (inCut.equals(""))                         //check if cut is an empty string
    {
      throw new IllegalArgumentException("Invalid, no cut was entered");        //throw exception if cut is an empty string
    }
    if (inCut.equals(null))
    {
      throw new IllegalArgumentException("Invalid, no cut entered");
    }
    cut = inCut;
  }

  public void setWeight(double inWeight)
  {
    if (inWeight < 0)                   //Check if weight is negative
    {
      throw new IllegalArgumentException("weight cannot be negative");           //throw exception if weight is negative
    }
    else if (inWeight < 0.2 || inWeight > 5.0)            //check if weight is between range
    {
     throw new IllegalArgumentException("weight must be between 0.2 and 5.0");      //if range is not between 0.2 and 5.0 then throw exception
    }
    weight = inWeight;
  }

  public void setUsebyDate(Date inUsebyDate)
  {
    usebyDate = inUsebyDate;        
  }

  public boolean equals(Object inObject) //equals method
  {
    boolean isEqual = false;
    if (inObject instanceof MeatClass)         //check if MeatClass is an object
    {
      MeatClass meat = (MeatClass) inObject; 
      if (cut.equals(meat.getCut()))            //verify cut
      {
        if (weight == meat.getWeight())        //verify weight
        {
          if (usebyDate == meat.getUsebyDate())  //verify usebyDate
          {
            isEqual = true;
          }
        }
      }
    }
    return isEqual;              //export either true or false (boolean)
  }

  public String toString()            //toString method
  {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    //Obtained from manish_s
    ///https://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java
    //Accessed 29/05/2018
    String removeTime = dateFormatter.toString().substring(0,10);    //remove time from date variable
    //End of code obtained from manish_s      							      
    String meatString = ("Meat," + super.getName() + "," + cut + "," + weight + "," + 
                         super.getStorageTemp() + "," +  dateFormatter.format(usebyDate) 
                         + "," + super.getPackaging());
    return meatString;        //export meatString
  }
 
//Based on code from Alvin Alexandar
//https://alvinalexander.com/java/java-today-get-todays-date-now
//Accessed 29/05/2018

  public boolean calcExpiry(Date today)         //calcExpired checks if date is before current date
  { 
    boolean isExpired = false;
    if (usebyDate.before(today))       //check if usebydate is less than today
    {
      isExpired = true;               //if true, date is expired
    }
    return isExpired;              //return true or false
  } 
}

  
