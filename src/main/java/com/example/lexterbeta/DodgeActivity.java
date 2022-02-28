package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DodgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        setContentView(R.layout.activity_dodge);
        Button button = findViewById(R.id.Enter);
        configNextButton();// this is the back button

        final TextView attack_speed1 = (TextView) findViewById(R.id.speed1);// for attackers speed
        final TextView attack_speed2 = (TextView) findViewById(R.id.speed2);// for defenders speed
        final TextView attack_acc1 = (TextView) findViewById(R.id.acc1);// for attack accuracy
        final TextView attack_acc2 = (TextView) findViewById(R.id.acc2);// for attack roll
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll

        button.setOnClickListener(new View.OnClickListener() {// For the enter button
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(attack_speed1.getText().toString()) || Double.parseDouble(attack_speed1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    attack_speed1.setError("I might be wrong or missing");
                } else if (TextUtils.isEmpty(attack_speed2.getText().toString()) || Double.parseDouble(attack_speed2.getText().toString()) < 1) {
                    attack_speed2.setError("Forgot me or wronged me");
                } else if (TextUtils.isEmpty(attack_acc1.getText().toString()) || Double.parseDouble(attack_acc1.getText().toString()) < 1 || Double.parseDouble(attack_acc1.getText().toString()) > 100) {
                    attack_acc1.setError("Missed me or out of bounds");
                } else if (TextUtils.isEmpty(attack_acc2.getText().toString()) || Double.parseDouble(attack_acc2.getText().toString()) < 1 || Double.parseDouble(attack_acc2.getText().toString()) > 100 || Double.parseDouble(attack_acc2.getText().toString()) > Double.parseDouble(attack_acc1.getText().toString())) {
                    attack_acc2.setError("You might not be able to dodge this error");
                } else {
                    double attack_speed_1 = Double.parseDouble(attack_speed1.getText().toString());
                    double attack_speed_2 = Double.parseDouble(attack_speed2.getText().toString());
                    double attack_acc_1 = Double.parseDouble(attack_acc1.getText().toString());
                    double attack_acc_2 = Double.parseDouble(attack_acc2.getText().toString());
                    double margin = attack_speed_1 * .2;// 20% margin check
                    double check = attack_speed_1 - margin;

                    if (attack_speed_2 < check) {
                        openDialog();// for margin fail check, too slow
                    } else {
                        double success = 0;
                        success = attack_acc_1 - attack_acc_2;
                        openDialog2(success, rollsound);// for your chance to dodge
                    }
                }
            }
        });
    }

    private void openDialog3(int roll, double success) {// Dice roll dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Dice Roll");
        if (success > roll) {
            dialog.setMessage("You rolled a " + roll + " you dodged!");
        } else {
            dialog.setMessage("You rolled a " + roll + " the attack lands...");
        }

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog2(final double success, final MediaPlayer rollsound) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Dodge Chance");
        dialog.setMessage("You must roll on a D100 less than " + success + " for you to dodge.\nWould you like to roll it now? Remember you are risking extra damage.");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Random r = new Random();// D100 roll
                int roll = r.nextInt(100) + 1;
                rollsound.start();
                openDialog3(roll, success);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("You're Too Slow");
        dialog.setMessage("Sorry, but your Pokemon is too slow compared to the opponent to dodge an incoming attack.");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.Back);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DodgeActivity.this, MainHead.class));
            }
        });
    }
}