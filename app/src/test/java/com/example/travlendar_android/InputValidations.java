package com.example.travlendar_android;

import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Created by mukraswa on 11/30/2017.
 */

public class InputValidations {

    Validations val = new Validations();

    @Test
    public void test_isNameValid() throws Exception {
        boolean result = val.isNameValid("Event");
        assertEquals(true,result );
    }

    @Test
    public void test_isLocationValid() throws Exception {
        boolean result = val.isLocationValid("1210 Jenny dr., Sugar Land 76749");
        assertEquals(true,result);
    }

    @Test
    public void test_isSDateValid() throws Exception {
        boolean result = val.isStartDateValid("12-15-2017","12:16");
        assertEquals(true,result);
    }

    @Test
    //Should fail because the date is not valid
    public void test_isSDateValid_2() throws Exception {
        boolean result = val.isStartDateValid("15-12-2017","12:16");
        assertEquals(false,result);
    }

    @Test
    //Should fail because the end date is empty
    public void test_isEDateValid() throws Exception {
        boolean result = val.isStartDateValid("","12:16");
        assertEquals(false,result);
    }



}
