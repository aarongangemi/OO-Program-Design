//Based on code from Curtin University lecture slides 
//Accessed 20/05/2018

/***************************************
 * Purpose:
 * Author: Aaron Gangemi
 * Date modified: 30/05/2018
***************************************/
import java.util.*;
import java.text.*;
import java.io.*;
public class Main
{
  public static void main(String args[])
  {
    boolean isValid = false;
    StorageClass storage = new StorageClass();
    String fileName = null;
    int getInput = menuNumber();  //getInput is the return value of the menu which is displayed to the user when the program is run
    do
    {
      
      switch(getInput)
      {  
        case 1:
          switchFoodMenuOption(storage);
          getInput = menuNumber();        //repeated to loop to menu  
          break;
        case 2:  //if user selects display storage contents
          displayStorageContents(storage);  //call display storage contents
                                           // which outputs contents of storage to user.
          getInput = menuNumber();    
          break;
        case 3:  //if user selects find expired
          storage.findExpired();  //call find expired submodule
          getInput = menuNumber();    
          break;
        case 4:  //if the user selects read in storgae
          fileName = validateFileName();  //gets the fileName from the user
          storage = IOclass.readFile(fileName);  //reads in capacity and food
          getInput = menuNumber();    
          break;
        case 5:  //if user selects write out storage
          IOclass.writeOutStorage(storage);   //write out storage submodule
                                              // is located in the IOClass and write's
                                              // out the contents of storage to the user
          getInput = menuNumber();    
          break;
        case 6:  //if user selects remove food
          removeFood(storage);   //removes selected food from storageArray
          getInput = menuNumber();    
          break;
        case 7:  //if user selects exit
           //Exit program gracefully by having exit as final line of main
           break;
        default:
           System.out.println("please enter a valid menu number and try again");
           getInput = menuNumber();    
           break;
      }
    }while(getInput!= 7);
  
  }
/***************************************
 * Purpose: the menuNumber submodule is the first thing the user will see when they run the program and will ask them to select an option
 * Import: NONE
 * Export: menuNumber
 * *************************************/
  public static int menuNumber()  
  {
    int menuNumber = 0;
    boolean isValid = false;
    Scanner sc = new Scanner(System.in);  
    while (isValid != true)  //boolean condition used for the try/catch so that all input is valid  
    {
      try
      {
        System.out.println("Select a number: ");         //Displays menu
        System.out.println("1. Add food ");
        System.out.println("2. Display Contents ");
        System.out.println("3. Find Expired ");
        System.out.println("4. Read in storage");
        System.out.println("5. Write out storage");
        System.out.println("6. Remove food");
        System.out.println("7. Exit ");
        menuNumber = sc.nextInt();         //users inputs an integer for there option
        isValid = true;     //if all input is valid, change boolean to true 
      }
      catch (InputMismatchException e)
      {
        System.out.println("Invalid, please enter an integer in the menu");
        String flush = sc.nextLine();
      }
    }
    return menuNumber;
  }
/*******************************************
 * Purpose: The switchFoodMenuOption displays the selected food menu
 * Import: storage
 * Export: NONE
************************************************/
  public static void switchFoodMenuOption(StorageClass storage)
  {
    int foodNumber = foodMenu();
    if (storage == null)   //check if storage has been read in yet
    {
      System.out.println("Storage capacity has not been read in yet");    //Output message to user if
                                                                          // storage has not been read in
    }
    else if (storage != null)
    {
      switch(foodNumber)
      {
        case 1:      //if user selects meat
          MeatClass meat = getMeatInput();   //meat object holds input 
                                             //values if user selects meat
                                             
          storage.addFood(meat);  //pass meat object to addFood submodule in storage
        break;
        case 2:       //if user selects grain
          GrainClass grain =  getGrainInput();  //grain object holds input values if user slects grain
          storage.addFood(grain);      //pass grain object to addFood class in storage
        break;
        case 3:
          FruitClass fruit =  getFruitInput();  //fruit object holds input
                                                // values if user selects fruit
                                                
          storage.addFood(fruit);   //pass fruit object to addFood class in storage
        break;
        case 4:
          VegetablesClass vegetables =  getVegetablesInput();  //vegetables object holds input
                                                               // values if user selects vegetables
                                                               
          storage.addFood(vegetables);  //pass vegetables obejct to addFood class in storage
        break;
      }   
    }
  }  
/***********************************************
 * Purpose: The purpose of validateFileName is to get a valid fileName from the user
 * Import: NONE
 * Export: fileName
 * ********************************************/
  public static String validateFileName()
  {
    boolean isValid = false;
    String fileName = "";
    Scanner sc = new Scanner(System.in);
    while (isValid != true)    //boolean condition so user can not enter invalid input
    {
      try
      {
        System.out.println("Please enter a valid filename");  //ask user to enter a filename
        fileName = sc.nextLine();
        isValid = true;
      }
      catch(InputMismatchException e)
      {
        System.out.println("Invalid filename, please try again");   //if user enters invalid fileName
        String flush = sc.nextLine();
      }
    }
    return fileName;   //export valid fileName
  }

/********************************************
 * Purpose: the purpose of foodMenu is to ask the user which food option they'd like to select
 * Import: NONE
 * Export: foodOption
 * ****************************************/

