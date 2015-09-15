package com.example.guest.todayweather;

import android.os.AsyncTask;
import android.util.Log;

import com.example.guest.todayweather.DisplayActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class WeatherHttpClient extends AsyncTask {

            private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";


            private String country = "Cairo";
            private double temp = 12;
            private double pressure = 0;
            private double humid = 0;
            private Date date = null;
            private double windSpeed = 0;
            private String weather = null;
            private String weatherMain = null;
            private Boolean ready = false;

            /*
            public String getWeatherData(String location) {
                HttpURLConnection con = null;
                InputStream is = null;

                try {
                    con = (HttpURLConnection) (new URL(BASE_URL + location))
                            .openConnection();
                    con.setRequestMethod("GET");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();

                    // Let's read the response
                    StringBuffer buffer = new StringBuffer();
                    is = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = br.readLine()) != null)
                        buffer.append(line + "\r\n");

                    is.close();
                    con.disconnect();
                    return buffer.toString();
                } catch (Throwable t) {
                    t.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (Throwable t) {
                    }
                    try {
                        con.disconnect();
                    } catch (Throwable t) {
                    }
                }

                return null;

            }
           */
           


            public void parse(JSONObject object) {
                try {
                   //object = (JSONObject) object.getJSONArray("list").get(0);
                   //date = new Date(object.getLong("dt"));
                   // country = object.getString("country");
                    windSpeed = ((JSONObject) object.get("wind")).getDouble("speed");
                    weather = ((JSONObject) object.getJSONArray("weather").get(0))
                            .getString("description");
                    weatherMain = ((JSONObject) object.getJSONArray("weather").get(0))
                            .getString("main");
                    object = (JSONObject) object.get("main");
                    temp = object.getDouble("temp");
                    pressure = object.getDouble("pressure");
                    humid = object.getDouble("humidity");
                } catch (JSONException e) {
                    System.out.println(e);
                }
        }

        public String getCountry() {
            return country;
        }

        public double getTemp() {
            return temp;
        }

        public double getPressure() {
            return pressure;
        }

        public double getHumid() {
            return humid;
        }

        public Date getDate() {
            return date;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public boolean isReady() {
            return ready;
        }

        public String getWeather() {
            return weather;
        }

        public String getMainWeather() {
            return weatherMain;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            HttpURLConnection con = null;
            InputStream is = null;
            String location = DisplayActivity.location;
            country = location;
            try {
                con = (HttpURLConnection) (new URL(BASE_URL + location))
                        .openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();

                // Let's read the response
                StringBuffer buffer = new StringBuffer();
                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = br.readLine()) != null) {
                    buffer.append(line + "\r\n");
                    Log.i("aaaaaaaaaa",line);
                }
                is.close();
                con.disconnect();
                ready = true;
                return buffer.toString();
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (Throwable t) {
                }
                try {
                    con.disconnect();
                } catch (Throwable t) {
                }
            }

            return null;
        }
    }



