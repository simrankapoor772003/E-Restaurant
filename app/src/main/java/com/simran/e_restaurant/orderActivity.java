//package com.simran.e_restaurant;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class orderActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);
//
//        Intent i2=getIntent();
//        String message=i2.getStringExtra(MainActivity.MSG);
//
//        TextView tv=findViewById(R.id.orderText);
//        tv.setText(message);
//    }
//}