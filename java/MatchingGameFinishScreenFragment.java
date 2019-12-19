package cs.ucmo.finalproject;

import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.spark.submitbutton.SubmitButton;

public class MatchingGameFinishScreenFragment extends Fragment implements View.OnClickListener {

    SubmitButton submitButton;
    EditText name;
    TextView Score;
    TextView Clicks;
    TextView Time;
    static String nameStorage;
    static int score;
    static String clicksStorage;
    static String time;

    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.matching_game_finish, container, false);

        submitButton = view.findViewById(R.id.finishgamematching);
        name = view.findViewById(R.id.edittextname);
        Score = view.findViewById(R.id.score_text);
        Clicks = view.findViewById(R.id.door_count);
        Time = view.findViewById(R.id.time_text);
        nameStorage = "";
        score = 0;

        int seconds = MatchingGameFragment.time_count;
        System.out.println(seconds);

        int p1 = seconds % 60;
        int p2 = (int) Math.floor(seconds / 60);

        System.out.println(p2);

        time = p2 + ":" + p1;

        score = 10000 - (seconds * MatchingGameFragment.numberOfClicks);



        clicksStorage = String.valueOf(MatchingGameFragment.numberOfClicks);
        Clicks.setText(clicksStorage + " doors clicked");

        if(p2 < 1){
            Time.setText(p1 + " seconds");
        }
        else {
            Time.setText(p2 + " minutes & " + p1 + " seconds");
        }
        Score.setText(String.valueOf(score));

        submitButton.setOnClickListener(this);

        myDB = new DatabaseHelper(getContext());




        return view;
    }





    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.finishgamematching:
                nameStorage = name.getText().toString();
                System.out.println(nameStorage);

                name.setEnabled(false);
                Score.setEnabled(false);
                Time.setEnabled(false);
                Clicks.setEnabled(false);
                submitButton.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {



                    if (myDB.addData(nameStorage,time,clicksStorage,String.valueOf(score))) {
                        FragmentListMatching.nameList.add(nameStorage);
                        FragmentListMatching.timeList.add(time);
                        FragmentListMatching.clicksList.add(clicksStorage);
                        FragmentListMatching.scoreList.add(String.valueOf(score));

                        FragmentListMatching.isSet = true;
                    }


                    FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    MatchingGameStartScreenFragment matchingGameStartScreenFragment = new MatchingGameStartScreenFragment();
                    ft1.add(R.id.fragment_list, matchingGameStartScreenFragment);
                    ft1.commit();


                    name.setEnabled(true);
                    Score.setEnabled(true);
                    Time.setEnabled(true);
                    Clicks.setEnabled(true);
                    submitButton.setEnabled(true);


                }
            }, 2000);
            break;
        }

    }


}
