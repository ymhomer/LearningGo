package app.learninggo.com.learninggo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] suggestArr = {"Java","Html","C++","C#"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        try {
            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, suggestArr);

            ListView listView = (ListView) findViewById(R.id.lvCourse);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(CourseActivity.this, e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
