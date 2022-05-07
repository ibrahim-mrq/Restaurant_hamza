package com.restaurant.helpers

import android.app.Activity
import android.util.Patterns
import android.view.Gravity
import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.restaurant.R
import com.tapadoo.alerter.Alerter

open class BaseActivity : AppCompatActivity() {


    open fun showErrorAlert(
        activity: Activity?,
        title: String?,
        text: String?,
        @DrawableRes icon: Int
    ) {
        Alerter.create(activity!!)
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
        activity: Activity?,
        title: String?,
        text: String?
    ) {
        Alerter.create(activity!!)
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
        activity: Activity?,
        title: String?,
        text: String?
    ) {
        Alerter.create(activity!!)
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
        activity: Activity?,
        title: String?,
        text: String?,
        @DrawableRes icon: Int
    ) {
        Alerter.create(activity!!)
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.colorAccent)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .setIcon(icon)
            .show()
    }

    open fun showAlert(
        activity: Activity?,
        title: String?,
        text: String?
    ) {
        Alerter.create(activity!!)
            .setTitle(title!!)
            .setText(text!!)
            .setBackgroundColorRes(R.color.colorAccent)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .hideIcon()
            .show()
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

    fun isPasswordLess(pass: TextInputEditText, text: TextInputLayout): Boolean {
        return if (pass.text.toString().trim().length >= 6) {
            text.isErrorEnabled = false
            true
        } else {
            text.error = getString(R.string.password_less_6_char)
            text.isErrorEnabled = true
            false
        }
    }

    fun isPasswordMatch(
        pass: TextInputEditText,
        text: TextInputLayout,
        re_pass: TextInputEditText,
        re_text: TextInputLayout
    ): Boolean {
        return if (pass.text.toString().trim() == re_pass.text.toString().trim()) {
            text.isErrorEnabled = false
            re_text.isErrorEnabled = false
            true
        } else {
            text.error = getString(R.string.password_not_match)
            text.isErrorEnabled = true
            re_text.error = getString(R.string.password_not_match)
            re_text.isErrorEnabled = true
            false
        }
    }

    fun getText(editText: TextInputEditText): String {
        return editText.text.toString().trim()
    }
    fun getText(editText: AutoCompleteTextView): String {
        return editText.text.toString().trim()
    }


}