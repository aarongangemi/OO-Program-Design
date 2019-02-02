/*Obtained from Curtin University Assignment Addendum*/
//Accessed on 28/05/2018
/*****************************************
 * Purpose: this is the IFood interface which is implemented in the Food super class
 * Date Modified: 28/05/2018
 * Author: Aaron Gangemi
 * **************************************/
import java.util.*;
public interface IFood
{
   public boolean calcExpiry(Date today );    //calcExpiry method
   // imports todays date and exports true if this food item
   // has reached its expiry date, false otherwise.
   public int calcSpace( FoodClass food );    //calcSpace method
   // imports a Food class object, and exports an integer 
   // specifying the volume in litres of storage required.
}
   /* Additional information:
   Each subclass of Food has a different calculation for space:
   Meat – weight * 0.86 rounded up
   Grain – volume * 1.0 rounded up
   Fruit – number of pieces * 1.95 rounded up
   Vegetables – weight * 1.025 rounded up
   */






