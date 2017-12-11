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


    private int cuisine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_categories);
    }

    //setting up mybudget variable
    private EditText mybudget;
    //created a string to store the category chosen
    String category;


    //sets up and pass the cuisine id according to zomato
    public void americansbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 1;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void bakerybutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 5;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void beveragesbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 270;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void breakfastbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 182;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void burgerbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 168;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void chinesebutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 25;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void dessertbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 100;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void fastfoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 40;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void frenchbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 45;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void icecreambutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 233;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void indianbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 148;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void italianbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 55;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void japanesebutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 60;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void koreanbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 67;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void mediterraneanbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 70;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void mexicanbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 73;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void pizzabutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 82;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void seafoodbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 83;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void southernbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 471;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void thaibutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 95;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //sets up and pass the cuisine id according to zomato
    public void vegetarianbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class);
        v.findViewById(R.id.confirm);
        cuisine = 308;
        setCuisine(this, Integer.toString(cuisine));
        startActivity(intent);
    }

    //the cuisine id is set up in shared preferences
    public static void setCuisine(Context context, String cuisine)
    {
        SharedPreferences prefs5 = context.getSharedPreferences("Cuisine", 0);
        SharedPreferences.Editor editor5 = prefs5.edit();
        editor5.putString("cuisine",cuisine);
        editor5.apply();
    }

    //Go back to the home page
    public void homebutton(View v) {
        Button button = (Button) v;
        Intent intent = new Intent(this, HomeActivity.class);
        v.findViewById(R.id.home_button);
        startActivity(intent);
    }
}
