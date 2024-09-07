/*
    File Name: SystemRunner.java
    Names: Griffin, Steven, Rina
    Class: ICS4U
    Date: June 16, 2023
    Description: Runs the system
*/
import java.util.*;
class SystemRunner {
   
   public static void main(String[] args){
      
      Scanner sc = new Scanner(System.in);
      
      new GenshinSystem();
      
      boolean flag = true;
      
      while(flag){
         UserInterface.startScreen();
         
         System.out.println("Would you like to do another action? Yes/No");
         String s = sc.nextLine();
         
         if(s.equalsIgnoreCase("No") || !s.equalsIgnoreCase("Yes")){
            flag = false;
            System.out.println("Thank you for using our program!");
            GenshinSystem.endRun();
         }
      }
      
   }
    
}

