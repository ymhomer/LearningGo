package app.learninggo.com.learninggo;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        loadFrament(new Dashboard_frm());

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        try {
            //Get the bundle
            Bundle bundle = getIntent().getExtras();

            //Extract the data…
            String uid = bundle.getString("uid");
            String userName = bundle.getString("userName");
            String userEmail = bundle.getString("userEmail");

            Toast.makeText(DashboardActivity.this,"SignIn Success : " + uid + ", " + userName + ", " + userEmail, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(DashboardActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    //change the fragment
    private boolean loadFrament(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frmDashboard, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_dashboard:
                fragment = new Dashboard_frm();
                break;
            case R.id.navigation_about:
                intent = new Intent(DashboardActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }

        return loadFrament(fragment);
    }
}
