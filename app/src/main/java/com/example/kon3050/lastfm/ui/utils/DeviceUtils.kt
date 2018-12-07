package com.example.kon3050.lastfm.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.kon3050.lastfm.data.db.DATABASE_NAME
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


class DeviceUtils @Inject
constructor(private val mContext: Context) {

    /**
     * Detects the availability of a working network connection to open a
     * http/https connection.
     *
     * @return true if network is available, false otherwise.
     */
    val isNetworkAvailable: Boolean
        get() {
            val conMgr = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = conMgr.activeNetworkInfo
            return networkInfo != null
        }

    /**
     * Returns a boolean value indicating whether the GPS
     * is enabled or not.
     *
     * @return True if GPS is enabled, false otherwise.
     */
    val isGpsEnabled: Boolean
        get() {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            }

            val locationMode: Int
            try {
                locationMode = Settings.Secure.getInt(
                        mContext.contentResolver,
                        Settings.Secure.LOCATION_MODE)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
                return false
            }

            return when (locationMode) {
                Settings.Secure.LOCATION_MODE_HIGH_ACCURACY, Settings.Secure.LOCATION_MODE_SENSORS_ONLY -> true
                Settings.Secure.LOCATION_MODE_BATTERY_SAVING, Settings.Secure.LOCATION_MODE_OFF -> false
                else -> false
            }
        }


    /**
     * Exports Data Base
     */
    @Throws(IOException::class)
    fun exportDataBases() {
        writeToSD(mContext, DATABASE_NAME)
    }

    /**
     * Exports the data base file to /sdcard folder with the name mac_technique_test.db
     * This method only works on Debug Mode
     *
     * @param context Application Context
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun writeToSD(context: Context, databaseName: String) {
        val DB_PATH: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DB_PATH = context.filesDir.absolutePath.replace("files", "databases") + File.separator
        } else {
            DB_PATH = context.filesDir.path + context.packageName + "/databases/"
        }
        val sd = Environment.getExternalStorageDirectory()

        if (sd.canWrite()) {
            val backupDBPath = "demo_$databaseName"
            val currentDB = File(DB_PATH, databaseName)
            val backupDB = File(sd, backupDBPath)

            if (currentDB.exists()) {
                val src = FileInputStream(currentDB).channel
                val dst = FileOutputStream(backupDB).channel
                dst.transferFrom(src, 0, src.size())
                src.close()
                dst.close()
                Log.d("JLRF", "exported-path:" + backupDB.path)
            }
        }
    }

    companion object {
        private val LOG_TAG = DeviceUtils::class.java.name
    }

}
