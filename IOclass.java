//Based on Curtin University lecture slides and Curtin University IO prac
//Accessed on 30/05/2018

import java.util.*;
import java.io.*;
import java.text.*;

/***********************************************
 * Purpose: This class is the IO class. It is responsible for all functions in the program which are required to read in from a file
 * Author: Aaron Gangemi
 * Date modified: 23/05/2018
 **********************************************/

public class IOclass
{
/**********************************************
 * Purpose: The purpose of this submodule is to get the number of lines from the file
 * Import: String fileName
 * Export: numberOfLines
 **********************************************/
/*Parts of this submodule comprise externally obtained code */
//Obtained from IO help sheet
//Accessed on 25/05/2018
//https://lms.curtin.edu.au/bbcswebdav/pid-5483425-dt-content-rid-29083130_1/courses/2018_1_COMP1001_V1_L1_A1_INT_637069/2018_1_COMP1001_V1_L1_A1_INT_637069_ImportedContent_20180219103146/OOPD_File_IO_HelpSheet%286%29.pdf
  public static int getNoOfLines(String fileName)
  {
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;
    int numberOfLines = 0;
    try
    {
      fileStrm = new FileInputStream(fileName);      //Opens the file
      rdr = new InputStreamReader(fileStrm);          //create a new reader which reads the file stream
      bufRdr = new BufferedReader(rdr);          //used to read one line at a time
      String line = bufRdr.readLine();            //read the first line
      while (line != null)                        //repeat loop until there are no more lines in the file
      {
        numberOfLines ++;            //increment number of lines count by 1
      }
      fileStrm.close();               //close file
    }
    catch(IOException e)              //Catch IO exceptions
    {
      if (fileStrm != null)                //clean up file stream
      {
        try
        {
          fileStrm.close();          //close file stream
        }
        catch(IOException e2)
        {
          System.out.println("Error in file processing");
        }
      }
    }
    return numberOfLines;            //export total number of lines
  }
//End of code obtained from Curtin University
 

/****************************************
 * Purpose: the purpose of the submodule below is to read in the storage name and the capacity 
 * Import: String fileName
 * Export: storage (Object)
 * **************************************/

