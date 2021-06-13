package com.example.android.gmattaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gmattaker.model.ModelData;


public class Numerical extends AppCompatActivity {
    int counter;
    public BroadcastReceiver mMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String sc = intent.getStringExtra("rscore");
            counter = Integer.parseInt(sc);
            // textView = findViewById(R.id.score);
            // textView.setText(sc);
        }

    };
    int quizNo = 0;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CountDownTimer cdwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
        setTitle("Numerical Reasoning");
        Intent intent = getIntent();
        quizNo = intent.getExtras().getInt("test");
        final TextView countView = findViewById(R.id.numCounter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessage, new IntentFilter("score"));
        cdwn = new CountDownTimer(300000, 1000) {
            int left = 0;

            @Override
            public void onTick(long l) {
                countView.setText("" + String.format("%02d : %02d ",
                        TimeUnit.MILLISECONDS.toMinutes(l) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(l) % 60,
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toSeconds(l) % 60)));

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Numerical.this, ResultActivity.class);
                intent.putExtra("result", String.valueOf(counter));
                intent.putExtra("activity", "numerical");
                startActivity(intent);

            }
        }.start();
        Button btn = findViewById(R.id.submit_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cdwn.cancel();
                Intent intent = new Intent(Numerical.this, ResultActivity.class);
                intent.putExtra("result", String.valueOf(counter));
                intent.putExtra("activity", "numerical");
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes

        recyclerView.setHasFixedSize(true);


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
        TestAdapter recyclerViewAdapter = new
                TestAdapter(getPackages(), this);
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        cdwn.cancel();

    }

    private List<ModelData> getPackages() {
        List<ModelData> modelList = new ArrayList<>();
        if (quizNo == 1) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. -0.3");
            answerList1.add("B. 0");
            answerList1.add("C. 0.3");
            answerList1.add("D. 1.08");
            answerList1.add("E. 2.46");
            modelList.add(new ModelData("1. What is the value of 3x\u00b2 − 1.8x + 0.3 for x = 0.6?", answerList1, answerList1.get(2)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. 16");
            answerList.add("B. 36");
            answerList.add("C. 64");
            answerList.add("D. 81");
            answerList.add("E. 91");
            modelList.add(new ModelData("2. If a cube has a total surface area of 96, what " +
                    "is its volume?", answerList, answerList.get(2)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. 0.0008");
            answerList3.add("B. 0.001");
            answerList3.add("C. 0.008");
            answerList3.add("D. 0.04");
            answerList3.add("E. 0.08");
            modelList.add(new ModelData("3. (0.2)\u2078/(0.2)\u2075 = ?", answerList3, answerList3.get(2)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. 60");
            answerList4.add("B. 100");
            answerList4.add("C. 120");
            answerList4.add("D. 200");
            answerList4.add("E. 3,000");
            modelList.add(new ModelData("4.If a copier makes 3 copies every 4 seconds, " +
                    "then continues at this rate, how many " +
                    "minutes will it take to make 9,000 copies?", answerList4, answerList4.get(3)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. 5");
            answerList5.add("B. 6");
            answerList5.add("C. 9");
            answerList5.add("D. 15");
            answerList5.add("E. 25");
            modelList.add(new ModelData("5. If 3(x\u00b2 + x) - 7 = x\u00b2 + 2(4 + x\u00b2), then x =?", answerList5, answerList5.get(0)));

        } else if (quizNo == 2) {
            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. 1");
            answerList1.add("B. 2");
            answerList1.add("C. 3");
            answerList1.add("D. 4");
            answerList1.add("E. 5");
            modelList.add(new ModelData("1. What is the twenty-third decimal to the right" +
                    " in the fraction 23/24?", answerList1, answerList1.get(2)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. 3xw");
            answerList.add("B. 3w/x");
            answerList.add("C. w/3x");
            answerList.add("D. xw/3");
            answerList.add("E. x/3w");
            modelList.add(new ModelData("2. If Fash Gallagher has earned x dollars by working" +
                    " 3 days a week at a constant daily rate for w weeks, which of the following represents " +
                    "his daily wage?", answerList, answerList.get(4)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. -7/6");
            answerList3.add("B. -5/6");
            answerList3.add("C. -1/6");
            answerList3.add("D. 5/6");
            answerList3.add("E. 7/6");
            modelList.add(new ModelData("3. (1/3 - 1/2) - 1 = ?", answerList3, answerList3.get(0)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. 21");
            answerList4.add("B. 20");
            answerList4.add("C. 19");
            answerList4.add("D. 16");
            answerList4.add("E. 15");
            modelList.add(new ModelData("4. Marcella has 25 pairs of shoes. If she loses 9 individual shoes, what is the greatest number of matching pairs she could have left?", answerList4, answerList4.get(1)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. 5");
            answerList5.add("B. 6");
            answerList5.add("C. 9");
            answerList5.add("D. 15");
            answerList5.add("E. 25");
            modelList.add(new ModelData("5. If 3(x\u00b2 + x) - 7 = x\u00b2 + 2(4 + x\u00b2), then x =?", answerList5, answerList5.get(0)));

        } else if (quizNo == 3) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. 7\u03c0");
            answerList1.add("B. 14\u03c0");
            answerList1.add("C. 28\u03c0");
            answerList1.add("D. 49\u03c0");
            answerList1.add("E. 196\u03c0");
            modelList.add(new ModelData("1. If the diameter of a circle is 14, then the area of the circle is", answerList1, answerList1.get(3)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. x - 30");
            answerList.add("B. x - 12");
            answerList.add("C. 18 - x");
            answerList.add("D. 24 - x");
            answerList.add("E. 30 - x");
            modelList.add(new ModelData("2. If Finn was 18 months old one year ago, how old was he, in months, x months ago?", answerList, answerList.get(4)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. 24 and 25");
            answerList3.add("B. 25 and 26");
            answerList3.add("C. 26 and 27");
            answerList3.add("D. 27 and 28");
            answerList3.add("E. 28 and 29");
            modelList.add(new ModelData("3. The square root of 636 is between which set of integers?", answerList3, answerList3.get(1)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. 40");
            answerList4.add("B. 50");
            answerList4.add("C. 60");
            answerList4.add("D. 70");
            answerList4.add("E. 80");
            modelList.add(new ModelData("4. The time it took car P to travel 600 miles was 2 hours less than the time it took car R to travel the same distance. If car P’s average\n" +
                    "speed was 10 miles per hour greater than that " +
                    "of car R, what was car R’s average speed, " +
                    " in miles per hour?", answerList4, answerList4.get(1)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. 5");
            answerList5.add("B. 6");
            answerList5.add("C. 9");
            answerList5.add("D. 15");
            answerList5.add("E. 25");
            modelList.add(new ModelData("5. If x is 20 percent greater than 88, then x = ", answerList5, answerList5.get(3)));

        } else if (quizNo == 4) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. $2.50");
            answerList1.add("B. $5.00");
            answerList1.add("C. $5.50");
            answerList1.add("D. $7.50");
            answerList1.add("E. $15.00");
            modelList.add(new ModelData("1. Suzie’s Discount Footwear sells all pairs of " +
                    "shoes for one price and all pairs of boots for " +
                    "another price. On Monday the store sold " +
                    "22 pairs of shoes and 16 pairs of boots for $650. " +
                    "On Tuesday the store sold 8 pairs of shoes " +
                    "and 32 pairs of boots for $760. How much " +
                    "more do pairs of boots cost than pairs of shoes " +
                    "at Suzie’s Discount Footwear?", answerList1, answerList1.get(1)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. 4");
            answerList.add("B. 8");
            answerList.add("C. 12");
            answerList.add("D. 16");
            answerList.add("E. 18");
            modelList.add(new ModelData("2. If the area of a square with sides of length " +
                    "8 centimeters is equal to the area of a " +
                    "rectangle with a width of 4 centimeters, what " +
                    "is the length of the rectangle, in centimeters?", answerList, answerList.get(3)));


            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. 20%");
            answerList3.add("B. 25%");
            answerList3.add("C. 40%");
            answerList3.add("D. 50%");
            answerList3.add("E. 80%");
            modelList.add(new ModelData("3. Two hundred multiples of seven are chosen " +
                    "at random, and 300 multiples of eight are " +
                    "chosen at random. Approximately what " +
                    "percentage of the 500 selected numbers " +
                    "are odd?", answerList3, answerList3.get(0)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. 64/81");
            answerList4.add("B. 8/9");
            answerList4.add("C. 1");
            answerList4.add("D. 9/8");
            answerList4.add("E. 81/64");
            modelList.add(new ModelData("4. If 8a = 9b and ab \u2260 0, what is the ratio of a/9 to b/8?", answerList4, answerList4.get(2)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. $960");
            answerList5.add("B. $1,350");
            answerList5.add("C. $1,725");
            answerList5.add("D. $2,050");
            answerList5.add("E. $2,250");
            modelList.add(new ModelData("5. A hat company ships its hats, individually " +
                    " wrapped, in 8-inch by 10-inch by 12-inch " +
                    "boxes. Each hat is valued at $7.50. If the " +
                    "company’s latest order required a truck with " +
                    "at least 288,000 cubic inches of storage space " +
                    "in which to ship the hats in their boxes, what " +
                    "was the minimum value of the order?", answerList5, answerList5.get(4)));
        }


        return modelList;
    }
}


