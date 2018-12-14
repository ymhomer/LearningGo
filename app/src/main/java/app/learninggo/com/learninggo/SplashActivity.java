package app.learninggo.com.learninggo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    //private static int totalProgressTime = 100;
    private static int TIME_OUT = 3000;

    private ImageView sLogo;
    private TextView sSystemName;
    private TextView sSystemDesc;
    private TextView sVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sLogo = (ImageView) findViewById(R.id.ivLogo);
        sSystemName = (TextView) findViewById(R.id.tvSystemName);
        sSystemDesc = (TextView) findViewById(R.id.tvSystemDesc);
        sVersion = (TextView) findViewById(R.id.tvVersion);


        Animation ani = AnimationUtils.loadAnimation(this, R.anim.transition);
        sLogo.startAnimation(ani);
        sSystemName.startAnimation(ani);
        sSystemDesc.startAnimation(ani);
        sVersion.startAnimation(ani);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, IndexActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

    }
}
