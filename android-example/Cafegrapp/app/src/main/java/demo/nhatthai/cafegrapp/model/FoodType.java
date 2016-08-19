package demo.nhatthai.cafegrapp.model;

import io.realm.RealmObject;

/**
 * Created by nhatthai on 6/29/16.
 */
public class FoodType extends RealmObject{
    public int id;
    public String name;

    public FoodType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
