package com.appspouch.quizze.Auth_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appspouch.quizze.R;
import com.appspouch.quizze.Teacher_Section.TeaMainScreen;
import com.google.errorprone.annotations.concurrent.LockMethod;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth tAuth;
    @Override
    protected void onPause(){
        super.onPause();

        FirebaseUser firebaseUser = tAuth.getCurrentUser();
        if (firebaseUser != null){

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tAuth = FirebaseAuth.getInstance();


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
