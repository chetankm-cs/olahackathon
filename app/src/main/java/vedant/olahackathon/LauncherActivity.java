package vedant.olahackathon;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class LauncherActivity extends ActionBarActivity implements View.OnClickListener {
    public final static String KEY_READABLE_ADDRESS = "address";
    Button bookRide;
    EditText pnr;
    TextView location;
    ImageView setLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new GCMAcitivityRegister(this);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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

    private void initView() {
        bookRide = (Button) findViewById(R.id.book_ride_button);
        pnr = (EditText) findViewById(R.id.pnr_edit_text);
        location =(TextView) findViewById(R.id.location_text_view);
        setLocation = (ImageView) findViewById(R.id.get_location_button);

        location.setOnClickListener(this);
        bookRide.setOnClickListener(this);
        setLocation.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String str = data.getStringExtra(KEY_READABLE_ADDRESS);
            if(str != null) {
                Log.e("Launcher Activity", str);
                location.setText(str);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.book_ride_button :
                if(pnr.getText().toString().isEmpty())
                    pnr.setError("Please provide a pnr no");
                else if(location.getText().toString().equals("Drop Location"))
                    location.setError("Please provide drop point");
                else
                {

                }


                break;
            case R.id.location_text_view:
                startActivityForResult(new Intent(this,SelectLocationActivity.class),5);
                break;

            case R.id.get_location_button:
                startActivityForResult(new Intent(this,SelectLocationActivity.class),5);
                break;
        }
    }
}
