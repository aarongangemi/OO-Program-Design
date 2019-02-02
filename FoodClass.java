//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018

import java.util.*;
/**************************************
 * Purpose: This is the foodClass (parent class) which the other foods inherit from
 * Date modified: 30/05/2018
 * Author: Aaron Gangemi
 * ***********************************/

public abstract class FoodClass implements IFood         //abstract FoodClass
{
  private String name;                  //classfields
  private double storageTemp; 
  private String packaging;

  public FoodClass()              //default constructor
  {
    String name = "abc";              //set default values for classfields
    double storageTemp = 0.0;
    String packaging = "abc";
  }

  public FoodClass(String inName, double inStorageTemp, String inPackaging)  //alternate constructor
  {
    setName(inName);                //call setters
    setStorageTemp(inStorageTemp);
    setPackaging(inPackaging);
  }

  public FoodClass(FoodClass inFood)      //copy constructor
  {
    name = inFood.getName();             //make copy of variables
    storageTemp = inFood.getStorageTemp();
    packaging = inFood.getPackaging();
  }

  public String getName()        //Accessors
  {    
    return name;          //export name
  }

  public double getStorageTemp()
  {
    return storageTemp;        //export storageTemp
  }

  public String getPackaging()
  {
    return packaging;      //export packaging
  }

  public void setName(String inName)       //setters (mutators)
  {
    if (inName.equals(""))            //check name is not an empty string
    {
      throw new IllegalArgumentException("There is no name entered");       //throw exception if name is an empty string
    }
    if (inName.equals(null))      //check is name equals null
    {
      throw new IllegalArgumentException("There is no name entered"); //if name is null then throw exception
    }
    name = inName;       //name equals of name
  }

  public void setStorageTemp(double inStorageTemp)
  {
    storageTemp = inStorageTemp;        //storageTemp = copy of storageTemp. 
  }

  public void setPackaging(String inPackaging)
  {
    if (inPackaging.equals(""))      //check if packaging is empty string
    {
      throw new IllegalArgumentException("invalid, no packaging entered");   //throw exception if packaging is an empty string
    }
    if (inPackaging.equals(null))     //check if packaging is null
    {
      throw new IllegalArgumentException("invalid, no packaging entered"); //if packaging is null then throw excepton
    }
    packaging = inPackaging;    //set packaging 
  }

  public boolean equals(Object inObject, FoodClass inFood)       //equals method
  {
    boolean isEqual = false;
    FoodClass food = (FoodClass) inObject;
    if(inObject instanceof FoodClass)        //check if inObject is an instance of FoodClass
    {
      food = (FoodClass) inFood;
      if (name.equals(food.getName()))    //check name equals accessor for name
      {
        if(storageTemp == food.getStorageTemp()) //check if storage temp equals accessor for storageTemp
        {
          if(packaging.equals(food.getPackaging())) //check if packaging equals its accessor
          {
            isEqual = true;
          }
        } 
      }
    }
    return isEqual;     //export either true or false
  }

  public String toString()        //toString method
  { 
    String foodString = (name + "," + storageTemp + "," + packaging);
    return foodString;     //export foodString
  }
  
//Obtained and based off Curtin University Assignment addendum and Lecture slides
//Accessed 29/05/2018


  public abstract boolean calcExpiry(Date today);   //every class should have this method
 
  public int calcSpace(FoodClass Food) //this will be implemented in the future
  {
    return 0;
  }
}
 
