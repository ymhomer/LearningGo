package app.learninggo.com.learninggo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
    }

    public void onLearnerLogin (View view){
        //Intent intent = new Intent (this, LearnerLoginActivity.class);
        //startActivity(intent);
    }

    public void onInstructorLogin (View view){
        //Intent intent = new Intent (this, InstructorLoginActivity.class);
        //Intent intent = new Intent(this, SignupActivity.class);
        //startActivity(intent);
    }
}
