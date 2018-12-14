package app.learninggo.com.learninggo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private ImageView sLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        sLogo = (ImageView) findViewById(R.id.ivLogo);

        Animation ani = AnimationUtils.loadAnimation(this, R.anim.transition);
        sLogo.startAnimation(ani);
    }
}
