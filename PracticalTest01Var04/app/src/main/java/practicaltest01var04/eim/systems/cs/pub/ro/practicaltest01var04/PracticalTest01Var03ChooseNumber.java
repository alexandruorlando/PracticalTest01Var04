package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

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

public class PracticalTest01Var03ChooseNumber extends ActionBarActivity {
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private EditText numberField = null;
    private Button playButton = null;

    private int serviceStatus = Constants.SERVICE_STOPPED;


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int numberInserted = Integer.parseInt(numberField.getText().toString());
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
            intent.putExtra("numberInserted", numberInserted);
            if(numberInserted >= 0 && numberInserted <= 9) {
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);

        numberField = (EditText)findViewById(R.id.left_edit_text);
        playButton = (Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(buttonClickListener);
        //numberField = (EditText)findViewById(R.id.left_edit_text);
        //int val = Integer.parseInt(numberField.getText().toString());

        //playButton = (Button)findViewById(R.id.left_button);
       // playButton.setOnClickListener(buttonClickListener);


        /*for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var03_choose_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
