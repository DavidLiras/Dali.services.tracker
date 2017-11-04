package dali.service;

/**
 * Created by MDDALIMA on 03/11/2017.
 */

import android.os.Build;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;


public class Utils {

    public static final String TAG = "Dali.Service";

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        return  manufacturer + model;
    }

    public static  void Send2Dweet(String data)
    {

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://dweet.io/dweet/for/dali?" + data);
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            int response=urlConnection.getResponseCode();
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.i(Utils.TAG, "Response from dweet: " + Integer.toString(response));
        } catch ( Exception e) {
            Log.e(Utils.TAG, e.getMessage());
            //messageToast("ERROR: " + e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

    }

}
