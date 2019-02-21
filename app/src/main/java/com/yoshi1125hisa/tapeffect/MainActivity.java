package com.yoshi1125hisa.tapeffect;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout tapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapLayout = findViewById(R.id.tapLayout);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        float dp = getResources().getDisplayMetrics().density;
        int imgSize = (int) (50 * dp);

        final ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.ic_launcher_background);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgSize, imgSize);
        img.setLayoutParams(params);

        img.setX(pointX - imgSize / 2);
        img.setY(pointY - imgSize / 2);
        img.setScaleType(ImageView.ScaleType.FIT_XY);

        img.setScaleX(0.5f);
        img.setScaleY(0.5F);

        tapLayout.addView(img);

        ViewCompat.animate(img)
                .setDuration(300)
                .alpha(0)
                .scaleX(1)
                .scaleY(1)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        tapLayout.removeView(img);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                }).start();


        return super.onTouchEvent(event);
    }
}
