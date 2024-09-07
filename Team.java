/**
    File Name: Team
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores information regarding a players team of 4 genshin characters
*/

public class Team {
   private Character[] team;
   private static int TEAM_MAX_CHARACTERS = 4;
   
   //constructor
   public Team(){
      team = new Character[TEAM_MAX_CHARACTERS];
      for (int i = 0; i < TEAM_MAX_CHARACTERS; i++) {
         team[i] = null;
      }
   }

   public Team(String[] n){
      team = new Character[TEAM_MAX_CHARACTERS];
      for (int i=0; i<TEAM_MAX_CHARACTERS; i++) {
         team[i] = (Character) Database.searchByName(n[i]);
      }
   }
   
   //tostring
   public String toString(){
      String temp = "";
      for (int i=0; i<TEAM_MAX_CHARACTERS; i++){
         temp += "Character " + (i+1) + ": ";
         temp = temp + team[i].getName();
         if(i!=TEAM_MAX_CHARACTERS-1){
            temp += "\n";
         }
      }
      return temp;
   }
   
   //accessor
   public static int maxTeam(){
      return TEAM_MAX_CHARACTERS;
   }
   
   public Character[] getTeam(){
      return team;
   }

   //Adds characters to the team
   public boolean addCharacter(String n){
      boolean success = false;
   
      //checks if character exists
      Objects check = Database.searchByName(n);
      if (check == null || check instanceof Weapon) {
      //print character doesn’t exist
         return false;
      }
      
      Character c1 = (Character)check;
   
      //checks if the character being inserted repeats with a character already in the team
      for (int i = 0; i < TEAM_MAX_CHARACTERS; i++){
         if (team[i].equals(c1)) {
            return false;
         }
      }
   
      //inserts the character into the first empty slot
      for (int i = 0; i < TEAM_MAX_CHARACTERS; i++) {
         if (team[i] == null) {
            team[i] = c1;
            success = true;
         }
      }
      return success;
   }

   public boolean removeCharacter(int index){
      boolean success = false;
   
      //removes character if the slot chosen isn’t already empty
      if (team[index - 1] != null) {
         team[index-1] = null;
         success = true;
      }
      return success;
   }

   public boolean switchCharacter(int index, String n){
      //checks if character exists
      Objects check = Database.searchByName(n);
      if (check == null || check instanceof Weapon) {
      //print character doesn’t exist
         return false;
      }
   
      //checks if the character being inserted repeats with a character already in the team
      for (int i = 0; i < TEAM_MAX_CHARACTERS; i++){
         if (team[i] == (Character) check) {
            return false;
         }
      }
   
      //checks if slot is empty 
      if (team[index - 1] != null) {
         team[index - 1] = null;
      }
   
      //inserts character into team
      team[index-1] = (Character) check;
   
      return true;
   }
}








