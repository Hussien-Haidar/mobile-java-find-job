package com.example.findjobproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView txt_username = findViewById(R.id.txt_settingsUsername);
        Button btn_home = findViewById(R.id.btn_home);
        Button btn_newPost = findViewById(R.id.btn_newPost);
        Button btn_deleteAccount = findViewById(R.id.btn_deleteAccount);
        Button btn_signOut = findViewById(R.id.btn_signOut);
        ProgressBar progressBar3 = findViewById(R.id.progressBar3);

        Intent i = getIntent();
        final String username = i.getStringExtra("username");

        txt_username.setText("username: "+username);

        RequestQueue queue = Volley.newRequestQueue(this);

        btn_home.setOnClickListener(view -> {
            Intent i1 = new Intent(Settings.this, home.class);
            i1.putExtra("homeUsername", username);
            startActivity(i1);
            finish();
        });

        btn_newPost.setOnClickListener(view -> {
            Intent k = new Intent(Settings.this, NewPost.class);
            k.putExtra("username", username);
            startActivity(k);
            finish();
        });

        btn_signOut.setOnClickListener(view -> {
            Intent j = new Intent(Settings.this, MainActivity.class);
            startActivity(j);
            finish();
        });

        btn_deleteAccount.setOnClickListener(view -> {

            String url = "https://hussien300.000webhostapp.com/CSCI410/deleteUsers.php";

            btn_deleteAccount.setEnabled(false);
            progressBar3.setVisibility(View.VISIBLE);

            StringRequest request = new StringRequest(Request.Method.POST, url,
                    response -> {

                        Toast.makeText(Settings.this, response, Toast.LENGTH_SHORT).show();
                        btn_deleteAccount.setEnabled(true);
                        progressBar3.setVisibility(View.INVISIBLE);

                        Intent l = new Intent(Settings.this, MainActivity.class);
                        startActivity(l);
                        finish();

                    }, error -> {
                        Toast.makeText(Settings.this, error.toString(), Toast.LENGTH_SHORT).show();
                        btn_deleteAccount.setEnabled(true);
                        progressBar3.setVisibility(View.INVISIBLE);

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    return params;
                }
            };
            queue.add(request);
        });

    }
}