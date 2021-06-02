package com.example.darts;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private int id;
    private String name;
    private int allpoint;
    private int ot;
    private int hat;
    private int het;
    private int nyolc;
    private int kilenc;
    private int husz;
    private int huszonot;
    private static List<Players> list=new ArrayList<Players>();

    public Players(int id,String name, int allpoint, int ot, int hat, int het, int nyolc, int kilenc, int husz, int huszonot) {
        this.id = id;
        this.name = name;
        this.allpoint = allpoint;
        this.ot = ot;
        this.hat = hat;
        this.het = het;
        this.nyolc = nyolc;
        this.kilenc = kilenc;
        this.husz = husz;
        this.huszonot = huszonot;
    }

    public Players() {
    }

    public List<Players> getList() {
        return list;
    }

    public void setList(List<Players> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Players{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", allpoint=" + allpoint +
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
