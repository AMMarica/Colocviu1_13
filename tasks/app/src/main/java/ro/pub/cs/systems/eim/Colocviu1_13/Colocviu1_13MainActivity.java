package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private Button north, south, east, west, navigate_to_secondary_activity;
    private EditText commands;
    Integer pressButtons = 0;
    String PRESS = "pressButtons";
    String COMMANDS = "commands";

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                    String commd = commands.getText().toString();
                    intent.putExtra(COMMANDS, commd);
                    startActivityForResult(intent, 2020);
                    break;
                default:
                    pressButtons = pressButtons + 1;
                    Log.d("Increment", "Number of buttons pressed: " + String.valueOf(pressButtons));
                    commands.setText(commands.getText().toString() + " " + ((Button)view).getText().toString());
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        north = (Button)findViewById(R.id.north_button);
        north.setOnClickListener(buttonClickListener);
        south = (Button)findViewById(R.id.south_button);
        south.setOnClickListener(buttonClickListener);
        east = (Button)findViewById(R.id.east_button);
        east.setOnClickListener(buttonClickListener);
        west = (Button)findViewById((R.id.west_button));
        west.setOnClickListener(buttonClickListener);

        commands = (EditText) findViewById(R.id.instructions_edit_text);

        navigate_to_secondary_activity = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigate_to_secondary_activity.setOnClickListener((buttonClickListener));

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(PRESS)) {
                pressButtons = Integer.valueOf(savedInstanceState.getString(PRESS));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(PRESS, String.valueOf(pressButtons));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(PRESS)) {
            pressButtons = Integer.valueOf(savedInstanceState.getString(PRESS));
        }
        Log.d("Increment", "Number of buttons pressed: " + String.valueOf(pressButtons));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2020) {
            if (resultCode == 0) {
                Toast.makeText(this, "CANCEL pressed ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "REGISTER pressed ", Toast.LENGTH_LONG).show();
            }

            commands.setText("");
            pressButtons = 0;
        }
    }
}
