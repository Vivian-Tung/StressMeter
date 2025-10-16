package dev.viviantung.stressmeter


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object Permissions {

    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    fun hasAllRequiredPermissions(context: Context): Boolean {
        return getRequiredPermissions().all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }
    }

    // for files and images
    fun requestRequiredPermissions(activity: FragmentActivity, launcher: ActivityResultLauncher<Array<String>>) {
        launcher.launch(getRequiredPermissions())
    }

    // for notifs
    fun requestOptionalNotificationPermission(activity: FragmentActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val launcher = activity.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                Log.d("PERMISSIONS", "Notification permission granted? $isGranted")
            }
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}