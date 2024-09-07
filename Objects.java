/**
    File Name: Objects
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores data of what characters can interact with and store
*/

abstract class Objects {
   protected String name;
   protected int releasePatch;
   protected String weaponType;
   
   //constructor 
   public Objects(String n, int r, String w) {
      name = n;
      releasePatch = r;
      weaponType = w;
   }
   //accessors
   public String getName(){
      return name;
   }
   
   public int getReleasePatch(){
      return releasePatch;
   }
   
   public String getWeaponType(){
      return weaponType;
   }
   
   //mutators
   public void setName(String newName){
      name = newName;
   }
   
   public void setReleasePatch(int newReleasePatch){
      releasePatch = newReleasePatch;
   }
   
   public void setWeaponType(String newWeaponType){  
      weaponType = newWeaponType;
   }
   //name releasePatch weaponType
   public String toString(){
      return ("\nName: " + name + "\nRelease Patch: " + releasePatch/10.0 + "\nWeapon Type: " + weaponType);
   }

//other methods
   
   //implicit - explicit (override with method in weapon and character class)
   public int compareToRelease(Objects other){
      return (releasePatch - other.getReleasePatch());
   }
   
   abstract String fileWrite();
}



