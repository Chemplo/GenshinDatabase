/*
    File Name: UserInterface.java
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 16, 2023
    Description: Handles and runs the GUI for the system
*/
import java.util.*;
class UserInterface{
   
   //Runs the GUI for the welcome screen
   public static void startScreen(){
   
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Welcome to our Genshin data system! What would you like to do today?");
      System.out.println("1. Access Database");
      System.out.println("2. Access Playerbase");
      System.out.println("3. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         dataAccess();
      } else if(n==2){
         playerAccess();
      }
      
      sc.close();

   }
   
   //Runs the GUI for database access
   public static void dataAccess(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nWelcome to the Database! What would you like to do?");
      System.out.println("1. Print");
      System.out.println("2. Modify");
      System.out.println("3. Search");
      System.out.println("4. Sort");
      System.out.println("5. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         dataPrint();
      } else if(n==2){
         dataModify();
      } else if(n==3){
         dataSearch();
      } else if(n==4){
         dataSort();
      }
      
   }
   
   //Runs the GUI for printing out Database
   public static void dataPrint(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nWhat would you like to print?");
      System.out.println("1. Just the Characters");
      System.out.println("2. Just the Weapons");
      System.out.println("3. All Objects");
      System.out.println("4. Quit");
      
      int n = sc.nextInt();
      sc.nextLine();
      if(n==1){
         System.out.println(Database.printCharacters());
      } else if(n==2){
         System.out.println(Database.printWeapons());
      } else if(n==3){
         System.out.println(Database.printList());
      }
      
      sc.close();

   }
   
   //Runs the GUI for modifying Database
   public static void dataModify(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to modify Database?");
      System.out.println("1. Add Object");
      System.out.println("2. Remove Object");
      System.out.println("3. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         GenshinSystem.addObject();
      } else if(n==2){
         GenshinSystem.removeObject();
      }
      
      sc.close();

   }
   
   //Runs the GUI for searching Database
   public static void dataSearch(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to search Database?");
      System.out.println("1. Search by Type (Weapon/Character)");
      System.out.println("2. Search by Name (ex. Diluc)");
      System.out.println("3. Search by Release Patch (ex. 2.1)");
      System.out.println("4. Search by Gender (M/F)");
      System.out.println("5. Search by Region (ex. Liyue)");
      System.out.println("6. Search by Weapon Type (ex. Claymore)");
      System.out.println("7. Search by Character Weapon Types (Only returns characters of a given weapon type)");
      System.out.println("8. Search by Weapon Weapon Types (Only returns weapons of a given weapon type)");
      System.out.println("9. Search by Element (ex. Pyro)");
      System.out.println("10. Search by Element and Region (ex. Hydro, Inazuma)");
      System.out.println("11. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         System.out.println("\nWould you like to search by Weapon or Character?");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Objects> list = Database.searchByType(s);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==2){
         System.out.println("\nPlease enter the name that you would like to search:");
         sc.nextLine();
         String s = sc.nextLine();
         
         Objects obj = Database.searchByName(s);
         if(obj == null){
            System.out.println("This Object does not exist!");
         } else {
            System.out.println(obj);
         }
      } else if(n==3){
         System.out.println("\nPlease enter the Release Patch that you would like to search (ex. 2.1):");
         int x = (int) (10 * sc.nextDouble());
         
         ArrayList<Objects> list = Database.searchByRelease(x);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==4){
         System.out.println("\nPlease enter the Gender you would like to search (M/F):");
         sc.nextLine();
         char c = sc.nextLine().charAt(0);
         
         ArrayList<Objects> list = Database.searchByGender(c);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==5){
         System.out.println("\nPlease enter the Region you would like to search (Mondstadt, Liyue, Inazuma, Sumeru):");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Character> list = Database.searchByRegion(s);
         for(Character c: list){
            System.out.println(c);
         }
      } else if(n==6){
         System.out.println("\nEnter the Weapon Type you would like to search (Sword, Bow, Claymore, Catalyst, Polearm):");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Objects> list = Database.searchByWeaponType(s);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==7){
         System.out.println("\nEnter the Character Weapon Type you would like to search (Sword, Bow, Claymore, Catalyst, Polearm):");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Objects> list = Database.searchByCharacterWeaponType(s);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==8){
         System.out.println("\nEnter the Weapon Weapon Type you would like to search (Sword, Bow, Claymore, Catalyst, Polearm):");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Objects> list = Database.searchByWeaponWeaponType(s);
         for(Objects o: list){
            System.out.println(o);
         }
      } else if(n==9){
         System.out.println("\nEnter the Element you would like to search (Pyro, Hydro, Dendro, Anemo, Electro, Cryo, Geo):");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Character> list = Database.searchByElement(s);
         for(Character c: list){
            System.out.println(c);
         }
      } else if(n==10){
         System.out.println("\nEnter the Element you would like to search (Pyro, Hydro, Dendro, Anemo, Electro, Cryo, Geo):");
         sc.nextLine();
         String element = sc.nextLine();
         
         System.out.println("\nEnter the Region you would like to also search (Mondstadt, Liyue, Inazuma, Sumeru):");
         String region = sc.nextLine();
         
         ArrayList<Character> list = Database.searchByElementRegion(element, region);
         for(Character c: list){
            System.out.println(c);
         }
      }
      
      sc.close();

   }
   
