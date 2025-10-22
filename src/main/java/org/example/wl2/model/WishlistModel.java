package org.example.wl2.model;

public class WishlistModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private String link;

    public WishlistModel(int id, String name, String description,double price,String link){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
    }
    public WishlistModel(){

    }

    public int getid(){
        return id;
    }

    public void setid(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String toString(){
        return "name, " + name + " descriptions: " + description + " price: " + price + " Link = " + link;
    }
}
