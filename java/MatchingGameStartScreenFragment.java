package cs.ucmo.finalproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.spark.submitbutton.SubmitButton;

public class MatchingGameStartScreenFragment extends Fragment implements View.OnClickListener {

    SubmitButton submitButton;
    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.matching_game_start, container, false);

        submitButton = view.findViewById(R.id.startgamematching);

        submitButton.setOnClickListener(this);


        startRecycler();



        return view;
    }





    @Override
    public void onClick(View view) {
        submitButton.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                MatchingGameFragment matchingGameFragment = new MatchingGameFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_list, matchingGameFragment);
                ft.commit();
                submitButton.setEnabled(true);
            }
        }, 2000);
    }


    public void startRecycler(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentListMatching listMatching = new FragmentListMatching();
        ft.add(R.id.matching_frame, listMatching);
        ft.commit();
    }




}
