package com.Revature.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Revature.dtos.requests.NewDeleteItemRequest;
import com.Revature.dtos.requests.NewItemRequest;
import com.Revature.dtos.requests.NewUpdateItemRequest;
import com.Revature.dtos.responses.Principal;
import com.Revature.models.Item;
import com.Revature.services.ItemService;
import com.Revature.services.StoreService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class ItemController {
    private ItemService itemService;
    private final TokenService tokenService;
    private StoreService storeService;

    public ItemController(ItemService itemService, TokenService tokenService, StoreService storeService) {  
        this.itemService = itemService;
        this.tokenService = tokenService;
        this.storeService = storeService;
    }   

    public void addItem(Context ctx) {
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
                errors.put("error", "you do not have the authority to do this");
                ctx.json(errors);
                return;
            }
            
            NewItemRequest req = ctx.bodyAsClass(NewItemRequest.class);
            if (req.getName().isEmpty()) {
                ctx.status(400);
                errors.put("error", "Your item needs a name!");
                ctx.json(errors);
                return;
            } 

            if (req.getStoreName().isEmpty()) {
                ctx.status(400);
                errors.put("error", "Your item needs a store!");
                ctx.json(errors);
                return;
            }

            if (storeService.findStoreByName(req.getStoreName()) == null) {
                ctx.status(400);
                errors.put("error", "Store not found");
                ctx.json(errors);
                return;
            }
            
            if(!itemService.isUnique(req.getName())) {
                ctx.status(409);
                errors.put("error", "item already exists or item name already taken");
                ctx.json(errors);
                return;
            }  
            itemService.createItem(req.getName(), req.getValue(), req.getGrade(), req.getQuantity(), storeService.findStoreByName(req.getStoreName()).getStore_id());
            ctx.status(201);
        } catch(Exception e) {
            ctx.status(500);
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void getAllItems(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            Principal principal = tokenService.parseToken(token);
            if (!(principal.getRole().getName().equalsIgnoreCase("ADMIN") || principal.getRole().getName().equalsIgnoreCase("DEFAULT"))) {
                ctx.status(403); // Forbidden
                errors.put("error", "You are not logged in");
                return;
            }
            List<Item> items = itemService.getAllItems();
            ctx.json(items);
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
            NewDeleteItemRequest req = ctx.bodyAsClass(NewDeleteItemRequest.class);
            Principal principal = tokenService.parseToken(token);
            if (!principal.getRole().getName().equalsIgnoreCase("ADMIN")) {
                ctx.status(403); // forbiddon
                errors.put("error", "You are not an admin");
                ctx.json(errors);
                return;
            }
            Item deletedItem = itemService.delete(req.getID());
            if (deletedItem == null) {
                ctx.status(404);
                errors.put("Error", "No item found");
                ctx.json(errors);
                return;
            }
            ctx.json(deletedItem);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }

    public void update(Context ctx) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            NewUpdateItemRequest req = ctx.bodyAsClass(NewUpdateItemRequest.class);
            Principal principal = tokenService.parseToken(token);
            if (principal == null) {
                ctx.status(401); // Unauthorized
                errors.put("error", "null user");
                ctx.json(errors);
                return;
            }
            if (!principal.getRole().getName().equalsIgnoreCase("ADMIN")) {
                ctx.status(403); // forbiddon
                errors.put("error", "You are not an admin");
                ctx.json(errors);
                return;
            }
            Item item = new Item(); 
            item.setItem_id(req.getId());
            item.setGrade(req.getGrade());
            item.setName(req.getName());
            item.setQuantity(req.getQuantity());
            item.setStore_id(req.getStore_id());
            item.setValue(req.getValue());
            
            Item updatedItem = itemService.update(item);
            if (updatedItem == null) {
                ctx.status(404);
                errors.put("Error", "No item found");
                ctx.json(errors);
                return;
            }
            ctx.json(updatedItem);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
}
