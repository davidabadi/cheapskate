package inc.cheapskates.cheapskatesapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.PopupWindow;

import java.io.Serializable;
import java.util.List;

public class ListOfRestaurantsActivity extends AppCompatActivity {
    List<Resturant> resturantList;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_restaurants);
        listView = findViewById(R.id.resturantList);


        //Get restaurant list from last intent
        resturantList = (List<Resturant>) getIntent().getSerializableExtra("listResturant");

        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), resturantList);

        //adding the adapter to listview
        listView.setAdapter(adapter);

        //Getting intent from ListView and passing the id for the clicked restaurant
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListOfRestaurantsActivity.this,resturant_info.class).putExtra("id",resturantList.get(position).getId());
                intent.putExtra("restaurantsList",(Serializable)resturantList );
                startActivity(intent);
            }
        });
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
        else if(mybudget.getText().toString().trim().length() > 3){
            PopupWindow();
            return;
        }
        else if(Integer.parseInt(mybudget.getText().toString().trim()) == 0){
            Intent intent12 = new Intent(this, Restaurant_no_name.class);
            startActivity(intent12);
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

    //Set the backbutton to the categories activity
    public void backbutton(View v) {
        Button button = (Button) v;
        Intent intent = new Intent(this, food_categories.class);
        v.findViewById(R.id.back_button);
        startActivity(intent);
    }

    Dialog MyDialog;
    Button closebutton;

    //Method for programming the buttons in pop-up
    public void PopupWindow()
    {
        MyDialog = new Dialog(ListOfRestaurantsActivity.this);
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
