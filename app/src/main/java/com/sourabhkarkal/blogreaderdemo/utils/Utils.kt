package com.sourabhkarkal.blogreaderdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


class Utils {

    companion object {
        //Used for Likes and Comments
        fun formatValue(value: Double): String? {
            var value = value
            val power: Int
            val suffix = " KMBT"
            var formattedNumber = ""
            val formatter: NumberFormat = DecimalFormat("#,###.#")
            power = StrictMath.log10(value).toInt()
            value /= Math.pow(10.0, power / 3 * 3.toDouble())
            formattedNumber = formatter.format(value)
            formattedNumber += suffix[power / 3]
            return if (formattedNumber.length > 4) formattedNumber.replace(
                "\\.[0-9]+".toRegex(),
                ""
            ) else formattedNumber
        }

        // Used to show Created Date
        fun formatDate(date: String) : String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.ENGLISH)
            val currentDate = Calendar.getInstance().time
            val serverDate = sdf.parse(date)
            val diffInTime =  abs(currentDate.time - serverDate.time)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInTime)
            val hours = TimeUnit.MILLISECONDS.toHours(diffInTime)
            return when {
                hours>24 -> {
                    "${TimeUnit.MILLISECONDS.toDays(diffInTime)} Days"
                }
                hours in 1..23 -> {
                    "${minutes / 60} Hrs ${minutes % 60} Min"
                }
                else -> {
                    "$minutes Min"
                }
            }
        }

        // Used to check network
        fun isNetworkAvailable(context: Context) : Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && (activeNetwork.type == ConnectivityManager.TYPE_WIFI
                    || activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
    }
}