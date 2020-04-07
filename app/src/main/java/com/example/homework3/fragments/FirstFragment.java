package com.example.homework3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homework3.adapters.UsersAdapter;
import com.example.homework3.classes.MySingleton;
import com.example.homework3.R;
import com.example.homework3.classes.User;
import com.example.homework3.listeners.UserClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UsersAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = myview.findViewById(R.id.userRecyclerView);

        // set the Layout Manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        parseUsersFromJSON();

        recyclerView.addOnItemTouchListener(
                new UserClickListener(getContext(), recyclerView, new UserClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        SecondFragment secondFragment = new SecondFragment(position);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        if (fragmentManager.findFragmentByTag("first_fragment") != null)
                            fragmentTransaction.replace(R.id.container, secondFragment, "second_fragment").addToBackStack("first_fragment");
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        return myview;
    }

    private void parseUsersFromJSON(){
        String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR17fRJDcz9TLs1-EpPeFIC3H92fLmCkCUh5MJkzwDhl_SS1ReZlM-xINvc";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<User> users = new ArrayList<>();

                for (int index = 0; index < response.length(); index++) {
                    try {
                        User user = new User().fromJSON(response.getJSONObject(index));
                        users.add(user);
                    } catch (JSONException ex) {
                    }
                }
                mAdapter = new UsersAdapter(users);
                recyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Volley error " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }
}
