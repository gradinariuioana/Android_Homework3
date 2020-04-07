package com.example.homework3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.homework3.R;
import com.example.homework3.classes.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> users;

    // Constructor (depends on the kind of dataset)
    public UsersAdapter(List<User> myDataset){
        users = myDataset;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.id.setText("Id: " + users.get(position).getId());
        holder.name.setText("Name: " + users.get(position).getName());
        holder.user_name.setText("User name: " + users.get(position).getUserName());
        holder.email.setText("Email: " + users.get(position).getEmail());
    }


    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return users.size();
    }


    public void addUser (User user) {
        List<User> users = this.users;
        users.add(user);
        this.users = users;


    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public TextView user_name;
        public TextView email;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.user_id);
            name = itemView.findViewById(R.id.user_name);
            user_name = itemView.findViewById(R.id.user_username);
            email = itemView.findViewById(R.id.user_email);
        }
    }
}
