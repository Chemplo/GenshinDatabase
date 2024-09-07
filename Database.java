/*
    File Name: Database
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 5, 2023
    Description: stores/accesses all of the objects in the game
*/
import java.util.*;
import java.io.*;
class Database{

   private static ArrayList<Objects> objects;
   private static int numCharacters;
   private static int numWeapons;
   
   //Constructor
   public Database(){
      objects = new ArrayList<Objects>();
      numCharacters = 0;
      numWeapons = 0;
      readFromFile();
   }
   
   //Accessor
   public static ArrayList<Objects> getObjects(){
      return objects;
   }
   
   //Returns size of the array
   public static int size(){
      return numCharacters + numWeapons;
   }
   
   //toString
   public static String printList() {
      String s = "";
      for (int i = 0; i < size(); i++) {
         s += objects.get(i) + "\n";
      }
      return s;
   }
   
   public static String printListName(){
      String s = "";
      for(Objects o: objects){
         s += o.getName() + "\n";
      }
      return s;
   }
   
   public static String printCharacters() {
      defaultSort();
      String s = "";
      for (int i=0; i<numCharacters; i++) {
         s += objects.get(i) + "\n";
      }
      return s;
   }
   
   public static String printWeapons() {
      defaultSort();
      String s = "";
      for (int i=numCharacters; i<size(); i++) {
         s += objects.get(i) + "\n";
      }
      return s;
   }
   
   //Add weapon
   public static void addWeapon(Weapon w){
      
      objects.add(w);
      numWeapons++;
      defaultSort();
      
      String name = w.getName();
      int index = getIndexOf(name);
      ArrayList<Player> players = Playerbase.getPlayers();
      for(int i=0; i<players.size(); i++){
         Collection col = players.get(i).getCollection();
         col.addObject(false, index);
      }
      
   }
   
   //Add character
   public static void addCharacter(Character c){
   
      objects.add(c);
      numCharacters++;
      defaultSort();
      
      String name = c.getName();
      int index = getIndexOf(name);
      ArrayList<Player> players = Playerbase.getPlayers();
      for(int i=0; i<players.size(); i++){
         Collection col = players.get(i).getCollection();
         col.addObject(false, index);
      }
   
   }
   
   //Remove Object
   public static void removeObject(int n){
      
      defaultSort();
      if(objects.get(n) instanceof Character){
         numCharacters--;
      } else {
         numWeapons--;
      }
      objects.remove(n);
      
      ArrayList<Player> p = Playerbase.getPlayers();
      for(int i=0; i<p.size(); i++){
         Player p1 = p.get(i);
         Collection c = p1.getCollection();
         c.removeObject(n);
      }
      
   }

//File Input
   public static void readFromFile(){
      try{
         BufferedReader in = new BufferedReader(new FileReader("GenshinObjects.txt"));
         
         String line = in.readLine();
         int numC = 0;
         int numW = 0;
         boolean characters = true;
         while((line=in.readLine())!=null){
            if(line.equalsIgnoreCase("Weapons")){
               characters = false;
               line = in.readLine();
            }
            
            if(characters){
               String name = line;
               int release = Integer.parseInt(in.readLine());
               String type = in.readLine();
               char gender = in.readLine().charAt(0);
               String region = in.readLine();
               String element = in.readLine();
               
               Character c1 = new Character(name, release, type, gender, region, element);
               objects.add(c1);
               numC++;
            } else {
               String name = line;
               int release = Integer.parseInt(in.readLine());
               String type = in.readLine();
               
               Weapon w1 = new Weapon(name, release, type);
               objects.add(w1);
               numW++;
            }
         }
         
         numCharacters = numC;
         numWeapons = numW;
         
         in.close();
      } catch(IOException iox){
         System.out.println("Sorry, there was an error reading from file!");
      }
   }
   
   //File Output
   public static void writeToFile(){
      defaultSort();
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("GenshinObjects.txt", false));
         
         out.write("Characters");
         out.newLine();
         for(int i=0; i<numCharacters; i++){
            Character c1 = (Character) objects.get(i);
            out.write(c1.fileWrite()); 
            out.newLine();      
         }
         
         out.write("Weapons");
         out.newLine();
         for(int i=0; i<numWeapons; i++){
            Weapon w1 = (Weapon) objects.get(numCharacters+i);
            out.write(w1.fileWrite());
            if(i!=numWeapons-1){
               out.newLine();
            }
         }
         
