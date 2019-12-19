package cs.ucmo.finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class MainActivity extends FragmentActivity {

    public FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //PORTRAIT MODE
        if(savedInstanceState == null) {
            System.out.println("Portrait Mode");

            FragmentTransaction ft = fragmentManager.beginTransaction();
            TicTacToeMenu ticTacToeMenu = new TicTacToeMenu();
            ft.add(R.id.fragment_list, ticTacToeMenu);
            ft.commit();


        }
        //LANDSCAPE MODE

    }

}
