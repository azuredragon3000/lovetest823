package com.myapp.lovetest_azuredragon3000.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.myapp.lovetest_azuredragon3000.R;
import com.myapp.lovetest_azuredragon3000.boitinhyeu.BoiTinhYeuMain;
import com.myapp.lovetest_azuredragon3000.demsotuoi.HowOldAreYou;
import com.myapp.lovetest_azuredragon3000.quiz.QuizMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button main1 = findViewById(R.id.main1);
        Button main3 = findViewById(R.id.main3);
        Button main4 = findViewById(R.id.main4);
        main1.setOnClickListener(this);

        main3.setOnClickListener(this);
        main4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.main1:
                intent = new Intent(MainActivity.this, BoiTinhYeuMain.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.main3:
                intent = new Intent(MainActivity.this, HowOldAreYou.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.main4:
                intent = new Intent(MainActivity.this, QuizMain.class);
                MainActivity.this.startActivity(intent);
                break;
        }
    }
}