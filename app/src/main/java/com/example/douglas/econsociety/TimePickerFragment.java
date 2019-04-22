package com.example.douglas.econsociety;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public  class TimePickerFragment extends DialogFragment{
//        implements TimePickerDialog.OnTimeSetListener {

    Context context;
    private  TimePickerDialog.OnTimeSetListener listener;

    public void setListener(TimePickerDialog.OnTimeSetListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        System.out.println(listener);
        System.out.println(context);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(context, listener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        // Do something with the time chosen by the user
//
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}