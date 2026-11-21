package org.example.Tools;

import java.io.IOException;

public class TextEffect {


    //PROBLÉMA: amikor entert ütök hogy felgyorsítsam a szöveg kiírását akkor ugyanúgy "karakternek" érzékeli az entert, kezd egy új sort majd kiírja a maradékot --> MÉG MINDIG NEM JÓ

    /*
     * MEGOLDÁS:
     * ---------
     * - ANSI escape kód --> ezzel tudom neki megmondani hogy vissza akarok lépni egy sorral feljebb és itt folytassa a kiírást --> \u001b[1A
     * \r --> ez a sor elején fogja kezdeni
     *
     * \u001b[<X>C --> lépjen jobbra annyi betűt ami ki volt már írva
     *
     * */
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

//                    itt számolja hány k. volt már kiírva
                    int lastNewline = text.lastIndexOf('\n', i - 1);

                    int charsOnLine = (lastNewline == -1) ? i : (i - 1 - lastNewline);

//                    itt vonja vissza az entert és a sor elejére áll
                    System.out.print("\u001b[1A\r");

                    if (charsOnLine > 0) {
//                        ha nem a sor elején állt akkor behúz annyit amennyi szöveg ki volt írva
                        System.out.print("\u001b[" + charsOnLine + "C");
                    }

//                    itt ahogy korábban folytatja majd a kiírást
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
