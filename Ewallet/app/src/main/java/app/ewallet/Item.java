package app.ewallet;

/**
 * Created by Seth Legaspi on 10/18/2015.
 * This is the java file that contains the getter and setter methods for the items in the localShop database
 */
public class Item {

    private int _item_ID;
    private String _name;
    private Double _cost;

    public Item() {
        //Empty constructor
    }

    /**
     *
     * @param itemID - Primary Key of the item (can also be the item's barcode or something
     * @param name - Name of the item or what it is called
     * @param cost - how much the item is
     */
    public Item(int itemID, String name, Double cost) {
        this._item_ID = itemID;
        this._name = name;
        this._cost = cost;
    }

    /**
     * Sets the items primary key
     * @param id - Item ID
     */
    public void setID(int id) {
        this._item_ID = id;
    }

    /**
     * Gets the primary key of this item
     * @return returns of the Primary Key of the item
     */
    public int getID() {
        return this._item_ID;
    }

    /**
     * Sets the name of this particular item
     * @param name - new name of the item
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Gets the name of this item
     * @return returns name (String value) of the item
     */
    public String getName() {
        return this._name;
    }

    /**
     * Sets the cost of the item
     * @param cost - new cost of the item
     */
    public void setCost(Double cost) {
        this._cost = cost;
    }

    /**
     * Gets the current cost of the item
     * @return Returns the cost of an item (a Double)
     */
    public Double getCost() {
        return this._cost;
    }


}
