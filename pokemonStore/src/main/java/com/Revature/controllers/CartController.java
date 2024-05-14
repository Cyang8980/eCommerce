package com.Revature.controllers;

import java.util.HashMap;
import java.util.Map;

import com.Revature.dtos.responses.Principal;
import com.Revature.models.Cart;
import com.Revature.services.CartService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class CartController {
    private CartService cartService;
    private TokenService tokenService;

    public CartController(CartService cartService, TokenService tokenService) {
        this.cartService = cartService;
        this.tokenService = tokenService;
    }

    public void getCartByUserID(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
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
            Cart cart = cartService.getCart(principal.getId());
            ctx.json(cart);
            ctx.status(200);
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
}
