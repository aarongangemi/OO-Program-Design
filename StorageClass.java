//based on code from Curtin University lecture slides (Module 4)(Slides 26-173)
//Accessed 30/05/2018
/***************************************
 * Purpose: This is the storage class
 * Author: Aaron Gangemi
 * Date Modified: 30/05/2018
 * **********************************/
import java.util.*;
import java.text.SimpleDateFormat;
public class StorageClass 
{
  private FoodClass[][] storageArray;        //Classfields
  private int fridgeCount;
  private int freezerCount;
  private int pantryCount;

  public StorageClass()    //default constructor
  {
    storageArray = new FoodClass[0][0];     //creates a storageArray called foodClass
    fridgeCount = 0;    //set item count to 0
    freezerCount = 0;
    pantryCount = 0;
  }
  public StorageClass(int fridgeCapacity, int freezerCapacity, int pantryCapacity)  //alternate constructor
  {
    storageArray = new FoodClass[3][];      //set storageArray to have 3 rows, 1 for fridge, pantry and freezer and an unknown amount of columns
    storageArray[0] = new FoodClass[fridgeCapacity];
    storageArray[1] = new FoodClass[freezerCapacity];  
    storageArray[2] = new FoodClass[pantryCapacity];           //set third row to pantry of size pantry capacity
    fridgeCount = 0;                             //set all counts to 0
    freezerCount = 0;
    pantryCount = 0;
  }
  public StorageClass(StorageClass inStorageClass)      //copy constructor
  {
    storageArray = inStorageClass.getStorageArray();    //makes a copy of storageArray
    fridgeCount = inStorageClass.getFridgeCount();      //makes a copy of fridgeCount
    freezerCount = inStorageClass.getFreezerCount();     //makes a copy of freezerCount
    pantryCount = inStorageClass.getPantryCount();      //makes a copy of pantryCount
  }

  public StorageClass clone()         //clone class
  {
    return new StorageClass(this);       //exports a new object of storage class with all of the above parameters
  }
 
  public FoodClass[][] getStorageArray()   //getters (accessors)
  {
    return storageArray;     //export storageArray
  }

  public int getFridgeCount()
  {
    return fridgeCount;  //export fridgeCount
  }

  public int getFreezerCount()
  {
    return freezerCount;    //export freezerCount
  }

  public int getPantryCount()
  {
    return pantryCount;  //export pantryCount
  }

  public void setStorageArray(FoodClass[][] inStorageArray)  //setters (mutators)
  {
    storageArray = inStorageArray;   
  }

  public void setFridgeCount(int inFridgeCount)   //set fridgeCount
  {
    if (inFridgeCount < 0) //validate that the fridgeCount (number of items) is not negative
    {
      throw new IllegalArgumentException("Invalid, fridge count cannot be negative"); //throws exception if fridge count is negative
    }
    fridgeCount = inFridgeCount;
  }

  public void setFreezerCount(int inFreezerCount)
  {
    if (inFreezerCount < 0)      //validate that the freezerCount (number of items) is not negative
    {
      throw new IllegalArgumentException("Invalid, freezer count cannot be negative");   //throw exception if freezerCount is negative
    }
    freezerCount = inFreezerCount;
  }

  public void setPantryCount(int inPantryCount)
  {
    if(inPantryCount < 0)         //validate that pantry count is not negative
    {
      throw new IllegalArgumentException("pantry count cannot be negative");    //throw exception if pantryCount is negative
    }
    pantryCount = inPantryCount;
  }

  public boolean equals(Object inObject)     //equals method
  {
    boolean isEqual = true;
    
    if (inObject instanceof StorageClass) //validate that inObject is an instance of storageClass
    {
      StorageClass storage  = (StorageClass) inObject;
      if(storageArray[0].length != storage.getStorageArray()[0].length || 
         storageArray[1].length != storage.getStorageArray()[1].length ||
         storageArray[2].length != storage.getStorageArray()[2].length)
      {    
         isEqual = false;
      }
      if(isEqual)
      {
        if (fridgeCount != storage.getFridgeCount() ||         //check counts equal accessor
            freezerCount != storage.getFreezerCount() ||
            pantryCount != storage.getPantryCount()) 
        { 
              isEqual = false;      //if all conditions are met, then set is equal to true
        }
      }
      if (isEqual)
      {
        for(int i = 0; i < fridgeCount; i++)       //loop through fridge
        { 
          if(!storageArray[0][i].equals(storage.getStorageArray()[0][i]))  //check fridge elements by looping 
          {
            isEqual = false;  //check if true
          }
        }
      }
      if (isEqual)    
      {
        for(int i = 0; i < freezerCount; i++)  //loop through freezer    
        {
          if(!storageArray[1][i].equals(storage.getStorageArray()[1][i]))    //check freezer elements by looping
          {
            isEqual = false;
          }
        }
      }
      if (isEqual)
      {
        for(int i = 0; i < pantryCount; i++)    //loop through pantry
        {
          if(!storageArray[2][i].equals(storage.getStorageArray()[2][i]))   //check pantry elements
          {
            isEqual = false;
          }
        }
      }
    }
    else
    {
      isEqual = false;
    }
    return isEqual;   //export isEqual
  }

