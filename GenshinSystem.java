/*
    File Name: GenshinSystem.java
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 16, 2023
    Description: Creates and access the database and playerbase
*/
import java.util.*;
public class GenshinSystem {

   public GenshinSystem(){
      new Database();
      new Playerbase();
   }
   
   //Closes the system
   public static void endRun(){
      
      Database.defaultSort();
      Database.writeToFile();
      Playerbase.sortByName();
      for(int i=1; i<=Playerbase.numberOfPlayers(); i++){
         Playerbase.writeToFile(i);
      }
      
   }

   //Add Object
   public static void addObject(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.print("\nWould you like to add a Character or a Weapon? ");
      String s = sc.nextLine();
      if(s.equalsIgnoreCase("Weapon")){
         System.out.print("\nPlease enter the Weapon name: ");
         String name = sc.nextLine();
         System.out.print("\nPlease enter the Weapon release patch: ");
         int n = (int) (sc.nextDouble() * 10);
         System.out.print("\nPlease type the name of a Weapon type(Sword, Claymore, Bow, Catalyst, Polearm): ");
         sc.nextLine();
         String type = sc.nextLine();
         
         Weapon w = new Weapon(name, n, type);
         Database.addWeapon(w);
      } else if(s.equalsIgnoreCase("Character")){
         System.out.print("\nPlease enter the Character name: ");
         String name = sc.nextLine();
         System.out.print("\nPlease enter the Character release patch: ");
         int n = (int) (sc.nextDouble() *10);
         System.out.print("\nPlease type the name of a Character weapon type(Sword, Claymore, Bow, Catalyst, Polearm): ");
         sc.nextLine();
         String type = sc.nextLine();
         System.out.print("\nPlease enter the Character Gender(M/F): ");
         char gender = sc.nextLine().charAt(0);
         System.out.print("\nPlease enter the Character element(Pyro, Hydro, Dendro, Electro, Anemo, Cryo, Geo): ");
         String element = sc.nextLine();
         System.out.print("\nPlease enter the Character region(Mondstadt, Liyue, Inazuma, Sumeru): ");
         String region = sc.nextLine();
         
         Character c = new Character(name, n, type, gender, region, element);
         Database.addCharacter(c);
      }
      
   }
   
   //Remove Object
   public static void removeObject(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println(Database.printListName());
      
      System.out.print("\nPlease enter the name of the Object you would like to remove: ");
      String n = sc.nextLine();
      
      int index = Database.getIndexOf(n);
      
      Database.removeObject(index);
      
   }
   
   //Add Player
   public static void addPlayer(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Enter the player name: ");
      String name = sc.nextLine();
      System.out.print("Enter the UID of the player: ");
      int uid = sc.nextInt();
      System.out.print("Enter the level of the player: ");
      int level = sc.nextInt();
      
      System.out.println("Enter the team:");
      sc.nextLine();
      String[] names = new String[Team.maxTeam()];
      for(int i=0; i<names.length; i++){
         System.out.print("Character " + (i+1) + ": ");
         names[i] = sc.nextLine();
      }
      Team t1 = new Team(names);
      
      boolean[] collection = new boolean[Database.size()];
      Arrays.fill(collection, false);
      Collection c1 = new Collection(collection);
      
      Player p1 = new Player(name, uid, level, t1, c1);
      
      Playerbase.addPlayer(p1);
      
   }
   
   //Remove Player
   public static void removePlayer(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println(Playerbase.printPlayerName());
      
      System.out.print("Please enter the UID of the Player you would like to remove: ");
      int n = sc.nextInt();
      
      int index = Playerbase.indexOfUID(n);
      
      Playerbase.removePlayer(index);
      
   }

}



