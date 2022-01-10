package edu.upc.minim2exemple1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public EditText usernameText;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        usernameText = findViewById(R.id.usernameEditText);


    }

    public void onClick(View view){

        String username = usernameText.getText().toString();

        if(username!=null){

            SharedPreferences sharedPref = getSharedPreferences("user", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", username);
            editor.commit();

            Intent intent = new Intent(this, FollowersActivity.class);
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