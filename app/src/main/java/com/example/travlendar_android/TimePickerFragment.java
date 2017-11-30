package com.example.travlendar_android;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.text.format.DateFormat;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.travlendar_android.R;

/**
 * Created by mukraswa on 11/27/2017.
 */

public class TimePickerFragment extends DialogFragment
                                        implements TimePickerDialog.OnTimeSetListener {
    int mNum;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mNum = getArguments() != null ? getArguments().getInt("buttonClick") : 1;
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Set the Time for the StartTime or EndTime depending on the time picker selected
        TextView tv = (TextView) getActivity().findViewById(R.id.startTime);
        switch  (mNum) {
            case 1:
                tv = (TextView) getActivity().findViewById(R.id.startTime);
                break;
            case 2:
                tv = (TextView) getActivity().findViewById(R.id.endTime);
                break;
        }


       //Display the user changed time on TextView
        tv.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + "\n");
    }
}
