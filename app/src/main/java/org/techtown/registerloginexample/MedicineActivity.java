package org.techtown.registerloginexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_move;
    private TextView textView2,textView7,textView8,textView9,textView12,textView15,textView16;
    private ImageButton imageButton;
    public static String meal;
    public static Context context_main;

    private TextView textView23;
    private Button check;
    private Button check2;
    private Button check3;
    private CheckBox checkBox,checkBox2,checkBox3,checkBox4;

    long Now;
    Date Date;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        int count = 0;

        context_main = this;

        textView2 = (TextView) findViewById(R.id.textView2);
        textView7 = (TextView) findViewById(R.id.user3);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView15 = (TextView) findViewById(R.id.textView16);
        textView23 = (TextView) findViewById(R.id.textView23);

        check = (Button) findViewById(R.id.check);

        checkBox4 = (CheckBox) findViewById(R.id.checkBox4) ;
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3) ;
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2) ;
        checkBox = (CheckBox) findViewById(R.id.checkBox);


        Intent intent = getIntent();
        String day = intent.getStringExtra("day");
        meal = intent.getStringExtra("check");
        String userName = intent.getStringExtra("userName");
        String disease = intent.getStringExtra("disease");
        String disNumber = intent.getStringExtra("disNumber");
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");
        String mediinfo1 = intent.getStringExtra("mediinfo1");
        String mediinfo2 = intent.getStringExtra("mediinfo2");
        String mediinfo3 = intent.getStringExtra("mediinfo3");
        String mediinfo4 = intent.getStringExtra("mediinfo4");


        textView2.setText(userName);
        textView7.setText(disease);
        textView8.setText(disNumber);
        textView9.setText(userName);
        checkBox.setText(mediinfo1);
        checkBox2.setText(mediinfo2);
        checkBox3.setText(mediinfo3);
        checkBox4.setText(mediinfo4);
        textView12.setText("아침");


        imageButton = (ImageButton) findViewById(R.id.imageButton);

        textView16.setText(getTime());
        imageButton.setOnClickListener(this);



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()&&checkBox2.isChecked()&&checkBox3.isChecked()&&checkBox4.isChecked()&&textView12.getText()=="아침"){
                    textView12.setText("점심");
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
                else if(checkBox.isChecked()&&checkBox2.isChecked()&&checkBox3.isChecked()&&checkBox4.isChecked()&&textView12.getText()=="점심"){
                    textView12.setText("저녁");
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
                else if(checkBox.isChecked()&&checkBox2.isChecked()&&checkBox3.isChecked()&&checkBox4.isChecked()&&textView12.getText()=="저녁"){
                    textView12.setText("아침");
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });


        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    textView23.setVisibility(View.INVISIBLE);
                } else {
                    textView23.setVisibility(View.VISIBLE);
                }
            }

        });

        checkBox2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    textView23.setVisibility(View.INVISIBLE);
                } else {
                    textView23.setVisibility(View.VISIBLE);
                }
            }

        }) ;

        checkBox3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    textView23.setVisibility(View.INVISIBLE);
                } else {
                    textView23.setVisibility(View.VISIBLE);
                }
            }

        }) ;

        checkBox4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    textView23.setVisibility(View.INVISIBLE);
                } else {
                    textView23.setVisibility(View.VISIBLE);
                }
            }

        }) ;

    }

    private String getTime(){
        Now = System.currentTimeMillis();
        Date = new Date(Now);
        return mFormat.format(Date);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                textView16.setText(getTime());
                break;
            default:
                break;
        }
    }
}