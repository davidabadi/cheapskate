package inc.cheapskates.cheapskatesapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class resturant_info extends AppCompatActivity {
    List<Resturant> resturantList;
    String lat;
    String lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_info);

        //Getting the list of restaurant and id number from last intent
        String id = getIntent().getExtras().getString("id");
        resturantList = (List<Resturant>) getIntent().getSerializableExtra("restaurantsList");
        //Comparing the id with the list of restaurants to get the specific restaurant
        Resturant restaurant = getResturant(id, resturantList);
        //Setting the text for the specific restaurant
        TextView rest_name = findViewById(R.id.resturantname);
        rest_name.setText(restaurant.getName());
        TextView rest_address = findViewById(R.id.RestaurantAddress);
        rest_address.setText(restaurant.getAddress());
        TextView rest_locality = findViewById(R.id.resturantlocality2);
        rest_locality.setText(restaurant.getLocality());
        TextView rest_rating = findViewById(R.id.resturantrating);
        rest_rating.setText(restaurant.getRating());
        lat = restaurant.getLatitude();
        lon = restaurant.getLongitude();
    }


    //setting up mybudget variable
    private EditText mybudget;

    //sets up gobutton to change budget to EditText input
    public void gobutton4(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class); //change this
        v.findViewById(R.id.gobutton4);
        mybudget = (EditText) findViewById(R.id.upperBudget1);
        if(mybudget.getText().toString().trim().length() == 0){//if statement to prevent crash when nothing is entered for budget
            Intent new_intent = new Intent(this, Restaurant_no_name.class);
            startActivity(new_intent);
            return;
        }
        else if(mybudget.getText().toString().trim().length() > 3){
            PopupWindow();
            return;
        }
        else if(Integer.parseInt(mybudget.getText().toString().trim()) == 0){
            Intent intent19 = new Intent(this, Restaurant_no_name.class);
            startActivity(intent19);
            return;
        }
        setBudget(this, mybudget.getText().toString());
        startActivity(intent);
    }

    //sets up  button to go to maps
    public void MapButton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, MapsActivity.class);//change this
        intent.putExtra("lat",lat);
        intent.putExtra("lon",lon);
        v.findViewById(R.id.MapButton);
        startActivity(intent);
    }

    //setBudget function
    public static void setBudget(Context context, String budget)
    {
        SharedPreferences prefs = context.getSharedPreferences("mybudget", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("budget",budget);
        editor.apply();
    }

    //Searching for a specific restaurant in the list of restaurants
    Resturant getResturant(String id, List<Resturant> resturantList){
        for(int i = 0; i < resturantList.size(); i++){
            if(resturantList.get(i).getId().equals(id)){
                Resturant result = resturantList.get(i);
                return result;
            }
        }

        return null;
    }



    //Setting up the back button to go to list of restaurants activity
    public void back(View v) {
        Button button = (Button) v;
        v.findViewById(R.id.back_button2);
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        intent.putExtra("listResturant", (Serializable) resturantList);
        startActivity(intent);
    }
    Dialog MyDialog;
    Button closebutton;

    //Method for programming the buttons in pop-up
    public void PopupWindow()
    {
        MyDialog = new Dialog(resturant_info.this);
        MyDialog.setContentView(R.layout.popupwindow);


        closebutton = (Button)MyDialog.findViewById(R.id.close);
        closebutton.setEnabled(true);

        //When button is clicked the Dialog closes
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });
        //Have the Diaglog show up
        MyDialog.show();
    }

}
