package com.example.findjobproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText u = findViewById(R.id.userNameInput);
        EditText p = findViewById(R.id.passInput);
        Button signUp = findViewById(R.id.signUpBtnInlogin);
        Button cont = findViewById(R.id.continueBtn);
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://hussien300.000webhostapp.com/CSCI410/getUsers.php";

        cont.setOnClickListener(view -> {

            if (u.getText().toString().equals("") || p.getText().toString().equals("")) {
                Toast.makeText(login.this, "Please fill all requirments", Toast.LENGTH_SHORT).show();

            }

            else{
                cont.setEnabled(false);
                cont.setBackgroundResource(R.color.buttonDisabled);
                progressBar2.setVisibility(View.VISIBLE);

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        response -> {

                            if(response.equalsIgnoreCase("incorrect username or password")){
                                Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                                cont.setEnabled(true);
                                cont.setBackgroundResource(R.color.originalColor);
                                progressBar2.setVisibility(View.INVISIBLE);
                                u.setText("");
                                p.setText("");

                            }
                            else {
                                Toast.makeText(login.this, "Welcome Back Sir", Toast.LENGTH_SHORT).show();
                                Intent j = new Intent(login.this, home.class);
                                j.putExtra("loginUsername", u.getText().toString());
                                startActivity(j);
                                progressBar2.setVisibility(View.INVISIBLE);
                                finish();
                            }

                        }, error -> {
                            Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();
                            cont.setEnabled(true);
                            cont.setBackgroundResource(R.color.originalColor);
                            progressBar2.setVisibility(View.INVISIBLE);
                            u.setText("");
                            p.setText("");
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", u.getText().toString());
                        params.put("password", p.getText().toString());
                        return params;
                    }
                };
                queue.add(request);

            }
        });

        signUp.setOnClickListener(view -> {
            Intent j = new Intent(login.this, createNewAccount.class);
            startActivity(j);
            finish();
        });
    }
}