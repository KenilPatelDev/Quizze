package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_student);

        Button s_register = (Button) findViewById(R.id.btn_sregister2);
        Button reset_pass = (Button) findViewById(R.id.btn_reset_password);
        {
            s_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sregintent = new Intent(StudentLoginActivity.this, StudentRegisterActivity.class);
                    startActivity(sregintent);
                }
            });

            reset_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent repassintent = new Intent(StudentLoginActivity.this, ResetPasswordActivity.class);
                    startActivity(repassintent);
                }
            });

        }
    }
}
