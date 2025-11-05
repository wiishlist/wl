package org.example.wl2.model;

import java.math.BigDecimal;

public class Wish {

    private int id;
    private int userId;
    private String name;
    private String description;
    private BigDecimal price;
    private String link;
    private String currency;

    public Wish(int id, int userId, String name, String description, BigDecimal price, String link, String currency){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.currency = currency;
    }

    public Wish(String name, String description, BigDecimal price, String link, String currency){
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;

    }
    public Wish(){

    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /* public String toString(){
        return "name, " + name + " descriptions: " + description + " price: " + price + " Link = " + link;
    }

    */
}
