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
    private static int SPLASH_TIME_OUT = 4000;

    List<Resturant> resturantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);


        TextView budget_view = findViewById(R.id.budview);
        budget_view.setText("$" + getBudget(this));
        resturantList = new ArrayList<>();

        //Displays the code in a TextView - For TESTING PURPOSES ONLY
        //TextView latitude_view = findViewById(R.id.latview);
        //latitude_view.setText(getLatitude(this));

        //TextView longitude_view = findViewById(R.id.lonview);
        //longitude_view.setText(getLongitude(this));

        //stores longitude and latitude
        String longitude = getLongitude(this);
        String latitude = getLatitude(this);
        String cuisine = getCuisine(this);
        //Writes URL in string form
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=" + latitude + "&lon=" +longitude + "&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3698998&lon=-71.072686&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3505&lon=-71.1054&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3505&lon=-71.1054&radius=2000&cuisines=1&sort=real_distance&order=asc";
        String request_url = "https://developers.zomato.com/api/v2.1/search?lat="+ latitude + "&lon="+ longitude + "&radius=2000&cuisines=" + cuisine + "&sort=real_distance&order=asc";

        //Just makes the loading page stay for 4 seconds
//        if (Integer.parseInt(getBudget(this)) == 0) {
//            Intent homeIntent = new Intent(LoadingPage.this, Restaurant_no_name.class);
//            startActivity(homeIntent);
//            finish();
//        } else
//            {
//                //Executes the API call
//                new JSONTask().execute(request_url);
//            }

        getResponse(longitude, longitude);
    }


    private void getResponse(String latitude, String longitude){
        RequestQueue queue = Volley.newRequestQueue(this);
        longitude = getLongitude(this);
        latitude = getLatitude(this);
        String cuisine = getCuisine(this);
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=" + latitude + "&lon=" +longitude + "&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3698998&lon=-71.072686&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3505&lon=-71.1054&radius=2000&sort=real_distance&order=asc";
        //String request_url = "https://developers.zomato.com/api/v2.1/search?lat=42.3505&lon=-71.1054&radius=2000&cuisines=1&sort=real_distance&order=asc";
        String request_url = "https://developers.zomato.com/api/v2.1/search?lat="+ latitude + "&lon="+ longitude + "&radius=2000&cuisines=" + cuisine + "&sort=real_distance&order=asc";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, request_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());

                        try {

//                            JSONObject obj = new JSONObject(response);

                            JSONArray restaurantsArray = response.getJSONArray("restaurants");


                            for (int i = 0; i < restaurantsArray.length(); i++) {

                                JSONObject restObject = restaurantsArray.getJSONObject(i);
                                JSONObject jsonObjectRest = restObject.getJSONObject("restaurant");

                                if(compareBudget(jsonObjectRest)) {
                                    Resturant resturant = new Resturant();
                                    jsonObjectRest.getString("name");
                                    resturant.setId(jsonObjectRest.getString("id"));
                                    resturant.setName(jsonObjectRest.getString("name"));
                                    resturant.setLatitude(jsonObjectRest.getJSONObject("location").getString("latitude"));
                                    resturant.setLongitude(jsonObjectRest.getJSONObject("location").getString("longitude"));
                                    resturant.setAddress(jsonObjectRest.getJSONObject("location").getString("address"));
                                    resturant.setRating(jsonObjectRest.getJSONObject("user_rating").getString("rating_text"));
                                    resturant.setRating(jsonObjectRest.getJSONObject("location").getString("locality"));

                                    resturantList.add(resturant);
                                }
                            }


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


    //Makes the API Call run in the background
    /*private class JSONTask extends AsyncTask<String, JSONObject, JSONObject>
    {
        //Shows the Progress Bar before the background processing is done
        protected void onPreExecute()
        {
            ProgressBar progressBar;
            progressBar = (ProgressBar)findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        //Processes background stuffs
        protected JSONObject doInBackground(String... request_url)
        {
            //Establishes a URL Connection here
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            JSONObject jsonObject = null;
            TextView text = findViewById(R.id.testcase); //testcase
            try
            {
                URL url = new URL(request_url[0]);
                connection = (HttpURLConnection) url.openConnection();
//                7620ea9ddb627ea338e82100eeeabb02

//                curl -X GET --header "Accept: application/json" --header "user-key: 7620ea9ddb627ea338e82100eeeabb02" "https://developers.zomato.com/api/v2.1/search?lat=26.8497&lon=75.7692&radius=200"
                connection.setRequestProperty("user-key","7620ea9ddb627ea338e82100eeeabb02");
                connection.setRequestProperty("Accept","application/json");
//                connection.setRequestProperty("x-api-key","fa221c30c201daf8380ac435cedfebe9");
                connection.connect();

                //Stores the data from url into a stream
                InputStream stream = connection.getInputStream(); //stores info into a stream object
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder stringBuilder = new StringBuilder();


                String currentLine;
                try {
                    while ((currentLine = streamReader.readLine()) != null) {
                        stringBuilder.append(currentLine);
                    }
                    JSONTokener jsonTokener = new JSONTokener(stringBuilder.toString());
                    jsonObject = new JSONObject(jsonTokener);
                    if(jsonObject == null)
                    {
                        text.setText("WINNNNNNNNNN");
                    }

                    String restaurantName;
                    //JsonElement restaurantJSONElement = null;
                    JsonPrimitive restaurantJSONPrimivite;
                    JsonObject jsonObject1;
                    JsonParser parser = new JsonParser();
                    JsonArray restaurantsJSONArray;
                    List<String> restaurantnamelist = new ArrayList<String>();


                    if(jsonObject == null)
                    {
                        text.setText("KIJJKJHKJJJ");
                    }
                    try {
                        if(jsonObject == null) {
                            text.setText("FAILS!!!!");
                        }
                        else
                        {
                            restaurantsJSONArray =  (JsonArray) jsonObject.get("restaurant");
                            text.setText("WE WON!!!!");
                        }

                        //for(int i = 0; i < restaurantsJSONArray.size(); i++) {
                        //gets the restaurant object
                        //jsonObject1 = ((JsonObject) restaurantsJSONArray.get(0));
                        //JsonElement restaurantJSONElement = jsonObject1.get("name");
                        //gets the name of the restaurant from the restaurant object
                /*restaurantJSONPrimivite = ((JsonObject) restaurantJSONElement.getAsJsonObject()).getAsJsonPrimitive("name");
                //gets the name of the restaurant as a string
                restaurantName = restaurantJSONPrimivite.getAsString();
                //adds the name of the restaurant to the list of restaurant
                restaurantnamelist.add(restaurantName);/
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                catch (IOException error)
                {
                    error.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            } finally
            {
                if(connection != null)
                {
                    connection.disconnect();
                }
            }


            return jsonObject;
        }

        //Move on to Food Categories Page when processing is done
        protected void onPostExecute(JSONObject result)
        {
            super.onPostExecute(result);
            Intent intent = new Intent(LoadingPage.this, food_categories.class);
            startActivity(intent);
            finish();
        }

    }*/

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

/*enum OffersOrder { BEST("BEST"),GOLD("GOLD"),RISK("RISK"),STANDARD("STANDARD"),PUBLIC("PUBLIC");
    String sound;
    OffersOrder(String s) { sound = s; }
}

    List<food_categories.OffersOrder> l = new ArrayList<>();

// code to populate your list
...

// sorting
        java.util.Collections.sort(l, new Comparator<OffersOrder>(){
@Override
public int compare(OffersOrder o1, OffersOrder o2) {

        if(OffersOrder o1 < o2)
        // this method should return a negative value if o1 < o2
        // this method should return 0 if o1 == o2
        // this method should return a positive value if o1 > o2
        return 0;
        }
        });*/