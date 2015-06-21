package stackoverflow.net.icare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import stackoverflow.net.icare.database.ProfileDataSource;
import stackoverflow.net.icare.utill.Profile;

public class SplashScreen extends Activity {
    ProfileDataSource mDataSource = null;
    Profile mProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mDataSource = new ProfileDataSource(this);
        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(2 * 1000);

                    if (mDataSource.isEmpty()) {
                        Intent i = new Intent(SplashScreen.this,
                                ProfileCreate.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashScreen.this,
                                Home.class);
                        startActivity(i);
                    }
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}
