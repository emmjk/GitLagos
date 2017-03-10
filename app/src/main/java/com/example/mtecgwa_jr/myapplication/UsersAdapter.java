package com.example.mtecgwa_jr.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.mtecgwa_jr.myapplication.R.layout.user_row;

/**
 * Created by mtecgwa-jr on 3/5/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    List<DataClass> userList;
    Context context;

    public UsersAdapter(List<DataClass> userList, Context context)
    {
        this.userList = userList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userName  ;
        ImageView profilePic;
        RelativeLayout singleUser;

        LinearLayout userRow;

        public MyViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.user_name);

            profilePic = (ImageView) itemView.findViewById(R.id.profile_image);
            singleUser = (RelativeLayout) itemView.findViewById(R.id.userRow);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row , parent , false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataClass data = userList.get(position);
        holder.userName.setText(data.getUserName());
        Picasso.with(context).load(data.getProfileUrl()).placeholder(R.drawable.user).into(holder.profilePic);

        holder.singleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context , "u clicked user" , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context , UserProfile.class);
                intent.putExtra("userName" , data.getUserName());
                intent.putExtra("profileUrl" , data.getProfileUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
