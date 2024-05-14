package com.Revature.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Revature.dtos.requests.NewOrderRequest;
import com.Revature.dtos.responses.OrderResponse;
import com.Revature.dtos.responses.Principal;
import com.Revature.models.Cart;
import com.Revature.models.CartProduct;
import com.Revature.models.Order;
import com.Revature.models.OrderItem;
import com.Revature.services.CartItemService;
import com.Revature.services.CartService;
import com.Revature.services.ItemService;
import com.Revature.services.OrderItemService;
import com.Revature.services.OrderService;
import com.Revature.services.TokenService;

import io.javalin.http.Context;

public class OrderController {
    private TokenService tokenService;
    private CartService cartService;
    private ItemService itemService;
    private CartItemService cartItemService;
    private OrderService orderService;
    private OrderItemService orderItemService;

    public OrderController() {
    }

    public OrderController(TokenService tokenService, 
        CartService cartService, 
        ItemService itemService, 
        CartItemService cartItemService, 
        OrderService orderService,
        OrderItemService orderItemService) {
        this.tokenService = tokenService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    public void addOrder(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            Principal principal = tokenService.parseToken(token);
            NewOrderRequest req = ctx.bodyAsClass(NewOrderRequest.class);
            if (!(principal.getRole().getName().equalsIgnoreCase("ADMIN") || principal.getRole().getName().equalsIgnoreCase("DEFAULT"))) {
                ctx.status(403); // Forbidden
                errors.put("error", "not logged in" + principal.getRole().getName());
                ctx.json(errors);
                return;
            }
            Cart cart = cartService.findByID(principal.getId());
            String cartID = cart.getCart_id();
            // cartItemService.findAllByCartID(cartService.findByID(principal.getId()).getCart_id())
            List<CartProduct> cartProducts = cartItemService.findAllByCartID(cartID);
            if (cartProducts.size() == 0) {
                ctx.status(200);
                return;
            } else {
                Order order = new Order();
                order.setUser_id(principal.getId());
                order.setOrder_id(req.getCartId());
                order.setStatus("pending");
                int totalCost = 0; 
                int cost = 0;
                OrderItem orderItem = new OrderItem();
                List<OrderItem> oi = new ArrayList<>();
                for (int i = 0; i < cartProducts.size(); i++) {
                    cost = cartProducts.get(i).getQuantity() * itemService.getItemById(cartProducts.get(i).getItem_id()).getValue();
                    orderItem.setCost(cost);
                    orderItem.setItem_id(cartProducts.get(i).getItem_id());
                    orderItem.setQuantity(cartProducts.get(i).getQuantity());
                    orderItem.setOrder_id(order.getOrder_id());
                    System.out.println(orderItem);
                    oi.add(orderItem);
                    totalCost += cost;
                }
                System.out.println(cartProducts.size());
                OrderResponse oResponse = new OrderResponse();
                oResponse.setCost(totalCost);
                oResponse.setStatus("pending");

                order.setCost(totalCost);
                orderService.save(order);
                for (int i = 0; i < oi.size(); i++ ) {
                    System.out.println(oi.size());
                    System.out.println(oi);
                    orderItemService.save(oi.get(i));
                } 
                cartItemService.delete(req.getCartId());
                cartService.delete(req.getCartId());
                oResponse.setItems(orderItemService.getAllByOrderID(order.getOrder_id()));
                ctx.status(201);
                ctx.json(oResponse);
                return;
            }
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }

    public void getOrderHistoryWithUserID(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            Principal principal = tokenService.parseToken(token);
            if (!(principal.getRole().getName().equalsIgnoreCase("ADMIN") || principal.getRole().getName().equalsIgnoreCase("DEFAULT"))) {
                ctx.status(403); // Forbidden
                errors.put("error", "not logged in");
                ctx.json(errors);
                return;
            }
            // find all orders that match with my userid
            List<Order> orders = orderService.getAllByUserID(principal.getId());
            List<OrderResponse> ors = new ArrayList<>();
            // loop and get all ORDER IDs
            for (int i = 0; i < orders.size(); i++) {
                // find ordertems that is found and put in array
                int cost = 0;
                List<OrderItem> os = orderItemService.getAllByOrderID(orders.get(i).getOrder_id());
                for (int j = 0; j < os.size(); j++) {
                    cost += os.get(j).getCost();
                }
                OrderResponse or = new OrderResponse();
                or.setItems(os);
                or.setCost(cost);
                or.setStatus("pending");
                or.setOrder_date(orders.get(i).getOrder_date());
                ors.add(or);
            }
            ctx.status(200);
            ctx.json(ors);
            return;
        } catch (Exception e) {
        ctx.status(500);
        e.printStackTrace();
        }
    }

    public void getAllOrderHistory(Context ctx) {
        Map<String,String> errors = new HashMap<>();
        try {
            String token = ctx.header("auth-token");
            Principal principal = tokenService.parseToken(token);
            if (!(principal.getRole().getName().equalsIgnoreCase("ADMIN"))) {
                ctx.status(403); // Forbidden
                errors.put("error", "only Admin can do this");
                ctx.json(errors);
                return;
            }
            List<Order> orders = orderService.getAllOrders();
            List<OrderResponse> ors = new ArrayList<>();
            // loop and get all ORDER IDs
            for (int i = 0; i < orders.size(); i++) {
                // find ordertems that is found and put in array
                int cost = 0;
                List<OrderItem> os = orderItemService.getAllByOrderID(orders.get(i).getOrder_id());
                for (int j = 0; j < os.size(); j++) {
                    cost += os.get(j).getCost();
                }
                OrderResponse or = new OrderResponse();
                or.setItems(os);
                or.setCost(cost);
                or.setStatus("pending");
                or.setOrder_date(orders.get(i).getOrder_date());
                ors.add(or);
            }
            ctx.status(200);
            ctx.json(orders);
            return;
        } catch (Exception e) {
            ctx.status(500);
            e.printStackTrace();
        }
    }
}   
