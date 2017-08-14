package com.macor.CodeWars;

import java.util.Collections;

public class App {

    public static void main(String[] args) {
        /*
         * @author: Mario Ortega
         * @version: 11/08/2017 STRING REPEAT CodeWars I did it the first solution The
         * second one was made it by the community of CodeWars The sysout are return
         */

        String word = "holA";
        int cant = 5;

        // First solution

        if (cant <= 0) {
            System.out.println("");
        } else {
            String repetir = String.join("", Collections.nCopies(cant, word));
            System.out.println(repetir);
        }

        // Second solution (Best Practice)

        System.out.println(cant < 0 ? "" : String.join("", Collections.nCopies(cant, word)));

        // Thirst solution
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cant; i++) {
            sb.append(word);
        }

        System.out.println(sb.toString());

    }
}
