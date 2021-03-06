package com.appspouch.quizze.Student_Section;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.appspouch.quizze.Attempt_Quiz_Section.Tests;
import com.appspouch.quizze.Auth_Controller.MainActivity;
import com.appspouch.quizze.Other.AboutUsActivity;
import com.appspouch.quizze.Other.BottomSheetFragment;
import com.appspouch.quizze.R;
import com.appspouch.quizze.Results_section.ResultsAdmin;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class StuMainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase database;
    private FirebaseAuth tAuth;
    private DatabaseReference myRef;
    public ImageButton imageButton;
    public TextView USer_email;
    private StorageReference firebaseStorage;
    public CircleImageView imageView1;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FrameLayout frameLayout;
    final long ONE_MEGABYTE = 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        imageButton = findViewById(R.id.userImage2);

        /*set profile_pic of user on navigation drawer**/
        View header = navigationView.getHeaderView(0);
        imageView1 = (navigationView.getHeaderView(0)).findViewById(R.id.imageView);
        USer_email = header.findViewById(R.id.textView);
        setTextOnUser();
        navigationView.setNavigationItemSelectedListener(this);
//        userID = findViewById(R.id.text_user_name);
//        setuserID();
//        setImageOnNavHeader();

        //fragment for term & conditions!
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment sheetFragment = new BottomSheetFragment();
                sheetFragment.show(getSupportFragmentManager(),sheetFragment.getTag());
            }
        });

        tAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();
    }

    /*    public void setImageOnNavHeader() {

        firebaseStorage = FirebaseStorage.getInstance().getReference(Objects.requireNonNull(tAuth.getUid()));
        firebaseStorage.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        imageView1.setMinimumHeight(90);
                        imageView1.setMinimumWidth(90);
                        imageView1.setMaxHeight(100);
                        imageView1.setMaxWidth(100);
                        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView1.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }  */


    public void setTextOnUser() {
        FirebaseUser suser = FirebaseAuth.getInstance().getCurrentUser();
        USer_email.setText(Objects.requireNonNull(suser).getEmail());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_Profile) {
            if ( isNetworkAvailable(StuMainScreen.this)) {
                Intent intent = new Intent(StuMainScreen.this, SProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_attempt_test) {
            if (isNetworkAvailable(StuMainScreen.this)) {
                startActivity(new Intent(StuMainScreen.this, Tests.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_result) {
            if (isNetworkAvailable(StuMainScreen.this)) {
                startActivity(new Intent(StuMainScreen.this, ResultsAdmin.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(StuMainScreen.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(StuMainScreen.this, AboutUsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (id == R.id.feedback_id) {

             Intent feedbackEmail = new Intent(Intent.ACTION_SEND);

             feedbackEmail.setType("text/email");
             feedbackEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {"kenilpatel229@gmail.com"});
             feedbackEmail.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
             startActivity(Intent.createChooser(feedbackEmail, "Send Feedback:"));
            /*
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kenilpatel229@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Your Test or Application Feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Put your subject here!");
            try {
                startActivity(Intent.createChooser(intent, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            } */
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    /*method to handle network connection**/
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void alertNoConnection() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(StuMainScreen.this);
        builder.setCancelable(true);
        builder.setTitle("No Connection Available!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

}

