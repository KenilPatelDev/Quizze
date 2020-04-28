package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;
import com.appspouch.quizze.Student_Section.StuMainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText sEmail, sPassword;
    private ProgressBar progressBar;
    CheckBox remember;
    private FirebaseAuth sAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_student);

        Button s_login = (Button) findViewById(R.id.btn_slogin);
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

        sEmail =  findViewById(R.id.email);
        sPassword =  findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        remember = findViewById(R.id.rememberme);
        progressBar.setVisibility(View.GONE);

        //Get Firebase auth instance
        sAuth = FirebaseAuth.getInstance();

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){
            Intent intent = new Intent(StudentLoginActivity.this, StuMainScreen.class);
            startActivity(intent);
        }


        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sEmail.getText().toString();
                final String password = sPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),"Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                             SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                             SharedPreferences.Editor editor = preferences.edit();
                             editor.putString("remember", "true");
                             editor.apply();
                         }
                });




                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                sAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        sPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(StudentLoginActivity.this,getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if(sAuth.getCurrentUser().isEmailVerified()) {
                                        startActivity(new Intent(StudentLoginActivity.this, StuMainScreen.class));
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                        finish();
                                    }else {
                                        Toast.makeText(StudentLoginActivity.this, R.string.email_unverified, Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                    }

                                }
                            }
                        });
            }
        });
    }

}
