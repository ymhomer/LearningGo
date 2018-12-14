package app.learninggo.com.learninggo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDialog = new Dialog(this);


        //ShowPopup();
        mAuth = FirebaseAuth.getInstance();
        ivlogo = (ImageView) findViewById(R.id.app_bar_image);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.transition);
        ivlogo.startAnimation(ani);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Bundle bundle = new Bundle();
        //bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Testing ID");
        //bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Testing Name");
        //bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //TODO : Button function
        Button btnProfile;
        Button btnLogout;
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                try {
                    mAuth.signOut();
                    Toast.makeText(MainActivity.this, "Sign out success.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {
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
    }

    private void updateUI(FirebaseUser currentUser) {
        if (uid.equals("none")){
            //Toast.makeText(MainActivity.this,"Please Login to continue.", Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(MainActivity.this,"SignIn Success : " + uid + ", " + userName + ", " + userEmail, Toast.LENGTH_LONG).show();
        }

        try {
            TextView tvUserName;
            TextView tvEmail;

            tvUserName = (TextView) findViewById(R.id.tvUserName);
            tvEmail = (TextView) findViewById(R.id.tvEmail);

            tvUserName.setText(userName);
            tvEmail.setText(userEmail);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //if (drawer.isDrawerOpen(GravityCompat.START)) {
        //    drawer.closeDrawer(GravityCompat.START);
        //} else {
        //    super.onBackPressed();
        //}

        //Do nothing
    }

    //Pop up - Login menu
    public void ShowPopup(View v) {
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
                Intent intent = new Intent(MainActivity.this, LearnerLoginActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnInstructorLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(MainActivity.this, InstructorLoginActivity.class);
                Intent intent = new Intent(MainActivity.this, Login2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loginDialog.show();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        try {
            int id = item.getItemId();
            if (id == R.id.profile) {
                try {
                    Intent intent = new Intent(this, ProfileActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            } else if (id == R.id.dashboard) {
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (id == R.id.find) {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
            } else if (id == R.id.course) {
                Intent intent = new Intent(this, CourseActivity.class);
                startActivity(intent);
            } else if (id == R.id.feedback) {
                Intent intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
            } else if (id == R.id.about) {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
            }
            else if (id == R.id.logout) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Sign out success.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, IndexActivity.class);
                startActivity(intent);
                //firebase.auth().signOut().then(function() {
                    // Sign-out successful.
                //}).catch(function(error) {
                    // An error happened.
                //});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
