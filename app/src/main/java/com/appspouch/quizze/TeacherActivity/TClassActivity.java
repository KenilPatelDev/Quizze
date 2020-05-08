package com.appspouch.quizze.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.appspouch.quizze.Create_Quiz.Custom_quiz;
import com.appspouch.quizze.R;

import java.util.Objects;

public class TClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_class);
        Toolbar toolbar = findViewById(R.id.toolbartc);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button cqab = (Button) findViewById(R.id.create_class_ab);

        cqab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tintent = new Intent(TClassActivity.this, Custom_quiz.class);
                startActivity(tintent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
