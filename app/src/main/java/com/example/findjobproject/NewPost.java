package com.example.findjobproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        EditText et_jobName = findViewById(R.id.et_jobName);
        Spinner et_type = findViewById(R.id.spr_jobType);
        Spinner et_gender = findViewById(R.id.spr_gender);
        EditText et_age = findViewById(R.id.et_age);
        EditText et_jobPosition = findViewById(R.id.et_jobPosition);
        EditText et_description = findViewById(R.id.et_description);
        EditText et_phoneNumber = findViewById(R.id.et_phoneNumber);
        Spinner et_wNational = findViewById(R.id.spr_workerNational);
        Button btn_createPost = findViewById(R.id.btn_signOut);
        ProgressBar progressBar = findViewById(R.id.progressBar3);


        Intent i = getIntent();
        final String username = i.getStringExtra("username");

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://hussien300.000webhostapp.com/CSCI410/savePosts.php";
        btn_createPost.setOnClickListener(view -> {

            if (et_jobName.getText().toString().equals("")) {
                et_jobName.setError("required!");
            }

            if (et_gender.getSelectedItem().equals("Gender")){
                Toast.makeText(NewPost.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            }

            else if (et_age.getText().toString().equals("")) {
                et_age.setError("required!");
            }

            else if (et_wNational.getSelectedItem().equals("Worker Nationality")) {
                Toast.makeText(NewPost.this, "Please Select worker nationality", Toast.LENGTH_SHORT).show();
            }


            else if (et_phoneNumber.getText().toString().equals("")) {
                et_phoneNumber.setError("required!");
            }

            else {
                btn_createPost.setEnabled(false);
                btn_createPost.setBackgroundResource(R.color.buttonDisabled);
                progressBar.setVisibility(View.VISIBLE);

                StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                    Toast.makeText(NewPost.this, response, Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(NewPost.this, home.class);
                    i1.putExtra("homeUsername", username);
                    startActivity(i1);
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();

                }, error -> {
                    Toast.makeText(NewPost.this, error.toString(), Toast.LENGTH_SHORT).show();
                    btn_createPost.setEnabled(true);
                    btn_createPost.setBackgroundResource(R.color.originalColor);
                    progressBar.setVisibility(View.INVISIBLE);

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("jName", et_jobName.getText().toString());
                        params.put("jType", et_type.getSelectedItem().toString());
                        params.put("jGender", et_gender.getSelectedItem().toString());
                        params.put("jAge", et_age.getText().toString());
                        params.put("jPosition", et_jobPosition.getText().toString());
                        params.put("wNational", et_wNational.getSelectedItem().toString());
                        params.put("description", et_description.getText().toString());
                        params.put("pNumber", et_phoneNumber.getText().toString());
                        params.put("userPost", username);
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        }
    }
