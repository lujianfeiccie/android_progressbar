package com.example.testandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	int MAX_PROGRESS = 20;
	MyProgressBar progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressbar = (MyProgressBar)findViewById(R.id.progressbar);
		progressbar.setMax(MAX_PROGRESS);
		progressbar.setTextSize(50);
		handler.post(runnable);
	}
	int i=0;
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if(i<=progressbar.getMax()){
			handler.postDelayed(this, 100);
			progressbar.setProgress(i);
			++i;
			}else{
				handler.removeCallbacks(this);
			}
		}
	};
}
