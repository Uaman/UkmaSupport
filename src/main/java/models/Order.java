package models;

import java.models;


public class Order {

    private String id;

    private int amount;

    private string charge;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void getCharge(){

    }

    public void setCharge(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
            this.id = id;
        }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount='" + amount + '\''+
                ", charge='" + charge + '\''

                '}';
    }


}