package hu.bme.aut.hf.customchat2;

/**
 * Created by Bal�zs on 2015.05.17..
 */
public class Msg {
    public int id;

    public Msg(int id, int cnvId, int senderID, String msg, String geocode, String timestamp) {
        this.id = id;
        this.cnvId = cnvId;
        this.senderID = senderID;
        this.msg = msg;
        this.geocode = geocode;
        this.timestamp = timestamp;
    }

    public int cnvId;
    public int senderID;
    public String msg;
    public String geocode;



    public String timestamp;
}
