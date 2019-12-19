package cs.ucmo.finalproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Random;

public class SlidingPuzzleFragment extends Fragment {

    private static SlidingPuzzleGesture gridView;

    public static int COLUMNS;
    private static int DIMENSIONS;

    private static int columnWidth, columnHeight;

    public static int[] puzzleArray;

    private static String[] tileList;

    public static TextView textView;

    static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sliding_puzzle,container,false);


        //setting dimensions based on data set from start screen
        DIMENSIONS = COLUMNS * COLUMNS;


        textView = view.findViewById(R.id.final_text);

        fragmentManager = getFragmentManager();


        initialize(view);

        randomizePuzzle();

        setDimensions();





        return view;
    }


    private void initialize(View view) {
        gridView = view.findViewById(R.id.grid);
        gridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    //randomizing the puzzle
    private void randomizePuzzle() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    //setting the dimensions of the puzzle
    private void setDimensions() {
        ViewTreeObserver vto = gridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                columnWidth = gridView.getMeasuredWidth() / COLUMNS;
                columnHeight = gridView.getMeasuredHeight() / COLUMNS;

                display(getContext());
            }
        });
    }


    //displaying tiles on gridView
    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            button.setBackgroundResource(puzzleArray[Integer.parseInt(tileList[i])]);

            buttons.add(button);
        }

        //displaying onto gridView
        gridView.setAdapter(new CustomAdapter(buttons, columnWidth, columnHeight));
    }

    //swapping tiles
    public static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (puzzleSolved()){

            textView.setText("YOU WIN!!!!");
            finish();
        }
    }

    //all movements of tiles
    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals("right")) swap(context, position, 1);
            else if (direction.equals("down")) swap(context, position, COLUMNS);

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals("left")) swap(context, position, -1);
            else if (direction.equals("down")) swap(context, position, COLUMNS);
            else if (direction.equals("right")) swap(context, position, 1);

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals("left")) swap(context, position, -1);
            else if (direction.equals("down")) swap(context, position, COLUMNS);

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals("up")) swap(context, position, -COLUMNS);
            else if (direction.equals("right")) swap(context, position, 1);
            else if (direction.equals("down")) swap(context, position, COLUMNS);

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals("up")) swap(context, position, -COLUMNS);
            else if (direction.equals("left")) swap(context, position, -1);
            else if (direction.equals("down")) {
                // swap tiles downward
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
            }

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals("up")) swap(context, position, -COLUMNS);
            else if (direction.equals("right")) swap(context, position, 1);

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals("up")) swap(context, position, -COLUMNS);
            else if (direction.equals("left")) swap(context, position, -1);
            else if (direction.equals("right")) swap(context, position, 1);

            // Center tiles
        } else {
            if (direction.equals("up")) swap(context, position, -COLUMNS);
            else if (direction.equals("left")) swap(context, position, -1);
            else if (direction.equals("right")) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }

    //check if puzzle is solved
    private static boolean puzzleSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }


    //puzzle solved, wait before going back a fragment
    public static void finish(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                fragmentManager.popBackStackImmediate();
            }
        }, 2000);
    }




}
