/**
    File Name: Player
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores information regarding an individual player in genshin
*/

import java.util.*;
import java.io.*;
public class Player {
 
   //fields
   private String name;
   private int uid;
   private int level;
   private Team team;
   private Collection collection;
   
   //constructor
   public Player(String n, int u, int l, Team t, Collection c){
      name = n;
      uid = u;
      level = l;
      team = t;
      collection = c;
   }
   //accessors
   public String getName(){
      return name;
   }
   
   public int getUID(){
      return uid;
   }
   
   public int getLevel(){
      return level;
   }
   
   public Team getTeam(){
      return team;
   }
   
   public Collection getCollection(){
      return collection;
   }
   
   //mutators
   public void setName(String newName){
      name = newName;
   }
   
   public void setUid(int newUid){
      uid = newUid;
   }
   
   public void setLevel(int newLevel){
      level = newLevel;
   }
   
   //Modifies the team (also handles the UI)
   public void modifyTeam(){
      
      Scanner sc = new Scanner(System.in);
   
      System.out.println(team);
      System.out.println("\nPlease enter an option: ");
      System.out.println("1. Add Character");
      System.out.println("2. Remove Character");
      System.out.println("3. Switch Characters");
      
      int n = sc.nextInt();
      sc.nextLine();
      if(n==1){
         System.out.println("\nPlease enter the name of the Character you would like to add: ");
         String s = sc.nextLine();
         if(team.addCharacter(s)){
            System.out.println("Success!");
         } else {
            System.out.println("Unsuccessful...");
         }
      } else if(n==2){
         System.out.println("\n" + team);
         System.out.println("\nPlease indicate the index of the character you would like to remove: ");
         int i = sc.nextInt();
         if(team.removeCharacter(i)){
            System.out.println("Successful!");
         } else {
            System.out.println("Unsuccessful...");
         }
      } else if(n==3){
         System.out.println("\n" + team);
         System.out.println("\nPlease indicate the index of the character you would like to swap out: ");
         int i = sc.nextInt();
         System.out.println("Please enter the name of the character you would like to swap to: ");
         sc.nextLine();
         String s = sc.nextLine();
         if(team.switchCharacter(i, s)){
            System.out.println("Successful!");
         } else {
            System.out.println("Unsuccessful...");
         }
      }
      
   }
   
   //Print Collection (also contains the UI)
   public void printCollection(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Please select an option:");
      System.out.println("1. Print entire collection");
      System.out.println("2. Print unlocked collection");
      System.out.println("3. Print locked collection");
      
      int n = sc.nextInt();
      sc.nextLine();
      if(n==1){
         System.out.println(collection.printCollection());
      } else if(n==2){
         System.out.println(collection.printUnlocked());
      } else if(n==3){
         System.out.println(collection.printLocked());
      }
      
   }
   
   //Modify Collection (also contains the UI)
   public void modifyCollection(){
   
      Scanner sc = new Scanner(System.in);
      
      boolean flag = true;
      while(flag){
         System.out.println(collection.modifyTeamPrint());
         System.out.println("\nPlease enter the index of the object you would like to update: ");
         int index = sc.nextInt();
         collection.updateObject(index-1);
         flag = false;
         System.out.println("\nWould you like to update another object? Yes/No");
         sc.nextLine();
         String s = sc.nextLine();
         if(s.equalsIgnoreCase("Yes")){
            flag = true;
         }
      }
      
   }
   
   //tostring
   public String toString(){
      return ("Name: " + name + "\nUID: " + uid + "\nLevel: " + level + "\nTeam:\n" + team + "\nCollection:\n" + collection);
   }
}
 	 
  













