package aptproject.travlendarapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;




/**
 * Created by mukraswa on 11/27/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    int mNum;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        mNum = getArguments() != null ? getArguments().getInt("buttonClick") : 1;
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tv = (TextView) getActivity().findViewById(R.id.startDate);
        switch  (mNum) {
        case 1:
            tv = (TextView) getActivity().findViewById(R.id.startDate);
            break;
        case 2:
            tv = (TextView) getActivity().findViewById(R.id.endDate);
            break;
        }

             //Set a message for user
        //Display the user changed time on TextView
        tv.setText(  String.valueOf(month + 1)+ "-" + String.valueOf(day)  + "-" + String.valueOf(year));

    }

}
