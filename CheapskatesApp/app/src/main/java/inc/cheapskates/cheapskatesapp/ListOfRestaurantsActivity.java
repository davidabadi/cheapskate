package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ListOfRestaurantsActivity extends AppCompatActivity {
    List<Resturant> resturantList;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_restaurants);

        listView = findViewById(R.id.resturantList);

        resturantList = (List<Resturant>) getIntent().getSerializableExtra("listResturant");


        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), resturantList);

        //adding the adapter to listview
        listView.setAdapter(adapter);

    }

    //setting up mybudget variable
    private EditText mybudget;

    //sets up gobutton to change budget to EditText input
    public void gobutton2(View v) {
        Button button = (Button) v;
        Intent intent = new Intent(this, LoadingPage.class); //change this
        v.findViewById(R.id.gobutton2);
        mybudget = (EditText) findViewById(R.id.upperBudget1);
        if (mybudget.getText().toString().trim().length() == 0) {//if statement to prevent crash when nothing is entered for budget
            Intent new_intent = new Intent(this, Restaurant_no_name.class);
            startActivity(new_intent);
            return;
        }
        setBudget(this, mybudget.getText().toString());
        startActivity(intent);
    }

    //setBudget function
    public static void setBudget(Context context, String budget) {
        SharedPreferences prefs = context.getSharedPreferences("mybudget", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("budget", budget);
        editor.apply();
    }
}