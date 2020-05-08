package com.appspouch.quizze.TeacherActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.appspouch.quizze.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class TProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView d_name, d_dept, d_branch, d_desi, d_email, d_contact;
    private Button profileUpdate;
    private FirebaseAuth tAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_profile);
        Toolbar toolbar = findViewById(R.id.toolbartp);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

/*
      //  profilePic = findViewById(R.id.ivProfilePic);
        d_name = findViewById(R.id.t_det_name);
        d_dept = findViewById(R.id.t_det_department);
        d_branch = findViewById(R.id.t_det_branch);
        d_desi = findViewById(R.id.t_det_designation);
        d_email = findViewById(R.id.t_det_email);
        d_contact = findViewById(R.id.t_det_contact);
        profileUpdate = findViewById(R.id.t_profile_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(tAuth.getUid()).child("Teachers").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 //   Teacher teacher = dataSnapshot.child("Teachers").getValue(Teacher.class);
                    d_name.setText(dataSnapshot.child("name").getValue().toString());
                    d_dept.setText(dataSnapshot.child("department").getValue().toString());
                    d_branch.setText(dataSnapshot.child("branch").getValue().toString());
                    d_desi.setText(dataSnapshot.child("designation").getValue().toString());
                    d_email.setText(dataSnapshot.child("email").getValue().toString());
                    d_contact.setText(dataSnapshot.child("mobile").getValue().toString());

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
            //   Toast.makeText(ProfileFragment.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
           }
       });


        */


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}