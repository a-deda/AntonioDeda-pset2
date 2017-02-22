package nl.adeda.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // Load story
        Intent intent = getIntent();
        Serializable story = intent.getSerializableExtra("Story");

        TextView completeStory = (TextView) findViewById(R.id.completeStory);
        completeStory.setText(story.toString());

        Button newStoryBtn = (Button) findViewById(R.id.newStory);
        newStoryBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
        finish();
    }
}
