package inc.cheapskates.cheapskatesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Restaurant_no_name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_no_name);
    }

    //yesbutton command
    public void yesButton(View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, HomeActivity.class);
        v.findViewById(R.id.yesbutton);
        startActivity(intent);
    }

}
