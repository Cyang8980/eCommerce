// package com.Revature.screens;

// import java.util.Scanner;

// import com.Revature.services.RouterService;

// public class StartScreen extends BaseScreen{
//     private Scanner scan;
//     private RouterService routerService;

//     public StartScreen(RouterService routerService, Scanner scan) {
//         this.routerService = routerService;
//         this.scan = scan;
//     }

//     @Override
//     public void startInterface() {
//         while(true) {
//             clearScreen();
//             System.out.println("Welcome to the Pokemon Store!");
//             System.out.println("\n[1] Login");
//             System.out.println("[2] Register");
//             System.out.println("[x] Exit");
            
//             System.out.println("What do you want to do?"); 
//             String choice = scan.nextLine();

//             switch(choice) {
//                 case "1":
//                     routerService.navigate("/login").startInterface();
//                 case "2":
//                     routerService.navigate("/register").startInterface();
//                 case "x":
//                     return;
//                 default:
//                     clearScreen();
//                     System.out.println("Invalid choice, please try again");
//                     pause(scan);
//                     break;
//             }
//         }
//     }
    
// }
