package com.myapp.lovetest_azuredragon3000.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.lovetest_azuredragon3000.R;

public class QuizMain extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_DIFFCULTY = "DIFF";
    Intent intent;
    private int categoryID;
    private String difficulty;

    ImageView view1,view2,view3;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main);

        view1 = findViewById(R.id.danhchochang);
        view2 = findViewById(R.id.danhchonang);
        view3 = findViewById(R.id.danhchocahai);

        intent = new Intent(QuizMain.this, QuizChild.class);

        view1.setOnClickListener(v -> {
            //visible_tv();
            categoryID = 2;
            difficulty = "EASY";
            sendData();
        });

        view2.setOnClickListener(v -> {
           // visible_tv();
            categoryID = 3;
            difficulty = "EASY";
            sendData();
        });

        view3.setOnClickListener(v -> {
           // visible_tv();
            categoryID = 1;
            difficulty = "EASY";
            sendData();
        });
    }

    private void invisible_tv() {
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
    }

    private void visible_tv() {
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizChild.EXTRA_SCORE,0);
                TextView tv  = findViewById(R.id.kq);
                if(score == 8){
                    tv.setText("quá tuyệt vời, kết quả trên cả mong đợi, 2 bạn cực kỳ hợp nhau");
                }else if(score < 8){
                    tv.setText("tuyệt vời, hợp đấy nhưng cần phải giành thời gian hỏi thăm nhiều hơn");
                }else if(score < 3){
                    tv.setText("có vẻ hợp nhưng người ấy còn dè chừng, nên cố gắng tiếp cận");
                }else if(score < 2){
                    tv.setText("cố gắng xíu sẻ có kết quả ");
                }else{
                    tv.setText("2 bạn có vẻ không hợp lắm, làm bạn củng được đúng không");
                }
            }
        }
    }

    private void sendData() {
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_DIFFCULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }
}