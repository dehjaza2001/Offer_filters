package org.example.Model;

public class Merchant implements Comparable<Merchant>  {
    private int id;
    private String name;
    private double distance;

    public Merchant(int id, String name, double distance){
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public  Merchant(){
        this.id = -1;
        this.name = null;
        this.distance = 0.0;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public int compareTo(Merchant compareMerchant) {
        return (int) (compareMerchant.getDistance() - this.distance);
    }
}
