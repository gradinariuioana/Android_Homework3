package com.example.homework3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homework3.listeners.ToDoClickListener;
import com.example.homework3.adapters.ToDosAdapter;
import com.example.homework3.classes.MySingleton;
import com.example.homework3.R;
import com.example.homework3.classes.ToDo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ToDosAdapter mAdapter;
    public int id;

    SecondFragment(int id){
        this.id = id + 1;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_second, container, false);

        recyclerView = myview.findViewById(R.id.todoRecyclerView);

        // set the Layout Manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        parseToDosFromJSON(id);
        System.out.println(5);

        recyclerView.addOnItemTouchListener(
                new ToDoClickListener(getContext(), recyclerView, new ToDoClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ThirdFragment thirdFragment = new ThirdFragment(((TextView)view.findViewById(R.id.todo_title)).getText().toString().substring(7));
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        if (fragmentManager.findFragmentByTag("second_fragment") != null)
                            fragmentTransaction.replace(R.id.container, thirdFragment, "third_fragment").addToBackStack("second_fragment");
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        return myview;
    }

    private void parseToDosFromJSON(int id){
        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ToDo> toDos = new ArrayList<>();

                for (int index = 0; index < response.length(); index++) {
                    try {
                        ToDo toDo = new ToDo().fromJSON(response.getJSONObject(index));
                        toDos.add(toDo);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                mAdapter = new ToDosAdapter(toDos);
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
