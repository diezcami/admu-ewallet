package app.ewallet;

/**
 * Created by Seth Legaspi on 10/29/2015.
 */
public class Stock {

    private int _prim;
    private int _shop_ID;
    private int _item_ID;
    private String _timeStamp;

    public Stock() {
        //empty constructor
    }

    public Stock(int prim, int shopID, int itemID, String timeStamp) {
        this._prim = prim;
        this._shop_ID = shopID;
        this._item_ID = itemID;
        this._timeStamp = timeStamp;
    }

    public void setPrim(int id) {
        this._prim = id;
    }

    public int getPrim() {
        return this._prim;
    }

    public void setShopID(int id) {
        this._shop_ID = id;
    }

    public int getShopID() {
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
