package com.appspouch.quizze.Teacher_Section;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.appspouch.quizze.Auth_Controller.AboutUsActivity;
import com.appspouch.quizze.Auth_Controller.PrivacyPolicyActivity;
import com.appspouch.quizze.Auth_Controller.TeacherLoginActivity;
import com.appspouch.quizze.Fragment.HomeFragment;
import com.appspouch.quizze.Fragment.ProfileFragment;
import com.appspouch.quizze.Fragment.ClassFragment;
import com.appspouch.quizze.Fragment.ResultFragment;
import com.appspouch.quizze.R;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeaMainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase database;
    private FirebaseAuth tAuth;
    public ImageButton imageButton;
    private StorageReference firebaseStorage;
    public CircleImageView imageView1;
    final long ONE_MEGABYTE = 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main_screen);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


  /*  public void setImageOnNavHeader() {

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (isNetworkAvailable(TeaMainScreen.this)) {
                startActivity(new Intent(TeaMainScreen.this, HomeFragment.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_Profile) {
            if ( isNetworkAvailable(TeaMainScreen.this)) {
                Intent intent = new Intent(TeaMainScreen.this, ProfileFragment.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_class) {
            if (isNetworkAvailable(TeaMainScreen.this)) {
                startActivity(new Intent(TeaMainScreen.this, ClassFragment.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else if (isNetworkAvailable(TeaMainScreen.this))
                Toast.makeText(getApplicationContext(), "You are not Admin!", Toast.LENGTH_SHORT).show();
            else
                alertNoConnection();
        } else if (id == R.id.nav_result) {
            if (isNetworkAvailable(TeaMainScreen.this)) {
                startActivity(new Intent(TeaMainScreen.this, ResultFragment.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
                alertNoConnection();
        } else if (id == R.id.nav_logout) {
            tAuth.signOut();
            startActivity(new Intent(TeaMainScreen.this, TeacherLoginActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(TeaMainScreen.this, AboutUsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (id == R.id.nav_privacy_policy) {
            startActivity(new Intent(TeaMainScreen.this, PrivacyPolicyActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (id == R.id.feedback_id) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Enter your email here"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Your Test or Application Feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Put your subject here!");
            try {
                startActivity(Intent.createChooser(intent, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
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

        final AlertDialog.Builder builder = new AlertDialog.Builder(TeaMainScreen.this);
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