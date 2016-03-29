package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import java.util.Date;
import java.util.Random;


public class PracticalTest01Var02PlayActivity extends ActionBarActivity {
    int numberInsertedOriginal = 100;
    final public static String SOME_ACTION = "ro.pub.cs.systems.eim.lab04.SomeAction.SOME_ACTION";
    private Button generateButton = null;
    private Button checkButton = null;
    private Button backButton = null;
    private Random random = new Random();
    private EditText guessText = null;
    private EditText scoreText = null;
    private int totalScore = 0;
    private IntentFilter intentFilter = new IntentFilter(SOME_ACTION);

    private ButtonGenerateListener buttonGenerateListener = new ButtonGenerateListener();
    private class ButtonGenerateListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int randNum = random.nextInt(10);
            guessText.setText(String.valueOf(randNum));
        }
    }
    private ButtonCheckListener buttonCheckListener = new ButtonCheckListener();
    private class ButtonCheckListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String numGen = guessText.getText().toString();
            String numInsert = Integer.toString(numberInsertedOriginal);
            if(numGen.equals(numInsert)) {
                totalScore++;
            }
            scoreText.setText("" + totalScore);
        }
    }
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            guessText.setText(intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    private ButtonBackListener buttonBackListener = new ButtonBackListener();
    private class ButtonBackListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("score", scoreText.getText().toString());
        savedInstanceState.putString("guess", guessText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("score")) {
            scoreText.setText(savedInstanceState.getString("score"));
        } else {
            scoreText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("guess")) {
            guessText.setText(savedInstanceState.getString("guess"));
        } else {
            guessText.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("numberInserted")) {
            numberInsertedOriginal = intent.getIntExtra("numberInserted", -1);
        }


        guessText = (EditText)findViewById(R.id.guessField);
        scoreText = (EditText)findViewById(R.id.score);
        generateButton = (Button)findViewById(R.id.generate);
        generateButton.setOnClickListener(buttonGenerateListener);
        checkButton = (Button)findViewById(R.id.check);
        checkButton.setOnClickListener(buttonCheckListener);
        backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(buttonBackListener);

        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Service.class);
        getApplicationContext().startService(intent2);
    }

}
