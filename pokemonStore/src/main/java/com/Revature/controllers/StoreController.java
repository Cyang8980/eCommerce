package com.Revature.controllers;

import java.util.HashMap;
import java.util.Map;

import com.Revature.dtos.requests.NewStoreRequest;
import com.Revature.dtos.responses.Principal;
import com.Revature.models.Store;
import com.Revature.services.StoreService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class StoreController {
    private final StoreService storeService;
    private final TokenService tokenService;

    public StoreController(StoreService storeService, TokenService tokenService) {
        this.storeService = storeService;
        this.tokenService = tokenService;
    }
    public void addStore(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            // get token from header
            String token = ctx.header("auth-token");
            if (token == null || token.isEmpty()) {
                ctx.status(401); // unauthorized
                errors.put("error", "token is null or empty");
                ctx.json(errors);
                return;
            }  
            // parse token to get the principal (auth)

            Principal principal = tokenService.parseToken(token);
            if (principal == null) {
                ctx.status(401); // unauthorized
                errors.put("error", "principal is null");
                ctx.json(errors);
                return;
            }

            if (!principal.getRole().getName().equalsIgnoreCase("ADMIN")) {
                ctx.status(403); // forbiddon
                errors.put("error", "You are not an admin");
                ctx.json(errors);
                return;
            }
            
            NewStoreRequest req = ctx.bodyAsClass(NewStoreRequest.class);
            if (req.getName().isEmpty()) {
                ctx.status(400);
                errors.put("error", "Your store needs a name!");
                ctx.json(errors);
                return;
            }
            if(!storeService.isUnique(req.getName())) {
                ctx.status(409);
                errors.put("error", "Store already exists or store name already taken");
                ctx.json(errors);
                return;
            }
            Store store = storeService.createStore(req.getName());
            ctx.json(store);
            ctx.status(201);
        } catch(Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
    public void getAllStores(Context ctx) {
        Map<String, String> errors = new HashMap<>();
        try {
            // get token from header
            String token = ctx.header("auth-token");
            if (token == null || token.isEmpty()) {
                ctx.status(401); // unauthorized
                errors.put("error", "token is null or empty");
                ctx.json(errors);
                return;
            }  
            // parse token to get the principal (auth)

            Principal principal = tokenService.parseToken(token);
            if (principal == null) {
                ctx.status(401); // unauthorized
                errors.put("error", "principal is null");
                ctx.json(errors);
                return;
            }
            ctx.json(storeService.getAllStores());
        } catch(Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
}
