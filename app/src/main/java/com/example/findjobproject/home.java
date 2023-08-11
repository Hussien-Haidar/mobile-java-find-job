package com.example.findjobproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For night mode theme
        setContentView(R.layout.activity_home);

        ListView lvPosts = findViewById(R.id.lvPosts);
        Button newPostButton = findViewById(R.id.newPostButton);
        Button settings = findViewById(R.id.settingsButton);
        Button refresh = findViewById(R.id.refreshButton);
        TextView txt_homeUsername = findViewById(R.id.txt_homeUsername);
        TextView txt_noResult = findViewById(R.id.noResultFound_txt);
        Spinner spr_workerNationality = findViewById(R.id.spr_workerNationality);
        Button btn_searchForSpecific = findViewById(R.id.searchForSpecificBtn);

        Intent i = getIntent();

        String testloginUsername = i.getStringExtra("loginUsername");
        String testnewUsername = i.getStringExtra("newUsername");
        String testhomeUsername = i.getStringExtra("homeUsername");
        String testownUsername = i.getStringExtra("ownUsername");
        String finalTest = null;

        if(testnewUsername!=null)
            finalTest = testnewUsername;

        if(testloginUsername!=null)
            finalTest= testloginUsername;

        if(testhomeUsername!=null)
            finalTest = testhomeUsername;

        if(testownUsername!=null)
            finalTest = testownUsername;

        final String username = finalTest;

        txt_homeUsername.setText(username);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://hussien300.000webhostapp.com/CSCI410/getPosts.php";

        ArrayList<Post> posts = new ArrayList<>();

        ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1, posts) {
            @SuppressLint("Range")
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v =  super.getView(position, convertView, parent);
                ((TextView)v).setTextSize(20);
                return v;
            }
        };

        lvPosts.setAdapter(adapter);

        JsonArrayRequest request = new JsonArrayRequest(url, response -> {
            for (int i14 = 0; i14 < response.length(); i14++) {
                try {
                    JSONObject row = response.getJSONObject(i14);
                    String jName = row.getString("jName");
                    String jType = row.getString("jType");
                    String jGender = row.getString("jGender");
                    String jAge = row.getString("jAge");
                    String jPosition = row.getString("jPosition");
                    String wNational = row.getString("wNational");
                    String description = row.getString("description");
                    String pNumber = row.getString("pNumber");

                    posts.add(new Post(jName, jType, jGender, jAge, jPosition, description, wNational,
                            pNumber));

                } catch (Exception ex) {
                    Toast.makeText(home.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
            adapter.notifyDataSetChanged();
        }, null);

        queue.add(request);

        newPostButton.setOnClickListener(view -> {
            Intent i12 = new Intent(home.this, NewPost.class);
            i12.putExtra("username", username);
            startActivity(i12);
        });

        settings.setOnClickListener(view -> {
            Intent i1 = new Intent(home.this, Settings.class);
            i1.putExtra("username", username);
            startActivity(i1);
        });


        refresh.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, home.class);
            intent.putExtra("ownUsername", username);
            finish();
            startActivity(intent);
        });

        btn_searchForSpecific.setOnClickListener(v -> {
            final String choosedNationality = spr_workerNationality.getSelectedItem().toString();
            ArrayList<Post> posts2 = new ArrayList<>();
            int g=0;

            for (int i13 = 0; i13 < posts.size(); i13++) {

                if (choosedNationality.equals("Worker Nationality")){
                    g = 0;
                }

                else if (posts.get(i13).getWorkerNationality().equals(choosedNationality)){
                    posts2.add(posts.get(i13));
                    g = 1;
                }

                else {
                    posts2.clear();
                    g = 2;
                }

            }

            if (g == 0) {
                lvPosts.setAdapter(adapter);
                txt_noResult.setVisibility(View.INVISIBLE);
            }

            if (g == 1) {
                ArrayAdapter<Post> adapter2 = new ArrayAdapter<>(home.this, android.R.layout.simple_list_item_1, posts2);
                lvPosts.setAdapter(adapter2);
                txt_noResult.setVisibility(View.INVISIBLE);
            }

            if (g == 2) {
                ArrayAdapter<Post> adapter2 = new ArrayAdapter<>(home.this, android.R.layout.simple_list_item_1, posts2);
                lvPosts.setAdapter(adapter2);
                txt_noResult.setVisibility(View.VISIBLE);
            }

        });
    }

    @Override
    public void onBackPressed() {
    }

}



