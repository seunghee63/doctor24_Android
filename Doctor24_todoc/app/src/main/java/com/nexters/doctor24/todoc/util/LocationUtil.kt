package com.nexters.doctor24.todoc.util

import com.naver.maps.geometry.LatLng
import java.text.SimpleDateFormat
import java.util.*

fun LatLng.toDistance(from : LatLng?) : String {
    var meter = from?.let{this.distanceTo(it)} ?: 0.0

    return when {
        meter > 1000 -> {
            meter /= 1000
            String.format("%.2f km", meter)
        }
        else -> String.format("%.2f m", meter)
    }
}

fun isCurrentMapDarkMode() = when (getCurrentTimeHours()) {
    in 8..17 -> false
    else -> true
}

private fun getCurrentTimeHours(): Int {
    val now = System.currentTimeMillis()
    val date = Date(now)

    return SimpleDateFormat("HH", Locale.KOREA).format(date).toInt()
}