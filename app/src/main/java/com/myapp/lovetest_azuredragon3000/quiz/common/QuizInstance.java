package com.myapp.lovetest_azuredragon3000.quiz.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.myapp.lovetest_azuredragon3000.quiz.object.Category;
import com.myapp.lovetest_azuredragon3000.quiz.object.QuizDB;

import java.util.ArrayList;


public class QuizInstance extends QuizDB {

    private static final String TOKEN = "~";
    @SuppressLint("StaticFieldLeak")
    private static QuizInstance instance;

    public QuizInstance(@Nullable Context context, int database_version, String database_name) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    public static synchronized QuizInstance getInstance(Context context, int database_version, String database_name){
        if(instance == null){
            instance = new QuizInstance(context.getApplicationContext(),  database_version,  database_name);
        }
        return instance;
    }

    @Override
    public void fill_category() {
        Category c1 = new Category("Trắc Nghiệm dành cho nam");
        addCategory(c1);
        Category c2 = new Category("Trắc Nghiệm dành cho nữ");
        addCategory(c2);
        Category c3 = new Category("Trắc Nghiệm dành cho cả hai");
        addCategory(c3);
    }

    @Override
    public String[] processString(String[] textline, String ans, String diff, String category) {

        String question = "";
        ArrayList<String> m = new ArrayList<String>();
        for(int i=0;i<textline.length ; i++){
            int last = textline[i].length() -1 ;

            if(textline[i].equals("")){
                question = question + TOKEN + ans +  TOKEN +diff + TOKEN+category;
                m.add(question);
                question = "";
            }
            else if(textline[i].charAt(last) == '?'|| textline[i].charAt(last) == '.'){
                    question = question + textline[i] + TOKEN;
            }else{
                // log file
            }
        }

        String[] arr = new String[m.size()];

        // ArrayList to Array Conversion
        for (int i = 0; i < m.size(); i++)
            arr[i] = m.get(i);
        return arr;
    }
}

