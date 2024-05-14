// package com.Revature.services;

// import java.util.Scanner;

// import com.Revature.models.User;
// import com.Revature.screens.BaseScreen;
// import com.Revature.screens.LoginScreen;
// import com.Revature.screens.RegisterScreen;
// import com.Revature.screens.StartScreen;


// public class RouterService {
//     private final Scanner scan;
//     private final UserService userService;
//     private User session;
    
//     public RouterService(Scanner scan, UserService userService, User session) {
//         this.scan = scan;
//         this.userService = userService;
//         this.session = session;
//     }

//     public BaseScreen navigate(String path) {
//         switch (path) {
//             case "/start":
//                 return new StartScreen(this, scan);
//             case "/register":
//                 return new RegisterScreen(this, scan, userService);
//             case "/login":
//                 return new LoginScreen(this, scan, userService, session);
//             default:
//                 throw new IllegalArgumentException("Invalid path" + path);
//         }
//     }
// }
