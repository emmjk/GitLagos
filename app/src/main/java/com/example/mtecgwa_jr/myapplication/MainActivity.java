package com.example.mtecgwa_jr.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataClass> dataList = new ArrayList<DataClass>();
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private final String tag = "MyApplication";
    private final String url = "https://api.github.com/search/users?q=%22%22+location:%22Lagos%22";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        usersAdapter = new UsersAdapter(dataList , this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray itemsArray = response.getJSONArray("items");
                            for(int i = 0 ; i < itemsArray.length() ; i++)
                            {
                                final JSONObject object = itemsArray.getJSONObject(i);
                                String name = (object.getString("login")).substring(0,1).toUpperCase() + (object.getString("login")).substring(1);
                                dataList.add(new DataClass(name , object.getString("avatar_url") , object.getInt("id") , 20));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        usersAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MainActivity" , "Error while retrieving json data......");
                    }
                });

        requestQueue.add(jsonObjectRequest);



    }


}
