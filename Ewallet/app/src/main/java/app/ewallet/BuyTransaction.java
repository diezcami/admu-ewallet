package app.ewallet;

/**
 * Created by Seth Legaspi on 10/29/2015.
 */
public class BuyTransaction {
    private int _transaction_id;
    private String _timeStamp;
    private int _id_number;
    private int _shop_terminal_id;



    public BuyTransaction() {
        //Empty Constructor
    }

    public BuyTransaction(int transID, String timeStamp, int idNum, int terminalID) {
        this._transaction_id = transID;
        this._timeStamp = timeStamp;
        this._id_number = idNum;
        this._shop_terminal_id = terminalID;
    }

    public void setTransID(int id) {
        this._transaction_id = id;
    }

    public int getTransID() {
        return this._transaction_id;
    }

    public void setTimeStamp(String ts) {
        this._timeStamp = ts;
    }

    public String getTimeStamp() {
        return this._timeStamp;
    }

    public void setIDNum(int id) {
        this._id_number = id;
    }

    public int getIDNum() {
        return this._id_number;
    }

    public void setShopID(int id) {
        this._shop_terminal_id = id;
    }

    public int getShopID() {
        return this._shop_terminal_id;
    }
}
