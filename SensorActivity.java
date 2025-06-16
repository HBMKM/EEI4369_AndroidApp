package com.s23010175.manuli;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor tempSensor;
    private MediaPlayer mediaPlayer;
    private TextView tvStatus;
    private TextView tvTempValue;
    private final float THRESHOLD_TEMP = 75.0f;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tvStatus = findViewById(R.id.tvStatus);
        tvTempValue = findViewById(R.id.tvTempValue);

        mediaPlayer = MediaPlayer.create(this,R.raw.song);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        if (tempSensor == null){
            tvStatus.setText("Temprature Sensor Not Found");
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (tempSensor != null){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        float temp = event.values[0];
        tvTempValue.setText("Current Temperature: "+ temp + "°C");

        if (temp > THRESHOLD_TEMP && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
            Toast.makeText(this, "Temperature too high", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public  void  onAccuracyChanged(Sensor sensor, int accuracy){}
}
