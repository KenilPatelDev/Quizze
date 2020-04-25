package com.appspouch.quizze.Auth_Controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;
import com.appspouch.quizze.Teacher_Section.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText tEmail, tPassword;
    private ProgressBar progressBar;

    private FirebaseAuth tAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_teacher);

        Button t_login = (Button) findViewById(R.id.btn_tlogin);
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

        // set the view now

        tEmail =  findViewById(R.id.email);
        tPassword =  findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        //Get Firebase auth instance
        tAuth = FirebaseAuth.getInstance();

        t_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tEmail.getText().toString();
                final String password = tPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),"Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                tAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TeacherLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                            tPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(TeacherLoginActivity.this,getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if(tAuth.getCurrentUser().isEmailVerified()) {
                                        startActivity(new Intent(TeacherLoginActivity.this, MainScreen.class));
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                        finish();
                                    }else {
                                        Toast.makeText(TeacherLoginActivity.this, R.string.email_unverified, Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                    }

                                }
                            }
                        });
            }
        });
    }
    }

