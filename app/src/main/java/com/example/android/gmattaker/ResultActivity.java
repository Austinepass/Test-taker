package com.example.android.gmattaker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity  extends AppCompatActivity {
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        activity = intent.getExtras().getString("activity");

        String result = intent.getExtras().getString("result");
        int chckResult = Integer.parseInt(result);

        TextView msgTv = findViewById(R.id.result_msg);
        ImageView msgImg = findViewById(R.id.msg_img);

        if (chckResult >= 3) {

            msgTv.setText(R.string.awesome);
            msgImg.setImageResource(R.drawable.result);
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.result_lyt);
            linearLayout.setBackgroundColor(Color.parseColor("#f7f7f7"));
            msgTv.setText("You can always do better!");
            msgImg.setImageResource(R.drawable.failed);
        }

            TextView textView = findViewById(R.id.result_score);
            textView.setText(result + "/5");




    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (activity.equals("numerical")) {
                Intent intent = new Intent(ResultActivity.this, NumericalTests.class);
                finish();

                startActivity(intent);
            }else if (activity.equals("verbal")){
                Intent intent = new Intent(ResultActivity.this, VerbalTests.class);
                finish();

                startActivity(intent);
            }
        }
        return true;
    }
}

