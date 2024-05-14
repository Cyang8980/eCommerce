package com.Revature.screens;

import java.util.Scanner;

public abstract class BaseScreen {
    
    public abstract void startInterface();

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void pause(Scanner scan) {
        System.out.println("Press Enter to continue");
        scan.nextLine();
    }
}
