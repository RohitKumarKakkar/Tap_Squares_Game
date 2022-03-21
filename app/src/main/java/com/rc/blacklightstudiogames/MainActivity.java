package com.rc.blacklightstudiogames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    FrameLayout redCard, greenCard, yellowCard, blueCard;
    TextView tvScore;
    int randomNumber;
    final int min = 1;
    final int max = 5;
    int score, dialogScore;
    int currentposition;
    Dialog dialog, dialog2;
    int previousnumber = 0;
    Timer timer, timer2;
    boolean tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tvScore);
        redCard = findViewById(R.id.redCard);
        greenCard = findViewById(R.id.greenCard);
        yellowCard = findViewById(R.id.yellowCard);
        blueCard = findViewById(R.id.blueCard);

//Dialog 1 Init
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.startdialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

//Dialog 2 Init
        dialog2 = new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.restartdialog);
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(false);

//Start Button
        Button btnStart = dialog.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap = true;
                startGame();
                dialog.dismiss();
            }
        });


    }

    public void generateRandom() {
        timer2.cancel();
        timer2.purge();
        randomNumber = (int) (Math.random() * (max - min)) + min;
        if (previousnumber != 0) {
            if (randomNumber == previousnumber) {
                //  Log.e("randomNumber", "" + randomNumber);
                if (randomNumber == 4) {
                    randomNumber--;
                } else {
                    randomNumber++;
                }
                // Log.e("randomNumber", "Added + 1 " + randomNumber);
            }
        }

        if (randomNumber == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tap = false;
                    previousnumber = 1;
                    redCard.setBackgroundColor(Color.parseColor("#C1C1C1"));
                    blueCard.setBackgroundColor(Color.parseColor("#3854FF"));
                    greenCard.setBackgroundColor(Color.parseColor("#43A047"));
                    yellowCard.setBackgroundColor(Color.parseColor("#FFB300"));
                }
            });
        } else if (randomNumber == 2) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tap = false;
                    previousnumber = 2;
                    blueCard.setBackgroundColor(Color.parseColor("#C1C1C1"));
                    redCard.setBackgroundColor(Color.parseColor("#F31C17"));
                    greenCard.setBackgroundColor(Color.parseColor("#43A047"));
                    yellowCard.setBackgroundColor(Color.parseColor("#FFB300"));
                }
            });
        } else if (randomNumber == 3) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tap = false;
                    previousnumber = 3;
                    greenCard.setBackgroundColor(Color.parseColor("#C1C1C1"));
                    redCard.setBackgroundColor(Color.parseColor("#F31C17"));
                    blueCard.setBackgroundColor(Color.parseColor("#3854FF"));
                    yellowCard.setBackgroundColor(Color.parseColor("#FFB300"));
                }
            });
        } else if (randomNumber == 4) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tap = false;
                    previousnumber = 4;
                    yellowCard.setBackgroundColor(Color.parseColor("#C1C1C1"));
                    redCard.setBackgroundColor(Color.parseColor("#F31C17"));
                    blueCard.setBackgroundColor(Color.parseColor("#3854FF"));
                    greenCard.setBackgroundColor(Color.parseColor("#43A047"));
                }
            });
        }
    }

    public void startGame() {
        check();
        timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                generateRandom();
            }
        };
        timer.schedule(hourlyTask, 1000, 1000);
        clickColor();
        check();
    }

    public void clickColor() {

        redCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap = true;
                currentposition = 1;
                if (currentposition == randomNumber) {
                    score++;
                    tvScore.setText("Score : " + score);
                    dialogScore = score;
                } else {
                    timer.cancel();
                    timer.purge();
                    ShowDialog2();
                }
            }
        });

        blueCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap = true;
                currentposition = 2;
                if (currentposition == randomNumber) {
                    score++;
                    tvScore.setText("Score : " + score);
                    dialogScore = score;
                } else {
                    timer.cancel();
                    timer.purge();
                    ShowDialog2();
                }
            }
        });

        greenCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap = true;
                currentposition = 3;
                if (currentposition == randomNumber) {
                    score++;
                    tvScore.setText("Score : " + score);
                    dialogScore = score;
                } else {
                    timer.cancel();
                    timer.purge();
                    ShowDialog2();
                }
            }
        });

        yellowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap = true;
                currentposition = 4;
                if (currentposition == randomNumber) {
                    score++;
                    tvScore.setText("Score : " + score);
                    dialogScore = score;
                } else {
                    timer.cancel();
                    timer.purge();
                    ShowDialog2();
                }
            }
        });

    }

    public void ShowDialog2() {
        Button btnRestart = dialog2.findViewById(R.id.btnRestart);
        TextView tvScoreDialog = dialog2.findViewById(R.id.tvScoredialog);
        tvScoreDialog.setText("Your Score : " + dialogScore);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.cancel();
                timer2.cancel();
                timer.purge();
                timer2.purge();
                dialog2.show();
            }
        });

    }

    public void check() {

        timer2 = new Timer();
        TimerTask hourlyTask2 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (tap == false) {
                            Log.e("Tap", "False");
                            timer.cancel();
                            timer2.cancel();
                            timer.purge();
                            timer2.purge();
                            ShowDialog2();
                        } else {
                            Log.e("Tap", "True");
                        }
                    }
                });
            }
        };
        timer2.schedule(hourlyTask2, 2000, 2000);

    }

}