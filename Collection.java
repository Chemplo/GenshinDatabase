/**
	File Name: Collection
	Names: Griffin, Steven, Rina
	Class: ICS4U
	Date: June 5, 2023
	Description: stores a players collection of unlocked objects
*/

import java.util.*;
public class Collection {
   
   //each index boolean represents if that corresponding item in the database is unlocked
   private ArrayList<Boolean> collection;
   
   //constructor
   public Collection(){
      collection = new ArrayList<Boolean>();
      for (int i=0; i < Database.size(); i++){
         collection.add(false);
      }
   }
   
   public Collection(boolean[] c) {
      collection = new ArrayList<Boolean>();
      for (int i=0; i<c.length; i++){
         collection.add(c[i]);
      }
   }
   
   //accessor
   public ArrayList<Boolean> getCollection(){
      return collection;
   }
   
   //does not return if succesful
   public void addObject(boolean b, int index){
      collection.add(index, b);
   }
   
   //removes object at the index, need indexof method
   public void removeObject(int index){
      collection.remove(index);
   }
   
   //changes object at index to be unlocked
   public void updateObject(int index){
      int num = -1;
      for(int i=0; i<collection.size(); i++){
         if(collection.get(i) == false){
            num++;
            if(num == index){
               collection.set(i, true);
            }
         }
      }
   }
   
   public String printCollection(){
      String temp = "";
      ArrayList<Objects> array = Database.getObjects();
      for (int i=0; i<collection.size(); i++){
         temp += array.get(i).getName();
         if(collection.get(i) == true){
            temp += "\tUnlocked";
         } else {
            temp += "\tLocked";
         }
         if(i!=collection.size()-1){
            temp += "\n";
         }
      }
      return temp;
   }
   
   public String printUnlocked(){
      String temp = "";
      ArrayList<Objects> array = Database.getObjects();
      for (int i=0; i<collection.size(); i++){
         if (collection.get(i) == true) {
            temp += array.get(i).getName() + "\n";
         }
      }
      return temp;
   }
   
   public String printLocked(){
      String temp = "";
      ArrayList<Objects> array = Database.getObjects();
      for (int i=0; i<collection.size(); i++){
         if (collection.get(i) == false) {
            temp += array.get(i).getName() + "\n";
         }
      }
      return temp;
   }
   
   public String modifyTeamPrint(){
      String temp = "";
      ArrayList<Objects> array = Database.getObjects();
      int index = 1;
      for (int i=0; i<collection.size(); i++){
         if (collection.get(i) == false) {
            temp += "" + index + ": " + array.get(i).getName() + "\n";
            index++;
         }
      }
      return temp;
   }
   
   //prints out item: locked, item: unlocked etc
   public String toString(){
      String temp = "";
      ArrayList<Objects> array = Database.getObjects();
      for (int i=0; i<collection.size(); i++){
         temp += array.get(i).getName() + ": ";
         if (collection.get(i) == false){
            temp = temp + "Locked\n";
         }
         else {
            temp = temp + "Unlocked\n";
         }
      }
      return temp;
   }
   
}












