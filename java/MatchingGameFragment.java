package cs.ucmo.finalproject;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.spark.submitbutton.SubmitButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MatchingGameFragment extends Fragment implements View.OnClickListener {

    Chronometer chronometer;

    AnimationDrawable open_door;
    AnimationDrawable winning_door;
    AnimationDrawable close_door;

    TextView door_count_text;

    static int numberOfClicks;

    Integer[] random_door = new Integer[] {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};

    static int time_count;

    long time_start = 0;
    long time_end = 0;

    ImageView button1;
    ImageView button2;
    ImageView button3;
    ImageView button4;
    ImageView button5;
    ImageView button6;
    ImageView button7;
    ImageView button8;
    ImageView button9;
    ImageView button10;
    ImageView button11;
    ImageView button12;
    ImageView button13;
    ImageView button14;
    ImageView button15;
    ImageView button16;

    boolean door1 = false;
    boolean door2 = false;
    boolean door3 = false;
    boolean door4 = false;
    boolean door5 = false;
    boolean door6 = false;
    boolean door7 = false;
    boolean door8 = false;
    boolean door9 = false;
    boolean door10 = false;
    boolean door11 = false;
    boolean door12 = false;
    boolean door13 = false;
    boolean door14 = false;
    boolean door15 = false;
    boolean door16 = false;


    int chrono_count = 0;


    int doorNumberSelected = -1;
    int doorValueSelected = -1;
    boolean isDoorSelected = false;

    int[][] boardStatus = new int[4][4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.matching_game, container, false);

        setRandom_door();

        chronometer = (Chronometer) view.findViewById(R.id.chronometer_button);

        door_count_text = view.findViewById(R.id.door_clicked_count);


        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        button7 = view.findViewById(R.id.button7);
        button8 = view.findViewById(R.id.button8);
        button9 = view.findViewById(R.id.button9);
        button10 = view.findViewById(R.id.button10);
        button11 = view.findViewById(R.id.button11);
        button12 = view.findViewById(R.id.button12);
        button13 = view.findViewById(R.id.button13);
        button14 = view.findViewById(R.id.button14);
        button15 = view.findViewById(R.id.button15);
        button16 = view.findViewById(R.id.button16);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);

        numberOfClicks = 0;

        setBoard();


        return view;
    }

    private void setButtonsEnabled(boolean value){
        button1.setEnabled(value);
        button2.setEnabled(value);
        button3.setEnabled(value);
        button4.setEnabled(value);
        button5.setEnabled(value);
        button6.setEnabled(value);
        button7.setEnabled(value);
        button8.setEnabled(value);
        button9.setEnabled(value);
        button10.setEnabled(value);
        button11.setEnabled(value);
        button12.setEnabled(value);
        button13.setEnabled(value);
        button14.setEnabled(value);
        button15.setEnabled(value);
        button16.setEnabled(value);
    }


    @Override
    public void onClick(View view) {

        if(chrono_count == 0){
            time_start = System.currentTimeMillis();
            System.out.println("time start " + time_start);

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }

        chrono_count++;


        numberOfClicks++;
        door_count_text.setText(numberOfClicks + " doors clicked");

        boolean resetButtonPressed = false;

        switch (view.getId()) {
            case R.id.button1:

                System.out.println("Button Pressed...");
                if(!door1) {
                    setOpen_door(boardStatus[0][0]);
                    button1.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[0][0];
                        doorNumberSelected = 1;
                        System.out.println("door number selected = " + boardStatus[0][0]);
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                        if(checkForMatch(boardStatus[0][0]) && doorNumberSelected != 1){
                            door1 = true;
                            winningDoorAnimation(boardStatus[0][0]);
                            button1.setImageDrawable(winning_door);
                            winning_door.start();
                            winningDoorAnimation(doorValueSelected);
                            setDoorMatch(doorNumberSelected);
                            doorValueSelected = -1;
                            doorNumberSelected = -1;
                            isDoorSelected = false;


                        }
                        else {
                            //close doors
                                    setClose_door(boardStatus[0][0]);
                                    button1.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                        }

                        setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }

                break;

            case R.id.button2:

                if(!door2) {
                    setOpen_door(boardStatus[0][1]);
                    button2.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[0][1];
                        doorNumberSelected = 2;
                        System.out.println("door number selected = " + boardStatus[0][1]);
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                        if(checkForMatch(boardStatus[0][1]) && doorNumberSelected != 2){
                            door2 = true;
                            winningDoorAnimation(boardStatus[0][1]);
                            button2.setImageDrawable(winning_door);
                            winning_door.start();
                            winningDoorAnimation(doorValueSelected);
                            setDoorMatch(doorNumberSelected);
                            doorValueSelected = -1;
                            doorNumberSelected = -1;
                            isDoorSelected = false;
                        }
                        else {
                            //close doors
                                    setClose_door(boardStatus[0][1]);
                                    button2.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                        }
                        setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }

                break;

            case R.id.button3:

                System.out.println("Button Pressed...");
                if(!door3) {
                    setOpen_door(boardStatus[0][2]);
                    button3.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[0][2];
                        doorNumberSelected = 3;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                        if(checkForMatch(boardStatus[0][2]) && doorNumberSelected != 3){
                            door3 = true;
                            winningDoorAnimation(boardStatus[0][2]);
                            button3.setImageDrawable(winning_door);
                            winning_door.start();
                            winningDoorAnimation(doorValueSelected);
                            setDoorMatch(doorNumberSelected);
                            doorValueSelected = -1;
                            doorNumberSelected = -1;
                            isDoorSelected = false;
                        }
                        else {
                                    setClose_door(boardStatus[0][2]);
                                    button3.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                        }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button4:

                System.out.println("Button Pressed...");
                if(!door4) {
                    setOpen_door(boardStatus[0][3]);
                    button4.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[0][3];
                        doorNumberSelected = 4;
                        isDoorSelected = true;
                    } else {
                        //match
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                        if(checkForMatch(boardStatus[0][3]) && doorNumberSelected != 4){
                                    door4 = true;
                                    winningDoorAnimation(boardStatus[0][3]);
                                    button4.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                        }
                        else {
                                    setClose_door(boardStatus[0][3]);
                                    button4.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                        }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }

                break;

            case R.id.button5:

                System.out.println("Button Pressed...");
                if(!door5) {
                    setOpen_door(boardStatus[1][0]);
                    button5.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[1][0];
                        doorNumberSelected = 5;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                        if(checkForMatch(boardStatus[1][0]) && doorNumberSelected != 5){
                                    door5 = true;
                                    winningDoorAnimation(boardStatus[1][0]);
                                    button5.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                        }
                        else {
                                    setClose_door(boardStatus[1][0]);
                                    button5.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                        }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button6:
                System.out.println("Button Pressed...");
                if(!door6) {
                    setOpen_door(boardStatus[1][1]);
                    button6.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[1][1];
                        doorNumberSelected = 6;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[1][1]) && doorNumberSelected != 6){
                                    door6 = true;
                                    winningDoorAnimation(boardStatus[1][1]);
                                    button6.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[1][1]);
                                    button6.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button7:
                System.out.println("Button Pressed...");
                if(!door7) {
                    setOpen_door(boardStatus[1][2]);
                    button7.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[1][2];
                        doorNumberSelected = 7;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[1][2]) && doorNumberSelected != 7){
                                    door7 = true;
                                    winningDoorAnimation(boardStatus[1][2]);
                                    button7.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[1][2]);
                                    button7.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button8:
                System.out.println("Button Pressed...");
                if(!door8) {
                    setOpen_door(boardStatus[1][3]);
                    button8.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[1][3];
                        doorNumberSelected = 8;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[1][3]) && doorNumberSelected != 8){
                                    door8 = true;
                                    winningDoorAnimation(boardStatus[1][3]);
                                    button8.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[1][3]);
                                    button8.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button9:
                System.out.println("Button Pressed...");
                if(!door9) {
                    setOpen_door(boardStatus[2][0]);
                    button9.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[2][0];
                        doorNumberSelected = 9;
                        isDoorSelected = true;
                    } else {
                        //match found

                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[2][0]) && doorNumberSelected != 9){
                                    door9 = true;
                                    winningDoorAnimation(boardStatus[2][0]);
                                    button9.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[2][0]);
                                    button9.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button10:
                System.out.println("Button Pressed...");
                if(!door10) {
                    setOpen_door(boardStatus[2][1]);
                    button10.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[2][1];
                        doorNumberSelected = 10;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[2][1]) && doorNumberSelected != 10){
                                    door10 = true;
                                    winningDoorAnimation(boardStatus[2][1]);
                                    button10.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[2][1]);
                                    button10.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button11:
                System.out.println("Button Pressed...");
                if(!door11) {
                    setOpen_door(boardStatus[2][2]);
                    button11.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[2][2];
                        doorNumberSelected = 11;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[2][2]) && doorNumberSelected != 11){
                                    door11 = true;
                                    winningDoorAnimation(boardStatus[2][2]);
                                    button11.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[2][2]);
                                    button11.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button12:
                System.out.println("Button Pressed...");
                if(!door12) {
                    setOpen_door(boardStatus[2][3]);
                    button12.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[2][3];
                        doorNumberSelected = 12;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[2][3]) && doorNumberSelected != 12){
                                    door12 = true;
                                    winningDoorAnimation(boardStatus[2][3]);
                                    button12.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[2][3]);
                                    button12.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button13:
                System.out.println("Button Pressed...");
                if(!door13) {
                    setOpen_door(boardStatus[3][0]);
                    button13.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[3][0];
                        doorNumberSelected = 13;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[3][0]) && doorNumberSelected != 13){
                                    door13 = true;
                                    winningDoorAnimation(boardStatus[3][0]);
                                    button13.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[3][0]);
                                    button13.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button14:
                System.out.println("Button Pressed...");
                if(!door14) {
                    setOpen_door(boardStatus[3][1]);
                    button14.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[3][1];
                        doorNumberSelected = 14;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[3][1]) && doorNumberSelected != 14){
                                    door14 = true;
                                    winningDoorAnimation(boardStatus[3][1]);
                                    button14.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[3][1]);
                                    button14.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button15:
                System.out.println("Button Pressed...");
                if(!door15) {
                    setOpen_door(boardStatus[3][2]);
                    button15.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[3][2];
                        doorNumberSelected = 15;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[3][2]) && doorNumberSelected != 15){
                                    door15 = true;
                                    winningDoorAnimation(boardStatus[3][2]);
                                    button15.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[3][2]);
                                    button15.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            case R.id.button16:
                System.out.println("Button Pressed...");
                if(!door16) {
                    setOpen_door(boardStatus[3][3]);
                    button16.setImageDrawable(open_door);
                    open_door.start();

                    if (!isDoorSelected) {
                        doorValueSelected = boardStatus[3][3];
                        doorNumberSelected = 16;
                        isDoorSelected = true;
                    } else {
                        //match found
                        setButtonsEnabled(false);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(checkForMatch(boardStatus[3][3]) && doorNumberSelected != 16){
                                    door16 = true;
                                    winningDoorAnimation(boardStatus[3][3]);
                                    button16.setImageDrawable(winning_door);
                                    winning_door.start();
                                    winningDoorAnimation(doorValueSelected);
                                    setDoorMatch(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;

                                }
                                else {
                                    setClose_door(boardStatus[3][3]);
                                    button16.setImageDrawable(close_door);
                                    close_door.start();
                                    setDoorClose(doorNumberSelected);
                                    doorValueSelected = -1;
                                    doorNumberSelected = -1;
                                    isDoorSelected = false;
                                }
                                setButtonsEnabled(true);

                            }
                        }, 500);
                    }
                }
                break;

            default:
                break;

        }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (checkForWin()) {
                        time_end = System.currentTimeMillis();
                        System.out.println("time end " + time_end);


                        time_count = (int) TimeUnit.MILLISECONDS.toSeconds(time_end - time_start);

                        System.out.println("time count " + time_count);


                        MatchingGameFinishScreenFragment matchingGameFinishScreenFragment = new MatchingGameFinishScreenFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_list, matchingGameFinishScreenFragment);
                        ft.commit();
                    }
                }
            }, 1000);

        if(resetButtonPressed){
            resetBoard();
        }


    }

    private void setBoard(){
        int count = 0;

        for(int i =0; i< 4; i++){
            for(int j=0; j<4; j++) {
                count++;
                boardStatus[i][j] = random_door[count - 1];
            }
        }
    }

    private void setOpen_door(int image){
        open_door = new AnimationDrawable();
        open_door.addFrame(getResources().getDrawable(R.drawable.door_1),130);
        open_door.addFrame(getResources().getDrawable(MyData.doorArrayPostition2[image]),130);
        open_door.addFrame(getResources().getDrawable(MyData.doorArrayOpen[image]),130);
        open_door.setOneShot(true);
    }

    private void winningDoorAnimation(int image){
        winning_door = new AnimationDrawable();
        winning_door.addFrame(getResources().getDrawable(R.drawable.xando_start),150);
        winning_door.addFrame(getResources().getDrawable(MyData.doorArrayOpen[image]),150);
        winning_door.addFrame(getResources().getDrawable(R.drawable.xando_start),150);
        winning_door.addFrame(getResources().getDrawable(MyData.doorArrayOpen[image]),150);
        winning_door.addFrame(getResources().getDrawable(R.drawable.xando_start),150);
        winning_door.addFrame(getResources().getDrawable(MyData.doorArrayOpen[image]),150);
        winning_door.setOneShot(true);
    }



    private void setClose_door(int door){
        close_door = new AnimationDrawable();
        close_door.addFrame(getResources().getDrawable(MyData.doorArrayOpen[door]),700);
        close_door.addFrame(getResources().getDrawable(MyData.doorArrayPostition2[door]),150);
        close_door.addFrame(getResources().getDrawable(R.drawable.door_closed),150);
        close_door.setOneShot(true);
    }

    private void resetBoard(){
        button1.setImageResource(R.drawable.door_closed);
        button2.setImageResource(R.drawable.door_closed);
        button3.setImageResource(R.drawable.door_closed);
        button4.setImageResource(R.drawable.door_closed);
        button5.setImageResource(R.drawable.door_closed);
        button6.setImageResource(R.drawable.door_closed);
        button7.setImageResource(R.drawable.door_closed);
        button8.setImageResource(R.drawable.door_closed);
        button9.setImageResource(R.drawable.door_closed);
        button10.setImageResource(R.drawable.door_closed);
        button11.setImageResource(R.drawable.door_closed);
        button12.setImageResource(R.drawable.door_closed);
        button13.setImageResource(R.drawable.door_closed);
        button14.setImageResource(R.drawable.door_closed);
        button15.setImageResource(R.drawable.door_closed);
        button16.setImageResource(R.drawable.door_closed);

        setRandom_door();
        setBoard();
        doorSet();
        isDoorSelected = false;
        doorValueSelected = -1;
        doorNumberSelected = -1;
    }

    private void setRandom_door(){
        List<Integer> l = Arrays.asList(random_door);
        Collections.shuffle(l);
    }

    private boolean checkForWin(){

        boolean[] doorNumbers = new boolean[]{
                door1,door2,door3,door4,door5,door6,door7,door8,door9,door10,door11,door12,door13,door14,door15,door16
        };
        for(int i=0; i<16; i++){
            if(!doorNumbers[i]){
                return false;
            }
        }
        return true;
    }


    private void doorSet(){
        door1 = false;
        door2 = false;
        door3 = false;
        door4 = false;
        door5 = false;
        door6 = false;
        door7 = false;
        door8 = false;
        door9 = false;
        door10 = false;
        door11 = false;
        door12 = false;
        door13 = false;
        door14 = false;
        door15 = false;
        door16 = false;
    }

    private boolean checkForMatch(int value){
        if(value == doorValueSelected){
            System.out.println("FOUND MATCH");
            return true;
        }
        return false;
    }

    private void setDoorMatch(int door){
            switch (door) {
                case 1:
                    button1.setImageDrawable(winning_door);
                    winning_door.start();
                    door1 = true;
                    break;
                case 2:
                    button2.setImageDrawable(winning_door);
                    winning_door.start();
                    door2 = true;
                    break;
                case 3:
                    button3.setImageDrawable(winning_door);
                    winning_door.start();
                    door3 = true;
                    break;
                case 4:
                    button4.setImageDrawable(winning_door);
                    winning_door.start();
                    door4 = true;
                    break;
                case 5:
                    button5.setImageDrawable(winning_door);
                    winning_door.start();
                    door5 = true;
                    break;
                case 6:
                    button6.setImageDrawable(winning_door);
                    winning_door.start();
                    door6 = true;
                    break;
                case 7:
                    button7.setImageDrawable(winning_door);
                    winning_door.start();
                    door7 = true;
                    break;
                case 8:
                    button8.setImageDrawable(winning_door);
                    winning_door.start();
                    door8 = true;
                    break;
                case 9:
                    button9.setImageDrawable(winning_door);
                    winning_door.start();
                    door9 = true;
                    break;
                case 10:
                    button10.setImageDrawable(winning_door);
                    winning_door.start();
                    door10 = true;
                    break;
                case 11:
                    button11.setImageDrawable(winning_door);
                    winning_door.start();
                    door11 = true;
                    break;
                case 12:
                    button12.setImageDrawable(winning_door);
                    winning_door.start();
                    door12 = true;
                    break;
                case 13:
                    button13.setImageDrawable(winning_door);
                    winning_door.start();
                    door13 = true;
                    break;
                case 14:
                    button14.setImageDrawable(winning_door);
                    winning_door.start();
                    door14 = true;
                    break;
                case 15:
                    button15.setImageDrawable(winning_door);
                    winning_door.start();
                    door15 = true;
                    break;
                case 16:
                    button16.setImageDrawable(winning_door);
                    winning_door.start();
                    door16 = true;
                    break;
            }



    }

    private void setDoorClose(int door){
        switch (door) {
            case 1:
                setClose_door(boardStatus[0][0]);
                button1.setImageDrawable(close_door);
                close_door.start();
                break;
            case 2:
                setClose_door(boardStatus[0][1]);
                button2.setImageDrawable(close_door);
                close_door.start();
                break;
            case 3:
                setClose_door(boardStatus[0][2]);
                button3.setImageDrawable(close_door);
                close_door.start();
                break;
            case 4:
                setClose_door(boardStatus[0][3]);
                button4.setImageDrawable(close_door);
                close_door.start();
                break;
            case 5:
                setClose_door(boardStatus[1][0]);
                button5.setImageDrawable(close_door);
                close_door.start();
                break;
            case 6:
                setClose_door(boardStatus[1][1]);
                button6.setImageDrawable(close_door);
                close_door.start();
                break;
            case 7:
                setClose_door(boardStatus[1][2]);
                button7.setImageDrawable(close_door);
                close_door.start();
                break;
            case 8:
                setClose_door(boardStatus[1][3]);
                button8.setImageDrawable(close_door);
                close_door.start();
                break;
            case 9:
                setClose_door(boardStatus[2][0]);
                button9.setImageDrawable(close_door);
                close_door.start();
                break;
            case 10:
                setClose_door(boardStatus[2][1]);
                button10.setImageDrawable(close_door);
                close_door.start();
                break;
            case 11:
                setClose_door(boardStatus[2][2]);
                button11.setImageDrawable(close_door);
                close_door.start();
                break;
            case 12:
                setClose_door(boardStatus[2][3]);
                button12.setImageDrawable(close_door);
                close_door.start();
                break;
            case 13:
                setClose_door(boardStatus[3][0]);
                button13.setImageDrawable(close_door);
                close_door.start();
                break;
            case 14:
                setClose_door(boardStatus[3][1]);
                button14.setImageDrawable(close_door);
                close_door.start();
                break;
            case 15:
                setClose_door(boardStatus[3][2]);
                button15.setImageDrawable(close_door);
                close_door.start();
                break;
            case 16:
                setClose_door(boardStatus[3][3]);
                button16.setImageDrawable(close_door);
                close_door.start();
                break;
        }



    }

}
