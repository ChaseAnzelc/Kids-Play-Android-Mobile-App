package cs.ucmo.finalproject;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.spark.submitbutton.SubmitButton;

public class TicTacToeMenu extends Fragment implements View.OnClickListener {

    ImageView button_left;
    ImageView button_right;
    ImageView start_game;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tic_tac_toe_menu, container, false);

        button_left = view.findViewById(R.id.button_left);
        button_right = view.findViewById(R.id.button_right);
        start_game = view.findViewById(R.id.start_game);

        button_left.setOnClickListener(this);
        button_right.setOnClickListener(this);
        start_game.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_left:

                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                SlidingMenu slidingMenu = new SlidingMenu();
                ft1.add(R.id.fragment_list, slidingMenu);
                ft1.commit();

                break;
            case R.id.button_right:

                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                MatchingMenu matchingMenu = new MatchingMenu();
                ft2.add(R.id.fragment_list, matchingMenu);
                ft2.commit();

                break;
            case R.id.start_game:

                TicTacToeStartScreenFragment ticTacToeStartScreenFragment = new TicTacToeStartScreenFragment();
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fragment_list, ticTacToeStartScreenFragment);
                ft3.addToBackStack(null);
                ft3.commit();

                break;

        }

    }

}
