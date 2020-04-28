package com.appspouch.quizze.Auth_Controller;

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

import com.appspouch.quizze.Model.Student;
import com.appspouch.quizze.R;
import com.google.android.gms.common.api.Batch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class StudentRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_sreg;
    private EditText sName, sId, sEmail, sPassword, sMobile;
    private ProgressBar progressBar;
    private FirebaseAuth sAuth;
    Spinner stu_dept, stu_branch, stu_sem, stu_batch;


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

        stu_branch = (Spinner) findViewById(R.id.stu_branch_spinner);
        stu_dept = (Spinner) findViewById(R.id.stu_dept_spinner);
        stu_sem = (Spinner) findViewById(R.id.stu_semester_spinner);
        stu_batch = (Spinner) findViewById(R.id.stu_batch_spinner);

        String[] studept = {"CSPIT", "DEPSTAR"};
        String[] stubranch = {"CE", "CSE", "IT"};
        String[] stusem = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        String[] stubatch = {"2017", "2018", "2019", "2020", "2021"};

        ArrayAdapter<String> sdadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, studept);
        ArrayAdapter<String> sbranadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stubranch);
        ArrayAdapter<String> ssadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stusem);
        ArrayAdapter<String> sbatadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stubatch);

        stu_dept.setAdapter(sdadapter);
        stu_branch.setAdapter(sbranadapter);
        stu_sem.setAdapter(ssadapter);
        stu_batch.setAdapter(sbatadapter);

        sAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_sregister).setOnClickListener(this);
        btn_sreg = (Button) findViewById(R.id.btn_tregister);
        sName = findViewById(R.id.name);
        sEmail = findViewById(R.id.email);
        sId = findViewById(R.id.id_no);
        sPassword = findViewById(R.id.password);
        sMobile = findViewById(R.id.contact_no);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        stu_branch = (Spinner) findViewById(R.id.stu_branch_spinner);
        stu_dept = (Spinner) findViewById(R.id.stu_dept_spinner);
        stu_sem = (Spinner) findViewById(R.id.stu_semester_spinner);
        stu_batch = (Spinner) findViewById(R.id.stu_batch_spinner);

       // btn_sreg.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (sAuth.getCurrentUser() != null) {


        }
    }

    private void registerStudent() {
        final String name = sName.getText().toString().trim();
        final String id = sId.getText().toString().trim();
        final String email = sEmail.getText().toString().trim();
        String password = sPassword.getText().toString().trim();
        final String mobile = sMobile.getText().toString().trim();
        final String department = stu_dept.getSelectedItem().toString().trim();
        final String branch = stu_branch.getSelectedItem().toString().trim();
        final String semester = stu_sem.getSelectedItem().toString().trim();
        final String batch = stu_batch.getSelectedItem().toString().trim();

        String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (name.isEmpty()) {
            sName.setError(getString(R.string.input_error_name));
            sName.requestFocus();
            return;
        }

       if (id.isEmpty()){
           sId.setError(getString(R.string.input_error_id));
           sId.requestFocus();
           return;
       }

        if (email.isEmpty()) {
            sEmail.setError(getString(R.string.input_error_email));
            sEmail.requestFocus();
            return;
        }

        if (!email.matches(emailPattern)) {
            sEmail.setError(getString(R.string.input_error_email_invalid));
            sEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            sPassword.setError(getString(R.string.input_error_password));
            sPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            sPassword.setError(getString(R.string.input_error_password_length));
            sPassword.requestFocus();
            return;
        }

        if (mobile.isEmpty()) {
            sMobile.setError(getString(R.string.input_error_mobile));
            sMobile.requestFocus();
            return;
        }

        if (mobile.length() != 10) {
            sMobile.setError(getString(R.string.input_error_mobile_invalid));
            sMobile.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        sAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Student student = new Student(
                                    name,
                                    id,
                                    mobile,
                                    email,
                                    department,
                                    branch,
                                    semester,
                                    batch
                            );


                            FirebaseDatabase.getInstance().getReference("Students")
                                    .child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()))
                                    .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentRegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(StudentRegisterActivity.this, getString(R.string.registration_unsuccess), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(StudentRegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sregister) {
            registerStudent();
        }
    }
}
