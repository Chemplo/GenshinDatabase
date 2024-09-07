/**
    File Name: Player
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores information of an individual character
*/
public class Character extends Objects {

   private char gender;
   private String region;
   private String element;
   
    //constructor
   public Character(String n, int rp, String w, char g, String r, String e) {
      super(n, rp, w);
      gender = g;
      region = r;
      element = e;
   }

   //accessors
   public char getGender(){
      return gender;
   }
   
   public String getRegion(){
      return region;
   }
   
   public String getElement(){
      return element;
   }
   
   public String getName(){
      return super.getName();
   }
   
   public int getReleasePatch(){
      return super.getReleasePatch();
   }
   
   public String getWeaponType(){
      return super.getWeaponType();
   }
   
   //mutators
   public void setGender(char newGender){
      gender = newGender;
   }
   
   public void setRegion(String newRegion){
      region = newRegion;
   }
   
   public void setElement(String newElement){
      element = newElement;
   }
   
   public void setName(String newName){
      super.setName(newName);
   }
   
   public void setReleasePatch(int newReleasePatch){
      super.setReleasePatch(newReleasePatch);
   }
   
   public void setWeaponType(String newWeaponType){  
      super.setWeaponType(newWeaponType);
   }
   
   //gender region element
   public String toString(){
      return (super.toString() + "\nGender: " + gender + "\nRegion: " + region + "\nElement: " + element);
   }
   
   //.equals method that compares if two characters are the same
   public boolean equals(Character c){
      if(name.equalsIgnoreCase(c.name)){
         return true;
      } else {
         return false;
      }
   }
   
   //other methods
   
   //implicit - explicit (override with objects and weapons class)
   public int compareToRelease(Character other){
      return (releasePatch - other.getReleasePatch());
   }
   
   public String fileWrite(){
      String s = super.name + "\n" + super.releasePatch + "\n" + super.weaponType + "\n" + gender + "\n" + region + "\n" + element;
      return s;
   }
   
}




