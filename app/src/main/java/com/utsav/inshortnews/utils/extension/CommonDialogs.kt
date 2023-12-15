package com.utsav.inshortnews.utils.extension

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.utsav.inshortnews.R
import com.utsav.inshortnews.utils.LoadingDialog


fun Fragment.showInternetDialog(
    isCancelAble: Boolean = false,
    positiveClick: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this.context)
    builder.setTitle(this.getString(R.string.app_name))
    builder.setMessage(this.getString(R.string.network_connection_error))
    builder.setPositiveButton("ok") { _, _ ->

        positiveClick?.invoke()
    }


    // builder.setPositiveButton(this.getString(R.string.ok),  positiveClick?.invoke())
    builder.setCancelable(isCancelAble)
    val alertDialog = builder.create()
    alertDialog.show()
}


fun Fragment.showMessageDialog(
    title: String = "",
    message: String = "",
    positiveClick: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this.context)
    builder.setTitle(this.getString(R.string.app_name))
    builder.setMessage(message)
    builder.setPositiveButton("ok") { _, _ ->

        positiveClick?.invoke()
    }


    // builder.setPositiveButton(this.getString(R.string.ok),  positiveClick?.invoke())
    builder.setCancelable(true)
    val alertDialog = builder.create()
    alertDialog.show()

}


fun Fragment.showLoader() {
    LoadingDialog.showLoadDialog(this.requireActivity())
}

fun Fragment.hideLoader() {
    LoadingDialog.hideLoadDialog()
}




