package com.example.barnes.ummoqmasterdashboard;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMaster;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMasterListener;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.HitBuilders;
///i//mport com.google.android.gms.analytics.Tracker;

/**
 * Created by barnes on 8/6/15.
 */
public class SplashScreen extends Activity implements QMasterListener
{
    //Overloads for QmasterListener


    @Override
    public void qCreated(String string) {

    }

    @Override
    public void registrationError(String string) {

    }

    @Override
    public void createQError(String string) {

    }

    @Override
    public void registered(String string) {

    }

    @Override
    public void qDestroyed(String string) {

    }

    @Override
    public void userMoved(String string) {

    }

    @Override
    public void userDQd(String string) {

    }

    @Override
    public void feedBackRecieved(String string) {

    }

    @Override
    public void myQRecieved(String string) {

    }

    @Override
    public void updatesRecieved(String string) {

    }


    @Override
    public void onUpdtaesError(String string) {

    }

    @Override
    public void onFeedBackError(String string) {

    }

    @Override
    public void onUserMoveError(String string) {

    }

    @Override
    public void onUserDQError(String string) {

    }

    @Override
    public void onQDestroyError(String sting) {

    }

    //End Overloads
    long Delay = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ummo_splash);

        if (isInternetOn() == true)
        {
            new Handler().postDelayed(new Thread() {
                @Override
                public void run() {
                    QMaster qMaster=new QMaster(SplashScreen.this);
                    if(qMaster.isRegistered()){

                        Intent login_register = new Intent(SplashScreen.this, AnimateToolbar.class);
                        SplashScreen.this.startActivity(login_register);
                        finish();
                        overridePendingTransition(R.layout.fadein, R.layout.fadeout);
                    }

                    else {
                        Intent login_register = new Intent(SplashScreen.this, MainActivity_Register_Login.class);
                        SplashScreen.this.startActivity(login_register);
                        finish();
                        overridePendingTransition(R.layout.fadein, R.layout.fadeout);
                    }
                }


            }, Delay);
        }
    }

    public final boolean isInternetOn()
    {
        //get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if (    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED  ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED )
        {
            //if connected with internet
            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }
        else if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  )
        {
            Toast.makeText(this, "Please turn on your data or connect to a wifi hotspot", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}