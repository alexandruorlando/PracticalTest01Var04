package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Random;

public class PracticalTest01Service extends Service {
	
	private ProcessingThread processingThread = null;
	private Random random = new Random();
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int randNumService = random.nextInt(10);
		processingThread = new ProcessingThread(this, randNumService);
		processingThread.start();
		return Service.START_REDELIVER_INTENT;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		processingThread.stopThread();
	}

}
