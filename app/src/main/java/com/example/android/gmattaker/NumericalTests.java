package com.example.android.gmattaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NumericalTests extends AppCompatActivity {
    LinearLayout v1, v2, v3, v4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerical_tests);
        v1 = findViewById(R.id.t1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumericalTests.this, Numerical.class);
                intent.putExtra("test", 1);
                startActivity(intent);
            }

        });

        v2 = findViewById(R.id.t2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumericalTests.this, Numerical.class);
                intent.putExtra("test", 2);
                startActivity(intent);
            }

        });

        v3 = findViewById(R.id.t3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumericalTests.this, Numerical.class);
                intent.putExtra("test", 3);
                startActivity(intent);
            }

        });

        v4 = findViewById(R.id.t4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumericalTests.this, Numerical.class);
                intent.putExtra("test", 4);
                startActivity(intent);
            }

        });
    }
}
