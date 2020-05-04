package com.appspouch.quizze.Auth_Controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appspouch.quizze.Model.Teacher;
import com.appspouch.quizze.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class TeacherRegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_treg;
    private EditText tName, tEmail, tPassword, tMobile, tdesignation;
    private ProgressBar progressBar;
    Spinner dept_spinner, branch_spinner;

    private FirebaseAuth tAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_teacher);

        Button t_login = (Button) findViewById(R.id.btn_tlogin2);
        {
            t_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tloginintent = new Intent(TeacherRegisterActivity.this, TeacherLoginActivity.class);
                    startActivity(tloginintent);
                }
            });

        }

        dept_spinner = (Spinner) findViewById(R.id.dept_spinner);
        branch_spinner = (Spinner) findViewById(R.id.branch_spinner);

        String[] dept = {"CSPIT", "DEPSTAR"};
        String[] branch = {"CE", "CSE", "IT"};

        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dept);
        ArrayAdapter<String> badapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, branch);

        dept_spinner.setAdapter(dadapter);
        branch_spinner.setAdapter(badapter);

        tAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_tregister).setOnClickListener(this);

        btn_treg = (Button) findViewById(R.id.btn_tregister);
        tName = (EditText) findViewById(R.id.f_name);
        tEmail = (EditText) findViewById(R.id.email);
        tPassword = (EditText) findViewById(R.id.password);
        tMobile = (EditText) findViewById(R.id.contact_no);
        tdesignation = (EditText) findViewById(R.id.designation);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        dept_spinner = (Spinner) findViewById(R.id.dept_spinner);
        branch_spinner = (Spinner) findViewById(R.id.branch_spinner);


        //  btn_treg.setOnClickListener((View.OnClickListener) this);
    }




    private void registerTeacher() {
        final String name = tName.getText().toString().trim();
        final String email = tEmail.getText().toString().trim();
        String password = tPassword.getText().toString().trim();
        final String mobile = tMobile.getText().toString().trim();
        final String designation = tdesignation.getText().toString().trim();
        final String department = dept_spinner.getSelectedItem().toString().trim();
        final String branch = branch_spinner.getSelectedItem().toString().trim();
        String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (name.isEmpty()) {
            tName.setError(getString(R.string.input_error_name));
            tName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            tEmail.setError(getString(R.string.input_error_email));
            tEmail.requestFocus();
            return;
        }

        if (!email.matches(emailPattern)) {
            tEmail.setError(getString(R.string.input_error_email_invalid));
            tEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            tPassword.setError(getString(R.string.input_error_password));
            tPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            tPassword.setError(getString(R.string.input_error_password_length));
            tPassword.requestFocus();
            return;
        }

        if (mobile.isEmpty()) {
            tMobile.setError(getString(R.string.input_error_mobile));
            tMobile.requestFocus();
            return;
        }

        if (mobile.length() != 10) {
            tMobile.setError(getString(R.string.input_error_mobile_invalid));
            tMobile.requestFocus();
            return;
        }

        if (designation.isEmpty()) {
            tdesignation.setError(getString(R.string.input_error_designation));
            tdesignation.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        tAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Teacher teacher = new Teacher(
                                    name,
                                    email,
                                    mobile,
                                    designation,
                                    department,
                                    branch
                            );


                            FirebaseDatabase.getInstance().getReference("Teachers")
                                    .child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()))
                                    .setValue(teacher).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(TeacherRegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(TeacherRegisterActivity.this, getString(R.string.registration_unsuccess), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(TeacherRegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (tAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tregister:
                registerTeacher();
                break;
        }
    }
}