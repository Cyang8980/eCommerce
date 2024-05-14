// package com.Revature.screens;

// import java.util.Scanner;

// import com.Revature.models.User;
// import com.Revature.services.RouterService;
// import com.Revature.services.UserService;

// public class RegisterScreen extends BaseScreen{

//     private Scanner scan;
//     private final UserService userService;
//     private final RouterService routerService;

//     public RegisterScreen(RouterService routerService, Scanner scan, UserService userService) {
//         this.scan = scan;
//         this.userService = userService;
//         this.routerService = routerService;
//     }
//     @Override
//     public void startInterface() {
//         while (true) {
//             clearScreen();
//             System.out.println("Creating User....");
        
//             System.out.println("\nUsername: ");
//             String username = scan.nextLine();
        
//             if (!userService.isValidUsername(username)) {
//                 clearScreen();
//                 System.out.println("Username is already taken");
//                 pause(scan);
//                 continue;
//             }
//             String password;

//             while (true) {
//                 clearScreen();
//                 System.out.println("Password: ");
//                 password = scan.nextLine();
//                 if (!userService.validPassword(password)) {
//                     System.out.println("Password not valid. Please enter a password with minimum eight characters, at least one letter, one number, and one special character");
//                     pause(scan);
//                     continue;
//                 } 
//                 break;
//             }
        
//             System.out.println("Email: ");
//             String email = scan.nextLine();
            
//             // Perform email verification (maybe)
        
//             User newUser = new User(username, password, email);
//             userService.save(newUser);

//             System.out.println("Account created Successfully");
//             pause(scan);
//             break;
//         }
    
//     }
// }
