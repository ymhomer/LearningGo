package app.learninggo.com.learninggo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    String[] suggestArr = {"Java","Html","C++","C#"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        try {
            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, suggestArr);

            ListView listView = (ListView) findViewById(R.id.lvSuggestion);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(SearchActivity.this, e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}