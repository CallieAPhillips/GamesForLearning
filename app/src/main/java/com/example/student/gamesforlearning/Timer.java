package com.example.student.gamesforlearning;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    private TextView mTextMessage;
    private double time;
    private Chronometer timer;
    private EditText scoreDisplay;
    private boolean timing;
    private Button startStopButton;
    private int points;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timer = (Chronometer) findViewById(R.id.chronometer);
//        timer.setFormat("H:MM:SS");
        scoreDisplay = (EditText) findViewById(R.id.pointsText);
        startStopButton = (Button) findViewById(R.id.StartStopButton);
        timing = false;
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                int elapsedTime = (int)(SystemClock.elapsedRealtime() - timer.getBase())/1000;
                points = (elapsedTime!=0 && (elapsedTime < 120))? ((int) (5000000 / elapsedTime)): elapsedTime!=0? 400 : 50000;
                scoreDisplay.setText(Integer.toString(points));
            }

        });

    }

    protected void startStopTimer(View view){
        if(timing){
            startStopButton.setText("Begin");
            timer.stop();
        }else{
            startStopButton.setText("Done");
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
        }
        timing = !timing;
    }

    protected void startTimer(){

    }

    protected void stopTimer(){}


}
