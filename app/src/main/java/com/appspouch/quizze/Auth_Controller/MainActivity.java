package com.appspouch.quizze.Auth_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appspouch.quizze.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tbutton = (Button) findViewById(R.id.btn_teacher);
        Button sbutton = (Button) findViewById(R.id.btn_student);
        {
            tbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tintent = new Intent(MainActivity.this, TeacherLoginActivity.class);
                    startActivity(tintent);
                }
            });

            sbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sintent = new Intent(MainActivity.this, StudentLoginActivity.class);
                    startActivity(sintent);
                }
            });

        }
    }
}
