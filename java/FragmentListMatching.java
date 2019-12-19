package cs.ucmo.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentListMatching extends Fragment {

    static boolean isSet;
    static ArrayList<String> nameList = new ArrayList<>();
    static ArrayList<String> timeList = new ArrayList<>();
    static ArrayList<String> clicksList = new ArrayList<>();
    static ArrayList<String> scoreList = new ArrayList<>();


    DatabaseHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.recycler_matching,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_matching);

        settingValues();


        RecyclerViewAdapterMatching myadapter = new RecyclerViewAdapterMatching(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

        return view;
    }

    //setting arraylist for values
    public void settingValues() {

            nameList.clear();
            timeList.clear();
            clicksList.clear();
            scoreList.clear();


            myDB = new DatabaseHelper(getContext());
            Cursor data = myDB.getListContents();
            if (data.getCount() != 0) {
                while (data.moveToNext()) {
                    nameList.add(data.getString(0));
                    timeList.add(data.getString(1));
                    clicksList.add(data.getString(2));
                    scoreList.add(data.getString(3));
                }
            }
    }
}
