package app.ewallet;

/**
 * Created by Seth Legaspi on 10/29/2015.
 */
public class Stock {

    private int _prim;
    private String _shop_ID;
    private int _item_ID;
    private String _timeStamp;
    private int _qty;

    public Stock() {
        //empty constructor
    }

    public Stock(int prim, String shopID, int itemID, String timeStamp, int qty) {
        this._prim = prim;
        this._shop_ID = shopID;
        this._item_ID = itemID;
        this._timeStamp = timeStamp;
        this._qty = qty;
    }

    public void setQty(int qty) {
        this._qty = qty;
    }

    public int getQty() {
        return this._qty;
    }

    public void setPrim(int id) {
        this._prim = id;
    }

    public int getPrim() {
        return this._prim;
    }

    public void setShopID(String id) {
        this._shop_ID = id;
    }

    public String getShopID() {
        return this._shop_ID;
    }

    public void setItemID(int id) {
        this._item_ID = id;
    }

    public int getItemID() {
        return this._item_ID;
    }

    public void setTimeStamp(String ts) {
        this._timeStamp = ts;
    }

    public String getTimeStamp() {
        return this._timeStamp;
    }
}
