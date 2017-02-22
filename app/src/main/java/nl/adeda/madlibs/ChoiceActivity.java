package nl.adeda.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button btn1 = (Button) findViewById(R.id.choiceBtn1);
        Button btn2 = (Button) findViewById(R.id.choiceBtn2);
        Button btn3 = (Button) findViewById(R.id.choiceBtn3);
        Button btn4 = (Button) findViewById(R.id.choiceBtn4);
        Button btn5 = (Button) findViewById(R.id.choiceBtn5);

        // Set listeners
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String fileName = "";

        // Select story
        switch(v.getId()) {
            case R.id.choiceBtn1:
                fileName = "madlib0_simple.txt";
                break;
            case R.id.choiceBtn2:
                fileName = "madlib1_tarzan.txt";
                break;
            case R.id.choiceBtn3:
                fileName = "madlib2_university.txt";
                break;
            case R.id.choiceBtn4:
                fileName = "madlib3_clothes.txt";
                break;
            case R.id.choiceBtn5:
                fileName = "madlib4_dance.txt";
                break;
        }

        // Launch next activity
        Intent intent = new Intent(this, FillingActivity.class);
        intent.putExtra("Filename", fileName);
        startActivity(intent);
        finish();

    }
}
