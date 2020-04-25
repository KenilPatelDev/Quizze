package com.appspouch.quizze.Auth_Controller;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.wang.avi.AVLoadingIndicatorView;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail =  findViewById(R.id.email);
        btnReset =  findViewById(R.id.btn_reset_password);
        btnBack =  findViewById(R.id.btn_back);
        avLoadingIndicatorView = findViewById(R.id.loader1);

        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(),"Enter your registered email id",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                avLoadingIndicatorView.setVisibility(View.VISIBLE);
                avLoadingIndicatorView.show();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPasswordActivity.this,"We have sent you instructions to reset your password!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResetPasswordActivity.this,"Failed to send reset email!",
                                            Toast.LENGTH_SHORT).show();
                                }

                                avLoadingIndicatorView.setVisibility(View.GONE);
                                avLoadingIndicatorView.hide();
                            }
                        });
            }
        });
    }

}
