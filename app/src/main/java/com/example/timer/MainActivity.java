package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean isRunning = false;
    TextView time;
    Button startBtn, stopBtn, pauseBtn;
    int flag=1;

    ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
        runTimer();

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_SHORT).show();
            }
        });

        mainLayout = findViewById(R.id.main_layout);

//        mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Tap Detected!", Toast.LENGTH_SHORT).show();
//            }
//        });






        pauseBtn.setText("start");
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isRunning=true;
//                startBtn.setVisibility(View.GONE);
//            }
//        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0){
                    isRunning=false;
                    pauseBtn.setText("resume");
                    flag=1;
                } else{
                    isRunning=true;
                    pauseBtn.setText("pause");
                    flag=0;
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText("00:00:00");
                seconds = 0;
                flag = 1;
                isRunning=false;
                pauseBtn.setText("start");
            }
        });
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
//        startBtn = findViewById(R.id.btn_start);
        pauseBtn = findViewById(R.id.btn_pause);
        stopBtn = findViewById(R.id.btn_stop);
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