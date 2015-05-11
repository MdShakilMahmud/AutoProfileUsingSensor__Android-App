package mehedi.aiub.AutoProfileUsingSensor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

public class TheService extends Service implements SensorEventListener 
{
	
	private SensorManager sensorManager;
	private AudioManager audioManager;
	private Sensor proximitySensor;
	private int flag = 0;

	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
//		Toast.makeText(getApplicationContext(), "Service Created", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		if(flag==0)
		{
			sensorManager.registerListener(this, proximitySensor, sensorManager.SENSOR_DELAY_FASTEST);
			flag=1;
		}	
		
		
		Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}

	
	public void onDestroy()
	{
		Toast.makeText(getApplicationContext(), "Service Destroyed", Toast.LENGTH_SHORT).show();
		sensorManager.unregisterListener(this);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) 
	{
		double value = event.values[0];
		int mode;
		
		if(value == 0.0)
		{	
			mode = audioManager.getRingerMode();
			
			if(mode == AudioManager.RINGER_MODE_NORMAL)
			{
				audioManager.setRingerMode(audioManager.RINGER_MODE_VIBRATE);
				Toast.makeText(getApplicationContext(), "Vibration Mode Started", Toast.LENGTH_SHORT).show();
			}
			else
			{
				audioManager.setRingerMode(audioManager.RINGER_MODE_NORMAL);			
			
				Toast.makeText(getApplicationContext(), "Normal Mode Started", Toast.LENGTH_SHORT).show();
			}
		}
		
//		else
//		{			
//			mode = audioManager.getRingerMode();
//			if(mode == AudioManager.RINGER_MODE_NORMAL)
//			{
//				audioManager.setRingerMode(audioManager.RINGER_MODE_NORMAL);
//			}
//			else
//				audioManager.setRingerMode(audioManager.RINGER_MODE_VIBRATE);
//				
//			
//			Toast.makeText(getApplicationContext(), "Normal Mode Started", Toast.LENGTH_SHORT).show();
//		}
		
		
	}

}
