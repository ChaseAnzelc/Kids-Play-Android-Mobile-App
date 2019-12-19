package cs.ucmo.finalproject;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;



public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    AnimationDrawable x_animation;
    AnimationDrawable o_animation;
    AnimationDrawable x_winner;
    AnimationDrawable o_winner;

    int[] checkPositionTaken = {0,0,0,0,0,0,0,0,0};

    boolean PLAYER_X;


    boolean PLAYER_1 = true;

    int TURN_COUNT = 0;

    ImageView button1;
    ImageView button2;
    ImageView button3;
    ImageView button4;
    ImageView button5;
    ImageView button6;
    ImageView button7;
    ImageView button8;
    ImageView button9;

    boolean box1 = false;
    boolean box2 = false;
    boolean box3 = false;
    boolean box4 = false;
    boolean box5 = false;
    boolean box6 = false;
    boolean box7 = false;
    boolean box8 = false;
    boolean box9 = false;


    TextView status;

    int[][] boardStatus = new int[3][3];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tic_tac_toe,container,false);

        status = view.findViewById(R.id.status);

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        button7 = view.findViewById(R.id.button7);
        button8 = view.findViewById(R.id.button8);
        button9 = view.findViewById(R.id.button9);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        initializeBoardStatus();

        PLAYER_X = TicTacToeStartScreenFragment.player_x;
        PLAYER_1 = TicTacToeStartScreenFragment.player_one;


        if(!PLAYER_1){
            TURN_COUNT --;
            aimove();
        }

        return view;
    }

    public void aimove(){
            TURN_COUNT ++;
            if(TURN_COUNT == 9){
                if(!checkWinner()) {
                    System.out.println("herrrrrree");
                result("Game Draw");
                resetBoard();
                }
                else{
                    resetBoard();
                }
            }
            else {

                if (!checkWinner()) {


                    final ArrayList<Integer> aiShuffle = new ArrayList<>();


                    for (int i = 0; i < checkPositionTaken.length; i++) {
                        //position not selected
                        if (checkPositionTaken[i] != 1) {
                            aiShuffle.add(i);
                        }
                    }

                    Collections.shuffle(aiShuffle);
                    System.out.println(aiShuffle);

                    switch (aiShuffle.get(0)){
                        case 0:
                            box1 = true;
                            break;
                        case 1:
                            box2 = true;
                            break;
                        case 2:
                            box3 = true;
                            break;
                        case 3:
                            box4 = true;
                            break;
                        case 4:
                            box5 = true;
                            break;
                        case 5:
                            box6 = true;
                            break;
                        case 6:
                            box7 = true;
                            break;
                        case 7:
                            box8 = true;
                            break;
                        case 8:
                            box9 = true;
                            break;


                    }

                    enableAllBoxes(false);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            switch (aiShuffle.get(0)) {
                                case 0:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button1.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[0][0] = 0;

                                    } else {
                                        setX_animation();
                                        button1.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[0][0] = 1;
                                    }
                                    checkPositionTaken[0] = 1;
                                    break;
                                case 1:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button2.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[0][1] = 0;

                                    } else {
                                        setX_animation();
                                        button2.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[0][1] = 1;
                                    }
                                    checkPositionTaken[1] = 1;
                                    break;
                                case 2:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button3.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[0][2] = 0;

                                    } else {
                                        setX_animation();
                                        button3.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[0][2] = 1;
                                    }
                                    checkPositionTaken[2] = 1;
                                    break;
                                case 3:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button4.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[1][0] = 0;

                                    } else {
                                        setX_animation();
                                        button4.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[1][0] = 1;
                                    }
                                    checkPositionTaken[3] = 1;
                                    break;
                                case 4:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button5.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[1][1] = 0;

                                    } else {
                                        setX_animation();
                                        button5.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[1][1] = 1;
                                    }
                                    checkPositionTaken[4] = 1;
                                    break;
                                case 5:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button6.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[1][2] = 0;

                                    } else {
                                        setX_animation();
                                        button6.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[1][2] = 1;
                                    }
                                    checkPositionTaken[5] = 1;
                                    break;
                                case 6:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button7.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[2][0] = 0;

                                    } else {
                                        setX_animation();
                                        button7.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[2][0] = 1;
                                    }
                                    checkPositionTaken[6] = 1;
                                    break;
                                case 7:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button8.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[2][1] = 0;

                                    } else {
                                        setX_animation();
                                        button8.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[2][1] = 1;
                                    }
                                    checkPositionTaken[7] = 1;
                                    break;
                                case 8:
                                    if (PLAYER_X) {
                                        setO_animation();
                                        button9.setImageDrawable(o_animation);
                                        o_animation.start();
                                        boardStatus[2][2] = 0;

                                    } else {
                                        setX_animation();
                                        button9.setImageDrawable(x_animation);
                                        x_animation.start();
                                        boardStatus[2][2] = 1;
                                    }
                                    checkPositionTaken[8] = 1;
                                    break;


                            }

                            TURN_COUNT ++;
                            if(TURN_COUNT == 9) {
                                if (!checkWinner()) {
                                    System.out.println("in this one");
                                    result("Game Draw");
                                    resetBoard();
                                }
                            }
                            if(checkWinner()){resetBoard();}
                            enableAllBoxes(true);
                        }
                    }, 1000);


                }
                else {
                    resetBoard();
                }
            }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button1:
                if(!box1) {
                    box1 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button1.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[0][0] = 1;

                    } else {
                        setO_animation();
                        button1.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[0][0] = 0;
                    }
                    checkPositionTaken[0] = 1;
                    aimove();
                }

                break;

            case R.id.button2:
                if(!box2) {
                    box2 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button2.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[0][1] = 1;
                        //checkPositionTaken[1] = 1;
                        //aimove();
                    } else {
                        setO_animation();
                        button2.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[0][1] = 0;
                    }
                    checkPositionTaken[1] = 1;
                    aimove();
                }
                break;

            case R.id.button3:
                if(!box3) {
                    box3 = true;
                if (PLAYER_X) {
                        setX_animation();
                        button3.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[0][2] = 1;

                    } else {
                        setO_animation();
                        button3.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[0][2] = 0;
                    }
                    checkPositionTaken[2] = 1;
                    aimove();
                }
                break;

            case R.id.button4:
                if(!box4) {
                    box4 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button4.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[1][0] = 1;
                    } else {
                        setO_animation();
                        button4.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[1][0] = 0;
                    }
                    checkPositionTaken[3] = 1;
                    aimove();
                }
                break;

            case R.id.button5:
                if(!box5) {
                    box5 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button5.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[1][1] = 1;
                    } else {
                        setO_animation();
                        button5.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[1][1] = 0;
                    }
                    checkPositionTaken[4] = 1;
                    aimove();
                }
                break;

            case R.id.button6:
                if(!box6) {
                    box6 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button6.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[1][2] = 1;
                    } else {
                        setO_animation();
                        button6.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[1][2] = 0;
                    }
                    checkPositionTaken[5] = 1;
                    aimove();
                }
                break;

            case R.id.button7:
                if(!box7) {
                    box7 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button7.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[2][0] = 1;
                    } else {
                        setO_animation();
                        button7.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[2][0] = 0;
                    }
                    checkPositionTaken[6] = 1;
                    aimove();
                }
                break;

            case R.id.button8:
                if(!box8) {
                    box8 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button8.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[2][1] = 1;
                    } else {
                        setO_animation();
                        button8.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[2][1] = 0;
                    }
                    checkPositionTaken[7] = 1;
                    aimove();
                }
                break;

            case R.id.button9:
                if(!box9) {
                    box9 = true;
                    if (PLAYER_X) {
                        setX_animation();
                        button9.setImageDrawable(x_animation);
                        x_animation.start();
                        boardStatus[2][2] = 1;
                    } else {
                        setO_animation();
                        button9.setImageDrawable(o_animation);
                        o_animation.start();
                        boardStatus[2][2] = 0;
                    }
                    checkPositionTaken[8] = 1;
                    aimove();
                }
                break;

            default:
                break;

        }

    }

    private void setX_animation(){
        x_animation = new AnimationDrawable();
        x_animation.addFrame(getResources().getDrawable(R.drawable.x_2),150);
        x_animation.addFrame(getResources().getDrawable(R.drawable.x_3),150);
        x_animation.addFrame(getResources().getDrawable(R.drawable.x_4),150);
        x_animation.addFrame(getResources().getDrawable(R.drawable.x_final),150);
        x_animation.setOneShot(true);
    }

    private void setO_animation(){
        o_animation = new AnimationDrawable();
        o_animation.addFrame(getResources().getDrawable(R.drawable.o_2),150);
        o_animation.addFrame(getResources().getDrawable(R.drawable.o_3),150);
        o_animation.addFrame(getResources().getDrawable(R.drawable.o_final),150);
        o_animation.setOneShot(true);
    }

    private void animate_winner_x(){
        x_winner = new AnimationDrawable();
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_final),300);
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_winner),300);
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_final),300);
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_winner),300);
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_final),300);
        x_winner.addFrame(getResources().getDrawable(R.drawable.x_winner),300);
        x_winner.setOneShot(true);
    }

    private void animate_winner_o(){
        o_winner = new AnimationDrawable();
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_final),300);
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_winner),300);
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_final),300);
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_winner),300);
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_final),300);
        o_winner.addFrame(getResources().getDrawable(R.drawable.o_winner),300);
        o_winner.setOneShot(true);
    }

    private void winnerAnimationX(int value1, int value2, int value3){

        int value = 0;
        for (int x=0; x<=2; x++){
            animate_winner_x();
            if(x==0) {
                value = value1;
            }
            if(x==1) {
                value = value2;
            }
            if(x==2){
                value = value3;
            }

            System.out.println("Value = " + value);
            switch (value){
                case 1:
                    button1.setImageDrawable(x_winner);
                    break;
                case 2:
                    button2.setImageDrawable(x_winner);
                    break;
                case 3:
                    button3.setImageDrawable(x_winner);
                    break;
                case 4:
                    button4.setImageDrawable(x_winner);
                    break;
                case 5:
                    button5.setImageDrawable(x_winner);
                    break;
                case 6:
                    button6.setImageDrawable(x_winner);
                    break;
                case 7:
                    button7.setImageDrawable(x_winner);
                    break;
                case 8:
                    button8.setImageDrawable(x_winner);
                    break;
                case 9:
                    button9.setImageDrawable(x_winner);
                    break;
            }
            x_winner.start();
        }


    }

    private void winnerAnimationO(int value1, int value2, int value3){

        int value = 0;
        for (int o=0; o<=2; o++){
            animate_winner_o();
            if(o==0) {
                value = value1;
            }
            if(o==1) {
                value = value2;
            }
            if(o==2){
                value = value3;
            }

            System.out.println("Value = " + value);
            switch (value){
                case 1:
                    button1.setImageDrawable(o_winner);
                    break;
                case 2:
                    button2.setImageDrawable(o_winner);
                    break;
                case 3:
                    button3.setImageDrawable(o_winner);
                    break;
                case 4:
                    button4.setImageDrawable(o_winner);
                    break;
                case 5:
                    button5.setImageDrawable(o_winner);
                    break;
                case 6:
                    button6.setImageDrawable(o_winner);
                    break;
                case 7:
                    button7.setImageDrawable(o_winner);
                    break;
                case 8:
                    button8.setImageDrawable(o_winner);
                    break;
                case 9:
                    button9.setImageDrawable(o_winner);
                    break;
            }
            o_winner.start();
        }


    }

    private boolean checkWinner(){
        //Rows
        for(int i=0; i<3; i++){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    result("X Wins");
                    int row = i+1;
                    if(row == 1) {
                        winnerAnimationX(1, 2, 3);
                        return true;
                    }
                    if(row == 2) {
                        winnerAnimationX(4, 5, 6);
                        return true;
                    }
                    if(row == 3) {
                        winnerAnimationX(7, 8, 9);
                        return true;
                    }
                    break;
                }
                else if (boardStatus[i][0]==0) {
                    result("O Wins");
                    int row = i+1;
                    if(row == 1) {
                        winnerAnimationO(1, 2, 3);
                        return true;
                    }
                    if(row == 2) {
                        winnerAnimationO(4, 5, 6);
                        return true;
                    }
                    if(row == 3) {
                        winnerAnimationO(7, 8, 9);
                        return true;
                    }
                    break;
                }
            }
        }

        //Columns
        for(int i=0; i<3; i++){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    result("X Wins");
                    int column = i+1;
                    if(column == 1) {
                        winnerAnimationX(1, 4, 7);
                        return true;
                    }
                    if(column == 2) {
                        winnerAnimationX(2, 5, 8);
                        return true;
                    }
                    if(column == 3) {
                        winnerAnimationX(3, 6, 9);
                        return true;
                    }
                    break;
                }
                else if (boardStatus[0][i]==0) {
                    result("O Wins");
                    int column = i+1;
                    if(column == 1) {
                        winnerAnimationO(1, 4, 7);
                        return true;
                    }
                    if(column == 2) {
                        winnerAnimationO(2, 5, 8);
                        return true;
                    }
                    if(column == 3) {
                        winnerAnimationO(3, 6, 9);
                        return true;
                    }
                    break;
                }
            }
        }

        //Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if (boardStatus[0][0]==1){

                result("X Wins");
                winnerAnimationX(1,5,9);
                return true;
            }
            else if (boardStatus[0][0]==0) {
                result("O Wins");
                winnerAnimationO(1,5,9);
                return true;
            }
        }

        //Other Diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if (boardStatus[0][2]==1){
                result("X Wins");
                winnerAnimationX(3,5,7);
                return true;
            }
            else if (boardStatus[0][2]==0) {
                result("O Wins");
                winnerAnimationO(3,5,7);
                return true;
            }
        }
        return false;
    }

    private void enableAllBoxes(boolean value){
        button1.setEnabled(value);
        button2.setEnabled(value);
        button3.setEnabled(value);

        button4.setEnabled(value);
        button5.setEnabled(value);
        button6.setEnabled(value);

        button7.setEnabled(value);
        button8.setEnabled(value);
        button9.setEnabled(value);
    }


    private void result(String winner){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.edited_toast, (ViewGroup) getView().findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(winner);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        enableAllBoxes(false);
    }

    private void resetBoard(){

        enableAllBoxes(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
        getFragmentManager().popBackStack();
        enableAllBoxes(true);
            }
        }, 2000);

    }

    private void setInfo(String text){
        status.setText(text);
    }

    private void initializeBoardStatus(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardStatus[i][j] = -1;
            }
        }

    }





}