  public String toString()   //toString method
  {
    String storageString = ("Storage has: " + fridgeCount + "items in fridge" + "," + freezerCount + "items in freezer" + "," + pantryCount + "items in pantry");
    return storageString; //export storageString
  } 
 
/*********************************************
 * Purpose: check if any items any of the storage locations have expired based on their best before date and output it to the console
 * Import: none
 * Export: none
***********************************************/
  public void findExpired()   
  {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    String removeTime = dateFormatter.toString().substring(0,10);     
    Date today = new Date();    
    for(int i = 0; i < fridgeCount; i++)     //loop through the fridge until capacity is reached
    {
      if (storageArray[0][i].calcExpiry(today))   //check if item has expired
      {
        System.out.println("Location: Fridge"); //Output location
        System.out.println(storageArray[0][i]);  //output item that has expired
      }
    }
    for(int i = 0; i < freezerCount; i++)  //loop through the freezer until capacity is reached
    {
      if (storageArray[1][i].calcExpiry(today))  //check if item has expired
      {
        System.out.println("Location: Freezer");  //output location
        System.out.println(storageArray[1][i]);  //output item that has expired
      }
    }
    for(int i = 0; i < pantryCount; i++)  //loop through the pantry until capacity is reached
    {
      if (storageArray[2][i].calcExpiry(today))  //check if item has expired
      {
        System.out.println("Location: Pantry ");  //output location
        System.out.println(storageArray[2][i]);  //output item that has expired
      }
    } 
  }
/*************************************************
 * Purpose: is to ask the user for an itemNumber and location in storage and remove that item
 * Import: location, itemNumber
 * Export: NONE
 * ***********************************************/
  public void removeItem(int location, int itemNumber)
  {
    if ((location == 1) && (itemNumber <= fridgeCount) && (itemNumber > 0) && (fridgeCount > 0))  //check location is fridge and item number is valid and fridgeCount is valid
    { 
      for(int i = itemNumber; i < (fridgeCount - 1); i++)  //loop through from item number typed in by the user to the number of elements -1. increment the count
      {
        storageArray[0][i] = storageArray[0][i+1];  //bring all the elements back 1
      }
      fridgeCount = fridgeCount - 1;     //decrement the fridgeCount
    }
    else if((location == 2) && (itemNumber <= freezerCount) && (itemNumber > 0) && (freezerCount > 0)) //check location is freezer and itemNumber and FridgeCount is valid
    {
      for(int i = itemNumber; i < (freezerCount - 1); i++) //loop through from item number typed in by the user to the number of elements -1. Increment the count
      {
        storageArray[1][i] = storageArray[1][i+1];  //bring all the elements back 1
      }
      freezerCount = freezerCount - 1;  //decrement the freezerCount
    }
    else if ((location == 3) && (itemNumber <= pantryCount) && (itemNumber > 0) && (pantryCount > 0))  //check loction is pantry and itemNumber and pantryCount is valid
    {
      for (int i = itemNumber; i < (freezerCount - 1); i++)  //loop through from item number typed in by the user to the number of elements -1. Increment the count.
      {
        storageArray[2][i] = storageArray[2][i+1];  //bring all the elements after the specified item number back by 1
      }
      pantryCount = pantryCount - 1;  //decrement the pantryCount
    }
    
  }
/****************************************************
 * Purpose: the purpose is to check the storage temperature and add meat into the designated storage (next 4 methods USES METHOD OVERLOADING)
 * Import: meat
 * Export: NONE
 * ***************************************************/    
  public void addFood(MeatClass meat)
  {
    if (meat.getStorageTemp() > -27.0 && meat.getStorageTemp() < -5.0)  //check if meat storagetemp is within range
    {
      if(freezerCount < storageArray[1].length)  //check if freezer has room to store the item
      {
        storageArray[1][freezerCount] = meat;  //store meat in next free location
        freezerCount++;
      }
    }
    else if(meat.getStorageTemp() > -2.0 && meat.getStorageTemp() < 6.0)  //check if meat storagetemp is within range
    {
      if(fridgeCount < storageArray[0].length)  //check if the fridge has space to store the item
      {
        storageArray[0][fridgeCount] = meat;  //store meat in next available entry in fridge
        fridgeCount++;  //increment fridgeCount
      }
    }
    else if(meat.getStorageTemp() > 8.0 && meat.getStorageTemp()< 25.0)  //check if meat storageTemp is withing range for pantry
    {
      if(pantryCount < storageArray[2].length)  //check if pantry has space to store meat
      {
        storageArray[2][pantryCount] = meat;
        pantryCount++;  //store meat in next available slot for pantry
      }
    }
    else   
    {
      System.out.println("cannot add item to any storage location, storage temp is out of range or storage location is full");
    } 
  }
 /**************************************************
 * Purpose: the purpose is to check the storage temperature and add grain into the designated storage 
 * IMPORT: grain
 * EXPORT: NONE
 * **************************************************/

