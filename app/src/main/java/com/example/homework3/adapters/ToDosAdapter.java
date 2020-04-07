package com.example.homework3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.homework3.R;
import com.example.homework3.classes.ToDo;

import java.util.List;

public class ToDosAdapter extends RecyclerView.Adapter<ToDosAdapter.MyViewHolder> {

    private List<ToDo> toDos;

    // Constructor (depends on the kind of dataset)
    public ToDosAdapter(List<ToDo> myDataset){
        toDos = myDataset;
    }

    public void setToDos(List<ToDo> toDos) {
        this.toDos = toDos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ToDosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_info, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.userId.setText("User id: " + toDos.get(position).getUserId());
        holder.id.setText("Id: " + toDos.get(position).getId());
        holder.title.setText("Title: " + toDos.get(position).getTitle());
        holder.completed.setText("Completed: " + toDos.get(position).getCompleted());
    }


    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return toDos.size();
    }


    public void addToDo (ToDo toDo) {
        List<ToDo> toDos = this.toDos;
        toDos.add(toDo);
        this.toDos = toDos;


    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView userId;
        public TextView id;
        public TextView title;
        public TextView completed;

        public MyViewHolder(View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.todo_userid);
            id = itemView.findViewById(R.id.todo_id);
            title = itemView.findViewById(R.id.todo_title);
            completed = itemView.findViewById(R.id.todo_completed);
        }
    }
}
