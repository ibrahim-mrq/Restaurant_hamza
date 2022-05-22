@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.restaurant.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.ColorRes
import com.orhanobut.hawk.Hawk
import com.restaurant.controller.activities.SplashActivity
import com.tapadoo.alerter.Alerter
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val TYPE_MODEL = "type_model"

    const val IS_LOGIN = "is_login"
    const val USER = "user"
    const val USER_ID = "user_id"

    @JvmStatic
    fun getDate(): String? {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
        return sdf.format(Date())
    }

    @JvmStatic
    fun logout(context: Context) {
        Hawk.delete(USER)
        Hawk.delete(USER_ID)
        Hawk.delete(IS_LOGIN)
        Toast.makeText(context, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context, SplashActivity::class.java))
    }

    @JvmStatic
    fun showAlert(
        activity: Activity,
        text: String?,
        @ColorRes color: Int?,
    ) {
        Alerter.create(activity)
            .setTitle("")
            .setText(text!!)
            .setBackgroundColorRes(color!!)
            .setContentGravity(Gravity.CENTER)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .hideIcon()
            .show()
    }

    const val LOCAL_CART_LIST = "local_cart_list"

}