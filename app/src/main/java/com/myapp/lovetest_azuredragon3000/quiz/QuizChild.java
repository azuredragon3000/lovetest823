package com.myapp.lovetest_azuredragon3000.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.myapp.lovetest_azuredragon3000.R;

import java.util.ArrayList;

public class QuizChild extends AppCompatActivity {

    Quiz2 intansce;
    public static final String EXTRA_SCORE = "score";
    public static final String EXTRA_RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_child);

        TextView quest = findViewById(R.id.question);
        TextView textViewCountdown = findViewById(R.id.text2);
        ArrayList<RadioButton> option = new ArrayList<>();
        RadioGroup rbGroup = findViewById(R.id.radio_group);
        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);
        RadioButton option4 = findViewById(R.id.option4);
        RadioButton option5 = findViewById(R.id.option5);
        RadioButton option6 = findViewById(R.id.option6);
        option.add(option1);
        option.add(option2);
        option.add(option3);
        option.add(option4);
        option.add(option5);
        option.add(option6);
        intansce = new Quiz2(this, quest, option, textViewCountdown,rbGroup, QuizChild.this);
        intansce.getDataFromDB(51,"MyAwesomeQuiz.db");

        Intent intent = getIntent();
        final int categoryID = intent.getIntExtra(QuizMain.EXTRA_CATEGORY_ID, 0);
        String difficulty = intent.getStringExtra(QuizMain.EXTRA_DIFFCULTY);
        intansce.getQuestion(categoryID, difficulty);
        intansce.showNextquestion();

        Button b1 = findViewById(R.id.button_confirm);
        b1.setOnClickListener(v -> {
            intansce.checkAnswer();
            intansce.showNextquestion();
        });
    }

    void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, intansce.score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishQuiz();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intansce.countDownTimer != null) {
            intansce.countDownTimer.cancel();
        }
    }
}