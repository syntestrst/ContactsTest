package timefeel.com.contactstest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import timefeel.com.contactstest.R;

/**
 * Created by test on 29/08/2015.
 */
public class LoginScreen extends Activity {

    private static int SPLASH_TIME_OUT = 1000;
    private static boolean currentAccessToken = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // The activity is being created.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BypassAuth();

    }


    private void BypassAuth( ) {
        if (currentAccessToken) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginScreen.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}


