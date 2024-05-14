package com.Revature.controllers;

import java.util.HashMap;
import java.util.Optional;

import com.Revature.dtos.requests.NewDeleteUserRequest;
import com.Revature.dtos.requests.NewLoginRequest;
import com.Revature.dtos.requests.NewRegisterRequest;
import com.Revature.dtos.responses.Principal;
import com.Revature.models.Cart;
import com.Revature.models.User;
import com.Revature.services.CartService;
import com.Revature.services.UserService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class UserController {
    private UserService userService;
    private CartService cartService;
    private final TokenService tokenService;

    public UserController(UserService userService, CartService cartService, TokenService tokenService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.cartService = cartService;
    }

    public void register(Context ctx) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            NewRegisterRequest req = ctx.bodyAsClass(NewRegisterRequest.class);

            if (!userService.isValidUsername(req.getUsername())) {
                ctx.status(400);
                errors.put("Error:", "Username is not valid");
                ctx.json(errors);
                return;
            } if (!userService.uniqueUsername(req.getUsername())) {
                ctx.status(409);
                errors.put("Error:", "Username is not unique");
                ctx.json(errors);
                return;
            } if (!userService.validPassword(req.getPassword())) {
                ctx.status(400);
                errors.put("Error:", "Password is not valid");
                ctx.json(errors);
                return;
            }
            User newUser = new User(req);
            Cart newCart = new Cart(newUser.getId());
            newUser.setCartID(newCart.getCart_id());
            newUser = userService.save(newUser);
            newCart = cartService.save(newCart, newUser);
            ctx.json(newUser);
            ctx.status(201);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
    public void login(Context ctx) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            NewLoginRequest req = ctx.bodyAsClass(NewLoginRequest.class);
            Optional<User> existingUser = userService.login(req.getUsername(), req.getPassword());
            if (existingUser.isEmpty()) {
                ctx.status(400);
                errors.put("Error:", "Invalid username or password");
                ctx.json(errors);
                return;
            }
            User foundUser = existingUser.get();
            Principal principle = new Principal(foundUser);
            System.out.println(foundUser.toString());
            // create jswt
            String token = tokenService.generateToken(principle);
            // set token to header
            ctx.header("auth-token", token);
            ctx.json(principle);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
    public void delete(Context ctx) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            NewDeleteUserRequest req = ctx.bodyAsClass(NewDeleteUserRequest.class);
            Principal principal = tokenService.parseToken(token);
            if (!principal.getRole().getName().equalsIgnoreCase("ADMIN")) {
                ctx.status(403); // forbiddon
                errors.put("error", "You are not an admin");
                ctx.json(errors);
                return;
            }
            cartService.delete(req.getId());
            User deletedUser = userService.delete(req.getId());
            if (deletedUser == null) {
                ctx.status(400);
                errors.put("Error", "No user found");
                ctx.json(errors);
                return;
            }
            ctx.json(deletedUser);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }

    public void getCart(Context ctx) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            NewDeleteUserRequest req = ctx.bodyAsClass(NewDeleteUserRequest.class);
            Principal principal = tokenService.parseToken(token);
            if (!principal.getRole().getName().equalsIgnoreCase("ADMIN")) {
                ctx.status(403); // forbiddon
                errors.put("error", "You are not an admin");
                ctx.json(errors);
                return;
            }
            cartService.delete(req.getId());
            User deletedUser = userService.delete(req.getId());
            if (deletedUser == null) {
                ctx.status(400);
                errors.put("Error", "No user found");
                ctx.json(errors);
                return;
            }
            ctx.json(deletedUser);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }

}
