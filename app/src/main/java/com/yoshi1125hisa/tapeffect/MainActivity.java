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

//画面dpを取得して画像サイズを準備
        float dp = getResources().getDisplayMetrics().density;
        int imgSize = (int) (50 * dp);

//画像を設定
        final ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.ic_launcher_background);

//ImageViewに画像サイズを設定
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgSize, imgSize);
        img.setLayoutParams(params);

//画像の真ん中にタッチポイントが来るように設定
        img.setX(pointX - imgSize / 2);
        img.setY(pointY - imgSize / 2);
        img.setScaleType(ImageView.ScaleType.FIT_XY);

//画像を半分のサイズに縮小
        img.setScaleX(0.5f);
        img.setScaleY(0.5F);

//画像を描画
        tapLayout.addView(img);

//0.3秒かけて元のサイズに拡大しながら透明になるアニメーション
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
//アニメーション終了時に削除
                        tapLayout.removeView(img);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                }).start();


        return super.onTouchEvent(event);
    }
}