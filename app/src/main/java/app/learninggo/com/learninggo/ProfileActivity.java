package app.learninggo.com.learninggo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvName;
        TextView tvEmail;
        Button btnUpdate;

        try {
            //Get the bundle
            Bundle bundle = getIntent().getExtras();

            //Extract the data…
            String uid = bundle.getString("uid");
            String userName = bundle.getString("userName");
            String userEmail = bundle.getString("userEmail");

            tvName = (TextView) findViewById(R.id.tvName);
            tvEmail = (TextView) findViewById(R.id.tvEmail);
            tvName.setText(userName);
            tvEmail.setText(userEmail);

            btnUpdate = (Button) findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(ProfileActivity.this, ProfileUpdateActivity.class);
                    startActivity(intent);
                }
            });

            //Toast.makeText(ProfileActivity.this,"SignIn Success : " + uid + ", " + userName + ", " + userEmail, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
