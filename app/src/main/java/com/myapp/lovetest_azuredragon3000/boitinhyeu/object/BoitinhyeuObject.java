package com.myapp.lovetest_azuredragon3000.boitinhyeu.object;

import java.util.Random;

public class BoitinhyeuObject {
    public boolean flagCheck;
    public int numberLove;
    public String contentLove;

    public void start_app(String strManName, String strNameWoman) {
        flagCheck = true;
        if((strManName.equals(null))||(strNameWoman.equals(null))){
            flagCheck = false;
        }else{
            numberLove = funRandom();
            if((numberLove >= 0) && (numberLove <= 50) ){
                contentLove = "Quan tâm bạn ấy 1 xíu nhe";
            }else if((numberLove >= 50) && (numberLove <= 70)){
                contentLove  = "Quá hợp, tiếp tục tiến tới nhe";
            }else{
                contentLove   = "Hai người là có duyên vợ chồng rồi";
            }
        }
    }
    private int funRandom() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        return r.nextInt(high-low) + low;
    }
}
