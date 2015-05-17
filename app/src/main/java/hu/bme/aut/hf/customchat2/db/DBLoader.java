package hu.bme.aut.hf.customchat2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import hu.bme.aut.hf.customchat2.Cnv;
import hu.bme.aut.hf.customchat2.Msg;
import hu.bme.aut.hf.customchat2.Session;
import hu.bme.aut.hf.customchat2.User;

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

    public User getUserByName(String name)
    {
        Cursor c = mDb.query(
                DBConsts.Users.DATABASE_TABLE,
                new String[]{
                        DBConsts.Users.KEY_ROWID,
                        DBConsts.Users.KEY_NAME,
                        DBConsts.Users.KEY_PW},
                DBConsts.Users.KEY_NAME + " like '" + name+"'", null,null,null,DBConsts.Users.KEY_NAME
        );
        if (c.moveToFirst())
            return new User(Integer.parseInt(c.getString(c.getColumnIndex(DBConsts.Users.KEY_ROWID))),c.getString(c.getColumnIndex(DBConsts.Users.KEY_NAME)),c.getString(c.getColumnIndex(DBConsts.Users.KEY_PW)));
        else return null;
    }

    public void reloadCnvCache(int userid) //TODO DEBUG STATE
    {

        Session.cnvCache = new ArrayList<>();
        Session.cnvCache.add(new Cnv(4, 1, 2));
        Session.cnvCache.add(new Cnv(5, 2, 1));
        Session.cnvCache.add(new Cnv(6, 1, 1));
        Session.cnvCache.add(new Cnv(7, 2, 2));
    }
    public void reloadMsgCache(int cnvid) //TODO DEBUG STATE
    {

        Session.msgCache = new ArrayList<>();
        Session.msgCache.add(new Msg(1,1,1,"message1","geocode1","timestamp1"));
        Session.msgCache.add(new Msg(2,1,2,"message1","geocode2","timestamp2"));
        Session.msgCache.add(new Msg(1,1,1,"message3","geocode3","timestamp3"));
        Session.msgCache.add(new Msg(4,2,4,"message4","geocode4","timestamp4"));
        Session.msgCache.add(new Msg(5,1,5,"message5","geocode5","timestamp5"));
    }

    // CRUD és egyéb metódusok
}
