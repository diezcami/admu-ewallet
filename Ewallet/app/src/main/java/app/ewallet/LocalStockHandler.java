package app.ewallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Shows items at a certain shop terminal | Inventory
 * Created by Seth Legaspi on 10/29/2015.
 */
public class LocalStockHandler extends SQLiteOpenHelper {

    private int PRIMARY_KEY = 10;
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "LocalDB_EWallet";

    //Table name
    private static final String TABLE_STOCK = "stock";

    //Students column names
    private static final String KEY_PRIM = "Primary_id";
    private static final String KEY_ID_SHOPTERMINAL = "Shop_Terminal_ID"; //1st column
    private static final String KEY_ID_ITEM = "Item_ID";
    private static final String KEY_TS_STOCK = "stock_ts";
    private static final String KEY_QUANTIY ="quantity";

    public LocalStockHandler(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_STOCK + "(" +
                KEY_PRIM + " INTEGER PRIMARY KEY," +
                KEY_ID_SHOPTERMINAL + " INT," +
                KEY_ID_ITEM + " INT," +
                KEY_TS_STOCK + " DATETIME," +
                KEY_QUANTIY + " INT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public int generatePrimaryKey()
    {
        return PRIMARY_KEY++;
    }
    public int getPrimaryKey()
    {
        return PRIMARY_KEY;
    }

    public void addStock(Stock stock) {
        SQLiteDatabase db = getWritableDatabase();

        //onCreate(db);

        ContentValues values = new ContentValues();
        values.put(KEY_PRIM, stock.getPrim());
        values.put(KEY_ID_SHOPTERMINAL, stock.getShopID()); //1st col
        values.put(KEY_ID_ITEM, stock.getItemID()); //2nd col
        values.put(KEY_TS_STOCK, stock.getTimeStamp()); //3rd col
        values.put(KEY_QUANTIY, stock.getQty());

        db.insert(TABLE_STOCK, null, values);
        db.close();
    }

    public Stock getStock(int id) {
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.query(TABLE_STOCK, new String[]{KEY_PRIM, KEY_ID_SHOPTERMINAL, KEY_ID_ITEM,
                        KEY_TS_STOCK, KEY_QUANTIY}, KEY_PRIM + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Stock stock = new Stock();
        stock.setPrim(Integer.parseInt(cursor.getString(0)));
        stock.setShopID(cursor.getString(1));
        stock.setItemID(Integer.parseInt(cursor.getString(2)));
        stock.setTimeStamp(cursor.getString(3));
        stock.setQty(Integer.parseInt(cursor.getString(4)));

        db.close();
        return stock;
    }

    public Stock getStockViaItem(int itemID) {
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.query(TABLE_STOCK, new String[]{KEY_PRIM, KEY_ID_SHOPTERMINAL, KEY_ID_ITEM,
                        KEY_TS_STOCK, KEY_QUANTIY}, KEY_ID_ITEM + "=?",
                new String[]{String.valueOf(itemID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Stock stock = new Stock();
        stock.setPrim(Integer.parseInt(cursor.getString(0)));
        stock.setShopID(cursor.getString(1));
        stock.setItemID(Integer.parseInt(cursor.getString(2)));
        stock.setTimeStamp(cursor.getString(3));
        stock.setQty(Integer.parseInt(cursor.getString(4)));

        db.close();
        return stock;
    }

    public boolean checkExist(int ID) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * from " + TABLE_STOCK + " where " + KEY_PRIM + " = " + ID;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() < 1) {
                db.close();
                cursor.close();
                return false;
            } else
                db.close();
            cursor.close();
            return true;
        } catch (Exception e) {
            db.close();
            return false;
        }
    }

    public boolean checkItemExist(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * from " + TABLE_STOCK + " where " + KEY_ID_ITEM + " = " + id;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() < 1) {
                db.close();
                cursor.close();
                return false;
            } else
                db.close();
            cursor.close();
            return true;
        } catch (Exception e) {
            db.close();
            return false;
        }
    }

    public void update(int itemId, int newQty) {
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);

        String query = "UPDATE " + TABLE_STOCK + " SET " + KEY_QUANTIY + "=" + newQty + " WHERE " + KEY_ID_SHOPTERMINAL + "=" + itemId;
        db.rawQuery(query, null);
        db.close();
    }

    public void drop() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
        onCreate(db);
        db.close();
    }
}
