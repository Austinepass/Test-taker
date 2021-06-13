package com.example.android.gmattaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout numericalText = findViewById(R.id.numericalTv);
        numericalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NumericalTests.class);
                startActivity(intent);
            }
        });

        LinearLayout verbalText = findViewById(R.id.verbal);
        verbalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VerbalTests.class);
                startActivity(intent);
            }
        });

        LinearLayout abstractText = findViewById(R.id.Abstract);
        abstractText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Logical.class);
                startActivity(intent);
            }
        });
    }
}
