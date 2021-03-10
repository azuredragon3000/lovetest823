package com.myapp.lovetest_azuredragon3000.quiz.object;

import java.util.ArrayList;

public class InforQuestion {
    public String question;
    public ArrayList<String> option = new ArrayList<String>();
    public InforQuestion(Question ques){
        this.question = ques.getQuestion();
        this.option.addAll(ques.getList_option());
    }
}
