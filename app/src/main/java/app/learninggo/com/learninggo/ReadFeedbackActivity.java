package app.learninggo.com.learninggo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadFeedbackActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("FEEDBACK");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_feedback);
        try {
            /*FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("FEEDBACK");*/
            auth = FirebaseAuth.getInstance();
            final TextView tvText;
            tvText = (TextView) findViewById(R.id.tvText);

            // Read from the database
            FirebaseUser user = auth.getCurrentUser();

            myRef.child("FEEDBACK").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.


                    String value = dataSnapshot.child("FEEDBACK").child("zbNRDLnJ8MXzn7PpgYTxR4y6MX33").child("Content").getValue(String.class);
                    DatabaseReference value2 = dataSnapshot.getRef();
                    DataSnapshot feedbackSnapshot = dataSnapshot.child("FEEDBACK");
                    String feedbackSnapshot2 = dataSnapshot.child("FEEDBACK").getKey();
                    Iterable<DataSnapshot> feedbackChild = feedbackSnapshot.getChildren();
                    String feedbackChild2 = String.valueOf(feedbackSnapshot.getChildren());
                    Toast.makeText(ReadFeedbackActivity.this, feedbackSnapshot.toString() + feedbackChild2, Toast.LENGTH_LONG).show();
                    tvText.setText(feedbackSnapshot.toString() + feedbackChild2);

                    //String value = dataSnapshot.getValue(String.class);
                    //Log.d(TAG, "Value is: " + value);
                    //Toast.makeText(ReadFeedbackActivity.this, value, Toast.LENGTH_LONG).show();
                    //tvText.setText(value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ReadFeedbackActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
