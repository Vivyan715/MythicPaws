package org.example.Tools;

import java.io.IOException;

public class TextEffect {


    //TODO: ezt optimalizálni --> amikor megy a szöveg akkor ugyan kiírja a teljes sor tartalmát de közben a konzolon le lesz ütve egy enter --> undertale szerűen kellene működnie
    public static boolean FAST_MODE = false;

    public static void sleep(int milliseconds) {
        if (FAST_MODE) return;

//        ha be van kapcsolva akkor 50ms időkkel "alszik"
        int elapsed = 0;  //eltelt idő számolása --> [ms]
        int step = 50;
        while (elapsed < milliseconds) {
            try {
                if (System.in.available() > 0) {
                    clearInputBuffer();
                    break;
                }
            } catch (IOException ignored) {
            }

            try {
                Thread.sleep(Math.min(step, milliseconds - elapsed));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            elapsed += step;
        }
    }

    public static void typeWriter(String text, int delayMillis) {
        if (FAST_MODE) {
            System.out.println(text);
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            try {
                // enter lenyomására kiírja az egész szöveget
                if (System.in.available() > 0) {
                    clearInputBuffer();
                    System.out.print(text.substring(i));
                    break;
                }
            } catch (IOException ignored) {
            }

            System.out.print(text.charAt(i));
            System.out.flush();

            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println();
    }


    public static void clearInputBuffer() {
//        puffer kiűrítése --> nem fogja megzavarni a későbbi scanner olvasásokat
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException ignored) {
        }
    }
}
