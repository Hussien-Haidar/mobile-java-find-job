package com.example.findjobproject;

import static com.example.findjobproject.R.color.originalColor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class createNewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        EditText newU = findViewById(R.id.userNameInput2);
        EditText newP = findViewById(R.id.passInput2);
        CheckBox tos = findViewById(R.id.acceptTermsOfService);
        Button log = findViewById(R.id.loginBtnInNewAcc);
        Button cont2 = findViewById(R.id.continueBtn2);
        ProgressBar progressBar1 = findViewById(R.id.progressBar1);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://hussien300.000webhostapp.com/CSCI410/saveUsers.php";

        cont2.setOnClickListener(view -> {

            if(tos.isChecked() && (newU.getText().toString().length()>=8 &&
                    newP.getText().toString().length()>=8)){

                cont2.setEnabled(false);
                cont2.setBackgroundResource(R.color.buttonDisabled);
                progressBar1.setVisibility(View.VISIBLE);

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        response -> {

                            if(response.equalsIgnoreCase("Username is already used")){
                                Toast.makeText(createNewAccount.this, response, Toast.LENGTH_SHORT).show();
                                cont2.setEnabled(true);
                                cont2.setBackgroundResource(originalColor);
                                progressBar1.setVisibility(View.INVISIBLE);
                                newU.setText("");
                                newP.setText("");
                            }
                            else {
                                Toast.makeText(createNewAccount.this, "Welcome", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(createNewAccount.this, home.class);
                                i.putExtra("newUsername", newU.getText().toString());
                                startActivity(i);
                                progressBar1.setVisibility(View.INVISIBLE);
                                finish();

                            }

                        }, error -> {
                            Toast.makeText(createNewAccount.this, error.toString(), Toast.LENGTH_SHORT).show();
                            cont2.setEnabled(true);
                            cont2.setBackgroundResource(originalColor);
                            progressBar1.setVisibility(View.INVISIBLE);
                            newU.setText("");
                            newP.setText("");
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", newU.getText().toString());
                        params.put("password", newP.getText().toString());
                        return params;
                    }
                };
                queue.add(request);
            }

            else if(!tos.isChecked() && (newU.getText().toString().equals("") || newP.getText().toString().equals(""))){

                Toast.makeText(createNewAccount.this, "Please fill all requirments", Toast.LENGTH_SHORT).show();

            }

            else if(tos.isChecked() && (newU.getText().toString().equals("") || newP.getText().toString().equals(""))){

                Toast.makeText(createNewAccount.this, "Please fill all requirments", Toast.LENGTH_SHORT).show();

            }

            else if(!tos.isChecked()){

                Toast.makeText(createNewAccount.this, "You must first accept our terms and privacy policy", Toast.LENGTH_LONG).show();

            }

            else if(tos.isChecked() && (newU.getText().toString().equals("") && newP.getText().toString().equals(""))){

                Toast.makeText(createNewAccount.this, "You must fill username and password cases", Toast.LENGTH_SHORT).show();

            }

            else if( (!newU.getText().toString().equals("") && !newP.getText().toString().equals(""))
                  && (newU.getText().toString().length()<8 || newP.getText().toString().length()<8) ) {

                Toast.makeText(createNewAccount.this,
                        "Username and password must contain 8 characters at least", Toast.LENGTH_LONG).show();

            }
        });

        log.setOnClickListener(view -> {
            Intent j = new Intent(createNewAccount.this, login.class);
            startActivity(j);
            finish();
        });

    }
}
