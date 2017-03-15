package com.example.mtecgwa_jr.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final Context context = getApplicationContext();

        Intent intent = getIntent();

        final TextView userName = (TextView) findViewById(R.id.userName);
        final TextView profileUrl = (TextView) findViewById(R.id.githubProfileLink);
        final TextView followersNo = (TextView) findViewById(R.id.followers_no);
        final TextView followingNo = (TextView) findViewById(R.id.following_no);
        final TextView repositoryNo = (TextView) findViewById(R.id.repository_no);
        final TextView blogUrl = (TextView) findViewById(R.id.blogLink);
        final TextView mailToUser = (TextView) findViewById(R.id.mailToUser);
        final ImageView profilePic = (ImageView) findViewById(R.id.profile_photo);


        final String singleUser = intent.getStringExtra("userName");

        final String profilePicUrl = intent.getStringExtra("profileUrl");



        String userObjectUrl = "https://api.github.com/users/"+singleUser;

        userName.setText(singleUser);


        Picasso.with(this).load(profilePicUrl).into(profilePic);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, userObjectUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            profileUrl.setText(User.getProfileUrl());
                            followersNo.setText(User.getFollowersNo());
                            followingNo.setText(User.getFollowingNo());
                            repositoryNo.setText(User.getReposNo());

                            if(response.getString("blog").equals("null"))
                            {
                                blogUrl.setText("No registered blog");
                            }


                            if(response.getString("email").equals("null"))
                            {
                                mailToUser.setText("No registred email");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MainActivity" , "Error while retrieving json data......");
                    }
                });

        requestQueue.add(jsonObjectRequest);

        LinearLayout githubProfile = (LinearLayout) findViewById(R.id.githubUrl);
        LinearLayout blog = (LinearLayout) findViewById(R.id.blogUrl);
        LinearLayout mailTo = (LinearLayout) findViewById(R.id.mailTo);
        Button share = (Button) findViewById(R.id.share);

        githubProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(profileUrl.getText().toString()));
                startActivity(intent1);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if(!(User.getBlogUrl().equals("null")))
                    {
                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse(blogUrl.getText().toString()));
                        startActivity(intent1);
                    }


                }
                catch (ActivityNotFoundException e)
                {

                }

            }
        });

        mailTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!(User.getEmail().equals("null")))
                {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", mailToUser.getText().toString(), null));

                    startActivity(emailIntent);
                }

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context , ShareActivity.class);
                intent1.putExtra("userName" , singleUser);
                intent1.putExtra("url" , profileUrl.getText().toString());
                startActivity(intent1);

            }
        });
    }
}
