package com.example.myapplication2.core.platform.activity

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication2.R

/**
 * @purpose checks() permissionsNeeded() to be granted from allPermissionsRequested().
 * if permissionsNeeded() than request(),
 * other wise proceed()
 *
 * if user denies one or more permissions than
 * shows warningDialog() that all permissions are not granted,
 * openSettings() to grant all permission through warningDialog().
 * closing warningDialog() checks() permissionsNeeded() again.
 * */
abstract class PermissionActivity : AppCompatActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }

    abstract fun proceed()
    abstract fun allPermissionsRequested(): Array<String>

    override fun onStart() {
        super.onStart()
        checks()
    }

    override fun onRestart() {
        super.onRestart()
        checks()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val permissionResult = HashMap<String, Int>()
            var deniedCount = 0
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionResult[permissions[i]] = grantResults[i]
                    println("Denied Permission ${permissions[i]}")
//                    if(!permissions[i]!!.equals("android.permission.ACCESS_BACKGROUND_LOCATION"))
                    deniedCount++
                }
            }
            if (deniedCount == 0) proceed()
            else warningDialog()
        }
    }

    private fun checks() {
        val permissionNeeded = permissionsNeeded()
        if (permissionNeeded.isNotEmpty())
            request(permissionNeeded)
        else
            proceed()
    }

    private fun request(permissions: List<String>) {
        if (dialogIsNotOpened())
            ActivityCompat.requestPermissions(
                this,
                arrayOfNulls<String>(permissions.size).let { ArrayList(permissions).toArray(it) },
                PERMISSION_REQUEST_CODE
            )
    }

    @SuppressLint("NewApi")
    private fun dialogIsNotOpened(): Boolean {
        (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).run {
            val cn = if (Build.VERSION.SDK_INT >= 23) appTasks[0].taskInfo.topActivity
            else getRunningTasks(1).get(0).topActivity
            return "com.android.packageinstaller.permission.ui.GrantPermissionsActivity" != cn!!.className
        }
    }

    private fun permissionsNeeded(): List<String> =
        allPermissionsRequested().filter { permission ->
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        }

    private fun warningDialog() {
        //TODO
    }

    private fun openSettings() =
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            this.data = Uri.fromParts("package", packageName, null)
        })
}
