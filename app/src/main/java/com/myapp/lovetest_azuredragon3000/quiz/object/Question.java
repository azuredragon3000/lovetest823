package com.myapp.lovetest_azuredragon3000.quiz.object;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable{

    private int id;
    private String question;

    ArrayList<String> list_option = new ArrayList<>();
    private int answerNr;
    private String difficulty;
    private int categoryID;

    public Question(){
    }

    public Question(String question, ArrayList<String> list_option, int answerNr, String difficulty, int categoryID) {
        this.question = question;
        this.list_option = list_option;
        this.answerNr = answerNr;
        this.difficulty = difficulty;
        this.categoryID = categoryID;
    }

    public void setList_option(ArrayList<String> list_option) {
        this.list_option = list_option;
    }
    public ArrayList<String> getList_option() {
        return this.list_option;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        for(int i=0;i<list_option.size();i++){
            list_option.set(i,in.readString());
        }
        answerNr = in.readInt();
        difficulty = in.readString();
        categoryID = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public com.myapp.lovetest_azuredragon3000.quiz.object.Question createFromParcel(Parcel in) {
            return new com.myapp.lovetest_azuredragon3000.quiz.object.Question(in);
        }

        @Override
        public com.myapp.lovetest_azuredragon3000.quiz.object.Question[] newArray(int size) {
            return new com.myapp.lovetest_azuredragon3000.quiz.object.Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(question);
        for(int i=0;i<list_option.size();i++){
            dest.writeString(list_option.get(i));
        }
        dest.writeInt(answerNr);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);

    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
