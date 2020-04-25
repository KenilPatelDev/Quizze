package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;

public class StudentRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_student);

        Button s_login = (Button) findViewById(R.id.btn_slogin2);
        {
            s_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      Intent sloginintent = new Intent(StudentRegisterActivity.this, StudentLoginActivity.class);
                      startActivity(sloginintent);
                }
            });

        }
    }
}
