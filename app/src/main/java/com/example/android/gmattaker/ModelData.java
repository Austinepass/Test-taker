package com.example.android.gmattaker;

import java.util.List;

public class ModelData{
    private String mQuestions;
    private List<String> mAnswers;
    private String mCorrectOption;

    public ModelData(String questions, List<String> answers, String correctOption){
        mQuestions = questions;
        mAnswers = answers;
        mCorrectOption = correctOption;
    }
    public String getQuestions(){
        return mQuestions;
    }
    public List<String> getAnswers(){
        return mAnswers;
    }
    public String getCorrectOption(){
        return mCorrectOption;
    }

}
