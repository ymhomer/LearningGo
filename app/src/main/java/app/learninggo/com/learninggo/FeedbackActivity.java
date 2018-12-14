package app.learninggo.com.learninggo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText etTitle, etContent;
    private Button btnSubmit;
    private Date currentTime;

    String userName;
    String userEmail;
    String uid;
    String feedbackTitle = "";
    String feedbackContent = "";

    private DatabaseReference mDatabase;
// ...
    //mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //EditText etTitle;
        //EditText etContent;
        //Button btnSubmit;

        currentTime = Calendar.getInstance().getTime();

        etTitle = (EditText) findViewById(R.id.etTitle);
        etContent = (EditText) findViewById(R.id.etContent);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        try {
            mAuth = FirebaseAuth.getInstance();
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
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(FeedbackActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final Button btnSubmit = findViewById(R.id.btnSubmit);
        final Button btnView = findViewById(R.id.btnView);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etTitle = (EditText) findViewById(R.id.etTitle);
                etContent = (EditText) findViewById(R.id.etContent);
                Toast.makeText(FeedbackActivity.this, feedbackTitle,Toast.LENGTH_LONG).show();
                try {
                    feedbackTitle = etTitle.getText().toString();
                    feedbackContent = etContent.getText().toString();

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //DatabaseReference myRefID = database.getReference("feedback").child("userID");
                    String ID = database.getReference("FEEDBACK").push().getKey();

                    DatabaseReference myRef = database.getReference("FEEDBACK").child(ID).push();
                    DatabaseReference myRefID = database.getReference("FEEDBACK").child(ID).child("ID");
                    DatabaseReference myRefName = database.getReference("FEEDBACK").child(ID).child("userName");
                    DatabaseReference myRefEmail = database.getReference("FEEDBACK").child(ID).child("userEmail");
                    DatabaseReference myRefTitle = database.getReference("FEEDBACK").child(ID).child("Title");
                    DatabaseReference myRefContent = database.getReference("FEEDBACK").child(ID).child("Content");
                    DatabaseReference myRefDateNTime = database.getReference("FEEDBACK").child(ID).child("Time");

                    //myRefID.setValue(uid);
                    //myRefID.setValue(myRefID.push());
                    myRefName.setValue(userName);
                    myRefEmail.setValue(userEmail);
                    myRefTitle.setValue(feedbackTitle);
                    myRefContent.setValue(feedbackContent);
                    myRefDateNTime.setValue(currentTime);
                    Toast.makeText(FeedbackActivity.this, "Thank you for your feedback.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(FeedbackActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(FeedbackActivity.this, ReadFeedbackActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(FeedbackActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class User {

        public String username;
        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    public void onStart() {
        super.onStart();

    }
}
