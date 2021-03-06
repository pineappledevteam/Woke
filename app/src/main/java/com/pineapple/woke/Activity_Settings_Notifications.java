package com.pineapple.woke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pineapple.woke.resources.Constants;
import com.pineapple.woke.resources.Singleton;

public class Activity_Settings_Notifications extends AppCompatActivity {
    ImageButton imgButton_back;

    TextView textView_intervals_num;
    SeekBar seekBar_intervals;
    double user_interval;

    TextView textView_frequency_num;
    SeekBar seekBar_frequency;
    int user_frequency;

    boolean[] first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notifications);

        imgButton_back = findViewById(R.id.imageButton_buttonBack);
        imgButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        first = new boolean[]{true, true};

        initInterval();
        initFrequency();

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    private void initInterval(){
        textView_intervals_num = findViewById(R.id.textView_intervals_value);
        user_interval = Singleton.getInstance().getCurrUser().getNotif_interval();
        Log.d("INTERVAL", "User: " + Double.toString(user_interval));
        String str_interval = (user_interval > 1.0? ((int) user_interval +"m"):((int)(user_interval *60)+"s"));
        textView_intervals_num.setText(str_interval);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener_interval = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // updated continuously as the user slides the thumb
                if(!first[0]){
                    double change = (progress+(Constants.NOTIF_INTERVAL_MIN *10))/10.0;
                    String str_interval = (change > 1.0? ((int)change+"m"):((int)(change*60)+"s"));
                    textView_intervals_num.setText(str_interval);
                    Log.d("INTERVAL", "NewChange: " + Double.toString(change));
                }
                else{
                    first[0] = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // called when the user first touches the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // called after the user finishes moving the SeekBar
                Log.d("INTERVAL", "NewStop: " + Double.toString((seekBar.getProgress()+(Constants.NOTIF_INTERVAL_MIN *10))/10.0));
                Singleton.getInstance().getCurrUser().setNotif_interval((seekBar.getProgress()+(Constants.NOTIF_INTERVAL_MIN *10))/10.0);
            }
        };

        seekBar_intervals = findViewById(R.id.seekBar_intervals);
        seekBar_intervals.setProgress(((int)(user_interval *10))-(int)(Constants.NOTIF_INTERVAL_MIN *10));
        seekBar_intervals.setOnSeekBarChangeListener(seekBarChangeListener_interval);
        seekBar_intervals.setMax((int)((Constants.NOTIF_INTERVAL_MAX - Constants.NOTIF_INTERVAL_MIN)*10));
        Log.d("INTERVAL", "SetProgress: " + Double.toString(user_interval));
    }

    private void initFrequency(){
        textView_frequency_num = findViewById(R.id.textView_frequency_value);
        user_frequency = Singleton.getInstance().getCurrUser().getNotif_frequency();
        Log.d("FREQUENCY", "User: " + Integer.toString(user_frequency));
        String str_frequency = Integer.toString(user_frequency);
        textView_frequency_num.setText(str_frequency);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener_frequency = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // updated continuously as the user slides the thumb
                if(!first[1]){
                    int change = (progress+Constants.NOTIF_FREQUENCY_MIN);
                    String str_frequency = Integer.toString(change);
                    textView_frequency_num.setText(str_frequency);
                    Log.d("FREQUENCY", "NewChange: " + Integer.toString(change));
                }
                else{
                    first[1] = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // called when the user first touches the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // called after the user finishes moving the SeekBar
                Log.d("FREQUENCY", "NewStop: " + Integer.toString(seekBar.getProgress()+Constants.NOTIF_FREQUENCY_MIN));
                Singleton.getInstance().getCurrUser().setNotif_frequency(seekBar.getProgress()+Constants.NOTIF_FREQUENCY_MIN);
            }
        };

        seekBar_frequency = findViewById(R.id.seekBar_frequency);
        seekBar_frequency.setProgress(user_frequency - Constants.NOTIF_FREQUENCY_MIN);
        seekBar_frequency.setOnSeekBarChangeListener(seekBarChangeListener_frequency);
        seekBar_frequency.setMax(Constants.NOTIF_FREQUENCY_MAX - Constants.NOTIF_FREQUENCY_MIN);
        Log.d("FREQUENCY", "SetProgress: " + Integer.toString(user_frequency));
    }
}
