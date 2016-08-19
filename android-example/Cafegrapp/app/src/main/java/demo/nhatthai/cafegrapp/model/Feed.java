package demo.nhatthai.cafegrapp.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.realm.RealmObject;

/**
 * Created by nhatthai on 5/12/16.
 */
public class Feed extends RealmObject {
    private int id;
    private String username;
    private int userId;
    private String imgFeedUrl;
    private String imgUserUrl;
    private String name;
    private String foodType;
    private String rating;
    private int time;
    private boolean isLike;
    private int food_type_id;

    public FoodType food_type;
    public FeedImage feed_mage;
    public User user;

    public Feed(String name, String imgFeedUrl, String username, boolean isLike) {
        this.username = username;
        this.imgFeedUrl = imgFeedUrl;
        this.name = name;
        this.isLike = isLike;
    }

    public Feed(int id, String name, String imgFeedUrl, String username, String foodType,
                String rating, int time) {
        this.id = id;
        this.username = username;
        this.imgFeedUrl = imgFeedUrl;
        this.name = name;
        this.foodType = foodType;
        this.time = time;
        this.rating = rating;
    }

    public Feed(int id, String name, String rating, int time, int foodTypeId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.rating = rating;
        this.food_type_id = foodTypeId;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getLike() {
        return isLike;
    }

    public String getImageUrl() {
        return imgFeedUrl;
    }

    public void setImageUrl(String imgFeedUrl) {
        this.imgFeedUrl = imgFeedUrl;
    }

    public String getImgUserUrl() {
        return imgUserUrl;
    }

    public void setImgUserUrl(String imgUserUrl) {
        this.imgUserUrl = imgUserUrl;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public String getRating() {
        return rating;
    }

    public int getFoodTypeId() {
        return this.food_type_id;
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
