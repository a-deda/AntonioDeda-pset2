package nl.adeda.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.Serializable;

public class FillingActivity extends AppCompatActivity implements View.OnClickListener {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filling);

        // Determine which story to load
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("Filename");

        // Make stream
        InputStream textStream = getClass().getResourceAsStream("/assets/" + fileName);

        // Construct story class
        if (savedInstanceState == null) {
            story = new Story(textStream);
        }

        else {
            story = (Story) savedInstanceState.getSerializable("Story");
        }

        // Display info
        screenInfo();

        // Set listener on button
        Button button = (Button) findViewById(R.id.nextBtn);
        button.setOnClickListener(this);
    }

    public void screenInfo() {
        // Put placeholder in textbox
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setHint(story.getNextPlaceholder());

        // Display progress
        TextView progress = (TextView) findViewById(R.id.progressIndicator);
        int remCount = story.getPlaceholderRemainingCount();
        if (remCount == 1) {
            progress.setText(remCount + " " + "word left");
        } else {
            progress.setText(remCount + " " + "words left");
        }
    }

    @Override
    public void onClick(View view) {
        // Put word into placeholder list
        EditText editText = (EditText) findViewById(R.id.editText);
        String word = editText.getText().toString();

        // Check whether word is submitted
        if (word.equals("")) {
            Toast.makeText(this, "Type in a word!", Toast.LENGTH_SHORT).show();
            return;
        }

        story.fillInPlaceholder(word);

        // Empty textbox
        editText.setText("");

        // Repeat process until done
        screenInfo();

        if (story.isFilledIn()) {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("Story", story);
            startActivity(intent);
            finish();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save state
        savedInstanceState.putSerializable("Story", story);
        super.onSaveInstanceState(savedInstanceState);
    }


}
