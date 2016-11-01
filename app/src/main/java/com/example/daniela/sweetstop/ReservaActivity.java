package com.example.daniela.sweetstop;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity  implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    Button buttonFI, buttonFF, buttonHI, buttonHF, buttonEnviar;
    TextView textViewFI, textViewFF, textViewHI, textViewHF, textViewidMesa;

    String idMesa;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        buttonFI = (Button) findViewById(R.id.btn_reserva_fecha_inicio);
        buttonFF = (Button) findViewById(R.id.btn_reserva_fecha_final);
        buttonHI = (Button) findViewById(R.id.btn_reserva_hora_inicio);
        buttonHF = (Button) findViewById(R.id.btn_reserva_hora_final);
        buttonEnviar = (Button) findViewById(R.id.btn_reserva_guardar);

        textViewFI = (TextView) findViewById(R.id.tv_reserva_fecha_inicio);
        textViewFF = (TextView) findViewById(R.id.tv_reserva_fecha_final);
        textViewHI = (TextView) findViewById(R.id.tv_reserva_hora_inicio);
        textViewHF = (TextView) findViewById(R.id.tv_reserva_hora_final);
        textViewidMesa = (TextView) findViewById(R.id.tv_reserva_id_mesa);

        idMesa = getIntent().getStringExtra("idMesa");
        idUsuario = ((SwAndroid) getApplicationContext()).getId_cliente();
        textViewidMesa.setText(idMesa);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Show a timepicker when the timeButton is clicked
        buttonHI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        ReservaActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.enableMinutes(true);
                tpd.setTitle("TimePicker Title");
                tpd.setAccentColor(Color.parseColor("#9C27B0"));
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        buttonHF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        ReservaActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.enableMinutes(true);
                tpd.setTitle("TimePicker Title");
                tpd.setAccentColor(Color.parseColor("#9C27B0"));
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        buttonFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ReservaActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setTitle("DatePicker Title");
                dpd.setAccentColor(Color.parseColor("#9C27B0"));
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");

        if(tpd != null) tpd.setOnTimeSetListener(this);
        if(dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"-"+(++monthOfYear)+"-"+year;
        textViewFI.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = hourString+":"+minuteString+":"+secondString;
        textViewHI.setText(time);
    }
}
