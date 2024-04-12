package com.example.timer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private int seconds=0;
    private boolean isRunning = false;
    TextView time;
    int flag=1;
    int colorFlag=0;
    ConstraintLayout mainLayout;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        runTimer();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            time.setText("00:00:00");
            seconds = 0;
            flag = 1;
            isRunning=false;
            return super.onDoubleTap(e);
        }
        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            if(flag==0){
                isRunning=false;
                flag=1;
            } else{
                isRunning=true;
                flag=0;
            }
            return super.onSingleTapConfirmed(e);
        }
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            if(colorFlag==0) {
                mainLayout.setBackgroundColor(getColor(R.color.white));
                time.setTextColor(getColor(R.color.black));
                colorFlag=1;
            } else{
                mainLayout.setBackgroundColor(getColor(R.color.black));
                time.setTextColor(getColor(R.color.white));
                colorFlag=0;
            }
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(this.gestureDetector.onTouchEvent(motionEvent)){
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    void init(){
        time = findViewById(R.id.txt_time);
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        mainLayout = findViewById(R.id.main_layout);
    }
    void runTimer(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning) seconds++;
                int sec = seconds%60;
                int min = seconds/60;
                int hrs = seconds/3600;

                String timeStr = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, min, sec);
                time.setText(timeStr);

                handler.postDelayed(this, 1000);
            }
        });
    }
}