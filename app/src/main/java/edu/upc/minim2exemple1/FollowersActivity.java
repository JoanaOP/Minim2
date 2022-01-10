package edu.upc.minim2exemple1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    API API;

    AlertDialog.Builder alertBuilder;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        alertBuilder = new AlertDialog.Builder(this);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);
        createAPI();
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("name",null);
        doApiCallFollowers(username);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(API.class);
    }

    public void doApiCallFollowers(String username){
        Call<List<JsonObject>> call = API.getFollowers(username);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                if (response.isSuccessful()) {
                    //List<Track> tracksList = response.body();
                    //mAdapter.setData(tracksList);

                    try {
                        LinkedList<Follower> followersList = new LinkedList<Follower>();
                        for (int i = 0;i < response.body().size();i++) {
                            JSONObject jsonObject = null;
                            jsonObject = new JSONObject(new Gson().toJson(response.body().get(i)));

                            Follower f = new Follower();
                            f.setName(jsonObject.getString("login"));
                            f.setAvatarUrl(jsonObject.getString("avatar_url"));
                            followersList.add(f);
                        }
                        mAdapter.setData(followersList);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        showAlertDialog("Error","Error a l'hora de crear la pantalla");
                    }

                }
                else{
                    showAlertDialog("Error","No s'ha trobat el que buscaves");
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                t.printStackTrace();
                showAlertDialog("Error","Connexió fallida");
            }
        });
    }

    public void showAlertDialog(String title, String message){
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("Aceptar",null);
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }


}