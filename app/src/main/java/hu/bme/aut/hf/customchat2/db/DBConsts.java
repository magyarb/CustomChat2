package hu.bme.aut.hf.customchat2.db;

/**
 * Created by Balázs on 2015.05.17..
 */
public class DBConsts {
    // Broadcast Action, amely az adatbazis modosulasat jelzi
    public static final String ACTION_DATABASE_CHANGED = "hu.bute.daai.amorg.examples.DATABASE_CHANGED";  //TODO megcsinálni

    // fajlnev, amiben az adatbazis lesz
    public static final String DATABASE_NAME = "data.db";
    // verzioszam
    public static final int DATABASE_VERSION = 1;
    // osszes belso osztaly DATABASE_CREATE szkriptje osszefuzve
    public static String DATABASE_CREATE_ALL = Users.DATABASE_CREATE + Cons.DATABASE_CREATE + Cons.DATABASE_CREATE;
    // osszes belso osztaly DATABASE_DROP szkriptje osszefuzve
    public static String DATABASE_DROP_ALL = Users.DATABASE_DROP + Cons.DATABASE_DROP + Msgs.DATABASE_DROP;

    public static class Users{
        // tabla neve
        public static final String DATABASE_TABLE = "users";
        // oszlopnevek
        public static final String KEY_ROWID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_PW = "password";
        // sema letrehozo szkript
        public static final String DATABASE_CREATE =
                "create table if not exists "+DATABASE_TABLE+" ( "
                        + KEY_ROWID +" integer primary key autoincrement, "
                        + KEY_NAME + " text not null, "
                        + KEY_PW + " text not null "
                        + "); ";
        // sema torlo szkript
        public static final String DATABASE_DROP =
                "drop table if exists " + DATABASE_TABLE + "; ";
    }

    public static class Cons{
        // tabla neve
        public static final String DATABASE_TABLE = "cons";
        // oszlopnevek
        public static final String KEY_ROWID = "_id";
        public static final String KEY_USER1 = "u1";
        public static final String KEY_USER2 = "u22";
        // sema letrehozo szkript
        public static final String DATABASE_CREATE =
                "create table if not exists "+DATABASE_TABLE+" ( "
                        + KEY_ROWID +" integer primary key autoincrement, "
                        + KEY_USER1 + " integer not null, "
                        + KEY_USER2 + " integer not null "
                        + "); ";
        // sema torlo szkript
        public static final String DATABASE_DROP =
                "drop table if exists " + DATABASE_TABLE + "; ";
    }

    public static class Msgs{
        // tabla neve
        public static final String DATABASE_TABLE = "msgs";
        // oszlopnevek
        public static final String KEY_ROWID = "_id";
        public static final String KEY_CONVERSATION_ID = "conid";
        public static final String KEY_MESSAGE = "msg";
        public static final String KEY_GEOCODE = "gc";
        public static final String KEY_TIMESTAMP = "ts";
        // sema letrehozo szkript
        public static final String DATABASE_CREATE =
                "create table if not exists "+DATABASE_TABLE+" ( "
                        + KEY_ROWID +" integer primary key autoincrement, "
                        + KEY_CONVERSATION_ID + " integer not null, "
                        + KEY_MESSAGE + " text, "
                        + KEY_GEOCODE + " text, "
                        + KEY_TIMESTAMP + " text not null "
                        + "); ";
        // sema torlo szkript
        public static final String DATABASE_DROP =
                "drop table if exists " + DATABASE_TABLE + "; ";
    }
}
