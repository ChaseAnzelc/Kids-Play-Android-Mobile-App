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

public class TicTacToeStartScreenFragment extends Fragment implements View.OnClickListener {

    static boolean player_one;
    static boolean player_x;

    ImageView button1;
    ImageView button2;
    ImageView button3;
    ImageView button4;
    SubmitButton submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tic_tac_toe_start, container, false);

        player_one = true;
        player_x = true;

        button1 = view.findViewById(R.id.start_button1);
        button2 = view.findViewById(R.id.start_button2);
        button3 = view.findViewById(R.id.start_button3);
        button4 = view.findViewById(R.id.start_button4);
        submitButton = view.findViewById(R.id.submit_button_tic);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        return view;

    }


           @Override
        public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start_button1:
                button1.setImageResource(R.drawable.player_one);
                button2.setImageResource(R.drawable.player_two_clicked);
                player_one = true;
                break;
            case R.id.start_button2:
                button1.setImageResource(R.drawable.player_one_clicked);
                button2.setImageResource(R.drawable.player_two);
                player_one = false;
                break;
            case R.id.start_button3:
                button3.setImageResource(R.drawable.player_x);
                button4.setImageResource(R.drawable.player_o_clicked);
                player_x = true;
                break;
            case R.id.start_button4:
                button3.setImageResource(R.drawable.player_x_clicked);
                button4.setImageResource(R.drawable.player_o);
                player_x = false;
                break;
            case R.id.submit_button_tic:

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                submitButton.setEnabled(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_list, ticTacToeFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        button1.setEnabled(true);
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                        submitButton.setEnabled(true);
                    }
                    }, 2000);
                break;

        }

    }

}
