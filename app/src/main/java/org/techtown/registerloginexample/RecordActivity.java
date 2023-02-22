package org.techtown.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    private TextView user1,user2,user3,user4,user5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        user1 = (TextView)findViewById(R.id.user1);
        user2 = (TextView)findViewById(R.id.user2);
        user3 = (TextView)findViewById(R.id.user3);
        user4 = (TextView)findViewById(R.id.user4);
        user5 = (TextView)findViewById(R.id.user5);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        user1.setText(userName);
        user2.setText(userName);
        user3.setText(userName);
        user4.setText(userName);
        user5.setText(userName);

    }
}