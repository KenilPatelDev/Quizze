package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
