package com.example.push;

import com.urbanairship.push.APIDReceiver;
import com.urbanairship.push.AirMail;
import com.urbanairship.push.PushReceiver;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class SampleApplication extends Application {
    public void onCreate(){
        AirMail am = AirMail.getInstance();
        am.acceptPush(this, new PushReceiver() {
            @Override
            public void onReceive(String message, String payload){
                Log.d("push", "Got message '" + message +"' and payload '" + payload + "'");
            }

            @Override
            public void onClick(String message, String payload){
                Log.d("push", "User clicked the notification, got message and payload: "
                        + message + ", " + payload);
                /* In this example, we fire up our MainActivity class when the
                 * user clicks the Status Bar Notification. Note that we *must*
                 * use the flag Intent.FLAG_ACTIVITY_NEW_TASK to start a new
                 * activity because this callback is fired from within a
                 * BroadcastReceiver.
                 **/
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setClass(SampleApplication.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SampleApplication.this.startActivity(intent);
            }
        });

        am.setAPIDReceiver(this, new APIDReceiver() {
            @Override
            public void onReceive(String apid, boolean valid){
                if(valid){
                    Log.d("push", "Got apid: " + apid);
                } else {
                    Log.d("push", "Application registration invalid!");
                }
            }

            @Override
            public void onAirMailInstallRefusal() {
                MainActivity.register = false;
                Log.d("push", "AirMail Install Refused!");
            }
        });
    }
}
