package com.myapp.lovetest_azuredragon3000.quiz;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.myapp.lovetest_azuredragon3000.quiz.object.AbsQuiz;
import com.myapp.lovetest_azuredragon3000.quiz.object.InforQuestion;
import com.myapp.lovetest_azuredragon3000.quiz.object.Question;

import java.util.ArrayList;


public class Quiz2 extends AbsQuiz {

    //private static final long COUNTDOWN_IN_MILLIS = 30000;
    private int index;
    private final QuizChild activity2;
    public TextView textViewCountdown;
    public RadioGroup rbGroup;
    public RadioButton rbSelected;

    Quiz2(Context context, TextView quest, ArrayList<RadioButton> option, TextView textViewCountdown, RadioGroup rbGroup, QuizChild activity) {
        super(context);
        this.quest = quest;
        this.option = option;
        this.textViewCountdown = textViewCountdown;
        activity2 = activity;
        this.rbGroup = rbGroup;
        index=0;
    }

    @Override
    public void showNextquestion() {
        if(index <= questionList.size()-1){
            resetOption();
            Question currentQuestion = questionList.get(index);
            ans = currentQuestion.getAnswerNr();
            ques = new InforQuestion(currentQuestion);
            showToLayout(ques);
            index++;
        }else{
            activity2.finishQuiz();
        }
    }

    private void resetOption() {
        rbGroup.clearCheck();
    }

    @Override
    public void checkAnswer() {
        rbSelected = activity2.findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rbSelected) + 1;

        /* score will be increase after check ans */
        checkradionButton(answer);
    }

    @Override
    public void showToLayout(InforQuestion ques) {
        quest.setText(ques.question);
        for(int i=0;i<ques.option.size();i++){
            if(!ques.option.get(i).equals("")){
                option.get(i).setText(ques.option.get(i));
                option.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void prepare() {

    }
}
