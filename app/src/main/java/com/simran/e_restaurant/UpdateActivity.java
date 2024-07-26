package com.simran.e_restaurant;

import android.content.Intent;
import android.database.Cursor;
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

        // Get the ID from the intent
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        // Load data for the given ID
        loadData();

        update.setOnClickListener(v -> {
            String updatedName = name.getText().toString();
            String updatedAge = age.getText().toString();
            boolean isUpdated = DB.updateData(id, updatedName, updatedAge);
            if (isUpdated) {
                Intent intent1 = new Intent(getApplicationContext(), DisplayActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void loadData() {
        Cursor cursor = DB.getData();
        while (cursor.moveToNext()) {
            String currentId = cursor.getString(0);
            if (currentId.equals(id)) {
                name.setText(cursor.getString(1));
                age.setText(cursor.getString(2));
                break;
            }
        }
        cursor.close();
    }
}
