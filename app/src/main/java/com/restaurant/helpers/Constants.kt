@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.restaurant.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.ColorRes
import com.orhanobut.hawk.Hawk
import com.restaurant.R
import com.restaurant.controller.activities.MainActivity
import com.restaurant.controller.activities.SplashActivity
import com.restaurant.model.Meals
import com.restaurant.model.Order
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
    private lateinit var carts: ArrayList<Meals>
//
//    @JvmStatic
//    fun addToCartList(context: Activity, product: Meals) {
//        carts = if (Hawk.contains(LOCAL_CART_LIST)) {
//            ArrayList(Hawk.get(LOCAL_CART_LIST) as ArrayList<Meals>)
//        } else {
//            ArrayList()
//        }
//        val index =
//            carts.indexOfFirst { it.id == product.id } // -1 if not found
//        if (index == -1) {
//            carts.add(product)
//            Hawk.put(LOCAL_CART_LIST, carts)
//            showAlert(
//                context,
//                context.getString(R.string.successfully_add_cart),
//                R.color.successColor
//            )
//        } else {
//            val qq = carts[index]
//            val q = carts[index].quantity
//            val q1 = product.quantity
//            val q2 = q1.plus(q)
//            product.quantity = q2
//            carts.remove(qq)
//            Hawk.put(LOCAL_CART_LIST, carts)
//            carts.add(product)
//            Hawk.put(LOCAL_CART_LIST, carts)
//            showAlert(
//                context,
//                context.getString(R.string.updated_product_to_cart),
//                R.color.warningColor
//            )
//        }
//    }

    @JvmStatic
    fun removeFromCartList(context: Activity, product: Meals) {
        carts = ArrayList(Hawk.get(LOCAL_CART_LIST) as ArrayList<Meals>)
        val index = carts.indexOfFirst { it.id == product.id } // -1 if not found
        if (index >= 0) {
            carts.remove(product)
            Hawk.put(LOCAL_CART_LIST, carts)
            showAlert(context, context.getString(R.string.remove_from_cart), R.color.errorColor)
        } else {
            showAlert(context, context.getString(R.string.not_currently_in_cart), R.color.text)
        }
    }

    const val LOCAL_ORDER_LIST = "local_order_list"
    private lateinit var orders: ArrayList<Order>
//
//    @JvmStatic
//    fun addToOrderList(context: Activity, order: Order) {
//        orders = if (Hawk.contains(LOCAL_ORDER_LIST)) {
//            ArrayList(Hawk.get(LOCAL_ORDER_LIST) as ArrayList<Order>)
//        } else {
//            ArrayList()
//        }
//        orders.add(order)
//        Hawk.put(LOCAL_ORDER_LIST, orders)
//        showAlert(
//            context,
//            context.getString(R.string.successfully_add_order),
//            R.color.successColor
//        )
//        for (i in carts.indices) {
//            if (carts[i].userId != Hawk.get(USER_ID, 1)) {
//                carts.remove(carts[i])
//            }
//        }
//        MainActivity.updateCarts()
//    }

}