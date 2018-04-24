package com.example.kon3050.lastfm.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat

import javax.inject.Inject


class PermissionsManager @Inject
constructor(private val mContext: Context) {

    /**
     * Method to check if the app has permission to access location
     *
     * @return true if the location permission has been granted, false otherwise.
     */
    val isLocationPermissionGranted: Boolean
        get() {
            var isPermissionGranted = false

            if (isPermissionGranted(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) && isPermissionGranted(mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
                isPermissionGranted = true
            }

            return isPermissionGranted
        }

    /**
     * Method to check if the app has permission to access External Storage
     *
     * @return True if Storage permission is granted, false otherwise.
     */
    val isStoragePermissionGranted: Boolean
        get() {
            var isPermissionGranted = false

            if (isPermissionGranted(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) && isPermissionGranted(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                isPermissionGranted = true
            }

            return isPermissionGranted
        }

    fun shouldExplainStoragePermission(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun shouldExplainLocationPermission(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun showLocationPermissionsDialog(fragment: Fragment, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }


        if (ContextCompat.checkSelfPermission(fragment.context!!.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(fragment.context!!.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            fragment.requestPermissions(
                    arrayOf<String>(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                    requestCode)
        }
    }

    fun showLocationPermissionsDialog(activity: Activity, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }


        if (ContextCompat.checkSelfPermission(activity.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            activity.requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                    requestCode)
        }
    }

    fun showMediaPermissionsDialog(fragment: Fragment, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }


        if (ContextCompat.checkSelfPermission(fragment.context!!, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(fragment.context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            fragment.requestPermissions(
                    arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    requestCode)
        }
    }

    fun showMediaPermissionsDialog(context: Activity, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            context.requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    requestCode)
        }
    }

    companion object {
        val LOCATION_PERMISSION_REQUEST_CODE = 154
        val MEDIA_PERMISSION_REQUEST_CODE = 153

        /**
         * Utility method to check if a particular permission has been granted
         *
         * @param context    The application context
         * @param permission A string key identifying the android permission.
         * @return True if the permission has been granted, false otherwise.
         */
        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}
