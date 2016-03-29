package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {
	
	private Context context = null;
	private boolean isRunning = true;
	
	private Random random = new Random();
	final public static String SOME_ACTION = "ro.pub.cs.systems.eim.lab04.SomeAction.SOME_ACTION";
	private int randomNumberGeneratedInService = 0;

	public ProcessingThread(Context context, int numGenerated) {
		this.context = context;
		this.randomNumberGeneratedInService = numGenerated;
	}
	
	@Override
	public void run() {
		Log.d("[ProcessingThread]", "Thread has started!");
		while (isRunning) {
			sendMessage();
			sleep();
		}
		Log.d("[ProcessingThread]", "Thread has stopped!");
	}
	
	private void sendMessage() {
		Log.d("[ProcessingThread]", "Mesaj has started!");
		Intent intent = new Intent(SOME_ACTION);
		int ranNum = random.nextInt(10);
		intent.putExtra("message", Integer.toString(ranNum));
		context.sendBroadcast(intent);
	}
	
	private void sleep() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}
	
	public void stopThread() {
		isRunning = false;
	}

}
