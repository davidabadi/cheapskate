package inc.cheapskates.cheapskatesapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Double Latitude;
    private Double Longitude;
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }


    //Asking for permission to use the gps location services and if granted permission get the phones latitude and longitude
    void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location != null){
                Latitude = location.getLatitude();
                Longitude = location.getLongitude();
            }

            else{
                //Hard coded latitude and longitude for testing purposes in the simulator
                Latitude = 42.3505;
                Longitude = -71.1054;
            }
        }
    }


    //If the permission for location is granted ask everything about the location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    //Declare budget edittext
    private EditText mybudget;


    //Function for Button Press
    public void confirmbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, food_categories.class); //change this
        v.findViewById(R.id.confirm);
        //if statement to prevent crash when nothing is entered for budget
        mybudget = (EditText) findViewById(R.id.budget);
        if(mybudget.getText().toString().trim().length() == 0)
        {
            Intent new_intent = new Intent(this, Restaurant_no_name.class);
            startActivity(new_intent);
            return;
        }
        //Stops large numbers from being entered
        else if(mybudget.getText().toString().trim().length() > 3)
        {
            PopupWindow();
        }
        else if(Integer.parseInt(mybudget.getText().toString().trim()) == 0){
            Intent intent6 = new Intent(this, Restaurant_no_name.class);
            startActivity(intent6);
            return;
        }
        else
        {
            setBudget(this,mybudget.getText().toString());
            setLocation(this, Double.toString(Latitude), Double.toString(Longitude));
            startActivity(intent);
        }
    }

    //Function for TEN dollars
    public void fivebutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, food_categories.class);
        v.findViewById(R.id.five);
        setBudget(this, Integer.toString(10));//using setBudget to set budget to 5
        setLocation(this, Double.toString(Latitude), Double.toString(Longitude));
        startActivity(intent);
    }

    //Function for TWENTY FIVE dollars
    public void tenbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, food_categories.class);
        v.findViewById(R.id.ten);
        setBudget(this, Integer.toString(25));//using setBudget to set budget to 15
        setLocation(this, Double.toString(Latitude), Double.toString(Longitude));
        startActivity(intent);
    }

    //Function for FIFTY dollars
    public void fifbutton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, food_categories.class);
        v.findViewById(R.id.fifteen);
        setBudget(this, Integer.toString(50));//using setBudget to set budget to 25
        setLocation(this, Double.toString(Latitude), Double.toString(Longitude));
        startActivity(intent);
    }
    //store budget in SharedPreferences
    public static void setBudget(Context context, String budget)
    {
        SharedPreferences prefs = context.getSharedPreferences("mybudget", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("budget",budget);
        editor.apply();
    }


    //Set the location in shared preferences
    public static void setLocation(Context context, String latitude, String longitude)
    {
        SharedPreferences prefs1 = context.getSharedPreferences("Latitude", 0);
        SharedPreferences.Editor editor1 = prefs1.edit();
        editor1.putString("latitude",latitude);
        editor1.apply();
        SharedPreferences prefs2 = context.getSharedPreferences("Longitude", 0);
        SharedPreferences.Editor editor2 = prefs2.edit();
        editor2.putString("longitude",longitude);
        editor2.apply();
    }

    //Declare Dialog Window
    Dialog MyDialog;
    Button closebutton;

    //Method for programming the buttons in pop-up
    public void PopupWindow()
    {
        MyDialog = new Dialog(HomeActivity.this);
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