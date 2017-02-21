package timefeel.com.contactstest.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.IOException;

import timefeel.com.contactstest.R;
import timefeel.com.contactstest.fragments.FirstFragment;
import timefeel.com.contactstest.fragments.FourthFragment;
import timefeel.com.contactstest.fragments.SecondFragment;
import timefeel.com.contactstest.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private NavigationView mnvDrawer;
    private ActionBarDrawerToggle mdrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Tie DrawerLayout events to the ActionBarToggle
        mdrawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(mdrawerToggle);

        // Set the menu icon instead of the launcher icon.
        final ActionBar ab = getSupportActionBar();
        // To enable the app icon as an Up button, call setDisplayHomeAsUpEnabled()
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        ab.setDisplayHomeAsUpEnabled(true);

        // Setup drawer view
        mnvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(mnvDrawer);

        // Create a new Fragment to be placed in the activity layout
        FirstFragment runfragment = new FirstFragment();

        /*// In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        runfragment.setArguments(getIntent().getExtras());*/

        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager()
                .beginTransaction()
                .add(R.id.flContent, runfragment)
                .commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_all_contacts:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_me_info:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_search:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.nav_credits:
                fragmentClass = FourthFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
        fragmenttransaction
                .replace(R.id.flContent, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        if (mdrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mdrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mdrawerToggle.onConfigurationChanged(newConfig);
    }

    @NonNull
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}