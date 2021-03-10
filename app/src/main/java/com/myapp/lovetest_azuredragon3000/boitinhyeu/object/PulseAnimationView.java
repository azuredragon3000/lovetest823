package com.myapp.lovetest_azuredragon3000.boitinhyeu.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.RequiresApi;

import com.myapp.lovetest_azuredragon3000.R;


public class PulseAnimationView extends View {
    private float mRadius;
    private int rect;
    private float mX;
    private float mY;
    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();


    public PulseAnimationView(Context context,String content,String numberLove) {this(context, null,content,numberLove);}
    private final Paint mPaint = new Paint();
    private static final int COLOR_ADJUSTER = 5;

    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;
    private int width,height;
    private Drawable mCustomImage;
    private Paint paint;
    private String content;
    private String numberLove;
    private TextPaint textPaint;
    private Bitmap mCustomImage2;
    private boolean  check;

    ObjectAnimator growAnimator2;
    public PulseAnimationView(Context context, AttributeSet attrs, String content,String numberLove) {
        super(context, attrs);
        mCustomImage = context.getResources().getDrawable(R.drawable.heart);
        mCustomImage2 = BitmapFactory.decodeResource(getResources(),R.drawable.background2);

        width = getScreenWidth();
        height = getScreenHeight();
        mCustomImage2 = Bitmap.createScaledBitmap(mCustomImage2,width,height,false);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        this.content = content;
        this.numberLove = numberLove;

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius / COLOR_ADJUSTER);
        invalidate();
    }

    public void setRect(int rect) {
        this.rect = rect;
        invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

        ObjectAnimator growAnimator = ObjectAnimator.ofInt(this,
                "rect", 1, 512);
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

         growAnimator2 = ObjectAnimator.ofInt(this,
                "rect", 1, 512);
        growAnimator2.setDuration(ANIMATION_DURATION);
        growAnimator2.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofInt(this,
                "rect", 512, 1);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearInterpolator());

        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator repeatAnimator = ObjectAnimator.ofInt(this,
                "rect", 1, 512);
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);
        repeatAnimator.setRepeatCount(1);
        repeatAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        mPulseAnimatorSet.play(growAnimator2).after(shrinkAnimator);
        //mPulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator);

        mPulseAnimatorSet.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mCustomImage2,0,0,null);
        mCustomImage.setBounds((width/2)-rect,(height/2)-rect,(width/2)+rect,(height/2)+rect);
        mCustomImage.draw(canvas);
        if(growAnimator2.isStarted()){
            check = true;
        }
        if((!growAnimator2.isRunning())&&check){
            int headerFontSize = 14;
            Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(), "fonts/pacifico.ttf");
           // String header = "Previous Play Calls";
            float textsize = paint.measureText(content);
            int xOffset = getApproxXToCenterText(content, custom_font, headerFontSize, width);
            canvas.drawText(content, (width-textsize)/2, 400, paint);

            canvas.drawText(numberLove,(width/2)-20,(height/2)-20,paint);
        }

    }

    public static int getApproxXToCenterText(String text, Typeface typeface, int fontSize, int widthToFitStringInto) {
        Paint p = new Paint();
        p.setTypeface(typeface);
        p.setTextSize(fontSize);
        float textWidth = p.measureText(text);
        int xOffset = (int)((widthToFitStringInto-textWidth)/2f);// - (int)(fontSize/2f);
        return xOffset;
    }
}
