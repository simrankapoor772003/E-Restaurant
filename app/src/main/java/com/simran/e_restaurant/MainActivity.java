package com.simran.e_restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//public class MainActivity extends AppCompatActivity {
//    public static final String MSG="com.simran.erestaurant.order";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//    public void placeOrder(View view){
//        Intent i=new Intent(this,orderActivity.class);
//        EditText e1=findViewById(R.id.e1);
//        EditText e2=findViewById(R.id.e2);
//        EditText e3=findViewById(R.id.e3);
//        String message="YOUR ORDER FOR "+e1.getText().toString()+" , "+e2.getText().toString()+" , "+e3.getText().toString()+" HAS BEEN PLACE SUCCESSFULLY!";
//        i.putExtra(MSG,message);
//        startActivity(i);
//    }
//}

public class MainActivity extends AppCompatActivity {
    EditText name, age;
    Button submit;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        submit = findViewById(R.id.btn_submit);
        DB = new DBHelper(this);

        submit.setOnClickListener(v -> {
            String userName = name.getText().toString();
            String userAge = age.getText().toString();
            DB.insertData(userName, userAge);
            Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
            startActivity(intent);
        });
    }
}
