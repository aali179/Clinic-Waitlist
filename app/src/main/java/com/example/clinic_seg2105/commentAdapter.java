package com.example.clinic_seg2105;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder> {

    private ArrayList<Comments> mComments;

    public static class commentViewHolder extends RecyclerView.ViewHolder{

        public TextView comment_user_name, simple_text, comment_date, comment_text, comment_time;


        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_user_name = itemView.findViewById(R.id.comment_user_name);
            simple_text = itemView.findViewById(R.id.simple_text);
            comment_date = itemView.findViewById(R.id.comment_date);
            comment_text = itemView.findViewById(R.id.comment_text);
            comment_time = itemView.findViewById(R.id.comment_time);
        }
    }

    public commentAdapter(ArrayList<Comments> commentList){
        mComments = commentList;

    }

    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_comments_layout, parent, false);
        commentViewHolder cvh = new commentViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, int position) {

        Comments comment = mComments.get(position);

        holder.comment_user_name.setText(comment.getId());
        holder.comment_date.setText(comment.getDate());
        holder.comment_text.setText(comment.getComment());
        holder.comment_time.setText(comment.getTime());

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}

