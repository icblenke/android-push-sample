package com.example.push;

import com.urbanairship.push.AirMail;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    protected static boolean register = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(register){
            AirMail am = AirMail.getInstance();
            am.register(this);
        }

    }

}