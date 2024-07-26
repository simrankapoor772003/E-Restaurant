package com.simran.e_restaurant;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    DBHelper DB;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        layout = findViewById(R.id.layout);
        DB = new DBHelper(this);
        loadData();
    }

    private void loadData() {
        Cursor cursor = DB.getData();
        while (cursor.moveToNext()) {
            final String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);

            TextView textView = new TextView(this);
            textView.setText("ID: " + id + " Name: " + name + " Age: " + age);
            layout.addView(textView);

            Button update = new Button(this);
            update.setText("Update");
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
            layout.addView(update);

            Button delete = new Button(this);
            delete.setText("Delete");
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DB.deleteData(id);
                    finish();
                    startActivity(getIntent());
                }
            });
            layout.addView(delete);
        }
    }
}