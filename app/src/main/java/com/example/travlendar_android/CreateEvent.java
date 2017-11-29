package com.example.travlendar_android;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateEvent extends AppCompatActivity {

    private RadioGroup radioTravel;
    private RadioButton radioTravelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
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

    public void sendJSON() {

        EditText txtName = (EditText) findViewById(R.id.eventName);
        String eventName = txtName.getText().toString();

        EditText txtLocation = (EditText) findViewById(R.id.location);
        String  Location = txtLocation.getText().toString();

        EditText txtStartDate = (EditText) findViewById(R.id.startDate);
        EditText txtStartTime = (EditText) findViewById(R.id.startTime);
        String eventStart = txtStartDate.getText().toString() + " " + txtStartTime.getText().toString();

        EditText txtEndDate = (EditText) findViewById(R.id.endDate);
        EditText txtEndTime = (EditText) findViewById(R.id.endTime);
        String eventEnd = txtEndDate.getText().toString() + " " + txtEndTime.getText().toString();


        // get selected radio button from radioGroup
        int selectedId = radioTravel.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioTravelButton = (RadioButton) findViewById(selectedId);

        //String jsonString = "{ name: '" + eventName + "', address: '" + Location + "', eventStart: '" + eventStart + "', eventEnd: '" + eventEnd + "',travel: '" + radioTravelButton + "'}";
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", eventName);
            postData.put("address", Location);
            postData.put("eventStart", eventStart);
            postData.put("eventEnd", eventEnd);
            postData.put("travel", radioTravelButton);

            new SendEventDetails().execute("url to server", postData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
