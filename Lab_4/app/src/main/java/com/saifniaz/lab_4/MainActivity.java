package com.saifniaz.lab_4;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    IntentFilter filter;
    String notifyStatus = "", notifyHealth = "", notifyPlug = "", notifyTemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

    private void getBatteryStatus(Intent intent){

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int healthy = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
        int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);

        System.out.println(status + ", " +  healthy + ", " + plugged + ", " + temp);

        if(status == 2){
            notifyStatus = "Battery Charging";
        }else if (status == 3){
            notifyStatus = "Battery Discharging";
        }else{
            notifyStatus = "Battery Full";
        }

        switch (healthy){
            case 1:
                notifyHealth = "Health Unknown";
                break;
            case 2:
                notifyHealth = "Health Good";
                break;
            case 3:
                notifyHealth = "Health Overheat";
                break;
            case 4:
                notifyHealth = "Health Dead";
                break;
            case 5:
                notifyHealth = "Health Over Voltage";
                break;
            case 6:
                notifyHealth = "Health Unspecified Failure";
                break;
            case 7:
                notifyHealth = "Health Cold";
                break;
            default:
                notifyHealth = "Error Occured";
        }

        switch (plugged){
            case 1:
                notifyPlug = "Plugged to AC";
                break;
            case 2:
                notifyPlug = "Plugged to USB";
                break;
            default:
                notifyPlug = "Error Occured";
        }

        notifyTemp = "" + temp;

    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getBatteryStatus(intent);

            int icon = R.mipmap.ic_launcher;
            long now = System.currentTimeMillis();
            String message = notifyStatus + ", " + notifyHealth + ", " + notifyPlug + ", "+ notifyTemp;
           // Notification note = new NotificationCompat.Builder(icon, message, now);

            NotificationCompat.Builder myBuild =
                    new NotificationCompat.Builder(context)
                    .setSmallIcon(icon)
                    .setContentTitle("Lab 4")
                    .setContentText(message);

            NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            nm.notify(100, myBuild.build());
        }
    };
}
