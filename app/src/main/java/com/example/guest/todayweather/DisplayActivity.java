package com.example.guest.todayweather;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;


public class DisplayActivity extends AppCompatActivity {
    public static String location;
    WeatherHttpClient w = new WeatherHttpClient();
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        location = getIntent().getExtras().getString("EXTRA_MESSAGE");


        try {
            getWeather();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather() throws JSONException {
        s = (String) w.doInBackground(null);
      LOOP:  while (true) {
            if (w.isReady() == true) {
                getResponse();
                break LOOP;
            }
        }


    }
    public void getResponse() throws JSONException {
        JSONObject json = new JSONObject(s);
        Log.i("nlsjnl","xkhbkbxbxkzxb");
        w.parse(json);
        ((TextView)findViewById(R.id.countryView)).setText("london");
        ((TextView)findViewById(R.id.tempView)).setText(String.format(
                "%.2f\u2103", w.getTemp() - 265));
        ((TextView)findViewById(R.id.humidityView)).setText(String.format("Humidity: %.2f%%", w.getHumid()));
        ((TextView) findViewById(R.id.pressure)).setText(String.format(
                "Pressure: %.2f", w.getPressure()));
        ((TextView) findViewById(R.id.windView)).setText(String.format(
                "Wind: %.2f Mps", w.getWindSpeed()));
        ((TextView) findViewById(R.id.descView)).setText(w
                .getWeather());


    }

}