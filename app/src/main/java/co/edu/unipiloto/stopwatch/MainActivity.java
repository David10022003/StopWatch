package co.edu.unipiloto.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    private int laps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        TextView addlap = (TextView) findViewById(R.id.textView2);
        running = false;
        seconds = 0;
        addlap.setText("");
        laps = 0;
    }
    public void onClickLap(View view){
        TextView textView = (TextView) findViewById(R.id.time_view);
        TextView addlap = (TextView) findViewById(R.id.textView2);
        if(laps < 5){
            addlap.setText(addlap.getText().toString() + "\n" + textView.getText().toString());
            if(laps == 4) {
                Button lap = (Button) findViewById(R.id.reset_lap);
                lap.setEnabled(false);
            }
        }
        laps++;
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running)
                    seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }
}