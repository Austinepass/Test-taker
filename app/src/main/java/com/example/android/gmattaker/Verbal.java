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

public class Verbal extends AppCompatActivity {
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
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    CountDownTimer cdwn;
    int quizNo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verbal_layout);
        Intent intent = getIntent();
        quizNo = intent.getExtras().getInt("test");
        final TextView countView = findViewById(R.id.verbal_counter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessage, new IntentFilter("score"));
        cdwn = new CountDownTimer(300000, 1000){

            @Override
            public void onTick(long l) {
                countView.setText("" + String.format("%02d : %02d ",
                        TimeUnit.MILLISECONDS.toMinutes(l) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(l) % 60,
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toSeconds(l) % 60)));

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Verbal.this, ResultActivity.class);
                intent.putExtra("result", String.valueOf(counter));
                intent.putExtra("activity", "verbal");
                startActivity(intent);

            }
        }.start();
        Button btn = findViewById(R.id.verbal_submit_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cdwn.cancel();
                Intent intent = new Intent(Verbal.this, ResultActivity.class);
                intent.putExtra("result", String.valueOf(counter));
                intent.putExtra("activity", "verbal");
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.verbal_recycler_view);
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


    private List<ModelData> getPackages() {
        List<ModelData> modelList = new ArrayList<>();

        if (quizNo == 1) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. minstrel band");
            answerList1.add("B. Moorish boat");
            answerList1.add("C. native dance");
            answerList1.add("D. coloured handkerchief");
            answerList1.add("E. scary wally");
            modelList.add(new ModelData("1.What is a bandanna?", answerList1, answerList1.get(3)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A.  TEBLEE");
            answerList.add("B. AGLEBE");
            answerList.add("C. FLYDOG");
            answerList.add("D. DIPRES");
            answerList.add("E. WRIEAG");
            modelList.add(new ModelData("2. Which anagram is not a type of insect?", answerList, answerList.get(1)));

            List<String> answerList2 = new ArrayList<>();
            answerList2.add("A. tameable");
            answerList2.add("B. misdeed");
            answerList2.add("C. baleful");
            answerList2.add("D. vindictive");
            answerList2.add("E. reproachable");
            modelList.add(new ModelData("3. What is the opposite of intractable? ", answerList2, answerList2.get(0)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. control");
            answerList3.add("B. mar");
            answerList3.add("C. cohere");
            answerList3.add("D. expedite");
            answerList3.add("E. garner");
            modelList.add(new ModelData("4. Which of these words is closest in meaning to harmonize?", answerList3, answerList3.get(2)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. abhors");
            answerList4.add("B. object");
            answerList4.add("C. tripod");
            answerList4.add("D. celery");
            answerList4.add("E. socket");
            modelList.add(new ModelData("5. Glance, namely, emboss, ? , ejects. Which of these words is missing?", answerList4, answerList4.get(1)));
        }

        else  if (quizNo == 2) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. joyful");
            answerList1.add("B. practical");
            answerList1.add("C. interested");
            answerList1.add("D. alert");
            answerList1.add("E. vivid");
            modelList.add(new ModelData("1.  Which word is most opposite in meaning to dreary?", answerList1, answerList1.get(0)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. leveret");
            answerList.add("B. buck");
            answerList.add("C. joey");
            answerList.add("D. calf");
            answerList.add("E. foal");
            modelList.add(new ModelData("2. Which is the odd one out?", answerList, answerList.get(1)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. mien");
            answerList3.add("B. applause");
            answerList3.add("C. assuage");
            answerList3.add("D. denigrate");
            answerList3.add("E. vet");
            modelList.add(new ModelData("3.  Find an antonym for extol from: ", answerList3, answerList3.get(3)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. retina");
            answerList4.add("B. cornea");
            answerList4.add("C. pupil");
            answerList4.add("D. cochlea");
            answerList4.add("E. iris");
            modelList.add(new ModelData("4. Which is the odd one out?", answerList4, answerList4.get(3)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. KEBLOP");
            answerList5.add("B. EBLOGT");
            answerList5.add("C. DUNIRM");
            answerList5.add("D. TIALNC");
            answerList5.add("E. ARNILO");
            modelList.add(new ModelData("5. Only one group of six letters below can be rearranged to\n" +
                    "spell out a six-letter word in the English language. What is the word?", answerList5, answerList5.get(1)));
        }
        else  if (quizNo == 3) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. large wave");
            answerList1.add("B. an old runic letter");
            answerList1.add("C. the mouthpiece of a wind instrument");
            answerList1.add("D. an opening in a wall");
            answerList1.add("E. a fringed shoulder strap");
            modelList.add(new ModelData("1. What is an eagre?", answerList1, answerList1.get(0)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. comfort");
            answerList.add("B. solicitation");
            answerList.add("C. enlargement");
            answerList.add("D. aliment");
            answerList.add("E. plethora");
            modelList.add(new ModelData("2. Which of these words is closest in meaning to sustenance?", answerList, answerList.get(3)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A.  housing for a compass");
            answerList3.add("B. commercial ship");
            answerList3.add("C. flag");
            answerList3.add("D. ski run");
            answerList3.add("E. back of a pen");
            modelList.add(new ModelData("3.  What is a binnacle? ", answerList3, answerList3.get(0)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. MECIPA");
            answerList4.add("B. NGEACL");
            answerList4.add("C. TRILOC");
            answerList4.add("D. FPILEN");
            answerList4.add("E. XMYPL");
            modelList.add(new ModelData("4. Only one group of six letters below can be rearranged to\n" +
                    "spell out a six-letter word in the English language. What is the word?", answerList4, answerList4.get(1)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. a pottery fragment");
            answerList5.add("B. a water-raising apparatus");
            answerList5.add("C. a rope supporting a mast on a ship");
            answerList5.add("D. a grapefruit-like citrus fruit");
            answerList5.add("E. a small farmhouse");
            modelList.add(new ModelData("5. What is a shaddock?", answerList5, answerList5.get(3)));
        }
        else  if (quizNo == 4) {

            List<String> answerList1 = new ArrayList<>();
            answerList1.add("A. coyote");
            answerList1.add("B. gopher");
            answerList1.add("C. gerbil");
            answerList1.add("D. ashlar");
            answerList1.add("E. porcupine");
            modelList.add(new ModelData("1. Which is the odd one out of: ", answerList1, answerList1.get(3)));

            List<String> answerList = new ArrayList<>();
            answerList.add("A. comfort, degenerate");
            answerList.add("B. solicitation, opprobrium");
            answerList.add("C. idolize, deify");
            answerList.add("D. aliment, odium");
            answerList.add("E. plethora, exaltation");
            modelList.add(new ModelData("2. Which pair of words are most similar in meaning?", answerList, answerList.get(2)));

            List<String> answerList3 = new ArrayList<>();
            answerList3.add("A. shoe polish");
            answerList3.add("B. a scratch");
            answerList3.add("C.  a weapon");
            answerList3.add("D. ski run");
            answerList3.add("E. a farm horse");
            modelList.add(new ModelData("3.  What is a dobbin? ", answerList3, answerList3.get(3)));

            List<String> answerList4 = new ArrayList<>();
            answerList4.add("A. fathom");
            answerList4.add("B. study");
            answerList4.add("C. unravel");
            answerList4.add("D. decipher");
            answerList4.add("E. resolve");
            modelList.add(new ModelData("4. Which is the odd one out?", answerList4, answerList4.get(1)));

            List<String> answerList5 = new ArrayList<>();
            answerList5.add("A. cabbage");
            answerList5.add("B. a rough bread");
            answerList5.add("C. paste for wallpaper");
            answerList5.add("D. an animal");
            answerList5.add("E. a small convoy");
            modelList.add(new ModelData("5. What is a savoy?", answerList5, answerList5.get(0)));
        }
            return modelList;
    }

    @Override
    protected void onPause() {
        super.onPause();
        cdwn.cancel();

    }
}

