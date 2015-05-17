package hu.bme.aut.hf.customchat2;

/**
 * Created by Balázs on 2015.05.17..
 */
public class User {
    public User(int id, String name, String hashedPw) {
        this.id = id;
        this.name = name;
        this.hashedPw = hashedPw;
    }

    public int id;
    public String name;
    public String hashedPw;
}
