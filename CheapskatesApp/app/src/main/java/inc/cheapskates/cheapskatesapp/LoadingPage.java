package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadingPage extends AppCompatActivity {
    List<Resturant> resturantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);


        TextView budget_view = findViewById(R.id.budview);
        budget_view.setText("$" + getBudget(this));
        resturantList = new ArrayList<>();


        //stores longitude and latitude
        String longitude = getLongitude(this);
        String latitude = getLatitude(this);
        getResponse(latitude, longitude);
    }

    //JSON parsing and establishing connection to zomato
    private void getResponse(String latitude, String longitude){
        RequestQueue queue = Volley.newRequestQueue(this);
        String cuisine = getCuisine(this);
        String request_url = "https://developers.zomato.com/api/v2.1/search?lat="+ latitude + "&lon="+ longitude + "&radius=2000&cuisines=" + cuisine + "&sort=real_distance&order=asc";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, request_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());

                        try {
                            JSONArray restaurantsArray = response.getJSONArray("restaurants");

                            for (int i = 0; i < restaurantsArray.length(); i++) {

                                JSONObject restObject = restaurantsArray.getJSONObject(i);
                                JSONObject jsonObjectRest = restObject.getJSONObject("restaurant");

                                //Add restaurant object if the entered budget is above the restaurant's price range
                                if(compareBudget(jsonObjectRest)) {
                                    Resturant resturant = new Resturant();
                                    jsonObjectRest.getString("name");
                                    resturant.setId(jsonObjectRest.getString("id"));
                                    resturant.setName(jsonObjectRest.getString("name"));
                                    resturant.setLatitude(jsonObjectRest.getJSONObject("location").getString("latitude"));
                                    resturant.setLongitude(jsonObjectRest.getJSONObject("location").getString("longitude"));
                                    resturant.setAddress(jsonObjectRest.getJSONObject("location").getString("address"));
                                    resturant.setRating(jsonObjectRest.getJSONObject("user_rating").getString("rating_text"));
                                    resturant.setLocality(jsonObjectRest.getJSONObject("location").getString("locality"));

                                    resturantList.add(resturant);
                                }
                            }

                            //Creates an intent to display the restaurants and pass the list of restaurants as an extra
                            Intent intent = new Intent(LoadingPage.this, ListOfRestaurantsActivity.class);
                            intent.putExtra("listResturant", (Serializable) resturantList);
                            startActivity(intent);
                            Log.e("---obj",resturantList.size()+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user-key", "32c01c73d88b8b51ea88964a14563e61");
                params.put("Accept", "application/json");

                return params;
            }
        };
        queue.add(postRequest);
    }

    //Compares the entered budget with the restaurant's price range from zomato
    boolean compareBudget(JSONObject rest) throws JSONException {
        boolean result =  false;
        int range = 0;
        int budget = Integer.parseInt(getBudget(this));
        if(budget <= 10){
            range = 1;
        }else if(budget > 10  && budget <= 25){
            range = 2;
        }else if(budget > 25 && budget <= 50){
            range = 3;
        }else if(budget > 50){
            range = 4;
        }

        if(rest.getInt("price_range") <= range) {
            result = true;
        }

        return result;
    }

    //Finds the budget and retrieve from SharedPreferences
    public static String getBudget(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("mybudget",0);
        return prefs.getString("budget","");
    }

    //Finds the Latitude and retrieve from SharedPreferences
    public static String getLatitude(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("Latitude",0);
        return prefs.getString("latitude","");
    }

    //Finds the Longitude and retrieve from SharedPreferences
    public static String getLongitude(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("Longitude",0);
        return prefs.getString("longitude","");
    }

    //Finds the Cuisine id from SharedPreferences
    public static String getCuisine(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("Cuisine",0);
        return prefs.getString("cuisine","");
    }

}