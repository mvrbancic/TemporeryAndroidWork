package com.example.skaro.diplomskiaplikacija.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.skaro.diplomskiaplikacija.R;
import com.example.skaro.diplomskiaplikacija.requests.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etSurName = (EditText) findViewById(R.id.etSurname);
        final EditText etUserName = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPassword2 = (EditText) findViewById(R.id.etPassword2);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button btRegister = (Button) findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String surname = etSurName.getText().toString();
                final String username = etUserName.getText().toString();
                final String age = etAge.getText().toString();
                final String password = etPassword.getText().toString();
                final String password2 = etPassword2.getText().toString();
                final String email = etEmail.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    etName.setError("Molimo unesite ime!");
                    return;
                }

                if (TextUtils.isEmpty(surname)) {
                    etSurName.setError("Molimo unesite prezime!");
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    etUserName.setError("Molimo unesite korisničko ime!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Molimo unesite zaporku!");
                    return;
                }

                if (TextUtils.isEmpty(password2)) {
                    etPassword2.setError("Molimo ponovno unesite zaporku!");
                    return;
                }
                if (etPassword.getText().toString().equals(etPassword2.getText().toString())) {

                } else {
                    etPassword2.setError("Vaša zaporka se ne podudara!");
                    return;

                }


                if (TextUtils.isEmpty(age)) {
                    etAge.setError("Molimo unesite godine");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Molimo unesite email!");
                    return;
                }

                if (!email.contains("@")) {
                    etEmail.setError("Vaš mail je netočan");
                }

                Response.Listener<String> respListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);

                                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registracija uspješna")
                                        .setPositiveButton("Nastavi", null)
                                        .create()
                                        .show();


                            } else {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(RegisterActivity.this);
                                builder2.setMessage("Neuspješna registracija, username se već koristi")
                                        .setNegativeButton("Ponovi", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, surname, username, age, password, email, respListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });


    }
}
