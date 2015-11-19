package cs2340.edu.gatech.shoppingwithfriends.Items;

import java.util.ArrayList;

/**
 * Created by connorlindquist on 2/6/15.
 */
public class Item {

    private String name;
    private double price;
    private String notes;
    private static ArrayList<Item> itemList;

    /**
     * Constructor
     * @param name name of the item
     * @param price price of the item
     * @param notes notes about the item
     */
    public Item(String name, double price, String notes) {
        this.name = name;
        this.price = price;
        this.notes = notes;
        itemList = new ArrayList<>();
    }

    /**
     * Returns name of the item
     * @return item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the notes about the item
     * @return item's notes
     */
    public String getNotes(){ return notes; }

    /**
     * Returns the price of the item
     * @return item's price
     */
    public double getPrice(){ return price; }


    public static ArrayList<Item> getItems(){
        return itemList;
    }
}