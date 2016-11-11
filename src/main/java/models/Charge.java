package models;

import java.models;


public class Charge {

    private String id;

    private int amount;

    //if applicable
    private string invoice;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void getInvoice(){

    }

    public void setInvoice(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
            this.id = id;
        }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", amount='" + amount + '\''+
                ", invoice='" + invoice + '\''

                '}';
    }


}