package e.gebruiker.eindproject;

import java.io.Serializable;

public class TransactionEntry implements Serializable {

    private int _id;
    private String tag;
    private CharSequence price;
    private String category;
    private CharSequence date;


    public TransactionEntry(String tag, CharSequence price, String category) {
        this.tag = tag;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String getTag() {
        return tag;
    }

    public CharSequence getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public CharSequence getDate() {
        return date;
    }
}
