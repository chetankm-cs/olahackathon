package vedant.olahackathon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import vedant.olahackathon.network.RideConformationResponse;


public class RideConfirmationActivity extends ActionBarActivity {
    private String mNumber ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_confirmation);
        setTitle("Booking Success");
        Intent intent = getIntent();

        RideConformationResponse response = (RideConformationResponse)
                POJOToJSON.fromJson(intent.getStringExtra("JSON"),RideConformationResponse.class);

        Log.e("Ride", POJOToJSON.toJson(response, true));

                ((TextView) findViewById(R.id.name_text_view)).setText(response.getName());
        ((TextView)findViewById(R.id.vehicle_text_view)).setText(response.getVehicle().getNumber() + " "
                +response.getVehicle().getType());
        mNumber = response.getMobile();

        ImageButton btn = (ImageButton) findViewById(R.id.call_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9547891002"));
                startActivity(callIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ride_confirmation, menu);
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
