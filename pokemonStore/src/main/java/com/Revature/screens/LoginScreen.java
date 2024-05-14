// package com.Revature.screens;

// import java.util.Scanner;

// import com.Revature.models.User;
// import com.Revature.services.RouterService;
// import com.Revature.services.UserService;

// public class LoginScreen extends BaseScreen{
//     private Scanner scan;
//     private RouterService routerService;
//     private UserService userService;
//     private User userSession;

//     public LoginScreen(RouterService routerService, Scanner scan, UserService userService, User userSession) {
//         this.scan = scan;
//         this.routerService = routerService;
//         this.userService = userService;
//         this.userSession = userSession;
//     }
//     @Override
//     public void startInterface() {
//         while (true) {
//             clearScreen();
//             System.out.println("Loggin in....");
        
//             System.out.println("\nUsername: ");
//             String username = scan.nextLine();
//             System.out.println("\npassword");
//             String password = scan.nextLine();
                    
//             if (userService.login(username,password) != null) {
//                 System.out.println("logged in");
//             } else {
//                 System.out.println("invalid credentials");
//                 pause(scan);
//                 continue;
//             }
//             break;
//         }
//     }
    
// }
