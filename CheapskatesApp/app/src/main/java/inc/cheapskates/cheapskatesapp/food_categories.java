package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Objects;

public class food_categories extends AppCompatActivity {

//<<<<<<< HEAD

//=======
//>>>>>>> 01bdb969b6590e70bd6bd8b573188bc9ddfe7103
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_categories);
    }

    //setting up mybudget variable
    private EditText mybudget;
//created a string to store the category chosen

    String category;
    //sets up the go button to read from the text box and change budget
    public void gobutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class); //change this
        v.findViewById(R.id.gobutton1);
        mybudget = (EditText) findViewById(R.id.yourbudgettag2);
        if(mybudget.getText().toString().trim().length() == 0){//if statement to prevent crash when nothing is entered for budget
            Intent new_intent = new Intent(this, Restaurant_no_name.class);
            startActivity(new_intent);
            return;
        }
        setBudget(this, mybudget.getText().toString());
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

    public void bagelsbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void pizzabutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void chinesefoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void tacosbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void burgerbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void indianfoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void sandwichesbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void thaifoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void donutsbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void italianfoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
    }

    public void japanesefoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
        category = "japanese";
    }

    public void seafoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ListOfRestaurantsActivity.class);
        v.findViewById(R.id.confirm);
        startActivity(intent);
        category = "seafood";
    }

/*    enum OffersOrder {("cuisines":"seafood");
        String sound;
        OffersOrder(String s) { sound = s; }
    }

    List<OffersOrder> l = new ArrayList<>();

// code to populate your list
...

// sorting

        java.util.Collections.sort(l, new Comparator<OffersOrder>(){
        @Override
        public int compare(OffersOrder o1, category) {


            if(Objects.equals(category, o1.get("cuisines"))) //compares the category selected to the
        {
            //List<Objects>
        }

            // this method should return a negative value if o1 < o2
            // this method should return 0 if o1 == o2
            // this method should return a positive value if o1 > o2
            return 0;
        }
    });


    if Jsonstring

       /*BagelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadListOfRestaurants = new Intent(food_categories.this, ListOfRestaurantsActivity.class);
                startActivity(intentLoadListOfRestaurants);
            }
        });*/


//This sorts the JSON string by name
    /*String myJSONstring;
    String restaurantName;
    JsonElement restaurantJSONElement;
    JsonPrimitive restaurantJSONPrimitive;

    JsonParser parser = new JsonParser();
    // myJSONstring is the raw JSON data
    JsonElement parentJSONElement = parser.parse(myJSONstring);
    JsonObject parentJSONObject = parentJSONElement.getAsJsonObject();
    JsonArray restaurantsJSONArray = (JsonArray) parentJSONObject.get("restaurants");
//for(int i=0;i<restaurantsJSONArray.size(); i++)  //for some reason size was undeclared dont know why
    int i = 0;
    for(i = 0; i < 20; i++)
    {
        //get the element
        restaurantJSONElement = ((JsonObject)restaurantsJSONArray.get(i)).get("restaurant");
        //get the restaurant name
        restaurantJSONPrimitive = ((JsonObject)restaurantJSONElement.getAsJsonObject()).getAsJsonPrimitive("name");
        restaurantName = restaurantJSONPrimitive.getAsString();
    }*/


}
