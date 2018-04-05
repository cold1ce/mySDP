package de.hslu.mysdp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    public TextView t1, t2, t3;
    public Button b1, b2, b3;
    public CheckBox c1, c2, c3, c4;
    public int currentquestionnumber = 0;
    public String[] questions;
    public ArrayList<CheckBox> boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        questions = res.getStringArray(R.array.questionArray);
        t1 = (TextView) findViewById(R.id.textView2);
        t2 = (TextView) findViewById(R.id.textView3);
        t3 = (TextView) findViewById(R.id.textView4);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button4);
        c1 = (CheckBox) findViewById(R.id.checkBox);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        c4 = (CheckBox) findViewById(R.id.checkBox4);
        boxes = new ArrayList<CheckBox>();
        boxes.add(c1);
        boxes.add(c2);
        boxes.add(c3);
        boxes.add(c4);
        reset();
        //showNextQuestion();
    }
    public void onYesPressed(View view) {
        Log.d("Main", "Ja gedrückt, currentQnr ist:"+currentquestionnumber);
        if (currentquestionnumber==1) {
            displayResult(1,"V-Modell");
        }
        if (currentquestionnumber==2) {
            displayResult(2,"Wasserfallmodell");
        }
        if (currentquestionnumber==3) {
            int x = countCheckedBoxes(boxes);
            if (x == 0) {
                displayResult(3, "Spiralmodell oder XP");
            }
            else {
                String concat = "";
                if (c1.isChecked() == TRUE) {
                    concat = concat+"\nRUP";
                }
                if (c2.isChecked() == TRUE) {
                    concat = concat+"\nXP";
                }
                if (c3.isChecked() == TRUE) {
                    concat = concat+"\nSCRUM";
                }
                if (c4.isChecked() == TRUE) {
                    concat = concat+"\nSpiral";
                }
                displayResult(4, concat);
            }

        }
        else {
           // showNextQuestion();
        }
    }

    public void onNoPressed(View view) {
            showNextQuestion();
    }

    public void onResetPressed(View view) {
        reset();
    }

    public void reset() {
        currentquestionnumber = 0;
        b1.setText("Ja");
        b1.setVisibility(VISIBLE); b2.setVisibility(VISIBLE);
        c1.setVisibility(INVISIBLE);c2.setVisibility(INVISIBLE);c3.setVisibility(INVISIBLE);c4.setVisibility(INVISIBLE);
        c1.setChecked(FALSE);c2.setChecked(FALSE);c3.setChecked(FALSE);c4.setChecked(FALSE);

        showNextQuestion();
    }
    public void showNextQuestion() {
        currentquestionnumber += 1;
        t2.setText("Frage "+currentquestionnumber+":");
        t3.setText(questions[currentquestionnumber]+"");
        if (currentquestionnumber == 3) {
            b1.setText("Weiter");
            b2.setVisibility(INVISIBLE);
            c1.setVisibility(VISIBLE);c2.setVisibility(VISIBLE);c3.setVisibility(VISIBLE);c4.setVisibility(VISIBLE);
        }

    }

    public void displayResult(int code, String text) {
        Log.d("Main","DisplayResult code:"+code+"text: "+text);
        t2.setText("Empfehlung in Ihrem Fall:");
        b1.setVisibility(INVISIBLE); b2.setVisibility(INVISIBLE); c1.setVisibility(INVISIBLE);c2.setVisibility(INVISIBLE);c3.setVisibility(INVISIBLE);c4.setVisibility(INVISIBLE);
        t3.setText(text+"");
        t3.setTextSize(18);
    }

    public int countCheckedBoxes(ArrayList<CheckBox> boxes) {
        int counter = 0;
        for( CheckBox b: boxes) {
            if (b.isChecked() == TRUE) {
                counter += 1;
            }
        }
        return counter;
    }


}
