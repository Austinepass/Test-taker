package com.example.android.gmattaker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<ModelData> mModelDataList;
    private Context context;
    int score = 0;



    public TestAdapter(List<ModelData> modelDataList
            , Context ctx) {
        mModelDataList = modelDataList;
        context = ctx;
    }
    public class TestHolder extends RecyclerView.ViewHolder {

        public TextView questions;
        public RadioGroup answers;


        public TestHolder(View view) {
            super(view);
            questions = view.findViewById(R.id.questions);
            answers = view.findViewById(R.id.answer_grp);

        }
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View  view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numerical_recycler, parent, false);

        TestHolder viewHolder =
                new TestHolder(view);
        return viewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position){
            ModelData packageModel = mModelDataList.get(position);
            holder.questions.setText(packageModel.getQuestions());


            List<String> optionList = packageModel.getAnswers();


            if (holder.answers.getChildCount() == 0) {
                addRadioButtons(holder.answers, optionList, packageModel);

            }

    }
    //Helper method for dynamically creating RadioButtons
    private void addRadioButtons(final RadioGroup quizOptionsRadioGroup,
                                 List<String> optionList, final ModelData packageModel) {

        final RadioButton[] radioButtons = new RadioButton[optionList.size()];

        for (int i = 0; i < optionList.size(); i++) {
            radioButtons[i] = new RadioButton(context);
            radioButtons[i].setText(optionList.get(i));
            radioButtons[i].setTextSize(14);

            quizOptionsRadioGroup.addView(radioButtons[i]);

        }
        quizOptionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
            Variable to keep track of whether the current option has already been checked
             prior to checking another option that is not the current option
             */
            boolean alreadyChecked = true;
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = radioGroup.findViewById(checkedId);

                //String value of the selected RadioButton
                String ans = checkedRadioButton.getText().toString();

                //Compares the above String with the correct option defined in the model class
                //Increments score if they match
                    if (ans == packageModel.getCorrectOption()) {

                        if (alreadyChecked==true){
                        score++;
                        alreadyChecked = false;}

                        /**In a case where a user selected the correct score but changes their mind later on.
                         * Decrement the score in such cases
                         */
                    } else if (alreadyChecked == false){
                        score--;
                        alreadyChecked = true;

                        //Avoids having a negative score
                        if (score < 0) {
                            score = 0;
                        }
                    }

                /**Converts the score to a string and send it as an intent through the BroadCast manager to
                 * another activity where the score will be used without necessarily opening that activity
                 */
                String ss = String.valueOf(score);
                    Intent intent = new Intent("score");
                    intent.putExtra("rscore", ss);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }

        });

    }


    @Override
    public int getItemCount() {
        return mModelDataList.size();

    }
}