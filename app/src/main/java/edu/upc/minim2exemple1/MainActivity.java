package edu.upc.minim2exemple1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText usernameText;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        usernameText = findViewById(R.id.usernameEditText);
        SharedPreferences sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("name",null);

        if(username!=null){
            usernameText.setText(username);
        }

    }

    public void onClick(View view){

        String username = usernameText.getText().toString();

        if(username!=null){

            SharedPreferences sharedPref = getSharedPreferences("user", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", username);
            editor.commit();

            Intent intent = new Intent(this, ReposActivity.class);
            this.startActivity(intent);
        }
        else{
            String text = "Write something";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this.getBaseContext(), text, duration);
            toast.show();
        }

    }



}