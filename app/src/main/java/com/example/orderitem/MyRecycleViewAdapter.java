package com.example.orderitem;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private BlankFragmenthotdish.Dishes[] listdata;
    private OnItemClickListener myonclick;
    public MyRecycleViewAdapter(BlankFragmenthotdish.Dishes[] listdata) {
        this.listdata = listdata;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.myonclick=listener;
    }

    @NonNull
    @Override
    public MyRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.recycleview,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem,myonclick);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.ViewHolder holder, int position) {
        final BlankFragmenthotdish.Dishes itemData=listdata[position];
        holder.textView.setText(itemData.getName());
        holder.imageView.setImageResource(itemData.getImage());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        private OnItemClickListener mylistener;
        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            this.imageView=(ImageView)itemView.findViewById(R.id.imageshow);
            this.textView=(TextView)itemView.findViewById(R.id.nameshow);
            textView.setBackgroundResource(R.drawable.border);
            textView.setTextColor(Color.parseColor("#FF000000"));
            this.relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            mylistener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mylistener.onItemClick(v,getAdapterPosition());
        }
    }
}
