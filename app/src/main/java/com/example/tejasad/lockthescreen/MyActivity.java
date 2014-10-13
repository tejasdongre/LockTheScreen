package com.example.tejasad.lockthescreen;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {
    DevicePolicyManager deviceManager;
    ActivityManager activityManager;
    ComponentName componentName;
    public static int REQUEST_ENABLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_my);
        try {
            deviceManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            componentName = new ComponentName(this, myAdminClass.class);
            if (!deviceManager.isAdminActive(componentName)) {
                Intent devManagerIntent = new Intent(DevicePolicyManager.EXTRA_DEVICE_ADMIN);
                devManagerIntent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                devManagerIntent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Why??");
                startActivityForResult(devManagerIntent, REQUEST_ENABLE);


            } else {

                deviceManager.lockNow();

            }

        } catch (Exception ex) {


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE) {

            deviceManager.lockNow();


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class myAdminClass extends DeviceAdminReceiver {

        @Override
        public void onEnabled(Context context, Intent intent) {
            super.onEnabled(context, intent);
        }

        @Override
        public void onDisabled(Context context, Intent intent) {
            super.onDisabled(context, intent);
        }
    }
}

