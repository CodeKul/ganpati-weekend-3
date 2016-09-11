package com.codekul.myapplication;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {


    public static final String TAG_ALERT = "alert";
    public static final String TAG_DATE = "date";
    public static final String TAG_TIME = "time";
    public static final String TAG_PROGRESS = "progress";
    public static final String TAG_CUSTOM = "custom";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = null;

        if(getTag().equals(TAG_ALERT)) dialog = alert();
        else if(getTag().equals(TAG_DATE)) dialog = date();
        else if(getTag().equals(TAG_TIME)) dialog = time();
        else if(getTag().equals(TAG_PROGRESS)) dialog = progress();
        else dialog = custom();
        return dialog;
    }



    private Dialog date() {

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        mt(""+i2+" - "+(i1+1)+" - "+i);
                    }
                }, 2016, 8, 10);


        return datePickerDialog;
    }

    private Dialog time(){

        TimePickerDialog timePickerDialog =
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mt(""+i+" : "+i1);
                    }
                },5,35,false);

        return timePickerDialog;
    }

    private Dialog progress(){
        ProgressDialog progressDialog =
                new ProgressDialog(getActivity());
        progressDialog.setTitle("Title");
        progressDialog.setMessage("Message");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return  progressDialog;
    }

    private AlertDialog alert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Title")
                .setMessage("Message")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mt("Yes");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mt("No");
                    }
                });

        return builder.create();
    }

    private Dialog custom() {

        View view = getActivity()
                .getLayoutInflater()
                .inflate(R.layout.fragment_my_dialog,null,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return builder.create();
    }

    private void mt(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
