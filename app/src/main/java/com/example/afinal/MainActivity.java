package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private String[] englishArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        EditText vocab1 = findViewById(R.id.vocab1);
        englishArray = new String[5];

        Button ok1 = findViewById(R.id.ok1);
        ok1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String english1 = vocab1.getText().toString();
                englishArray[0] = english1;
            }
        });

        EditText vocab2 = findViewById(R.id.vocab2);

        Button ok2 = findViewById(R.id.ok2);
        ok2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String english2 = vocab2.getText().toString();
                englishArray[1] = english2;
            }
        });

        EditText vocab3 = findViewById(R.id.vocab3);

        Button ok3 = findViewById(R.id.ok3);
        ok3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String english3 = vocab3.getText().toString();
                englishArray[2] = english3;
            }
        });

        EditText vocab4 = findViewById(R.id.vocab4);

        Button ok4 = findViewById(R.id.ok4);
        ok4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String english4 = vocab4.getText().toString();
                englishArray[3] = english4;
            }
        });

        EditText vocab5 = findViewById(R.id.vocab5);

        Button ok5 = findViewById(R.id.ok5);
        ok5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String english5 = vocab5.getText().toString();
                englishArray[4] = english5;
            }
        });


        Intent intent = new Intent(this, Options.class);
        intent.putExtra("englishArray", englishArray);

        // continue button setup
        final Button Continue = findViewById(R.id.Continue);
        Continue.setOnClickListener(unused -> startActivity(intent));


    }
}
