package app.ewallet;

/**
 * Created by Seth Legaspi on 10/29/2015.
 */
public class ItemOrder {

    private int _buy_transaction_ID;
    private int _item_ID;
    private int _quantity;

    public ItemOrder() {
        //empty constructor
    }

    public ItemOrder(int btID, int itemID, int qty) {
        this._buy_transaction_ID = btID;
        this._item_ID = itemID;
        this._quantity = qty;
    }

    public void setBuyTransID(int id) {
        this._buy_transaction_ID = id;
    }

    public int getBuyTransID() {
        return this._buy_transaction_ID;
    }

    public void setItemID(int id) {
        this._item_ID = id;
    }

    public int getItemID() {
        return this._item_ID;
    }

    public void setQty(int qty) {
        this._quantity = qty;
    }

    public int getQty() {
        return this._quantity;
    }
}
