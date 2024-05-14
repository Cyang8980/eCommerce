package com.Revature.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Revature.dtos.responses.Principal;
import com.Revature.models.OrderItem;
import com.Revature.services.ItemService;
import com.Revature.services.OrderItemService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class OrderItemsController {
    private final TokenService tokenService;
    private OrderItemService orderItemService;

    public OrderItemsController(
        TokenService tokenService, 
        OrderItemService orderItemService, 
        ItemService itemService) {
        this.tokenService = tokenService;
        this.orderItemService = orderItemService;
    }

    public void getAllOrders(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            Principal principal = tokenService.parseToken(token);
            if (!(principal.getRole().getName().equalsIgnoreCase("ADMIN"))) {
                ctx.status(403); // Forbidden
                errors.put("error", "You are not an admin");
                return;
            }
            List<OrderItem> orders = orderItemService.getAll();
            ctx.json(orders);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
}