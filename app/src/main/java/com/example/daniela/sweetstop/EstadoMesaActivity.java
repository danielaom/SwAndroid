package com.example.daniela.sweetstop;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.daniela.sweetstop.adapter.CatalogoAdapter;
import com.example.daniela.sweetstop.model.Catalogo;
import com.example.daniela.sweetstop.service.ObtenerEstadosMesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by gonzalopro on 10/30/16.
 */

public class EstadoMesaActivity extends AppCompatActivity implements MonthLoader.MonthChangeListener {

    private String idMesa;
    private WeekView mWeekView;
    public StringBuilder result;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_mesa);

        mWeekView = (WeekView) findViewById(R.id.weekView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_nueva_reserva);

        assert mWeekView != null;
        mWeekView.setMonthChangeListener(this);
        setupDateTimeInterpreter(true);

        idMesa = getIntent().getStringExtra("idMesa");


    }

    @Override
    protected void onStart() {
        super.onStart();
        new ObtenerEstadosMesas(idMesa,EstadoMesaActivity.this).execute();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EstadoMesaActivity.this, ReservaActivity.class).putExtra("idMesa", idMesa));
            }
        });

    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour, int minutes) {
                String strMinutes = String.format("%02d", minutes);
                if (hour > 11) {
                    return (hour - 12) + ":" + strMinutes + " PM";
                } else {
                    if (hour == 0) {
                        return "12:" + strMinutes + " AM";
                    } else {
                        return hour + ":" + strMinutes + " AM";
                    }
                }
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        Log.d("State - Mesa", "Data: " + result);

        List<WeekViewEvent> events = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
        try {
            JSONObject group_info = new JSONObject(String.valueOf(result));

            JSONArray jsonArray = group_info.getJSONArray("estado_mesas_info");

            for (int i = 0; i < jsonArray.length() ; i++) {

                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                Log.d("MesaActivity", jsonGroup.getString("fechaInicio") + " " + jsonGroup.getString("fechaFin"));

                String ini = jsonGroup.getString("fechaInicio");
                Date date1 = dateFormat.parse(ini);

                String fin = jsonGroup.getString("fechaFin");
                Date date2 = dateFormat.parse(fin);

                Log.d("MesaActivity", date1.getHours() + " - " + (date1) + " - " + date1.getYear() + " - " + newYear + " - " + newMonth);

                Calendar startTime = Calendar.getInstance();
                startTime.setTime(date1);
                /*startTime.set(Calendar.HOUR_OF_DAY, date1.getHours());
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);*/

                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.setTime(date2);
                //endTime.set(Calendar.MONTH, newMonth-1);

                WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorPrimary));
                events.add(event);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


       /* Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);

        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);

        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorPrimary));
        events.add(event);*/



        return events;
    }

    protected String getEventTitle(Calendar time) {
        //String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
        return "";
    }

}
