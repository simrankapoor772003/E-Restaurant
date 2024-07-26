package com.simran.e_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText name, age;
    Button update;
    DBHelper DB;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.name_update);
        age = findViewById(R.id.age_update);
        update = findViewById(R.id.btn_update);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.updateData(id, name.getText().toString(), age.getText().toString());
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                startActivity(intent);
            }
        });
    }
}
