package com.example.cycle;


import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    public static int m1(Date d1,Date d2){
        if(d1==d2) {
            return 0;
        }

        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    // Define the variable of CalendarView type
    // and TextView type;
    CalendarView calender;
    TextView date_view;
    TextView days_between;
    TextView days_between2;
    EditText prev_date;
    EditText next_date;
    ProgressBar pro;
    int gap =28;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // By ID we can use each component
        // which id is assign in xml file
        // use findViewById() to get the
        // CalendarView and TextView
        calender = (CalendarView)
                findViewById(R.id.calender);
        days_between = (TextView)
                findViewById(R.id.days_b);
        days_between2 = (TextView)
                findViewById(R.id.days_b2);
        prev_date = (EditText)
                findViewById(R.id.previous);
        next_date = (EditText)
                findViewById(R.id.next);
        pro = (ProgressBar)findViewById(R.id.prog);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        prev_date.setText(date);
        pro.setProgress(50);

    }

    public void update(View view)
    {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String previous = prev_date.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        try {
            current = sdf3.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date d1 = null;
        try {
            d1 = sdf.parse(previous);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days;
        Date d2 = new Date();

            String next = next_date.getText().toString();
            try {
                d2 = sdf2.parse(next);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            days = m1(d1,d2);
        String str1 = Integer.toString(days);
        String str2= sdf1.format(d2);
        next_date.setText(str2);
        int day1 = m1(current,d2);
        String str3 = Integer.toString(day1);
        days_between2.setText(str1);
        days_between.setText(str3);

        pro.setMax(days);
        pro.setProgress(days-day1);
    }
}

