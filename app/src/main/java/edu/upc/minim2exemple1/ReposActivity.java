package edu.upc.minim2exemple1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class ReposActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    API API;

    AlertDialog.Builder alertBuilder;

    ProgressBar progressBar;
    ImageView avatarImg;
    TextView nameTxt;
    TextView followingTxt;
    TextView followersTxt;
    TextView reposTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        avatarImg = (ImageView) findViewById(R.id.profileImg);
        nameTxt = (TextView) findViewById(R.id.userText);
        followingTxt = (TextView) findViewById(R.id.followingText);
        followersTxt = (TextView) findViewById(R.id.followersText);
        reposTxt = (TextView) findViewById(R.id.repositoriesText);

        alertBuilder = new AlertDialog.Builder(this);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.VISIBLE);

        createAPI();

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("name",null);
        doApiCallFollowers(username);
        doApiCallUser(username);
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
        Call<List<JsonObject>> call = API.getRepos(username);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                if (response.isSuccessful()) {
                    //List<Track> tracksList = response.body();
                    //mAdapter.setData(tracksList);

                    try {
                        LinkedList<Repository> reposList = new LinkedList<Repository>();
                        for (int i = 0;i < response.body().size();i++) {
                            JSONObject jsonObject = null;
                            jsonObject = new JSONObject(new Gson().toJson(response.body().get(i)));

                            Repository repo = new Repository();
                            repo.setName(jsonObject.getString("name"));

                            if (jsonObject.has("language") && !jsonObject.isNull("language")) {
                                repo.setLanguage(jsonObject.getString("language"));
                            }
                            else{
                                repo.setLanguage(null);
                            }

                            reposList.add(repo);
                        }
                        mAdapter.setData(reposList);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        showAlertDialog("Error","Error a l'hora de crear la pantalla");
                    }

                }
                else if(response.code()==404){
                    showAlertDialog("Error","No s'ha trobat el usuari que buscaves");
                }
                else{
                    showAlertDialog("Error","Error al servidor");
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                t.printStackTrace();
                showAlertDialog("Error","Connexió fallida");
            }
        });
    }

    public void doApiCallUser(String username){
        Call<JsonObject> call = API.getUser(username);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject jsonObject = null;
                        jsonObject = new JSONObject(new Gson().toJson(response.body()));

                        nameTxt.setText(jsonObject.getString("login"));
                        String followers = "Followers: " + jsonObject.getString("followers");
                        followersTxt.setText(followers);
                        String following = "Following: " + jsonObject.getString("following");
                        followingTxt.setText(following);
                        String repos = "Repositories: " + jsonObject.getString("public_repos");
                        reposTxt.setText(repos);


                        Glide.with(avatarImg.getContext())
                                .load(jsonObject.getString("avatar_url"))
                                .into(avatarImg);

                        nameTxt.setVisibility(View.VISIBLE);
                        followersTxt.setVisibility(View.VISIBLE);
                        followingTxt.setVisibility(View.VISIBLE);
                        reposTxt.setVisibility(View.VISIBLE);
                        avatarImg.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                        showAlertDialog("Error","Error a l'hora de crear la pantalla");
                    }
                }
                else if(response.code()==404){
                    showAlertDialog("Error","No s'ha trobat el usuari que buscaves");
                }
                else{
                    showAlertDialog("Error","Error al servidor");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
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