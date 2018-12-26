package com.example.douglas.econsociety;
//this is comment for git
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private ArrayList<Member> memberList;
    private onItemClickListener mListener;

    public interface  onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView name,idNum,prog,dateSignIn;
        public ImageView bin;


        public viewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);

            dateSignIn = itemView.findViewById(R.id.signedInDate);
            name = itemView.findViewById(R.id.line1);
            idNum = itemView.findViewById(R.id.line2);
            prog = itemView.findViewById(R.id.line3);
            bin = itemView.findViewById(R.id.bin);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
            bin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                            // deletes record  req #1.
                        }
                    }
                }
            });
        }
    }
    public Adapter(ArrayList<Member> memberList){
        this.memberList = memberList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        viewHolder vh = new viewHolder(view,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Member currentItem = memberList.get(position);
       // currentItem.storeStart();
        holder.dateSignIn.setText(getDate());
        holder.name.setText(currentItem.getName());
        holder.idNum.setText(currentItem.getId());
        holder.prog.setText(currentItem.getProgress());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public String getDate(){
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        return  formattedDate;
    }
}