   //Runs the GUI for sorting Database
   public static void dataSort(){
   
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to sort Database?");
      System.out.println("1. Default Sort");
      System.out.println("2. Sort by Type (Character/Weapon)");
      System.out.println("3. Sort by Name (Alphabetically ascending)");
      System.out.println("4. Sort by Type and Name (Characters alphabetically then weapons alphabetically)");
      System.out.println("5. Sort by Release Patch (earliest to latest)");
      System.out.println("6. Sort by Weapon Type (Sword, Claymore, Bow, Catalyst, Polearm in that order)");
      System.out.println("7. Sort by Gender (Females then Males)");
      System.out.println("8. Sort by Element");
      System.out.println("9. Sort by Region");
      System.out.println("10. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         Database.defaultSort();
         System.out.println(Database.printList());
      } else if(n==2){
         Database.sortByType();
         System.out.println(Database.printList());
      } else if(n==3){
         Database.sortByName();
         System.out.println(Database.printList());
      } else if(n==4){
         Database.sortByTypeName();
         System.out.println(Database.printList());
      } else if(n==5){
         Database.sortByRelease();
         System.out.println(Database.printList());
      } else if(n==6){
         Database.sortByWeaponType();
         System.out.println(Database.printList());
      } else if(n==7){
         Database.sortByGender();
         System.out.println(Database.printList());
      } else if(n==8){
         Database.sortByElement();
         System.out.println(Database.printList());
      } else if(n==9){
         Database.sortByRegion();
         System.out.println(Database.printList());
      }
      
      sc.close();

   }
   
   //Runs the GUI for playerbase access
   public static void playerAccess(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nWelcome to the Playerbase! What would you like to do?");
      System.out.println("1. Print");
      System.out.println("2. Modify");
      System.out.println("3. Search");
      System.out.println("4. Sort");
      System.out.println("5. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         playerPrint();
      } else if(n==2){
         playerModify();
      } else if(n==3){
         playerSearch();
      } else if(n==4){
         playerSort();
      }
      
      sc.close();

   }
   
   //Runs the GUI for printing Playerbase
   public static void playerPrint(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to print Playerbase?");
      System.out.println("1. Print all Players");
      System.out.println("2. Print Collection");
      System.out.println("3. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         playerInfo();
      } else if(n==2){
         playerCollection();
      }
      
      sc.close();

   }
   
   //Runs the GUI for printing player info
   public static void playerInfo(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nWhat would you like to print?");
      System.out.println("1. All player information");
      System.out.println("2. Just player names");
      System.out.println("3. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         Playerbase.printPlayers();
      } else if(n==2){
         System.out.println(Playerbase.printPlayerName());
      }
    
      sc.close();

   }
   
   //Runs the GUI for print player collections
   public static void playerCollection(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println(Playerbase.printPlayerName());
      System.out.println("Please enter the index of the player you like to print the collection of (indexes start at 1):");
      int n = sc.nextInt();
      
      Playerbase.printCollection(n);
      
      sc.close();

   }
   
   //Runs the GUI for modifying Playerbase
   public static void playerModify(){
   
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to modify Playerbase?");
      System.out.println("1. Add Player");
      System.out.println("2. Remove Player");
      System.out.println("3. Modify existing player's Team");
      System.out.println("4. Modify existing player's Collection");
      System.out.println("5. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         GenshinSystem.addPlayer();
      } else if(n==2){
         GenshinSystem.removePlayer();
      } else if(n==3){
         System.out.println(Playerbase.printPlayerName());
         
         System.out.println("\nPlease enter the index of the player you like to print the collection of (indexes start at 1):");
         int index = sc.nextInt();
         
         Playerbase.modifyTeam(index);
      } else if(n==4){
         System.out.println(Playerbase.printPlayerName());
         
         System.out.println("\nPlease enter the index of the player you like to print the collection of (indexes start at 1):");
         int index = sc.nextInt();
         
         Playerbase.modifyCollection(index);
      }
      
      sc.close();

   }
   
   //Runs the GUI for searching Playerbase
   public static void playerSearch(){
   
      Scanner sc = new Scanner(System.in);
   
      System.out.println("\nHow would you like to search Playerbase?");
      System.out.println("1. Search by Name");
      System.out.println("2. Search by UID");
      System.out.println("3. Search by Name and Level");
      System.out.println("4. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         System.out.println("\nPlease enter the name you would like to search:");
         sc.nextLine();
         String s = sc.nextLine();
         
         ArrayList<Player> list = Playerbase.searchByName(s);
         for(Player p: list){
            System.out.println(p);
         }
      } else if(n==2){
         System.out.println("\nPlease enter the UID you would like to search:");
         int uid = sc.nextInt();
         
         Player p = Playerbase.searchByUID(uid);
         System.out.println(p);
      } else if(n==3){
         System.out.println("\nPlease enter the name you would like to search:");
         sc.nextLine();
         String name = sc.nextLine();
         System.out.println("\nPlease enter the level of the player you would like to search:");
         int level = sc.nextInt();
         
         ArrayList<Player> list = Playerbase.searchByNameLevel(name, level);
         for(Player p: list){
            System.out.println(p);
         }
      }
      
      sc.close();

   }
   
   //Runs the GUI for sorting Playerbase
   public static void playerSort(){
   
      Scanner sc = new Scanner(System.in);
      
      System.out.println("\nHow would you like to sort Playerbase?");
      System.out.println("1. Sort by Name (alphabetically ascending)");
      System.out.println("2. Sort by UID (ascending)");
      System.out.println("3. Sort by Level Ascending");
      System.out.println("4. Sort by Level Descending");
      System.out.println("5. Quit");
      
      int n = sc.nextInt();
      if(n==1){
         Playerbase.sortByName();
         System.out.println(Playerbase.printPlayerName());
      } else if(n==2){
         Playerbase.sortByUID();
         System.out.println(Playerbase.printPlayerName());
      } else if(n==3){
         Playerbase.sortByLevelAscending();
         System.out.println(Playerbase.printPlayerName());
      } else if(n==4){
         Playerbase.sortByLevelDescending();
         System.out.println(Playerbase.printPlayerName());
      }
      
      sc.close();

   }
      
}

