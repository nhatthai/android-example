package demo.nhatthai.cafegrapp.adapter;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.model.FoodType;


/**
 * Created by nhatthai on 6/29/16.
 */
public class FoodTypeAdapter extends ArrayAdapter<FoodType>{

    private Context mContext;
    private List<FoodType> arrData;

    /*************  CustomAdapter Constructor *****************/
    public FoodTypeAdapter(Context context, int textViewResourceId, List<FoodType> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        arrData = objects;
    }


    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

//        Inflating the layout for the custom Spinner
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.custom, parent, false);

        TextView txtFoodType = (TextView) layout
                .findViewById(R.id.txt_food_type);

        FoodType item = (FoodType) arrData.get(position);

        // Setting the text using the array
        txtFoodType.setText(item.getName());

        // Setting the color of the text
        txtFoodType.setTextColor(Color.rgb(75, 180, 225));

        return layout;
    }

    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // It gets a View that displays the data at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}