  public static int foodMenu()
  {
    int foodOption = 0;
    boolean isvalid = true;
    Scanner sc = new Scanner(System.in);
    while ((isvalid != false) && ((foodOption <= 0) || (foodOption > 4)))   //boolean condition used so user cannot enter invalid input
    {
      try
      {
        System.out.println("Please select an option number");      //ask user to select a food 
        System.out.println("1. Meat ");
        System.out.println("2. Grain ");
        System.out.println("3. Fruit ");
        System.out.println("4. Vegetables ");
        foodOption = sc.nextInt();
        if ((foodOption < 1) || (foodOption > 4))      //validates that user inputs a number between 1 and 4 (inclusive)
        {
          isvalid = true;      //input is invalid
        }
        else if ((foodOption >= 1) && (foodOption <= 4))
        {
          isvalid = false;  //input is valid
        }
      }
      catch(InputMismatchException e)    //catch exception if user enters invalid data type
      {
        System.out.println("Invalid, please try again");    //output error message
        String flush = sc.nextLine();
       }
    } 
      return foodOption;  //export foodOption
  }
/***************************************************
 * Purpose: is to remove food from the storageArray
 * Import: StorageClass storage
 * Export: NONE
**************************************************/

  public static void removeFood(StorageClass storage)
  {
    int location = 0;
    boolean isValid = false;
    Scanner sc = new Scanner(System.in);
    try
    {
      while(isValid != true)      
      {
        System.out.println("Please select number of storage location");   //Ask user for storage location
        System.out.println("1. Fridge");
        System.out.println("2. Freezer");
        System.out.println("3. Pantry");
        location = sc.nextInt();
        if (location >= 1 && location <= 3)   //checks if location is valid
        {
          isValid = true;
        }
      }
        System.out.println("Please enter a number representing the location in selected storage");  //ask user for location
                                                                                                    // in selected storage represented by integer
        int itemNumber = sc.nextInt();
        storage.removeItem(location, itemNumber);     //call removeItem method in storage
    }
    catch(InputMismatchException e)   //catch exception if user enters incorrect data type
    {
      System.out.println("Invalid number, please try again");
      String flush = sc.nextLine(); //clear invalid input
    }
  }
/******************************************
 * Purpose: to ask the user for meat attributes
 * Import: NONE
 * Export: meat
 * ***************************************/

  public static MeatClass getMeatInput()
  {
    boolean isValid = false;
    Scanner sc = new Scanner(System.in);
    String name = "abc";
    String cut = "abc";
    String packaging = "abc";
    double weight = 2.0;
    double storageTemp = 0.0;
    Date usebyDate = new Date();
    while (isValid != true)      //use isValid condition to determine whether all data types for input are valid
    {
      try
      {
        System.out.println("Please enter the name");
        name = sc.nextLine();     //user enters a name
        System.out.println("Please enter the cut");
        cut = sc.nextLine();  //user enters cut
        System.out.println("Please enter the weight");
        weight = sc.nextDouble();      //user enters weight
        if((weight < 0.2) || (weight > 5.0))      //check if weight is invalid (out of range)
        {
          System.out.println("Invalid, weight is out of range, please try again");
          System.out.println("Please enter the weight"); 
          weight = sc.nextDouble();    //if weight is invalid, ask user to re-enter weight
        }
        System.out.println("Please enter the storage temperature");
        storageTemp = sc.nextDouble(); //users enters storage temp
        usebyDate = getValidDate();
        System.out.println("Please enter the packaging");
        packaging = sc.nextLine();  //user enters packaging
        packaging = sc.nextLine();      //user enters packaging
        isValid = true;  // if all data types are valid then change isvalid to be true
      }
      catch(InputMismatchException e)     //catch any input mismatch exception 
      {
        System.out.println("Invalid, please enter valid input");    //display error message
        String flush = sc.nextLine();
      }
    }
    MeatClass meat = new MeatClass(name, cut, weight, storageTemp, usebyDate, packaging);    //create meat object and store parameters
    return meat;  //export meat object
  }

/******************************************
 * Purpose: to ask the user for grain attributes
 * Import: NONE
 * Export: grain
 ****************************************/
        
