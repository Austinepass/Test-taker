package com.example.android.gmattaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class VerbalTests extends AppCompatActivity {
    LinearLayout v1, v2, v3, v4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verbal_test);
        v1 = findViewById(R.id.vt1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerbalTests.this, Verbal.class);
                intent.putExtra("test", 1);
                startActivity(intent);
            }

        });

        v2 = findViewById(R.id.vt2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerbalTests.this, Verbal.class);
                intent.putExtra("test", 2);
                startActivity(intent);
            }

        });

        v3 = findViewById(R.id.vt3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerbalTests.this, Verbal.class);
                intent.putExtra("test", 3);
                startActivity(intent);
            }

        });

        v4 = findViewById(R.id.vt4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerbalTests.this, Verbal.class);
                intent.putExtra("test", 4);
                startActivity(intent);
            }

        });
    }
}
