package com.example.icecreamdisplay.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// These args must default to null.
// Not having a public, no-arg constructor for a fragment causes a crash.
internal class CustomAlertDialogFragment(
    private var message: String,
    private var positiveButtonText: String = DEFAULT_POSITIVE_BUTTON_TEXT,
    private var negativeButtonText: String? = null,
    private var title: String? = null,
    private var icon: Int = 0,
    private var onOkPressed: (() -> Unit)? = null
) : DialogFragment() {

    constructor() : this("")

    companion object {
        const val MESSAGE = "MESSAGE"
        const val POSITIVE_TEXT = "POSITIVE_TEXT"
        const val NEGATIVE_TEXT = "NEGATIVE_TEXT"
        const val TITLE = "TITLE"
        const val ICON = "ICON"

        const val DEFAULT_POSITIVE_BUTTON_TEXT = "Ok"
    }

    private var dialog: MaterialAlertDialogBuilder? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        retainInstance = true
        isCancelable = false

        if (savedInstanceState != null) {
            message = savedInstanceState.getString(MESSAGE) ?: ""
            positiveButtonText = savedInstanceState.getString(POSITIVE_TEXT)
                ?: DEFAULT_POSITIVE_BUTTON_TEXT
            negativeButtonText = savedInstanceState.getString(NEGATIVE_TEXT) ?: ""
            title = savedInstanceState.getString(TITLE)
            icon = savedInstanceState.getInt(ICON)
        }

        return createAlertDialog(message, positiveButtonText, negativeButtonText, title, icon)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MESSAGE, message)
        outState.putString(POSITIVE_TEXT, positiveButtonText)
        outState.putString(NEGATIVE_TEXT, negativeButtonText)
        outState.putString(TITLE, title)
        outState.putInt(ICON, icon)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        dialog?.let {
            if (retainInstance) {
                it.setOnDismissListener { }
            }
        }
        super.onDestroyView()
    }

    private fun createAlertDialog(
        message: String,
        positiveButtonText: String,
        negativeButtonText: String?,
        title: String?,
        icon: Int
    ): AlertDialog {
        val dialog = MaterialAlertDialogBuilder(context!!)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ -> onPositiveClick(dialog) }

        negativeButtonText?.let {
            dialog.setNegativeButton(it) { dialog, _ -> onNegativeClick(dialog) }
        }

        title?.let {
            dialog.setTitle(it)
        }

        dialog.setIcon(icon)

        return dialog.create()
    }

    open fun onPositiveClick(dialog: DialogInterface) {
        onOkPressed?.invoke()
        dialog.dismiss()
    }

    open fun onNegativeClick(dialog: DialogInterface) {
        dialog.dismiss()
    }

    fun setDialogMessage(message: String) {
        this.message = message
    }
}