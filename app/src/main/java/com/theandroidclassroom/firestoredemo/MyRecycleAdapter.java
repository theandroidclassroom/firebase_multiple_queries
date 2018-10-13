package com.theandroidclassroom.firestoredemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murtaza on 7/13/2018.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    private List<MembersPojo> mList = new ArrayList<>();
    private Context mContext;
    public MyRecycleAdapter(List<MembersPojo> list, Context context){
        mList = list;
        mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.list_layout,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(mList.get(position).getName());
        holder.gender.setText(mList.get(position).getGender());
        holder.age.setText(mList.get(position).getAge()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,age,gender;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}
