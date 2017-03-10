package com.example.mtecgwa_jr.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        final String githubUrl = intent.getStringExtra("url");

        TextView user = (TextView) findViewById(R.id.user);
        user.append(userName+" , ");
        TextView link = (TextView) findViewById(R.id.link);
        link.setText(githubUrl);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(githubUrl));
                startActivity(intent1);
            }
        });
    }
}
