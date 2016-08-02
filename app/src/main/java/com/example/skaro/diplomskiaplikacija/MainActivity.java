package com.example.skaro.diplomskiaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etName3 = (EditText) findViewById(R.id.etName3);
        final EditText etSurname = (EditText) findViewById(R.id.etSurname);
        final TextView welcome = (TextView) findViewById(R.id.tvWelcome);
        final Button btscooter   =(Button) findViewById(R.id.btScooter);
        final Button btnaked   =(Button) findViewById(R.id.btNaked);

        btscooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
                listIntent.putExtra("category", "scooter");
                MainActivity.this.startActivity(listIntent);


            }
        });

        btnaked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
                listIntent.putExtra("category", "naked");
                MainActivity.this.startActivity(listIntent);

            }
        });

        Intent intent= getIntent();
        String name= intent.getStringExtra("name");
        String surname= intent.getStringExtra("surname");

        etName3.setText(name);
        etSurname.setText(surname);


    }

}
