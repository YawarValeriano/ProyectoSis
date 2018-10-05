package com.example.yawar.gruposdeestudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ReservarSala extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    int progress=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_sala);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progress=1;
        seekBar.setProgress(progress);
        seekBar.setMax(10);

        textView = (TextView) findViewById(R.id.textView6);
        textView.setText(""+progress);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
