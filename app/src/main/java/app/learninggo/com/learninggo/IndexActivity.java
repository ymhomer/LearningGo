package app.learninggo.com.learninggo;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IndexActivity extends AppCompatActivity{
    Dialog loginDialog;

    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ImageView ivlogo;

    String userName;
    String userEmail;
    String uid;

    //Create the bundle
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            try {
                //Get Firebase auth instance
                mAuth = FirebaseAuth.getInstance();
            } catch (Exception e) {
                Toast.makeText(IndexActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            if (mAuth.getCurrentUser() != null) {
                startActivity(new Intent(IndexActivity.this, MainActivity.class));
                finish();
            }

            setContentView(R.layout.activity_index);
            loginDialog = new Dialog(this);

            //ShowPopup();

            mAuth = FirebaseAuth.getInstance();
            ivlogo = (ImageView) findViewById(R.id.app_bar_image);
            Animation ani = AnimationUtils.loadAnimation(this, R.anim.transition);
            ivlogo.startAnimation(ani);

            // Obtain the FirebaseAnalytics instance.
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Testing ID");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Testing Name");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            //drawer.addDrawerListener(toggle);
            //toggle.syncState();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            Toast.makeText(IndexActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart() {
        try {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                userName = currentUser.getDisplayName();
                userEmail = currentUser.getEmail();
                uid = currentUser.getUid();
            }
            else {
                userName = "none";
                userEmail = "none";
                uid = "none";
            }
            Intent i = new Intent(this, DashboardActivity.class);

            //Add your data to bundle
            bundle.putString("uid", uid);
            bundle.putString("userName", userName);
            bundle.putString("userEmail", userEmail);

            //Add the bundle to the intent
            i.putExtras(bundle);

            updateUI(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(IndexActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private void updateUI(FirebaseUser currentUser) {
        try {
            if (uid.equals("none")){
                Toast.makeText(IndexActivity.this,"Please Login to continue.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(IndexActivity.this,"SignIn Success : " + uid + ", " + currentUser + ", " + userName + ", " + userEmail, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(IndexActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void ShowPopup(View v) {
        try {
            TextView btnClose;
            ImageButton btnLearnerLogin;
            ImageButton btnInstructorLogin;
            Button btnRegister;
            loginDialog.setContentView(R.layout.activity_login_menu);
            btnClose = (TextView) loginDialog.findViewById(R.id.btnClose);
            btnLearnerLogin = (ImageButton) loginDialog.findViewById(R.id.btnLearnerLogin);
            btnInstructorLogin = (ImageButton) loginDialog.findViewById(R.id.btnInstructorLogin);
            btnRegister = (Button) loginDialog.findViewById(R.id.btnRegister);

            btnClose.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    loginDialog.dismiss();
                }
            });
            btnLearnerLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(IndexActivity.this, Login3Activity.class);
                    startActivity(intent);
                }
            });
            btnInstructorLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(IndexActivity.this, InstructorLoginActivity.class);
                    startActivity(intent);
                }
            });
            btnRegister.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(IndexActivity.this, SignupActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loginDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(IndexActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
