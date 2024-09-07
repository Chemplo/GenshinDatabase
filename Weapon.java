/**
    File Name: Weapons
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores information of an individual weapon
*/
public class Weapon extends Objects {

  //constructor
   public Weapon(String n, int r, String w){
      super(n, r, w);
   }

   //accessors
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
   public void setName(String newName){
      super.setName(newName);
   }
   
   public void setReleasePatch(int newReleasePatch){
      super.setReleasePatch(newReleasePatch);
   }
   
   public void setWeaponType(String newWeaponType){  
      super.setWeaponType(newWeaponType);
   }

   public String toString(){
      return super.toString();
   }

   //other methods
   
   //implicit - explicit  (override with objects and weapons class)
   public int compareToRelease(Weapon other){
      return (releasePatch - other.getReleasePatch());
   }
   
   public String fileWrite(){
      String s = super.name + "\n" + super.releasePatch + "\n" + super.weaponType;
      return s;
   }
   
}


