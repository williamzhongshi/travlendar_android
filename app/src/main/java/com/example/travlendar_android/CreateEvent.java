package aptproject.travlendarapp;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEvent extends AppCompatActivity {

    private RadioGroup radioTravel;
    private RadioButton radioTravelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        radioTravel = (RadioGroup) findViewById(R.id.radioTravel);
    }

    public void sendJSON(View v) {
        Log.e("Error","Inside sendJson");

        EditText txtName = (EditText) findViewById(R.id.eventName);
        String eventName = txtName.getText().toString();
        Log.e("Event Name",eventName);

        EditText txtLocation = (EditText) findViewById(R.id.location);
        String  Location = txtLocation.getText().toString();
        Log.e("Location", Location);

        EditText txtStartDate = (EditText) findViewById(R.id.startDate);
        EditText txtStartTime = (EditText) findViewById(R.id.startTime);
        String eventStart = txtStartDate.getText().toString() + " " + txtStartTime.getText().toString();
        Log.e("Event Start", eventStart);

        EditText txtEndDate = (EditText) findViewById(R.id.endDate);
        EditText txtEndTime = (EditText) findViewById(R.id.endTime);
        String eventEnd = txtEndDate.getText().toString() + " " + txtEndTime.getText().toString();
        Log.e("Event End", eventEnd);

        // get selected radio button from radioGroup
        int selectedId = radioTravel.getCheckedRadioButtonId();
        //Toast.makeText(this, "Selected Id:" + selectedId, Toast.LENGTH_SHORT).show();
        // find the radiobutton by returned id
        radioTravelButton = (RadioButton) findViewById(R.id.radioCab);
        String travelBy = radioTravelButton.getText().toString();

        String jsonString = "{ name: '" + eventName + "', address: '" + Location + "', eventStart: '" + eventStart + "', eventEnd: '" + eventEnd + "',travel: '" + travelBy + "'}";
        Log.e("JSONevent",jsonString);

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", eventName);
            postData.put("address", Location);
            postData.put("eventStart", eventStart);
            postData.put("eventEnd", eventEnd);
            postData.put("travel", radioTravelButton);

            //new SendEventDetails().execute("url to server", postData.toString());

            SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            try {
                Date dstart = f.parse(eventStart);
                Date dend = f.parse(eventEnd);
                long lngEventStart = dstart.getTime();
                long lngEventEnd = dend.getTime();

                addEvent(eventName,Location,lngEventStart,lngEventEnd);
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();

            CharSequence text = e.getMessage();

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        }



        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();

    }

    public void showTimePickerDialog(View v) {
        DialogFragment timeFragment = new TimePickerFragment();
        Bundle args = new Bundle();
        switch (v.getId()) {
            case (R.id.btnStartTime):
                args.putInt("buttonClick", 1);
                break;
            case (R.id.btnEndTime):
                args.putInt("buttonClick",2);
                break;
        }

        timeFragment.setArguments(args);
        timeFragment.show(getSupportFragmentManager(),"timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment dateFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        switch (v.getId()) {
            case (R.id.btnStartDate):
                args.putInt("buttonClick", 1);
                break;
            case (R.id.btnEndDate):
                args.putInt("buttonClick",2);
                break;
        }
        dateFragment.setArguments(args);
        dateFragment.show(getSupportFragmentManager(),"datePicker");
    }



    public void addEvent(String title, String location, long begin, long end) {
         Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, title)
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }    }
}
