/*
 * Copyright (c) 2015-2015 by Shanghai shootbox Information Technology Co., Ltd.
 * Create: 2015/10/9 4:31:55
 * Project: PorgressBar
 * File: MainActivity.java
 * Class: MainActivity
 * Module: app
 * Author: yangyankai
 * Version: 1.0
 */

package com.shootbox.porgressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private ProgressBar progressBar;
	private TextView textView;
	private Handler m_handler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		textView= (TextView) findViewById(R.id.tv);
		m_handler=new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
//				super.handleMessage(msg);
				textView.setText(msg.obj.toString());
			}
		};


	}

	public void m_click(View view)
	{
		if (progressBar.getVisibility() == View.VISIBLE)
		{	progressBar.setProgress(0);


			new Thread(new Runnable() {
				@Override
				public void run()
				{
					while (progressBar.getProgress()<=80){

						int progress = progressBar.getProgress();
//						textView.setText(progress);
						Message message=new Message();
						message.obj=progress;
						m_handler.sendMessage(message);
						progress = progress + 1;
						progressBar.setProgress(progress);


						try
						{
							Thread.sleep(10);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}).start();



		}
	}
}
