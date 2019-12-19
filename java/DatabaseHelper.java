package cs.ucmo.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "NAME";
    public static final String COL2 = "TIME";
    public static final String COL3 = "CLICKS";
    public static final String COL4 = "SCORE";

    Context tableContext;


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        tableContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Name TEXT, Time TEXT, Clicks TEXT, Score DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public boolean addData(String name, String time, String clicks, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, time);
        contentValues.put(COL3, clicks);
        contentValues.put(COL4, Double.parseDouble(score));




        long result = db.insert(TABLE_NAME, null, contentValues);

        System.out.println("Result is ..............." + result);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        System.out.println("Getting the sorted DATA");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.query(TABLE_NAME, null,null,null,null,null,COL4 + " DESC");
        return data;
    }

    public void deleteDatabase(){
        tableContext.deleteDatabase(DATABASE_NAME);
    }
}