  public void addFood(GrainClass grain)
  {
    if (grain.getStorageTemp() > -27.0 && grain.getStorageTemp() < -5.0)  //check the grain storage temp is withing range
    {
      if(freezerCount < storageArray[1].length)  //check their is space in freezer to store grain item
      {
        storageArray[1][freezerCount] = grain;  //store grain item in next available freezer slot
        freezerCount++;  //increment freezer count
      }
    }
    if(grain.getStorageTemp() > -2.0 && grain.getStorageTemp() < 6.0) //check the grain storage temp is withing range
    {
      if(fridgeCount < storageArray[0].length) //check their is space in fridge to store grain item
      {
        storageArray[0][fridgeCount] = grain;  //store grain item in next available fridge slot
        fridgeCount++;  //increment fridge count
      }
    }
    if(grain.getStorageTemp() > 8.0 && grain.getStorageTemp() < 25.0) //check the grain storage temp is withing range
    {
      if(pantryCount < storageArray[2].length) //check their is space in freezer to store grain item
      {
        storageArray[2][pantryCount] = grain; //store grain item in next available pantry slot
        pantryCount++;  //increment pantry count
      }
    }
    else 
    {
      System.out.println("cannot add item to any storage location, storage temp is out of range");  //display error message
    }  
  }

 /**************************************************
 * * Purpose: the purpose is to check the storage temperature and add fruit into the designated storage 
 * * IMPORT: fruit
 * * EXPORT: NONE
 * **************************************************/

  public void addFood(FruitClass fruit)
  {
    if (fruit.getStorageTemp() > -27.0 && fruit.getStorageTemp() < -5.0)  //check the fruit storage temp is within range
    {
      if(freezerCount < storageArray[1].length)  //check their is space in freezer to store fruit item
      {
        storageArray[1][freezerCount] = fruit;  //store fruit item in next available pantry slot
        freezerCount++;  //increment freezerCount
      }
    }
    if(fruit.getStorageTemp() > -2.0 && fruit.getStorageTemp() < 6.0) //check the grain storage temp is within range
    {
      if(fridgeCount < storageArray[0].length)  //check their is space in fridge to store fruit item
      {
        storageArray[0][fridgeCount] = fruit;  //store fruit item in next available pantry slot
        fridgeCount++;  //increment fridgeCount
      }
    }
    if(fruit.getStorageTemp() > 8.0 && fruit.getStorageTemp() < 25.0) //check the grain storage temp is within range
    {
      if(pantryCount < storageArray[2].length) //check their is space in pantry to store fruit item
      {
        storageArray[2][pantryCount] = fruit; //store fruit item in next available pantry slot
        pantryCount++;  //increment pantryCount
      }
    }
    else 
    {
      System.out.println("cannot add item to any storage location, storage temp is out of range");
    }
  }

 /**************************************************
 * * Purpose: the purpose is to check the storage temperature and add vegetables into the designated storage 
 * * IMPORT: vegetables
 * * EXPORT: NONE
 * * **************************************************/

  public void addFood(VegetablesClass vegetables)
  {
    if (vegetables.getStorageTemp() > -27.0 && vegetables.getStorageTemp() < -5.0) //check the vegetable storage temp is within range
    {
      if(freezerCount < storageArray[1].length)  //check their is space in freezer to store vegetable item
      {
        storageArray[1][freezerCount] = vegetables;  //store vegetables item in next available freezer slot
        freezerCount++;  //increment freezerCount
      }
    }
    if(vegetables.getStorageTemp() > -2.0 && vegetables.getStorageTemp() < 6.0)  //check the vegetable storage temp is within range
    {
      if(fridgeCount < storageArray[0].length)  //check their is space in fridge to store vegetable item
      {
        storageArray[0][fridgeCount] = vegetables;  //store vegetables item in next available fridge slot
        fridgeCount++; //increment fridgeCount
      }
    }
    if(vegetables.getStorageTemp() > 8.0 && vegetables.getStorageTemp() < 25.0) //check the vegetable storage temp is within range
    {
      if(pantryCount < storageArray[2].length)  //check their is space in pantry to store vegetable item
      {
        storageArray[2][pantryCount] = vegetables;  //store vegetables item in next available pantry slot
        pantryCount++; //increment pantryCount
      }
    }
    else
    {
      System.out.println("cannot add item to any storage location, storage temp is out of range");
    }
  }
}

  


  
