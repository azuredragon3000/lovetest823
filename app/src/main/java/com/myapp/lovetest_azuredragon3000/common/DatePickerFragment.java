package com.myapp.lovetest_azuredragon3000.common;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    Button buttonText;
    boolean iswitch = false;
    public DatePickerFragment(Button buttonText,boolean iswitch){
        this.buttonText = buttonText;
        this.iswitch = iswitch;
    }

    TextView textView;
    public DatePickerFragment(TextView textView, boolean iswitch){
        this.textView = textView;
        this.iswitch = iswitch;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();
        // int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = 1988;

        return new DatePickerDialog(getActivity(),
                AlertDialog.THEME_HOLO_LIGHT, this,year,month,day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){
        // Do something with the chosen date
        if(iswitch){
            int actualMonth = month+1; // Because month index start from zero
            buttonText.setText(day+"/"+actualMonth+"/"+year);
        }else{
            int actualMonth = month+1; // Because month index start from zero
            textView.setText(day+" "+actualMonth+" "+year);
        }

    }
}
