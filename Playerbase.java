/**
    File Name: Playerbase.java
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores/accesses all of the players in the database
*/

import java.util.*;
import java.io.*;
class Playerbase {

   private static ArrayList<Player> players;
   private static int numPlayers;

   //Constructor
   public Playerbase() {
      players = new ArrayList<Player>();
      numPlayers = 0;
      readFromFile();
   }
   
   //accessor
   public static ArrayList<Player> getPlayers(){
      return players;
   }
   
   public static int numberOfPlayers(){
      return numPlayers;
   }
   
   //User print
   public static void printPlayers(){
      for(Player i: players){
         System.out.println(i);
      }
   }
   
   public static String printPlayerName(){
      String s = "";
      for(int i=0; i<players.size(); i++){
         s+= "Name: " + players.get(i).getName() + "\nUID: " + players.get(i).getUID() + "\n";
         if(i!=players.size()-1){
            s += "\n";
         }
      }
      return s;
   }
   
   //Index of a certain player with UID
   public static int indexOfUID(int n){
      for(int i=0; i<players.size(); i++){
         Player p = players.get(i);
         if(p.getUID() == n){
            return i;
         }
      }
      return -1;
   }
   
   //Reading player information from files
   public static void readFromFile(){
      
      try{
      
         BufferedReader in = new BufferedReader(new FileReader("numPlayers.txt"));
         numPlayers = Integer.parseInt(in.readLine());
      
      } catch(IOException iox){
         System.out.println("Error reading from numPlayers.txt");
      }
      
      try{
         
         for(int i=1; i<=numPlayers; i++){
            String fileName = "player" + i + ".txt";
            
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            
            String name = in.readLine();
            int uid = Integer.parseInt(in.readLine());
            int level = Integer.parseInt(in.readLine());
            
            String[] names = new String[Team.maxTeam()];
            for(int j=0; j<Team.maxTeam(); j++){
               names[j] = in.readLine();
            }
            Team t1 = new Team(names);
            
            boolean[] collection = new boolean[Database.size()];
            for(int j=0; j<Database.size(); j++){
               if(in.readLine().equalsIgnoreCase("false")){
                  collection[j] = false;
               } else {
                  collection[j] = true;
               }
            }
            Collection c1 = new Collection(collection);
            
            Player p1 = new Player(name, uid, level, t1, c1);
            players.add(p1);
            in.close();
         }
         
      } catch(IOException iox){
         System.out.println("Error reading from Player files");
      }
      
   }
   
   //Printing out to a Player file
   public static void writeToFile(int n){
   
      try{
         
         String fileName = "player" + n + ".txt";
         
         BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
         BufferedWriter out2 = new BufferedWriter(new FileWriter("numPlayers.txt", false));
         
         out2.write("" + numPlayers);
         
         Player p1 = players.get(n-1);
         
         out.write(p1.getName());
         out.newLine();
         out.write("" + p1.getUID());
         out.newLine();
         out.write("" + p1.getLevel());
         out.newLine();
         
         Team t1 = p1.getTeam();
         Character[] c1 = t1.getTeam();
         for(int i=0; i<c1.length; i++){
            out.write(c1[i].getName());
            out.newLine();
         }
         
         Collection col1 = p1.getCollection();
         ArrayList<Boolean> b1 = col1.getCollection();
         for(int i=0; i<b1.size(); i++){
            if(b1.get(i) == true){
               out.write("True");
            } else {
               out.write("False");
            }
            if(i!=b1.size()-1){
               out.newLine();
            }
         }
         out.close();
         out2.close();
      } catch(IOException iox){
         System.out.println("Error printing out to Player file");
      }
      
   }
   
   //Sort by Name Alphabetically
   public static void sortByName(){
      
      for(int i=0; i<players.size(); i++){
         int minIndex = i;
         for(int j=i; j<players.size(); j++){
            Player temp1 = players.get(minIndex);
            Player temp2 = players.get(j);
            if(temp2.getName().compareToIgnoreCase(temp1.getName()) < 0){
               minIndex = j;
            }
         }
         Player p1 = players.get(minIndex);
         Player p2 = players.get(i);
         
         players.remove(i);
         players.add(i, p1);
         players.remove(minIndex);
         players.add(minIndex, p2);
      }
      
   }
   
   //Sort by UID in ascending order
   public static void sortByUID(){
      
      for(int i=players.size()-1; i>0; i--){
         for(int j=0; j<i; j++){
            Player t1 = players.get(j);
            Player t2 = players.get(j+1);
            if(t1.getUID() > t2.getUID()){
               players.remove(j);
               players.add(j, t2);
               players.remove(j+1);
               players.add(j+1, t1);
            }
         }
      }
      
   }
   
   //Sort by Level in ascending order
   public static void sortByLevelAscending(){
      
      for(int i=players.size()-1; i>0; i--){
         for(int j=0; j<i; j++){
            Player t1 = players.get(j);
            Player t2 = players.get(j+1);
            if(t1.getLevel() > t2.getLevel()){
               players.remove(j);
               players.add(j, t2);
               players.remove(j+1);
               players.add(j+1, t1);
            }
         }
      }
      
   }
   
   //Sort by Level in descending order
   public static void sortByLevelDescending(){
      
      for(int i=players.size()-1; i>0; i--){
         for(int j=0; j<i; j++){
            Player t1 = players.get(j);
            Player t2 = players.get(j+1);
            if(t1.getLevel() < t2.getLevel()){
               players.remove(j);
               players.add(j, t2);
               players.remove(j+1);
               players.add(j+1, t1);
            }
         }
      }
      
   }
   
   //Search by Name
   public static ArrayList<Player> searchByName(String s){
      ArrayList<Player> list = new ArrayList<Player>();
      for(int i=0; i<players.size(); i++){
         Player temp = players.get(i);
         if(temp.getName().equalsIgnoreCase(s)){
            list.add(temp);
         }
      }
      return list;
   }
   
   //Search by UID wrapper method
   public static Player searchByUID(int n){
      Player p = searchByUID(n, 0);
      return p;
   }
   
   //Search by UID Recursion Method
   public static Player searchByUID(int n, int index){
   
      if(index == players.size()){
         return null;
      } else if(players.get(index).getUID() == n){
         return players.get(index);
      } else {
         return searchByUID(n, index+1);
      }
   
   }
   
   //Search by Name and Level
   public static ArrayList<Player> searchByNameLevel(String s, int l){
      ArrayList<Player> list = new ArrayList<Player>();
      for(int i=0; i<players.size(); i++){
         Player p1 = players.get(i);
         if(p1.getName().equalsIgnoreCase(s) && p1.getLevel() == l){
            list.add(p1);
         }
      }
      return list;
   }
   
   //Modify Team
   public static void modifyTeam(int n){
      
      Player p = players.get(n-1);
      p.modifyTeam();
      
   }
   
   //Print Collection
   public static void printCollection(int n){
      
      Player p = players.get(n-1);
      p.printCollection();
      
   }
   
   //Modify Collection
   public static void modifyCollection(int n){
      
      Player p = players.get(n-1);
      p.modifyCollection();
      
   }
   
   //Add player
   public static void addPlayer(Player p1){
   
      players.add(p1);
      
      numPlayers++;
      
   }
   
   //Remove player
   public static void removePlayer(int n){
      players.remove(n);
      numPlayers--;
   }

}






