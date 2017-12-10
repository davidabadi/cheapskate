package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class resturant_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_info);
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
        setBudget(this, mybudget.getText().toString());
        startActivity(intent);
    }

    //sets up  button to go to maps
    public void MapButton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, MapsActivity.class); //change this
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

}
