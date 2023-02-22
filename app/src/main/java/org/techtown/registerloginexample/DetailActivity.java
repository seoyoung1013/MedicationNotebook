package org.techtown.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private TextView tv_name,tv_medicine1,tv_medicine2,tv_medicine3,tv_medicine4,tv_dose, tv_meditime, tv_totaltime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String medicine1 = intent.getStringExtra("medicine1");
        String medicine2 = intent.getStringExtra("medicine2");
        String medicine3 = intent.getStringExtra("medicine3");
        String medicine4 = intent.getStringExtra("medicine4");
        String dose = intent.getStringExtra("dose");
        String meditime = intent.getStringExtra("meditime");
        String totaltime = intent.getStringExtra("totaltime");
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");


        tv_name = findViewById(R.id.tv_name);
        tv_medicine1 = findViewById(R.id.tv_medicine1);
        tv_medicine2 = findViewById(R.id.tv_medicine2);
        tv_medicine3 = findViewById(R.id.tv_medicine3);
        tv_medicine4 = findViewById(R.id.tv_medicine4);
        tv_dose = findViewById(R.id.tv_dose);
        tv_meditime = findViewById(R.id.tv_meditime);
        tv_totaltime = findViewById(R.id.tv_totaltime);


        tv_name.setText(userName);
        tv_medicine1.setText(medicine1);
        tv_medicine2.setText(medicine2);
        tv_medicine3.setText(medicine3);
        tv_medicine4.setText(medicine4);
        tv_dose.setText(dose);
        tv_meditime.setText(meditime);
        tv_totaltime.setText(totaltime);

    }


    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

}