  public static GrainClass getGrainInput()      
  {
    boolean isvalid = false;
    String name = "";
    String type = "";
    String packaging = "";
    double volume = 2.0;
    double storageTemp = 0.0;
    Date bestBeforeDate = new Date();
    Scanner sc = new Scanner(System.in);
    while (isvalid != true)     //use isValid condition to determine whether all data types are successful
    {
      try     
      {
        System.out.println("please enter the name");
        name = sc.nextLine();      //user enters name
        System.out.println("Please enter the type ");
        type = sc.nextLine();  //user enters type
        System.out.println("Please enter the volume ");
        volume = sc.nextDouble();  //user enters volume
        while ((volume < 0.2) || (volume > 5.0))   //check volume is within range
        {
          System.out.println("Invalid, volume is outside of range. Please try again");
          System.out.println("Please enter the volume");
          volume = sc.nextDouble();    //if volume is outside of range, user re-enters volume
        }
        System.out.println("Please enter the storage temperature"); 
        storageTemp = sc.nextDouble();   //user enters storageTemp
        bestBeforeDate = getValidDate();
        System.out.println("Please enter packaging");
        packaging = sc.nextLine();  //users enter packaging
        packaging = sc.nextLine();      //user enters packaging
        isvalid = true;     //change isValid to true if all data types for input are valid
      }
      catch(InputMismatchException e)      //catch input mismatch exceptions
      {
        System.out.println("invalid, please enter valid data");      //display error message
        String flush = sc.nextLine(); 
     }
    } 
    
      GrainClass grain = new GrainClass(name, type, volume, storageTemp, bestBeforeDate, packaging);   //create grain object and pass parameters
      return grain;   //export grain object
  }

/******************************************
 * Purpose: to ask the user for fruit attributes
 * Import: NONE
 * Export: fruit
 ****************************************/

  public static FruitClass getFruitInput()
  {
    boolean isvalid = false;
    Scanner sc = new Scanner(System.in);
    String name = "";
    String type = "";
    String packaging = "";
    int noOfPieces = 1;
    double storageTemp = 0.0;
    Date usebyDate = new Date();
    while (isvalid != true)      //use boolean condition to determine whether all data types are valid (used to loop try/catch)
    {
      try
      {
        System.out.println("Please enter the name ");
        name = sc.nextLine();                //ask user for name
        System.out.println("Please enter the type ");
        type = sc.nextLine();      //ask user for type
        System.out.println("Please enter the number of pieces");
        noOfPieces = sc.nextInt();     //ask user for number of pieces
        while ((noOfPieces < 1) || (noOfPieces > 20))         //validate number of pieces
        {
          System.out.println("Number of pieces is out of range, please try again");      
          System.out.println("Please enter the number of pieces");
          noOfPieces = sc.nextInt();           //if number of pieces is out of range, ask user to input number of pieces
        }
        System.out.println("Please enter the storage temperature");
        storageTemp = sc.nextDouble();      //ask user to enter storage temp
        usebyDate = getValidDate();
        System.out.println("Please enter packaging");
        packaging = sc.nextLine();       //ask user for packaging
        packaging = sc.nextLine();      //user enters packaging
        isvalid = true;        //if all data types are valid, then change isvalid to true and exit loop
      }
      catch (InputMismatchException e)
      {
        System.out.println("Invalid, please enter valid data");     //display error message
        String flush = sc.nextLine();
      }
    }
    FruitClass fruit = new FruitClass(name, type, noOfPieces, storageTemp, usebyDate, packaging);     //create new fruit object
    return fruit;        //export fruit object
  }


/******************************************
 * Purpose: to ask the user for vegetables attributes
 * Import: NONE
 * Export: vegetables
 * ***************************************/

