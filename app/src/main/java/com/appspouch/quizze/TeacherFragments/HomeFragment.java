package com.appspouch.quizze.TeacherFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appspouch.quizze.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.getIntent;

public class HomeFragment extends Fragment {

    CircleImageView circleImageView;
    TextView d_name, d_dept, d_branch, d_desi, d_email, d_cont;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth tAuth;
    FirebaseUser user;
    private DatabaseReference databaseReference;
    private StorageReference firebaseStorage;
    final long ONE_MEGABYTE = 1024 * 1024;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       tAuth = FirebaseAuth.getInstance();
       user = tAuth.getCurrentUser();
       firebaseDatabase = FirebaseDatabase.getInstance();
       databaseReference = firebaseDatabase.getReference("Teachers");

        d_name =view.findViewById(R.id.t_det_name);
        d_dept =view.findViewById(R.id.t_det_department);
        d_branch =view.findViewById(R.id.t_det_branch);
        d_desi =view.findViewById(R.id.t_det_designation);
        d_email =view.findViewById(R.id.t_det_email);
        d_cont =view.findViewById(R.id.t_det_contact);

        Query query = databaseReference.orderByChild("Teachers").equalTo(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String name = ""+ds.child("name").getValue();
                    String dept = ""+ds.child("department").getValue();
                    String branch = ""+ds.child("branch").getValue();
                    String desi = ""+ds.child("designation").getValue();
                    String email = ""+ds.child("email").getValue();
                    String cont = ""+ds.child("mobile").getValue();

                    d_name.setText(name);
                    d_dept.setText(dept);
                    d_branch.setText(branch);
                    d_desi.setText(desi);
                    d_email.setText(email);
                    d_cont.setText(cont);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

}
