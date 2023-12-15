package com.utsav.inshortnews.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.utsav.inshortnews.R


object LoadingDialog {
    var dialog: Dialog? = null

    fun showLoadDialog(context: Context) {

        try {
            if (dialog != null) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)

            dialog?.apply {
                window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                setCancelable(false)
                setContentView(R.layout.dialog_loading)
                show()
            }
        } catch (e: Exception) {

        }
    }

    fun hideLoadDialog() {
        try {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

}