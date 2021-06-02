package com.example.darts;

public class iteratior {
    private static int iter;
    private static int korszamlalo = 1;
    private static int igaze;
    private static int nyil = 4;
    private static int osszertek = 0;
    private static int gombszamlalo = 0;

    private static boolean lista[] = new boolean[50];


    public iteratior(int iter) {
        this.iter = iter;
    }

    public iteratior() {
    }

    public static int getGombszamlalo() {
        return gombszamlalo;
    }

    public static void setGombszamlalo(int gombszamlalo) {
        iteratior.gombszamlalo = gombszamlalo;
    }

    public static boolean[] getLista() {
        return lista;
    }

    public static void setLista(int szam) {
        iteratior.lista[szam] = true;
    }

    public static int getOsszertek() {
        return osszertek;
    }

    public static void setOsszertek(int osszertek) {
        iteratior.osszertek = osszertek;
    }

    public static int getNyil() {
        return nyil;
    }

    public static void setNyil(int nyil) {
        iteratior.nyil = nyil;
    }

    public static int getIgaze() {
        return igaze;
    }

    public static void setIgaze(int igaze) {
        iteratior.igaze = igaze;
    }

    public static int getKorszamlalo() {
        return korszamlalo;
    }

    public static void setKorszamlalo(int korszamlalo) {
        iteratior.korszamlalo = korszamlalo;
    }

    public static int getIter() {
        return iter;
    }
    public static void setIter(int iter) {
        iteratior.iter = iter;
    }

    @Override
    public String toString() {
        return "iteratior{}";
    }
}
