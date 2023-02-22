package org.techtown.registerloginexample;

import static android.graphics.Color.BLACK;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View drawerView;

    private TextView tv_Name, tv_age,UserName;
    private Button detail_button, btn_open, btn_map, btn_medication,btn_record,btn_prescription;
    private ImageView QR_imageView;


    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent2 = getIntent();
        String userID = intent2.getStringExtra("userID");
        String userPass = intent2.getStringExtra("userPass");
        String userName = intent2.getStringExtra("userName");
        String userAge = intent2.getStringExtra("userAge");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        btn_record = (Button)findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ //로그인에 성공한 경우
                                String userName = jsonObject.getString("userName");
                                intent.putExtra("userName", userName);
                                startActivity(intent);
                            }
                            else{
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
                drawerLayout.closeDrawers();
            }
        });

        btn_prescription = (Button)findViewById(R.id.btn_prescription);
        btn_map = (Button)findViewById(R.id.btn_map);
        btn_medication = (Button)findViewById(R.id.btn_medication);

        btn_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");
                                    if(success){ //로그인에 성공한 경우
                                        String userName = jsonObject.getString("userName");
                                        String disease = jsonObject.getString("disease");
                                        String disNumber = jsonObject.getString("disNumber");
                                        String mediinfo1 = jsonObject.getString("mediinfo1");
                                        String mediinfo2 = jsonObject.getString("mediinfo2");
                                        String mediinfo3 = jsonObject.getString("mediinfo3");
                                        String mediinfo4 = jsonObject.getString("mediinfo4");


                                        intent.putExtra("userID", userID);
                                        intent.putExtra("userPass", userPass);
                                        intent.putExtra("userName", userName);
                                        intent.putExtra("disease", disease);
                                        intent.putExtra("disNumber", disNumber);
                                        intent.putExtra("mediinfo1", mediinfo1);
                                        intent.putExtra("mediinfo2", mediinfo2);
                                        intent.putExtra("mediinfo3", mediinfo3);
                                        intent.putExtra("mediinfo4", mediinfo4);


                                        startActivity(intent);
                                        System.out.println("실행");
                                    }
                                    else{   //로그인에 실패한 경우
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                MainRequest MainRequest = new MainRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(MainRequest);
                drawerLayout.closeDrawers();
            }
        });

       btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapMainActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();

            }
        });



        btn_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        tv_Name = findViewById(R.id.tv_Name);
        tv_age = findViewById(R.id.tv_Age);
        UserName = findViewById(R.id.UserName);
        detail_button = findViewById(R.id.detail_button);


        tv_Name.setText(userName);
        tv_age.setText(userAge);
        UserName.setText(userName + ", " + userAge + "세");


        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ //로그인에 성공한 경우
                                String userName = jsonObject.getString("userName");
                                String userAge = jsonObject.getString("userAge");
                                String medicine1 = jsonObject.getString("medicine1");
                                String medicine2 = jsonObject.getString("medicine2");
                                String medicine3 = jsonObject.getString("medicine3");
                                String medicine4 = jsonObject.getString("medicine4");
                                String dose = jsonObject.getString("dose");
                                String meditime = jsonObject.getString("meditime");
                                String totaltime = jsonObject.getString("totaltime");

                                intent.putExtra("userName", userName);
                                intent.putExtra("medicine1", medicine1);
                                intent.putExtra("medicine2", medicine2);
                                intent.putExtra("medicine3", medicine3);
                                intent.putExtra("medicine4", medicine4);
                                intent.putExtra("dose", dose);
                                intent.putExtra("meditime", meditime);
                                intent.putExtra("totaltime", totaltime);

                                startActivity(intent);
                            }
                            else{
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
        String text = userName;
        QR_imageView = findViewById(R.id.QR_imageView);
        if(!TextUtils.isEmpty(text)){
            Bitmap qrCode = createBitmap(text);
            QR_imageView.setImageBitmap(qrCode);
        }


        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        mCalender = new GregorianCalendar();

        Log.v("HelloAlarmActivity", mCalender.getTime().toString());

        setAlarm();

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

    private void setAlarm() {
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(MainActivity.this, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, receiverIntent, 0);

        String from = "2022-11-04 23:37:30"; //임의로 날짜와 시간을 지정

        //날짜 포맷을 바꿔주는 소스코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;

        try {
            datetime = dateFormat.parse(from);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);


    }


    private Bitmap createBitmap(String text) {
        BitMatrix result;
        try{
            result = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,300,300, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width*height];
        for(int x = 0; x<height; x++)
        {
            int offset = x * width;
            for(int k = 0; k < width; k++){
                pixels[offset + k] = result.get(k,x) ? BLACK : Color.WHITE;
            }
        }
        Bitmap myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        myBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return myBitmap;
    }
}

