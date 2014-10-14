package com.example.tejasad.lockthescreen;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {
    DevicePolicyManager deviceManager;
    ActivityManager activityManager;
    ComponentName componentName;
    public static final int RESULT_ENABLE  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my);
        try {
            //  Context mContext =   getApplicationContext();
            deviceManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            componentName = new ComponentName(this, myAdminClass.class);

            //  Intent devManagerIntent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

            Intent devManagerIntent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            devManagerIntent.putExtra(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN, componentName);
            devManagerIntent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Why??");
            startActivityForResult(devManagerIntent, RESULT_ENABLE);


        } catch (Exception ex) {
            Log.v("error",ex.getMessage()+"Testing");

        }




    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_ENABLE) {
            if(resultCode == Activity.RESULT_OK) {
                if (deviceManager.isAdminActive(componentName)) {
                    deviceManager.lockNow();

                }

            }

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

        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    }
}

