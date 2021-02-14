package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;

public class CalendarioActivity extends AppCompatActivity {

    private static final String TAG = "CalendarioActivity";
    private CalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario_layout);
         calendarView = (CalendarView) findViewById(R.id.calendarView2);

         calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 String date = dayOfMonth + "/" + month + "/" + year;
                 Log.d(TAG, "Data: " + date);

                 Intent intent = new Intent(CalendarioActivity.this, CalendarioData.class);
                 intent.putExtra("date", date);
                 startActivity(intent);
             }
         });
    }
}
