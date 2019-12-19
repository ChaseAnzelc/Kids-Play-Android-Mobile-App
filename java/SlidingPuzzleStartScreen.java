package cs.ucmo.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SlidingPuzzleStartScreen extends Fragment implements View.OnClickListener {

    ImageView button_left;
    ImageView button_right;
    ImageView puzzle_picture;

    ImageView threeby;
    ImageView fourby;

    boolean threebythree;
    int count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sliding_puzzle_start, container, false);

        threebythree = true;

        count = 0;


        threeby = view.findViewById(R.id.threeby);
        fourby = view.findViewById(R.id.fourby);


        button_left = view.findViewById(R.id.button_left);
        button_right = view.findViewById(R.id.button_right);
        puzzle_picture = view.findViewById(R.id.puzzle_picture);

        threeby.setOnClickListener(this);
        fourby.setOnClickListener(this);

        button_left.setOnClickListener(this);
        button_right.setOnClickListener(this);
        puzzle_picture.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.threeby:
                threebythree = true;
                threeby.setImageResource(R.drawable.sliding_blue_three);
                fourby.setImageResource(R.drawable.sliding_red_four);
                break;
            case R.id.fourby:
                threebythree = false;
                fourby.setImageResource(R.drawable.sliding_blue_four);
                threeby.setImageResource(R.drawable.sliding_red_three);
                break;
            case R.id.button_left:
                if(count == 0){
                    System.out.println("Button left");
                    count = MyData.puzzleList.length - 1;
                    puzzle_picture.setImageResource(MyData.puzzleList[count]);
                }
                else{
                    count--;
                    puzzle_picture.setImageResource(MyData.puzzleList[count]);
                }
                break;
            case R.id.button_right:

                if(count == MyData.puzzleList.length - 1){
                    count = 0;
                    puzzle_picture.setImageResource(MyData.puzzleList[count]);
                }
                else{
                    System.out.println("Button right");
                    count++;
                    puzzle_picture.setImageResource(MyData.puzzleList[count]);
                }
                break;
            case R.id.puzzle_picture:

                if(threebythree) {
                    SlidingPuzzleFragment.puzzleArray = MyData.chosenPuzzle[count];
                    SlidingPuzzleFragment.COLUMNS = 3;
                }
                else {
                    SlidingPuzzleFragment.puzzleArray = MyData.chosen4Puzzle[count];
                    SlidingPuzzleFragment.COLUMNS = 4;
                }

                        SlidingPuzzleFragment slidingPuzzleFragment = new SlidingPuzzleFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_list, slidingPuzzleFragment);
                        ft.addToBackStack(null);
                        ft.commit();

                break;

        }

    }

}
