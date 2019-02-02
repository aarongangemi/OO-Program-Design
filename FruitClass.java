//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018
import java.util.*;
import java.text.SimpleDateFormat;
/*************************************
 * Purpose: this is the class for fruit which inherits from Food
 * Author: Aaron Gangemi
 * Date Modified: 30/05/2018
 * *********************************/

public class FruitClass extends FoodClass      //Fruit class inherits from food super class
{
  private String type;                //classfields assigned to variables
  private int numberOfPieces;
  private Date usebyDate;

  public FruitClass()      //default constructor
  {
    String type = "abc";                     //assign classfields to default values
    double numberOfPieces = 1;
    Date usebyDate = new Date();
  }
 
  public FruitClass(String inName, String inType, int inNumberOfPieces, double inStorageTemp, Date inUsebyDate, String inPackaging)  //Alternate constructor imports all 
  {
    super(inName, inStorageTemp, inPackaging);  //send all values to alternate super constructor
    setType(inType);              //call on setters
    setNumberOfPieces(inNumberOfPieces);
    setUsebyDate(inUsebyDate);
  }

  public FruitClass(FruitClass inFruit)  //Copy Constructor
  {
    super (inFruit);              //copy all values using the gettters
    type = inFruit.getType();
    numberOfPieces = inFruit.getNumberOfPieces();
    usebyDate = inFruit.getUsebyDate();
  }

  public FruitClass clone() //Clone method
  {
    return new FruitClass(this);   //return new fruit object
  }

  public String getType()  //getters (Accessors)
  { 
    return type;  //export type
  }
  public int getNumberOfPieces()  
  {
    return numberOfPieces; //export number of pieces
  }
 
  public Date getUsebyDate()
  {
    return usebyDate;  //export usebyDate
  } 

  public void setType(String inType)  
  {
    if (inType.equals(""))         //check if type is an empty string
    { 
      throw new IllegalArgumentException("Invalid, no type found");  //throw exception if string is empty
    }
    if(inType.equals(null))
    {
      throw new IllegalArgumentException("invalid, no type found");
    }
    type = inType;
  }
  public void setNumberOfPieces(int inNumberOfPieces)
  {
    if(inNumberOfPieces <= 0)
    {
      throw new IllegalArgumentException("Invalid, number of pieces cannot be negative"); //check if number of pieces is negative and potentially throw exception
    }
    else if(inNumberOfPieces > 20 || inNumberOfPieces < 1)  //check if number of pieces is out of range
    {
      throw new IllegalArgumentException("Invalid, number of pieces out of range");  //throw exception if out of range
    }
    numberOfPieces = inNumberOfPieces;
  }
  public void setUsebyDate(Date inUsebyDate)
  {
    usebyDate = inUsebyDate;  //set usebyDate to inUseByDate
  }

  public boolean equals(Object inObject)  //Equals method
  {   
    boolean isEqual = false;
    if (inObject instanceof FruitClass)     //validate the inObject is an instance of fruitClass
    {   
      FruitClass fruit = (FruitClass) inObject;
      if (type.equals(fruit.getType()))    //validate type equals to accessor for type
      { 
        if (numberOfPieces == fruit.getNumberOfPieces())  //validate weight equals the getter for weight
        {  
          if (usebyDate.equals(fruit.getUsebyDate()))  //validate usebyDate equals the getter for usebydate
          { 
            isEqual = true;
          }
        }
      }
    }
    return isEqual;    //return either true or false
  }

  public String toString()
  {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        //Obtained from manish_s
        //https://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java
        //Accessed 29/05/2018
    String removeTime = dateFormatter.toString().substring(0,10);    //remove time from date
       //End of code obtained from manish_s
    String fruitString =  ("Fruit," + super.getName() + "," + type + "," + numberOfPieces + "," + 
                           super.getStorageTemp() + ","  + dateFormatter.format(usebyDate) + super.getPackaging());      //call toString
    return fruitString;     //export string
  }
/**********************************************
 * Purpose: is to check if the usebyDate is before today's date
 * Import: today
 * Export: isExpired
 * *********************************************/
//Based on code from Alvin Alexandar
//https://alvinalexander.com/java/java-today-get-todays-date-now
//Accessed 29/05/2018
  

public boolean calcExpiry(Date today)
  {
    boolean isExpired = false;
    if(usebyDate.after(today))   //check if date is before today
    {
      isExpired = true;   
    }
    return isExpired;   //return true if date is expired
  }
}

