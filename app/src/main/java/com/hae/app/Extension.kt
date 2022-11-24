package com.hae.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

fun Activity.isConnectedToIntenet(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnected == true
}

fun Activity.showAlertForInternet(message: String) {
    val dialog = AlertDialog.Builder(this, R.style.MyDialogTheme).create()
    dialog.setMessage(message)
    dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.label_connect)) { _, _ ->
        startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0)
    }
    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.label_cancel)) { _, _ ->
        dialog.dismiss()
        finish()
    }
    dialog.show()
}