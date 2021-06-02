package com.example.darts;

public class Numbers {
    private static int allpoint;
    private static int ot;
    private static int hat;
    private static int het;
    private static int nyolc;
    private static int kilenc;
    private static int husz;
    private static int huszonot;

    public Numbers(int allpoint, int ot, int hat, int het, int nyolc, int kilenc, int husz, int huszonot) {
        this.allpoint = allpoint;
        this.ot = ot;
        this.hat = hat;
        this.het = het;
        this.nyolc = nyolc;
        this.kilenc = kilenc;
        this.husz = husz;
        this.huszonot = huszonot;
    }
    public Numbers() {
    }

    public int getAllpoint() {
        return allpoint;
    }

    public void setAllpoint(int allpoint) {
        this.allpoint = allpoint;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public int getHat() {
        return hat;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public int getHet() {
        return het;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public int getNyolc() {
        return nyolc;
    }

    public void setNyolc(int nyolc) {
        this.nyolc = nyolc;
    }

    public int getKilenc() {
        return kilenc;
    }

    public void setKilenc(int kilenc) {
        this.kilenc = kilenc;
    }

    public int getHusz() {
        return husz;
    }

    public void setHusz(int husz) {
        this.husz = husz;
    }

    public int getHuszonot() {
        return huszonot;
    }

    public void setHuszonot(int huszonot) {
        this.huszonot = huszonot;
    }

    @Override
    public String toString() {
        return "Numbers{" +
                "allpoint=" + allpoint +
                ", ot=" + ot +
                ", hat=" + hat +
                ", het=" + het +
                ", nyolc=" + nyolc +
                ", kilenc=" + kilenc +
                ", husz=" + husz +
                ", huszonot=" + huszonot +
                '}';
    }
}