  public static VegetablesClass getVegetablesInput()
  {
    boolean isvalid = false;
    Scanner sc = new Scanner(System.in);
    String name = "";
    String packaging = "";
    double weight = 2.0;
    double storageTemp = 0.0;
    Date bestBeforeDate = new Date();
    while (isvalid != true);      //used to loop try catch statement
    {
      try
      {
        System.out.println("Please enter the name");
        name = sc.nextLine();         //user enters name
        System.out.println("Please enter the weight");
        weight = sc.nextDouble();     //user enters weight
        while ((weight < 0.2) || (weight > 5.0))        //validate weight is within range
        {
          System.out.println("weight out of range, please try again");
          System.out.println("Please enter the weight");
          weight = sc.nextDouble();       //if weight is out of range then ask user to re-enter range
        }
        System.out.println("Please enter temperature storage");
        storageTemp = sc.nextDouble();   //ask user for storage Temp
        bestBeforeDate = getValidDate();
        System.out.println("please enter packaging");
        packaging = sc.nextLine();      //user enters packaging
        packaging = sc.nextLine();      //user enters packaging
        isvalid = true;  //exit loop if all data types are valid
      }
      catch(InputMismatchException e)        //catch inputmismatch exception
      {
        System.out.println("Invalid, please enter valid data");    //display error message
        String flush = sc.nextLine();
      }
    }
    VegetablesClass vegetables = new VegetablesClass(name, weight, storageTemp, bestBeforeDate, packaging);    //create new vegetables object and pass parameters
    return vegetables;  //export vegetables object
  }

/*********************************************************
 * Purpose: to output the contents of the array to the user
 * Import: storage
 * Export: NONE
 * ******************************************************/


  public static void displayStorageContents(StorageClass storage)
  {
    try
    {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please select number of storage location");     //ask user for location number
      System.out.println("1. Fridge");       //display menu for location 
      System.out.println("2. Freezer");
      System.out.println("3. Pantry");
      int location = sc.nextInt();     //user enters location
      FoodClass[][] foodArray = storage.getStorageArray();        //accessor for storageArray
      if (location == 1)          //check if location is fridge
      {
        FoodClass[] fridge = foodArray[0];       //fridge equals first row in storage
        for(int i = 0; i < storage.getFridgeCount(); i++)      //loop through fridge
        {
          System.out.println(fridge[i].toString());     //display fridge contents
        }
      }
      else if (location == 2)        //loop through freezer
      {
        FoodClass[] freezer = foodArray[1];        //freezer equals second row in storage
        for(int i = 0; i < storage.getFreezerCount(); i++)       //loop through freezer
        {
          System.out.println(freezer[i].toString());    //display fridge contents
        }
      }
      else if (location == 3)        //check if location is pantry
      {
        FoodClass[] pantry = foodArray[2];
        for(int i = 0; i < storage.getPantryCount(); i++)       //loop through pantry
        {
          System.out.println(pantry[i].toString());     //output pantry contents
        }
      }
    }
    catch(InputMismatchException e)     //catch if user enters invalid data
    {
      System.out.println("Invalid, please enter an integer");     //output error message
    }
  }
/****************************************
 * Purpose: is to ask the user for a date and check if it's valid
 * Import: NONE
 * Export: usebyDate
 * *************************************/
   
  public static Date getValidDate()
  {
    Scanner sc = new Scanner(System.in);
    boolean isValid = false;   
    Date usebyDate = null;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    String dateString = "";
    int day = 0;
    int month = 0;
    int year = 0;   
    while (!isValid)
    {
      try
      {
        System.out.println("please enter the day of the usebyDate as an integer");
        day = sc.nextInt();    //input day
        System.out.println("please enter the month of the usebyDate as an integer");
        month = sc.nextInt();   //input month
        System.out.println("please enter the year of the usebyDate as an integer");
        year = sc.nextInt();    //input year
        dateString = (day + "/" + month + "/" + year);   //create date as a string
        usebyDate = dateFormatter.parse(dateString);   //convert date string to date
        if (usebyDate == null)   //check if date is invalid
        {
          isValid = false;
          System.out.println("Data entered is an invalid date, please try again");  //output if date is invalid
        } 
        else
        {
          isValid = true;
        }
      }
      catch(InputMismatchException e)   //catch invalid input
      {
        System.out.println("Please enter valid integers for the date");
        String flush = sc.nextLine();       //clear invalid input
      }
      catch(ParseException e)  //usebyDate error gets caught
      {
        System.out.println("Please enter a valid usebyDate");      
      }
    }
    return usebyDate;  //export usebyDate
  }
}
   
