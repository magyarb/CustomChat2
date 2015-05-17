package hu.bme.aut.hf.customchat2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Balázs on 2015.05.17..
 */
public class DBLoader { //TODO MSG, CV
    private Context ctx;
    private DBHelper dbHelper;
    protected SQLiteDatabase mDb;

    public DBLoader(Context ctx) {
        this.ctx = ctx;
    }

    public void open() throws SQLException {
        // DatabaseHelper objektum
        dbHelper = new DBHelper(
                ctx, DBConsts.DATABASE_NAME);
        // adatbázis objektum
        mDb = dbHelper.getWritableDatabase();
        // ha nincs még séma, akkor létrehozzuk
        dbHelper.onCreate(mDb);
    }

    public void close(){
        dbHelper.close();
    }

    public long createUser(String name, String pw){
        ContentValues v = new ContentValues();
        v.put(DBConsts.Users.KEY_NAME, name);
        v.put(DBConsts.Users.KEY_PW, pw);
        return mDb.insert(DBConsts.Users.DATABASE_TABLE, null, v);
    }

    public Cursor getAllUsers()
    {
        return mDb.query(
                DBConsts.Users.DATABASE_TABLE,
                new String[]{
                        DBConsts.Users.KEY_ROWID,
                        DBConsts.Users.KEY_NAME,
                        DBConsts.Users.KEY_PW}, null, null, null, null, DBConsts.Users.KEY_NAME
        );
    }

    public String getUserName(long uid)
    {
        Cursor c = mDb.query(
                DBConsts.Users.DATABASE_TABLE,
                new String[]{
                        DBConsts.Users.KEY_ROWID,
                        DBConsts.Users.KEY_NAME,
                        DBConsts.Users.KEY_PW},
                DBConsts.Users.KEY_ROWID + "=" + uid, null,null,null,DBConsts.Users.KEY_NAME
        );
        if (c.moveToFirst())
            return c.getString(c.getColumnIndex(DBConsts.Users.KEY_NAME));
        else return null;
    }
    // CRUD és egyéb metódusok
}
