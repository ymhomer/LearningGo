package app.learninggo.com.learninggo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_login);

        Button btnSignIn;
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(InstructorLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
