package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Flashcards extends AppCompatActivity {
    private int side;
    private int index;
    private String side1;
    private String side2;
    private String[] side1Array;
    private String[] side2Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcards);

        //setContentView(R.layout.activity_main);
        Intent received = getIntent();

        side1 = received.getStringExtra("side1");
        side2 = received.getStringExtra("side2");
        index = 0;
        final Button Flashcard = findViewById(R.id.Flashcard);
        final Button Previous = findViewById(R.id.Previous);
        final Button Next = findViewById(R.id.Next);
        final Button menu = findViewById(R.id.menu);

        Intent intent = new Intent(this, MainActivity.class);

        if (side1.equals("English")) {
            side1Array = received.getStringArrayExtra("englishArray");
            Flashcard.setText(side1Array[index]);
            side = 0;
        } else {
            side1Array = received.getStringArrayExtra("charactersArray");
            Flashcard.setText(side1Array[index]);
            side = 0;
        }

        if (side2.equals("English")) {
            side2Array = received.getStringArrayExtra("englishArray");
        } else {
            side2Array = received.getStringArrayExtra("charactersArray");
        }


        Flashcard.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (side == 0) {
                    Flashcard.setText(side2Array[index]);
                    side = 1;
                } else {
                    Flashcard.setText(side1Array[index]);
                    side = 0;
                }

            }
        });
        Previous.setOnClickListener(unused -> new View.OnClickListener() {
            public void onClick(View v) {
                index--;
                if (index <= -1) {
                    index = side1Array.length - 1;
                }
                Flashcard.setText(side1Array[index]);
                side = 0;
            }
        });
        Next.setOnClickListener((View v) -> {
            index++;
            if (index >= 5) {
                index = 0;
            }
            Flashcard.setText(side1Array[index]);
            side = 0;
        });

        menu.setOnClickListener(unused -> startActivity(intent));


    }
}
