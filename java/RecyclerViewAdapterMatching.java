package cs.ucmo.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterMatching extends RecyclerView.Adapter<RecyclerViewAdapterMatching.ViewHolder> {

    private ArrayList<String> nameList;
    private ArrayList<String> timeList;
    private ArrayList<String> clicksList;
    private ArrayList<String> scoreList;


    public RecyclerViewAdapterMatching(Context contexts){
        nameList = FragmentListMatching.nameList;
        timeList = FragmentListMatching.timeList;
        clicksList = FragmentListMatching.clicksList;
        scoreList = FragmentListMatching.scoreList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_matching, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.nameView.setText(nameList.get(position));
        holder.timeView.setText(timeList.get(position));
        holder.clicksView.setText(clicksList.get(position));
        holder.scoreView.setText(scoreList.get(position));


        if (position % 2 == 1){
            holder.nameView.setTextColor(Color.DKGRAY);
            holder.timeView.setTextColor(Color.DKGRAY);
            holder.clicksView.setTextColor(Color.DKGRAY);
            holder.scoreView.setTextColor(Color.DKGRAY);
        }
        else {
            holder.nameView.setTextColor(Color.WHITE);
            holder.nameView.setBackgroundResource(R.drawable.grey_trans);
            holder.timeView.setTextColor(Color.WHITE);
            holder.timeView.setBackgroundResource(R.drawable.grey_trans);
            holder.clicksView.setTextColor(Color.WHITE);
            holder.clicksView.setBackgroundResource(R.drawable.grey_trans);
            holder.scoreView.setTextColor(Color.WHITE);
            holder.scoreView.setBackgroundResource(R.drawable.grey_trans);
        }


    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        TextView timeView;
        TextView clicksView;
        TextView scoreView;


        public ViewHolder(View items){
            super(items);
            nameView = items.findViewById(R.id.nameView);
            timeView = items.findViewById(R.id.timeView);
            clicksView = items.findViewById(R.id.clicksView);
            scoreView = items.findViewById(R.id.scoreView);

        }
    }
}