  public static StorageClass readFile(String fileName)
  {
    int fridgeCapacity = 0;              //set storage capacity's to 0
    int freezerCapacity = 0;                       
    int pantryCapacity = 0;                        
    String[] storageNameArray = new String[3];  //create new array to hold the name of each storage location
    int[] storageCapacityArray = new int[3];    //create new array to hold the storage capcity of each location
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;
    StorageClass storage = null;
    try
    { 
      fileStrm = new FileInputStream(fileName);      //open the filestream
      rdr = new InputStreamReader(fileStrm);      //create reader to read the filestream
      bufRdr = new BufferedReader(rdr);
      for(int i = 0; i < 3; i++)
      { 
        String line = bufRdr.readLine();       //read the first line
        storageNameArray[i] =  processStorage(line);
        storageCapacityArray[i] = processCapacity(line);    
        if(storageNameArray[i].equals("Freezer"))   //check if the element is freezer
        {
          freezerCapacity = storageCapacityArray[i]; 
        }
        else if(storageNameArray[i].equals("Pantry"))      //check if the element is pantry
        {
          pantryCapacity = storageCapacityArray[i];
        } 
        else     //check if element is fridge
        {
          fridgeCapacity = storageCapacityArray[i];       
        } 
      }
     storage = new StorageClass(fridgeCapacity, freezerCapacity, pantryCapacity);  //create new object called 
                                                                                   //storage which passes the capacity for each storage location
     readFoodLines(bufRdr, storage);
     fileStrm.close();
    }
    
    catch(IOException e)   //catch IO exceptions
    { 
      throw new IllegalArgumentException("Error in file processing");  //throw exception if IO exception
    }
     return storage;      //export storage
  }

/************************************
 * Purpose: The purpose of the submodule below is to check the line for it's capacity
 * Import: String line
 * Export: capacity
 * *********************************/
/*Parts of these submodules comprise of externally-obtained code*/
//Accessed on the 25/05/2018
//https://lms.curtin.edu.au/bbcswebdav/pid-5483425-dt-content-rid-29083130_1/courses/2018_1_COMP1001_V1_L1_A1_INT_637069/2018_1_COMP1001_V1_L1_A1_INT_637069_ImportedContent_20180219103146/OOPD_File_IO_HelpSheet%286%29.pdf
  public static String processStorage(String line)    //import line 
  {
    //Obtained from Curtin University IO help sheet
    String[] lineArray = line.split(",");  //Split line at the comma
    String storageName = lineArray[0];  //storageName = first element 
                                        //(e.g. if element 1 is frudge, then storage name is fridge)
    return storageName;  //return storageName
  }
/***************************************
 * Purpose: the purpose of the submodule below is to process the capacities and convert them to integers
 * Import: line
 * Export: capacity
 * ************************************/
  public static int processCapacity(String line)     
  {
    String[] lineArray = line.split(",");            //Split the line at the comma
    int capacity = Integer.parseInt(lineArray[1]);       //convert capacity to an integer
    return capacity;        //export capacity as an integer
  }
//End of externally obtained code from Curtin University IO help sheet


/*Part of this code comprised from Curtin University module 3 - (lecture slide 112)*/
//Accessed on 25/05/2018
/**************************************
 * Purpose: the purpose of write out storage is to ask the user for a filename and writeout the storage contents
 * Import: storageArray
 * Export: NONE
 * ************************************/
   public static void writeOutStorage(StorageClass storageArray)
  {
    FileOutputStream outFileStrm = null;
    PrintWriter pw;
    Scanner sc = new Scanner(System.in);
    try
    {
      System.out.println("Please enter a fileName for storage to be written to with format");   //ask user for file name they would like to write to
      String file = sc.nextLine();
      outFileStrm = new FileOutputStream(file);
      pw = new PrintWriter(outFileStrm);
      for(int i = 0; i < storageArray.getFridgeCount(); i++)
      {
        pw.println(storageArray.getStorageArray()[0][i]);  //write each line of fridge to file
      }
      for(int i = 0; i < storageArray.getFreezerCount(); i++)
      {
        pw.println(storageArray.getStorageArray()[1][i]);  //write each line of freezer to file
      }
      for(int i = 0; i < storageArray.getPantryCount(); i++)
      {
        pw.println(storageArray.getStorageArray()[2][i]);  //write each line of pantry to file
      }
      pw.close();   //close print writer
    }
//End of code obtained from Curtin University
    catch(IOException e)    //catch IO exception
    {
      if (outFileStrm != null)  //clean up filestream
      {
        try
        {
          outFileStrm.close();   //close fileStream
        }
        catch(IOException ex2)
        {
          System.out.println("Error in file processing");
        }
      }
    }
  }
/*******************************************
 * Purpose: the purpose of this submodule is to read the food lines that are after the capacities and sort their properties
 * Import: storage
 * Export: NONE
 * *******************************************/
  public static void readFoodLines(BufferedReader bufRdr, StorageClass storage)
  {
    boolean isValid = true;           //if isvalid = true, then all data types are valid with no input mismatch
    try
    {
      String line = bufRdr.readLine();        //read first line
      while(line != null)                //continue loop until there are no more lines
      {
        String[] foodContentsArray = processFood(line);     //call processFood method with line import and store in foodContentsArray
        if (foodContentsArray[0].equals("Meat"))  //check if element 0 is meat
        {
          String name = foodContentsArray[1];    //get name from index 1
          String cut = foodContentsArray[2];     //get cut from index 2
          double weight = Double.parseDouble(foodContentsArray[3]);   //get weight from index 3
          double storageTemp = Double.parseDouble(foodContentsArray[4]);  //get storageTemp from index 4
          Date usebyDate = new SimpleDateFormat("dd/MM/yyyy").parse(foodContentsArray[5]); //get date from index 5
          String packaging = foodContentsArray[6]; //get packaging from index 6
          if (weight < 0.2 || weight > 5.0)   //check if weight is withing range
          {
            isValid = false;       //if weight is out of range, change boolean condition
          }
          if (isValid != false)     //if isvalid is true then create new meat object with inputted values as parameter     
          {
            MeatClass meat = new MeatClass(name, cut, weight, storageTemp, usebyDate, packaging);
            storage.addFood(meat); //Add above object to addFood method in storageClass
          }
        }
        else if (foodContentsArray[0].equals("Grain"))  //Check if element 0 is grain
        {
          String name = foodContentsArray[1];  //get name from 1st index
          String type = foodContentsArray[2];  //get type from 2nd index
          double volume = Double.parseDouble(foodContentsArray[3]);   //get volume from 3rd index and convert it to a double
          double storageTemp = Double.parseDouble(foodContentsArray[4]);     //get storageTemp from index 4 and and convert it to a double
          Date bestBeforeDate = new SimpleDateFormat("dd/MM/yyyy").parse(foodContentsArray[5]);    //get bestBeforeDate from index 5 and convert it to a Date type
          String packaging = foodContentsArray[6];       //get packaging from index 6
          if (volume < 0.2 || volume > 5.0)     //validate volume is within range
          {
            isValid = false;                 
          }
          if (isValid != false)     //check if isvalid is true and all conditions are met
          {
            GrainClass grain = new GrainClass(name, type, volume, storageTemp, bestBeforeDate, packaging);    //create grain object with above data as parameters
            storage.addFood(grain);    //pass to addFood
          }
        }
        else if (foodContentsArray[0].equals("Fruit"))  //check if element 0 is Fruit
        {
          String name = foodContentsArray[1];        //get the name from element 1
          String type = foodContentsArray[2];        //get type from element 2
          int numberOfPieces = Integer.parseInt(foodContentsArray[3]);    //get the numberOfPieces from element 3 and convert it to an integer
          double storageTemp = Double.parseDouble(foodContentsArray[4]);      //get the storage Temp from element 4 and convert it to a double
          Date usebyDate = new SimpleDateFormat("dd/MM/yyyy").parse(foodContentsArray[5]);  //get the usebyDate from element 5 and convert it to a Date
          String packaging = foodContentsArray[6];   //get packaging from element 6
          if (numberOfPieces < 1 || numberOfPieces > 20)  //validates that number of pieces is withing range
          {
            isValid = false;
          }
          if(isValid != false)      //if isValid is true then create a new object and store parameters
          {
            FruitClass fruit = new FruitClass(name, type, numberOfPieces, 
                                              storageTemp, usebyDate, packaging);   //create new object called fruit
                                                                                   // of type fruit and pass above data as parameters
            storage.addFood(fruit);    //pass fruit to addFood function
          }
        }
        else if(foodContentsArray[0].equals("Vegetables")) //check if element 0 is vegetables
        {
          String name = foodContentsArray[1];     //get name from the first element
          double weight = Double.parseDouble(foodContentsArray[2]);  //get weight from the second element
          double storageTemp = Double.parseDouble(foodContentsArray[3]);     //get storageTemp from element 3
          Date bestBeforeDate = new SimpleDateFormat("dd/MM/yyyy").parse(foodContentsArray[4]);  //get bestbeforeDate from element 4
          String packaging = foodContentsArray[5];  //get packaging from element 5
          if (weight < 0.2 || weight > 5.0)    //validate that weight is withing range
          {
            isValid = false;
          }
          if(isValid != false)
          {
             VegetablesClass vegetables = new VegetablesClass(name, weight, storageTemp, bestBeforeDate, packaging);  //pass above data as parameters into new vegetables object
             storage.addFood(vegetables);  //pass vegetables object to addFood function
          }
        }
        line = bufRdr.readLine();
      }
      
    }    
    catch(IOException e)       //Catch any IO exceptions
    {
      throw new IllegalArgumentException("error in file processing");   //throw exception if their is an IO exception
    }
    catch(ParseException e)
    {
      throw new IllegalArgumentException("invalid date");
    }
  }
 /* Parts of this submodule obtained from Curtin University IO help sheet*/  
/****************************************
 * Purpose: is to process the food lines and return the line in a String Array
 * Import: line
 * Export: lineArray
 * *************************************/
  public static String[] processFood(String line)    
  {
    String[] lineArray = line.split(",");    //split line at comma
    return lineArray;  //export lineArray
  }
}
 //End of code obtained from Curtin University IO help sheet
