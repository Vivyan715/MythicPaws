package org.example.Tools;

import java.util.Scanner;

public class InputHandler {

    private static final Scanner sc = new Scanner(System.in);

    public static String readLine() {
        return sc.nextLine().trim();  //trim levágja a felesleges szóközt
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
