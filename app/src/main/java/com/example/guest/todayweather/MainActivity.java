package com.example.guest.todayweather;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void next(View view) {

        EditText userName = (EditText) findViewById(R.id.user_name);
        String userNameValue = userName.getText().toString();
        EditText password = (EditText) findViewById(R.id.editText);
        String passwordValue = password.getText().toString();
        EditText mobile = (EditText) findViewById(R.id.mobile_number);
        String mobileValue = mobile.getText().toString();
        EditText email = (EditText) findViewById(R.id.email);
        String emailValue = email.getText().toString();
        if (userNameValue.matches("")|| passwordValue.matches("") || emailValue.matches("") || mobileValue.matches("")) {
            Context context = getApplicationContext();
            CharSequence text = " Please fill in all fields required ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            if(!isValidEmailAddress(emailValue)) {
                Toast emailtoast = Toast.makeText(context, " Wrong Email format, should be as 'someone@something.com' ", Toast.LENGTH_LONG);
                emailtoast.show();
            }
        } else {

            Intent intent = new Intent(this, EnterCityActivity.class);
            startActivity(intent);
        }
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
