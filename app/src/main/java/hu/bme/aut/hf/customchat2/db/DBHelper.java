package hu.bme.aut.hf.customchat2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Balázs on 2015.05.15..
 */
public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context, String name) {
        super(context, name, null, DBConsts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConsts.DATABASE_CREATE_ALL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConsts.DATABASE_DROP_ALL);
        db.execSQL(DBConsts.DATABASE_CREATE_ALL);
    }
}
