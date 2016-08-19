package demo.nhatthai.cafegrapp.model;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.realm.RealmObject;

/**
 * Created by nhatthai on 6/20/16.
 */
public class User extends RealmObject {
    public String username;
    public int id;
    private String first_name;
    private String last_name;
    private String email;
    private String avatar_url;
    private String city;
    private String name;
    private String location;
    public Address address;
    private int following;
    private int followers;
    private int recipes;

    public User(int id, String name, String username) {
        this.username = username;
        this.name = name;
        this.id = id;
    }

    public User(int id, String first_name, String last_name, String email, String city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.city = city;
    }

    public String getUserName() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public int getId() {
        return this.id;
    }

    public String getLocation() {
        return location;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }

    public int getRecipes() {
        return recipes;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public Object getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }


    /**
     *
     * @return
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject)jsonParser.parse(this.toJson()).getAsJsonObject();
        return jsonObj;
    }
}
