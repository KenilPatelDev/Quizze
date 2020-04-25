package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;

public class TeacherLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_teacher);

        Button t_register = (Button) findViewById(R.id.btn_tregister2);
        Button reset_pass = (Button) findViewById(R.id.btn_reset_password);
        {
            t_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tregintent = new Intent(TeacherLoginActivity.this, TeacherRegisterActivity.class);
                    startActivity(tregintent);
                }
            });

            reset_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent repassintent = new Intent(TeacherLoginActivity.this, ResetPasswordActivity.class);
                    startActivity(repassintent);
                }
            });
        }
    }
}