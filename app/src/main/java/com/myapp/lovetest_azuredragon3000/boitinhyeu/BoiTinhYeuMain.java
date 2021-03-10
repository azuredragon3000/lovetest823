package com.myapp.lovetest_azuredragon3000.boitinhyeu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.myapp.lovetest_azuredragon3000.R;
import com.myapp.lovetest_azuredragon3000.boitinhyeu.object.BoitinhyeuObject;
import com.myapp.lovetest_azuredragon3000.common.MyDialog;

public class BoiTinhYeuMain extends AppCompatActivity  {

    Intent myIntent;
    BoitinhyeuObject instance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_one);

        myIntent = new Intent(BoiTinhYeuMain.this, BoiTinhYeuChild.class);

        Button buttonStart =  findViewById(R.id.buttonTest);

        String strManName = getData(R.id.manName);
        String strNameWoman = getData(R.id.womanName);

        instance = new BoitinhyeuObject();
        instance.start_app(strManName,strNameWoman);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                if(instance.flagCheck){
                    myIntent.putExtra("content", instance.contentLove);
                    myIntent.putExtra("number", instance.numberLove);
                    BoiTinhYeuMain.this.startActivity(myIntent);
                }else{
                    openDiagLog(" Vui Lòng Điền Đúng Thông tin");
                }
            }
        });
    }

    private void openDiagLog(String text) {
        MyDialog myDialog = new MyDialog(text);
        myDialog.show(getSupportFragmentManager(),"dialog");
    }
    private String getData(int name) {
        TextInputLayout tv = findViewById(name);
        return tv.getEditText().toString();
    }
}