         out.close();
      } catch(IOException iox){
         System.out.println("Error printing to file!");
      }
   }
   
   //Default Sorting Method (By Release then Name Alphabetically)
   public static void defaultSort(){
      
      sortByType();
      for(int i=numCharacters-1; i>0; i--){
         int maxIndex = 0;
         for(int j=0; j<=i; j++){
            Objects temp1 = objects.get(maxIndex);
            Objects temp2 = objects.get(j);
            if(temp2.releasePatch > temp1.releasePatch){
               maxIndex = j;
            } else if(temp2.releasePatch == temp1.releasePatch && temp2.name.compareTo(temp1.name) > 0){
               maxIndex = j;
            }
         }
         Objects temp = objects.get(i);
         Objects temp2 = objects.get(maxIndex);
         objects.remove(i);
         objects.add(i, temp2);
         objects.remove(maxIndex);
         objects.add(maxIndex, temp);
      }
      
      for(int i=objects.size()-1; i>numCharacters; i--){
         int maxIndex = numCharacters;
         for(int j=numCharacters; j<=i; j++){
            Objects temp1 = objects.get(maxIndex);
            Objects temp2 = objects.get(j);
            if(temp2.releasePatch > temp1.releasePatch){
               maxIndex = j;
            } else if(temp2.releasePatch == temp1.releasePatch && temp2.name.compareTo(temp1.name) > 0){
               maxIndex = j;
            }
         }
         Objects temp = objects.get(i);
         Objects temp2 = objects.get(maxIndex);
         objects.remove(i);
         objects.add(i, temp2);
         objects.remove(maxIndex);
         objects.add(maxIndex, temp);
      }
      
   }
   
   

   //Sort by type (insertion sort)
   public static void sortByType () {
      for (int i = 0; i < objects.size(); i++) {
         int j = i;
         Objects temp = objects.get(i);
         
         while (j > 0 && temp instanceof Character) {
            objects.remove(j);
            objects.add(j, objects.get(j - 1));
            j--;
         }
         objects.remove(j);
         objects.add(j, temp);
      }
   }

   //bubble sort
   public static void sortByName() {
      for (int i = objects.size()-1; i>0; i--) {
         boolean sorted = false;
         for (int j=0; j<i; j++){
            sorted = true;
            Objects a = objects.get(j);
            Objects b = objects.get(j+1);
            if (a.getName().compareTo(b.getName()) > 0) {
               sorted = false;
             
               objects.remove(j);
               objects.add(j, b);
               objects.remove(j+1);
               objects.add(j+1, a);
            }
         }
      }
   }
 
  //sort into characters and then weapons with sortbytype, and then sort alphabetically
   public static void sortByTypeName(){
      sortByType();
      //weapon
      int i= objects.size() - 1;
      for (; i > 0 && objects.get(i) instanceof Weapon; i--) {
         boolean sorted = false;
         for (int j=0; j < i; j++){
            sorted = true;
            if (objects.get(j) instanceof Weapon && objects.get(j).getName().compareTo(objects.get(j+1).getName()) > 0) {
               sorted = false;
               Objects a = objects.get(j);
               Objects b = objects.get(j+1);
             
               objects.set(j, b);
               objects.set(j+1, a);
            }
         }
      }
      //character
      for (; i > 0; i--) {
         boolean sorted = false;
         for (int j=0; j < i; j++){
            sorted = true;
            if (objects.get(j) instanceof Character && objects.get(j).getName().compareTo(objects.get(j+1).getName()) > 0) {
               sorted = false;
               Objects a = objects.get(j);
               Objects b = objects.get(j+1);
             
               objects.set(j, b);
               objects.set(j+1, a);
            }
         }
      }
   }
   
   //sort by release date(earliest to latest) (insertion sort)
   public static void sortByRelease(){
      for(int i=objects.size()-1; i>0; i--){
         int maxIndex = i;
         for(int j=0; j<=i; j++){
            Objects temp = objects.get(maxIndex);
            Objects temp2 = objects.get(j);
            if(temp2.getReleasePatch() > temp.getReleasePatch()){
               maxIndex = j;
            }
         }
         Objects temp = objects.get(maxIndex);
         Objects temp2 = objects.get(i);
         
         objects.remove(maxIndex);
         objects.add(maxIndex, temp2);
         objects.remove(i);
         objects.add(i, temp);
      }
   }
   
   //sort by gender   
   public static void sortByGender() {
      sortByType();
      boolean sorted = false;
      int index = numCharacters - 1;
      while(!sorted){
         Character temp = (Character) objects.get(index);
         if(temp.getGender() == 'F'){
            objects.remove(index);
            objects.add(0, temp);
         }
         sorted = true;
         
         if(((Character)objects.get(index)).getGender() == 'M'){
            index--;
            sorted = false;
         } else {
            for(int i=0; i<index; i++){
               Character temp2 = (Character)objects.get(i);
               if(temp2.getGender() == 'M'){
                  sorted = false;
               }
            }
         }
      }
   }

   //sort by weapontype (sword, claymore, bow, catalyst, polearm)
   public static void sortByWeaponType(){
      boolean foundWeapon = false;
   	//weapon looking for
      String weaponWant = "Polearm";
   	//outside of parameter to carry on
      int i = objects.size()-1;
    
   	//insert sort all the polearms, then catalysts, then bows, then claymores, then swords
      for (; i>0 && foundWeapon == false; i--){
         foundWeapon = false;
         for (int j=0; j < i && foundWeapon == false; j++){
            if (objects.get(j).getWeaponType().equals(weaponWant)){
               Objects a = objects.get(i);
               Objects b = objects.get(j);
             
               objects.set(j, a);
               objects.set(i, b);
             	 
               foundWeapon = true;
            }
         }
        	//switch if no swaps made
         if (foundWeapon == false) {
           	//no more weapons of that type
            foundWeapon = true;
            i++;
         }
         else {
            foundWeapon = false;
         }
      }
   	
      foundWeapon = false;
      weaponWant = "Catalyst";
      for (; i>0 && foundWeapon == false; i--){
         foundWeapon = false;
         for (int j=0; j <= i && foundWeapon == false; j++){
            if (objects.get(j).getWeaponType().equals(weaponWant)){
               Objects a = objects.get(i);
               Objects b = objects.get(j);
             
               objects.set(j, a);
               objects.set(i, b);
             	 
               foundWeapon = true;
            }
         }
        	//switch if no swaps made
         if (foundWeapon == false) {
           	//no more weapons of that type
            foundWeapon = true;
            i++;
         }
         else {
            foundWeapon = false;
         }
      }
      
      foundWeapon = false;
      weaponWant = "Bow";
      for (; i>0 && foundWeapon == false; i--){
         foundWeapon = false;
         for (int j=0; j <= i && foundWeapon == false; j++){
            if (objects.get(j).getWeaponType().equals(weaponWant)){
               Objects a = objects.get(i);
               Objects b = objects.get(j);
             
               objects.set(j, a);
               objects.set(i, b);
             	 
               foundWeapon = true;
            }
         }
        	//switch if no swaps made
         if (foundWeapon == false) {
           	//no more weapons of that type
            foundWeapon = true;
            i++;
         }
         else {
            foundWeapon = false;
         }
      }
      
      foundWeapon = false;
      weaponWant = "Claymore";
      for (; i>0 && foundWeapon == false; i--){
         foundWeapon = false;
         for (int j=0; j <= i && foundWeapon == false; j++){
            if (objects.get(j).getWeaponType().equals(weaponWant)){
               Objects a = objects.get(i);
               Objects b = objects.get(j);
             
               objects.set(j, a);
               objects.set(i, b);
             	 
               foundWeapon = true;
            }
         }
        	//switch if no swaps made
         if (foundWeapon == false) {
           	//no more weapons of that type
            foundWeapon = true;
            i++;
         }
         else {
            foundWeapon = false;
         }
      }
      
      foundWeapon = false;
      weaponWant = "Sword";
      for (; i>0 && foundWeapon == false; i--){
         foundWeapon = false;
         for (int j=0; j <= i && foundWeapon == false; j++){
            if (objects.get(j).getWeaponType().equals(weaponWant)){
               Objects a = objects.get(i);
               Objects b = objects.get(j);
             
               objects.set(j, a);
               objects.set(i, b);
             	 
               foundWeapon = true;
            }
         }
        	//switch if no swaps made
         if (foundWeapon == false) {
           	//no more weapons of that type
            foundWeapon = true;
            i++;
         }
         else {
            foundWeapon = false;
         }
      }   	 
   }

   //sort by elements (pyro, hydro, dendro, electro, anemo, cryo, geo)
   public static void sortByElement(){
      boolean foundElement = false;
    //weapon looking for
      String elementWant = "Geo";
    //outside of parameter to carry on
      int i = objects.size()-1;
    
    //insert sort
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j < i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Cryo";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Cryo";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Anemo";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Dendro";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Hydro";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
    
      foundElement = false;
      elementWant = "Pyro";
      for (; i>0 && foundElement == false; i--){
         foundElement = false;
         for (int j=0; j <= i && foundElement == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getElement().equals(elementWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundElement = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundElement == false) {
         	 //no more weapons of that type
            foundElement = true;
            i++;
         }
         else {
            foundElement = false;
         }
      }
   }
 	 
   //sort by regions (Mondstadt, Liyue, Inazuma, Sumeru)
   public static void sortByRegion(){
      boolean foundRegion = false;
    //weapon looking for
      String regionWant = "Sumeru";
    //outside of parameter to carry on
      int i = objects.size()-1;
    
    //insert sort
      for (; i>0 && foundRegion == false; i--){
         foundRegion = false;
         for (int j=0; j < i && foundRegion == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getRegion().equals(regionWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundRegion = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundRegion == false) {
         	 //no more weapons of that type
            foundRegion = true;
            i++;
         }
         else {
            foundRegion = false;
         }
      }
    
      foundRegion = false;
      regionWant = "Inazuma";
      for (; i>0 && foundRegion == false; i--){
         foundRegion = false;
         for (int j=0; j <= i && foundRegion == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getRegion().equals(regionWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundRegion = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundRegion == false) {
         	 //no more weapons of that type
            foundRegion = true;
            i++;
         }
         else {
            foundRegion = false;
         }
      }
    
      foundRegion = false;
      regionWant = "Liyue";
      for (; i>0 && foundRegion == false; i--){
         foundRegion = false;
         for (int j=0; j <= i && foundRegion == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getRegion().equals(regionWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundRegion = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundRegion == false) {
         	 //no more weapons of that type
            foundRegion = true;
            i++;
         }
         else {
            foundRegion = false;
         }
      }
    
      foundRegion = false;
      regionWant = "Mondstadt";
      for (; i>0 && foundRegion == false; i--){
         foundRegion = false;
         for (int j=0; j <= i && foundRegion == false; j++){
            if (objects.get(j) instanceof Character){
               Character temp = (Character)objects.get(j);
               if (temp.getRegion().equals(regionWant)){
                  Objects a = objects.get(i);
                  Objects b = objects.get(j);
               
                  objects.set(j, a);
                  objects.set(i, b);
                
                  foundRegion = true;
               }
            }
         }
      	 //switch if no swaps made
         if (foundRegion == false) {
         	 //no more weapons of that type
            foundRegion = true;
            i++;
         }
         else {
            foundRegion = false;
         }
      }
   }

 //Search by Name
   public static Objects searchByName(String n){
      
      for(int i=0; i<size(); i++){
         if(objects.get(i).name.equalsIgnoreCase(n)){
            return objects.get(i);
         }
      }
      
      return null;
      
   }
   
  //search by type
   public static ArrayList<Objects> searchByType(String s) {
      ArrayList<Objects> list = new ArrayList<Objects>();
      if (s.equals("Character")) {
         for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Character) {
               list.add(objects.get(i));
            }
         }
      } else {
         for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Weapon) {
               list.add(objects.get(i));
            }
         }
      }
      
      return list;
   }
   
   
   //search by gender
   public static ArrayList<Objects> searchByGender(char c){
      sortByGender();
      ArrayList<Objects> list = new ArrayList<Objects>();
      boolean found = false;
      int index = 0;
      while (!found) {
         index++;
         if (((Character)objects.get(index)).getGender() == 'M') {
            found = true;
         }
      }
      
      if (c == 'F') {
         for (int i = 0; i < index; i++) {
            list.add(objects.get(i));
         }
      } else {
         for (int i = index; i < numCharacters; i++) {
            list.add(objects.get(i));
         }
      }
      return list;
   }
   

   //index of takes in the name, returns -1 if dne
   public static int getIndexOf(String s){
      defaultSort();
      for (int i=0; i<objects.size(); i++){
         Objects o = objects.get(i);
         if (o.getName().equalsIgnoreCase(s)){
            return i;
         }
      }
      return -1;
   }
   
   //binary search search by release
   public static ArrayList<Objects> searchByRelease(int n) {
      sortByRelease();
      boolean found = false;
      int bot = 0;
      int top = objects.size() -1;
    
      ArrayList foundObjects = new ArrayList<Objects>();
    
      while (bot <= top && found == false){
         int mid = (bot+top)/2;
         if (n == objects.get(mid).getReleasePatch()){
            found = true;
            foundObjects.add(objects.get(mid));
         //check all greater and lesser
            for (int i=mid+1; i<=top && n==objects.get(i).getReleasePatch(); i++){
               foundObjects.add(objects.get(i));
            }
            for (int i=mid-1; i>=bot && n==objects.get(i).getReleasePatch(); i--){
               foundObjects.add(objects.get(i));
            }
         }
         else if (n > objects.get(mid).getReleasePatch()) {
            bot = mid + 1;
         }
         else {
            top = mid - 1;
         }
      }
      return foundObjects;
    
   }


   public static ArrayList<Character> searchByRegion(String s){
      ArrayList characterFound = new ArrayList<Character>();
      sortByRegion();
      boolean foundFirst = false;
      boolean foundLast = false;
   //keep track when hit first copy of the object and when hit the last
      for (int i=numWeapons-1; i < objects.size() && foundLast == false; i++){
         if (objects.get(i) instanceof Character) {
            Character temp = (Character)objects.get(i);   
            if (temp.getRegion().equals(s)){
               foundFirst = true;
               characterFound.add(temp);   
            }
            else if (foundFirst == true){
               foundLast = true;
            }
         }
      }
    
      return characterFound;
   }

   //search by weapontype
   public static ArrayList<Objects> searchByWeaponType(String s){
      ArrayList objectsFound = new ArrayList<Objects>();
      sortByWeaponType();
      boolean foundFirst = false;
      boolean foundLast = false;
   //keep track when hit first copy of the object and when hit the last
      for (int i=0; i < objects.size() && foundLast == false; i++){
         Objects temp = objects.get(i);   
         if (temp.getWeaponType().equals(s)){
            foundFirst = true;
            objectsFound.add(temp);   
         }
         else if (foundFirst == true){
            foundLast = true;
         }
      }
    
      return objectsFound;
   }
   
   //search by character weapontype
   public static ArrayList<Objects> searchByCharacterWeaponType(String s){
      ArrayList objectsFound = new ArrayList<Objects>();
      sortByWeaponType();
      boolean foundFirst = false;
      boolean foundLast = false;
   //keep track when hit first copy of the object and when hit the last
      for (int i=0; i < objects.size() && foundLast == false; i++){
         Objects temp = objects.get(i);   
         if (temp.getWeaponType().equals(s)){
            foundFirst = true;
            if (objects.get(i) instanceof Character){
               objectsFound.add(temp);
            }  
         }
         else if (foundFirst == true){
            foundLast = true;
         }
      }
    
      return objectsFound;
   }

   
   //search by weapon weapontype
   public static ArrayList<Objects> searchByWeaponWeaponType(String s){
      ArrayList objectsFound = new ArrayList<Objects>();
      sortByWeaponType();
      boolean foundFirst = false;
      boolean foundLast = false;
      //keep track when hit first copy of the object and when hit the last
      for (int i=0; i < objects.size() && foundLast == false; i++){
         Objects temp = objects.get(i);   
         if (temp.getWeaponType().equals(s)){
            foundFirst = true;
            if (objects.get(i) instanceof Weapon){
               objectsFound.add(temp);
            }  
         }
         else if (foundFirst == true){
            foundLast = true;
         }
      }
    
      return objectsFound;
   }

   //search by element
   public static ArrayList<Character> searchByElement(String s){
      ArrayList characterFound = new ArrayList<Character>();
      sortByElement();
      boolean foundFirst = false;
      boolean foundLast = false;
   //keep track when hit first copy of the object and when hit the last
      for (int i=numWeapons-1; i < objects.size() && foundLast == false; i++){
         if (objects.get(i) instanceof Character) {
            Character temp = (Character)objects.get(i);   
            if (temp.getElement().equals(s)){
               foundFirst = true;
               characterFound.add(temp);   
            }
            else if (foundFirst == true){
               foundLast = true;
            }
         }
      }
    
      return characterFound;
   }
   
   //searchbyelementregion (search for the region, and then search within the region
   //element e then string r
   public static ArrayList<Character> searchByElementRegion(String e, String r){
      ArrayList characterFound = new ArrayList<Character>();
      sortByRegion();
      boolean foundFirst = false;
      boolean foundLast = false;
   //keep track when hit first copy of the object and when hit the last
      for (int i=numWeapons-1; i < objects.size() && foundLast == false; i++){
         if (objects.get(i) instanceof Character) {
            Character temp = (Character)objects.get(i);   
            if (temp.getRegion().equals(r)){
               foundFirst = true;
               characterFound.add(temp);   
            }
            else if (foundFirst == true){
               foundLast = true;
            }
         }
      }
    
   //now check the element
      ArrayList newCharacterFound = new ArrayList<Character>();
      for (int i=0; i < characterFound.size(); i++){
         if (((Character)characterFound.get(i)).getElement().equals(e)){
            newCharacterFound.add(characterFound.get(i));
         }
      }
      return newCharacterFound;
   }

}







