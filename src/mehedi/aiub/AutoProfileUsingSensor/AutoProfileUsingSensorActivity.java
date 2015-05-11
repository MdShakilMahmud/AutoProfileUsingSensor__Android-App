package mehedi.aiub.AutoProfileUsingSensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AutoProfileUsingSensorActivity extends Activity {
  
	Button startServiceBtn,stopServiceBtn;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startServiceBtn = (Button) findViewById(R.id.startServiceBTN);
        stopServiceBtn = (Button) findViewById(R.id.stopServiceBTN);
        
        startServiceBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) 
			{
				Intent i = new Intent(getApplicationContext(), TheService.class);
				Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
				startService(i);
				
			}
		});
        
        stopServiceBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) 
			{
				Intent i = new Intent(getApplicationContext(), TheService.class);
				Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
				stopService(i);
				
			}
		});
        
        
    }
    
    
}