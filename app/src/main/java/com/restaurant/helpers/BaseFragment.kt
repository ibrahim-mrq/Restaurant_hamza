package com.restaurant.helpers

import android.util.Patterns
import android.view.Gravity
import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.restaurant.R
import com.tapadoo.alerter.Alerter

open class BaseFragment : Fragment() {

    open fun showErrorAlert(
        title: String?,
        text: String?,
        @DrawableRes icon: Int
    ) {
        Alerter.create(requireActivity())
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.red)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .setIcon(icon)
            .show()
    }

    open fun showOfflineAlert(
        title: String?,
        text: String?
    ) {
        Alerter.create(requireActivity())
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.orange)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .hideIcon()
            .show()
    }

    open fun showErrorAlert(
        title: String?,
        text: String?
    ) {
        Alerter.create(requireActivity())
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.red)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .hideIcon()
            .show()
    }

    open fun showAlert(
        title: String?,
        text: String?,
        @DrawableRes icon: Int
    ) {
        Alerter.create(requireActivity())
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.colorPrimary)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .setIcon(icon)
            .show()
    }

    open fun showAlert(
        title: String?,
        text: String?
    ) {
        Alerter.create(requireActivity())
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.colorPrimary)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .hideIcon()
            .show()
    }

    fun getText(editText: TextInputEditText): String {
        return editText.text.toString().trim()
    }

    fun isNotEmpty(editText: TextInputEditText, textInputLayout: TextInputLayout): Boolean {
        return if (editText.text.toString().isBlank()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.empty_field)
            false
        } else {
            textInputLayout.isErrorEnabled = false
            true
        }
    }

    fun isNotEmpty(editText: AutoCompleteTextView, textInputLayout: TextInputLayout): Boolean {
        return if (editText.text.toString().isBlank()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.empty_field)
            false
        } else {
            textInputLayout.isErrorEnabled = false
            true
        }
    }

    fun isValidEmail(editText: TextInputEditText, textInputLayout: TextInputLayout): Boolean {
        return if (Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches()) {
            textInputLayout.isErrorEnabled = false
            true
        } else {
            textInputLayout.error = getString(R.string.invalid_email)
            textInputLayout.isErrorEnabled = true
            false
        }
    }
}