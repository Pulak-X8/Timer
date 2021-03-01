package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSeekBar;
    TextView timerText;
    Boolean CounterIsActive = false;
    Boolean ButtonClicked = true;
    Button ControlButton;
    CountDownTimer countDownTimer;

    public void UpdateTimer(int secondsLeft) {

        int minutes = secondsLeft / 60;
        int RemSec = secondsLeft % 60;

        String secString = Integer.toString(RemSec);

        if (secondsLeft <= 9) {

            secString = "0" + secString;
        }

        timerText.setText(Integer.toString(minutes) + ":" + secString);
    }

    public void ControlTimer(View view) {

        if (CounterIsActive == false) {
            CounterIsActive = true;
            timeSeekBar.setEnabled(false);
            ControlButton.setText("Reset");

            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    UpdateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    timerText.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                    mplayer.start();
                    timerText.setText("0:00");
                    timeSeekBar.setProgress(0);
                    countDownTimer.cancel();
                    ControlButton.setText("Start");
                    timeSeekBar.setEnabled(true);

                }
            }.start();

        } else {

            timerText.setText("0:00");
            timeSeekBar.setProgress(0);
            countDownTimer.cancel();
            ControlButton.setText("Start");
            timeSeekBar.setEnabled(true);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = findViewById(R.id.seekBar);
        timerText = findViewById(R.id.TimerText);
        ControlButton = findViewById(R.id.ControlButton);

        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(0);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                UpdateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}