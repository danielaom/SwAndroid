package com.example.daniela.sweetstop;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.daniela.sweetstop.service.EnviarReserva;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gonzalopro on 10/30/16.
 */

public class ReservaActivity extends AppCompatActivity {

    ImageView buttonFI, buttonHI, buttonHF;
    Button buttonEnviar;
    TextView textViewFI, textViewHI, textViewHF, textViewidMesa;

    String idMesa,fechaInicial,horaInicial,horaFinal;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        buttonFI = (ImageView) findViewById(R.id.btn_reserva_fecha_inicio);
        buttonHI = (ImageView) findViewById(R.id.btn_reserva_hora_inicio);
        buttonHF = (ImageView) findViewById(R.id.btn_reserva_hora_final);
        buttonEnviar = (Button) findViewById(R.id.btn_reserva_guardar);

        textViewFI = (TextView) findViewById(R.id.tv_reserva_fecha_inicio);
        textViewHI = (TextView) findViewById(R.id.tv_reserva_hora_inicio);
        textViewHF = (TextView) findViewById(R.id.tv_reserva_hora_final);
        textViewidMesa = (TextView) findViewById(R.id.tv_reserva_id_mesa);

        idMesa = getIntent().getStringExtra("idMesa");
        idUsuario = ((SwAndroid) getApplicationContext()).getId_cliente();
        textViewidMesa.setText(idMesa);

        fechaInicial = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());;
        horaInicial = "";
        horaFinal = "";

        //String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        String current = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(new Date());
        textViewFI.setText(current);
        //fechaInicial = currentDate;

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Show a timepicker when the timeButton is clicked
        buttonHI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_timer_picker, null, false);

                final TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);

                new AlertDialog.Builder(ReservaActivity.this).setView(view)
                        .setTitle("Hora Inicial")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                String hourString = timePicker.getCurrentHour() < 10 ? "0"+timePicker.getCurrentHour() : ""+timePicker.getCurrentHour();
                                String minuteString = timePicker.getCurrentMinute() < 10 ? "0"+timePicker.getCurrentMinute() : ""+timePicker.getCurrentMinute();
                                String time = hourString + ":" + minuteString + ":" + "00";
                                horaInicial = hourString + ":" + minuteString + ":" + "00";
                                textViewHI.setText(time);

                                dialog.cancel();

                            }

                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        buttonHF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_timer_picker, null, false);

                final TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);

                new AlertDialog.Builder(ReservaActivity.this).setView(view)
                        .setTitle("Hora Final")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                String hourString = timePicker.getCurrentHour() < 10 ? "0"+timePicker.getCurrentHour() : ""+timePicker.getCurrentHour();
                                String minuteString = timePicker.getCurrentMinute() < 10 ? "0"+timePicker.getCurrentMinute() : ""+timePicker.getCurrentMinute();
                                String time = hourString + ":" + minuteString + ":" + "00";
                                horaFinal = hourString + ":" + minuteString + ":" + "00";
                                textViewHF.setText(time);

                                dialog.cancel();

                            }

                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });


        buttonFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_date_picker, null, false);

                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);

                datePicker.setCalendarViewShown(false);


                new AlertDialog.Builder(ReservaActivity.this).setView(view)
                        .setTitle("Fecha Inicial")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                int month = datePicker.getMonth() + 1;
                                int day = datePicker.getDayOfMonth();
                                int year = datePicker.getYear();

                                String date = day + "-" + month + "-" + year;
                                fechaInicial = year + "-" + month + "-" + day;
                                textViewFI.setText(date);
                                dialog.cancel();

                            }

                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fechaInicial.equals("") && !horaInicial.equals("") && !horaFinal.equals("")) {

                    String inicio = fechaInicial + " " + horaInicial;
                    String fin = fechaInicial + " " + horaFinal;

                    new EnviarReserva(ReservaActivity.this,idUsuario,idMesa,inicio,fin).execute();
                } else {
                    Toast.makeText(ReservaActivity.this, "Existen Campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
