package com.macor003;

public class Reto3 {

    public static void main(String[] args) {

        String capital = "A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,";
        String lowercase = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,";
        String symbols = "!, #, $, &, *, .,";
        String numbers = "1, 2, 3, 4, 5, 6, 7, 8, 9, 0";

        String characters = String.join(capital, lowercase, symbols, numbers);

        System.out.println(characters);


    }
}
