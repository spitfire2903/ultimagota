package br.com.ricardonm.gotadagua;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ricardonm.gotadagua.fragment.BillFragment;
import br.com.ricardonm.gotadagua.fragment.MainFragment;
import br.com.ricardonm.gotadagua.fragment.ReadingHistoryFragment;
import br.com.ricardonm.gotadagua.fragment.ReservatoryFragment;
import br.com.ricardonm.gotadagua.fragment.TipsFragment;
import br.com.ricardonm.gotadagua.fragment.WeatherFragment;
import br.com.ricardonm.gotadagua.model.DeviceUser;
import br.com.ricardonm.gotadagua.task.LoadDeviceUserTask;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public enum FragmentIndex {
        MAIN_FRAGMENT(0),
        BILL_FRAGMENT(1),
        TIPS_FRAGMENT(2),
        RESERVATORY_FRAGMENT(3),
        WEATHER_FRAGMENT(4),
        READING_FRAGMENT(5);

        public int value;

        private FragmentIndex(int value){
            this.value = value;
        }

        public static FragmentIndex getFragmentFromIndex(int value){
            FragmentIndex fragment = null;

            switch (value){
                case 0:
                    fragment = MAIN_FRAGMENT;
                    break;
                case 1:
                    fragment = BILL_FRAGMENT;
                    break;
                case 2:
                    fragment = TIPS_FRAGMENT;
                    break;
                case 3:
                    fragment = RESERVATORY_FRAGMENT;
                    break;
                case 4:
                    fragment = WEATHER_FRAGMENT;
                    break;
                case 5:
                    fragment = READING_FRAGMENT;
                    break;
            }

            return fragment;
        }
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    public NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private FragmentIndex selectedFragment;

    private Location userLocation;

    private DeviceUser deviceUser;

    protected ProgressDialog progressDialog = null;

    public void showThrobber(){
        this.hideThrobber();

        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Carregando...");
        this.progressDialog.setMessage("Aguarde um instante");
        this.progressDialog.show();

    }

    public void hideThrobber(){
        if (this.progressDialog != null){
            if (this.progressDialog.isShowing())
                this.progressDialog.dismiss();

            this.progressDialog = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this.initParse();
        this.initUser();
        this.loadUserLocation();

        this.setupUI();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void initUser(){
        String deviceToken = null;
        LoadDeviceUserTask task = null;

        deviceToken = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);;

        task = new LoadDeviceUserTask(MainActivity.this, deviceToken);
                    task.execute();
    }

    public void setupUI(){
        ActionBar actionBar = null;

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment fragment = null;
        FragmentIndex fragmentIdx = null;

        fragmentIdx = FragmentIndex.getFragmentFromIndex(position);

        mTitle = this.getTitleFromFragmentIndex(fragmentIdx);
        selectedFragment = fragmentIdx;

        switch (fragmentIdx){
            case MAIN_FRAGMENT:
                fragment = new MainFragment();
                break;
            case BILL_FRAGMENT:
                fragment = new BillFragment();
                break;
            case TIPS_FRAGMENT:
                fragment = new TipsFragment();
                break;
            case RESERVATORY_FRAGMENT:
                fragment = new ReservatoryFragment();
                break;
            case WEATHER_FRAGMENT:
                fragment = new WeatherFragment();
                break;
            case READING_FRAGMENT:
                fragment = new ReadingHistoryFragment();
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            // getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    private void loadUserLocation(){
        LocationManager locationManager = null;
        String provider = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //se o provedor de localizacao nao estiver habilitado, teremos uma excecao.
        try{
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex){
            ex.printStackTrace();
            gps_enabled = false;
        }

        try{
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex){
            ex.printStackTrace();
            network_enabled = false;
        }

        // Codigo para nao tentar fazer a leitura sem provedor de localizacao disponivel
        if(gps_enabled){
            provider = LocationManager.GPS_PROVIDER;
        } else if(network_enabled){
            provider = LocationManager.NETWORK_PROVIDER;
        } else{
            provider = LocationManager.PASSIVE_PROVIDER;
        }

        locationManager.requestSingleUpdate(provider, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                MainActivity.this.setUserLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        }, null);
    }

    public FragmentIndex getSelectedFragment() {
        return selectedFragment;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public DeviceUser getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(DeviceUser deviceUser) {
        this.deviceUser = deviceUser;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    private String getTitleFromFragmentIndex(FragmentIndex fragmentIdx){

        String title = null;

        switch (fragmentIdx){
            case MAIN_FRAGMENT:
                title = getString(R.string.title_main);
                break;
            case BILL_FRAGMENT:
                title = getString(R.string.title_bill);
                break;
            case TIPS_FRAGMENT:
                title = getString(R.string.title_tips);
                break;
            case RESERVATORY_FRAGMENT:
                title = getString(R.string.title_reservatory);
                break;
            case WEATHER_FRAGMENT:
                title = getString(R.string.title_weather);
                break;
            case READING_FRAGMENT:
                title = getString(R.string.title_reading);
                break;
        }

        return title;

    }
}
