package com.Revature;

import java.io.IOException;

import com.Revature.utils.JavalinUtil;

public class App {
    public static void main(String[] args) throws IOException {
        new JavalinUtil().getJavalin().start(7070);
    }
}   