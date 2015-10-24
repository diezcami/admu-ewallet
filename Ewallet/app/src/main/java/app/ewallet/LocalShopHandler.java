package app.ewallet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class LocalShopHandler extends SQLiteOpenHelper {


    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "LocalDB_Shop";

    //Table name
    private static final String TABLE_SHOP = "ShopItems";

    //Students column names
    private static final String KEY_ITEM_ID = "Item_ID"; //1st column
    private static final String KEY_NAME = "Name"; //2nd column
    private static final String KEY_COST = "Cost"; //3rd column

    public LocalShopHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the Table for the database
     * @param db = the local Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SHOP + "(" +
                KEY_ITEM_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
                KEY_COST + " NUM" + ")";

        db.execSQL(CREATE_TABLE);
    }

    /**
     * Updates the database if ever
     * @param db - The database
     * @param i - previous version number
     * @param i1 - new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);
        onCreate(db);
    }

    /**
     * Inserts new row with new item into the database Shop Table
     * @param item - The item object
     */
    public void addItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, item.getID());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_COST, item.getCost());

        db.insert(TABLE_SHOP, null, values);
        db.close();
    }

    /**
     * Gets a particular row from the Database and inserts it into an "Item" object
     * @param id - primary key of the item you want (The Item_ID)
     * @return - returns the Item object
     */
    public Item getItem(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHOP, new String[]{KEY_ITEM_ID, KEY_NAME,
                        KEY_COST}, KEY_ITEM_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        item.setID(Integer.parseInt(cursor.getString(0)));
        item.setName(cursor.getString(1));
        item.setCost(Double.parseDouble(cursor.getString(2)));

        db.close();
        return item;
    }

    /**
     * Check if a particular item exists
     * @param id - ITEM_ID (Primary key of the item)
     * @return - Returns true or false depending on if the item exists or not
     */
    public boolean checkExist(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * from ShopItems where Item_ID = " + id;

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
}
