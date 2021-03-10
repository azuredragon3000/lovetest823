package com.myapp.lovetest_azuredragon3000.quiz.object;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.RadioButton;
import android.widget.TextView;

import com.myapp.lovetest_azuredragon3000.quiz.common.QuizInstance;

import java.util.ArrayList;

public abstract class AbsQuiz {
    public QuizInstance dbHelper;
    public ArrayList<Question> questionList;
    public Context context;
    public ArrayList<RadioButton> option;
    public InforQuestion ques;
    public TextView quest;
    public AbsQuiz(Context context){
        this.context = context;
    }
    public int score;
    public int ans;
    public long timeLeftInMillis;
    public CountDownTimer countDownTimer;

    public void getDataFromDB(int version, String name){
        dbHelper = QuizInstance.getInstance(context,version,name);
    }
    public void getQuestion(int category, String difficulty){
        questionList = dbHelper.getQuestions(category,difficulty);
    }

    public void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                showToLayout(ques);
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                showToLayout(ques);
                checkAnswer();
            }
        }.start();
    }

    public String revert(int i){
        switch (i){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            default:
                return "SOMETHING WRONG";
        }
    }
    public void checkradionButton(int answer) {
        if(answer == ans){
            score = score + 1;
        }
    }

    public abstract void showNextquestion();
    public abstract void checkAnswer();
    public abstract void showToLayout(com.myapp.lovetest_azuredragon3000.quiz.object.InforQuestion ques);
    public abstract void prepare();

}
