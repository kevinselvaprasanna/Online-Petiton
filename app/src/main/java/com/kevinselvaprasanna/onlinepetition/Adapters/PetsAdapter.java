package com.kevinselvaprasanna.onlinepetition.Adapters;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevinselvaprasanna.onlinepetition.Helper.DatabaseHelper;
import com.kevinselvaprasanna.onlinepetition.Objects.Pet;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import com.kevinselvaprasanna.onlinepetition.R;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Pet> mItems;
    //TimeHelper th;
    private static String LOG_TAG = "EventDetails";


    public PetsAdapter(Context context, ArrayList<Pet> items) {
        mContext = context;
        mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvheading, tvnot;
        LinearLayout petsfeed;
        CardView cv;
        Button support;

        public ViewHolder(View itemView) {
            super(itemView);
            tvheading =(TextView)itemView.findViewById(R.id.tvHead);
            tvnot = (TextView)itemView.findViewById(R.id.tvContent);
            petsfeed = (LinearLayout)itemView.findViewById(R.id.petsfeed);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            support = (Button)itemView.findViewById(R.id.support);
        }
    }

    public PetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pets, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetsAdapter.ViewHolder holder, final int position) {

        //th = new TimeHelper();

        holder.tvheading.setText(mItems.get(position).head);
        holder.tvnot.setText(mItems.get(position).content) ;
        if(mItems.get(position).up==5){
            holder.support.setText("SUPPORTED");
        }else{
            holder.support.setText("SUPPORT PETITION");
        }
        holder.support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.support.setText("SUPPORTED");
                DatabaseHelper data = new DatabaseHelper(mContext);
                ContentValues cv = new ContentValues();
                cv.put("up", 5);
                data.support(mItems.get(position).head, cv);
                Log.d("adap",cv.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
