package com.example.darts;

public class Statistics {
    private String name;
    private int korszam;
    private int gyozelem;
    private int kisebbbuntetopont;
    private int nagyobbbuntetopont;
    private int jatekszam;

    public Statistics(String name, int korszam, int gyozelem, int kisebbbuntetopont, int nagyobbbuntetopont, int jatekszam) {
        this.name = name;
        this.korszam = korszam;
        this.gyozelem = gyozelem;
        this.kisebbbuntetopont = kisebbbuntetopont;
        this.nagyobbbuntetopont = nagyobbbuntetopont;
        this.jatekszam = jatekszam;
    }

    public Statistics() {
    }

    public int getJatekszam() {
        return jatekszam;
    }

    public void setJatekszam(int jatekszam) {
        this.jatekszam = jatekszam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorszam() {
        return korszam;
    }

    public void setKorszam(int korszam) {
        this.korszam = korszam;
    }

    public int getGyozelem() {
        return gyozelem;
    }

    public void setGyozelem(int gyozelem) {
        this.gyozelem = gyozelem;
    }

    public int getKisebbbuntetopont() {
        return kisebbbuntetopont;
    }

    public void setKisebbbuntetopont(int kisebbbuntetopont) {
        this.kisebbbuntetopont = kisebbbuntetopont;
    }

    public int getNagyobbbuntetopont() {
        return nagyobbbuntetopont;
    }

    public void setNagyobbbuntetopont(int nagyobbbuntetopont) {
        this.nagyobbbuntetopont = nagyobbbuntetopont;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "name='" + name + '\'' +
                ", korszam=" + korszam +
                ", gyozelem=" + gyozelem +
                ", kisebbbuntetopont=" + kisebbbuntetopont +
                ", nagyobbbuntetopont=" + nagyobbbuntetopont +
                ", jatekszam=" + jatekszam +
                '}';
    }
}
