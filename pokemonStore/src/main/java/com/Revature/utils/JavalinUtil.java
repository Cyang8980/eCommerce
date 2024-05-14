package com.Revature.utils;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.io.IOException;

import com.Revature.controllers.CartController;
import com.Revature.controllers.CartItemController;
import com.Revature.controllers.ItemController;
import com.Revature.controllers.OrderController;
import com.Revature.controllers.OrderItemsController;
import com.Revature.controllers.StoreController;
import com.Revature.controllers.UserController;
import com.Revature.daos.CartDAO;
import com.Revature.daos.CartProductDAO;
import com.Revature.daos.ItemDAO;
import com.Revature.daos.OrderDAO;
import com.Revature.daos.OrderItemDAO;
import com.Revature.daos.RoleDAO;
import com.Revature.daos.StoreDAO;
import com.Revature.daos.UserDAO;
import com.Revature.services.CartItemService;
import com.Revature.services.CartService;
import com.Revature.services.ItemService;
import com.Revature.services.OrderItemService;
import com.Revature.services.OrderService;
import com.Revature.services.RoleService;
import com.Revature.services.StoreService;
import com.Revature.services.TokenService;
import com.Revature.services.UserService;

import io.javalin.Javalin;

public class JavalinUtil {

    public Javalin getJavalin() throws IOException {
        // Controllers
        UserController userController = new UserController(
            getUserService(),
            new CartService(
            new CartDAO()),
            new TokenService());
        StoreController storeController = new StoreController (
            new StoreService(new StoreDAO()),
            new TokenService()
        );
        ItemController itemController = new ItemController(
            new ItemService(new ItemDAO()), 
            new TokenService(), 
            getStoreService());
        CartItemController cartProductController = new CartItemController(new TokenService(), 
            new CartService(new CartDAO()), 
            new ItemService(new ItemDAO()), 
            new CartItemService(new CartProductDAO()));
        OrderItemsController orderItemsController = new OrderItemsController(new TokenService(), 
            new OrderItemService(new OrderItemDAO()), 
            new ItemService(new ItemDAO()));
        OrderController orderController = new OrderController(new TokenService(),
            new CartService(new CartDAO()), 
            new ItemService(new ItemDAO()), 
            new CartItemService(new CartProductDAO()),
            new OrderService(new OrderDAO()),
            new OrderItemService(new OrderItemDAO()));
        CartController cartController = new CartController(new CartService(new CartDAO()),
            new TokenService());
        return Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/users", () -> {
                    post("/register", userController::register);
                    post("/login", userController::login);
                    delete(userController::delete);
                });
                path("/carts", () -> {
                    get(cartController::getCartByUserID);
                });
                path("/stores", () -> {
                    post(storeController::addStore);
                    get(storeController::getAllStores);
                });

                path("/items", () -> {
                    post(itemController::addItem);
                    delete(itemController::delete);
                    get(itemController::getAllItems);
                    patch(itemController::update);
                });

                path("/cartItems", () -> {
                    post(cartProductController::addItem);
                    delete(cartProductController::delete);
                    patch(cartProductController::updateQuantity);
                    get(cartProductController::getUserCart);
                });

                path("orderItems", () -> {
                    get(orderItemsController::getAllOrders);
                });

                path("orders", () -> {
                    post(orderController::addOrder);
                    get("/{id}", orderController::getOrderHistoryWithUserID);
                    get(orderController::getAllOrderHistory);
                });
            });
        });
    }

    private UserService getUserService() {
        return new UserService(new UserDAO(), new RoleService(new RoleDAO()));
    }

    private StoreService getStoreService() {
        return new StoreService(new StoreDAO());
    }
}