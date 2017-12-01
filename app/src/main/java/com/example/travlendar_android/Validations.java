package com.example.travlendar_android;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by mukraswa on 11/30/2017.
 */

public class Validations {
    final static String DATE_FORMAT = "MM-dd-yyyy";
    private Pattern pattern;
    private Matcher matcher;

    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";



    public Validations() {

    }

    //@return true if the name is not null
    public boolean isNameValid(@NonNull String name) {
        return !TextUtils.isEmpty(name);
    }

    //return true if the location is not null
    public boolean isLocationValid(@NonNull String location) {
        return !TextUtils.isEmpty(location);
    }

    //return true if the startdate is not null and has a valid date
    public boolean isStartDateValid(@NonNull String sdate, @NonNull String stime) {
        return  !TextUtils.isEmpty(sdate) && isDateValid(sdate) && !TextUtils.isEmpty(stime);
    }

    //return true if the enddate is not null and has a valid date
    public boolean isEndDateValid(@NonNull String edate, @NonNull String etime) {
        return !TextUtils.isEmpty(edate) && isDateValid(edate) && !TextUtils.isEmpty(etime)  ;
    }

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            Log.e("Date Validation","true");
            return true;

        } catch (ParseException e) {
            Log.e("Date Validation","false");
            return false;

        }
    }


    public boolean isTimeValid(String time){
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
        matcher = pattern.matcher(time);
        return matcher.matches();

    }
}
