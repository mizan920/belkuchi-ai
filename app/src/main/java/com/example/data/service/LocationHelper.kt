package com.example.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.data.local.BelkuchiLocalData
import com.example.data.model.LocationCoordinate
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.math.*

class LocationHelper(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): LocationCoordinate {
        return try {
            val cancellationTokenSource = CancellationTokenSource()
            val location: Location? = suspendCancellableCoroutine { cont ->
                fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                    cancellationTokenSource.token
                ).addOnSuccessListener { loc ->
                    cont.resume(loc)
                }.addOnFailureListener {
                    cont.resume(null)
                }
            }

            if (location != null) {
                LocationCoordinate(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    locationName = "আপনার অবস্থান"
                )
            } else {
                defaultBelkuchiLocation()
            }
        } catch (e: Exception) {
            defaultBelkuchiLocation()
        }
    }

    fun defaultBelkuchiLocation(): LocationCoordinate {
        return LocationCoordinate(
            latitude = BelkuchiLocalData.BELKUCHI_CENTER_LAT,
            longitude = BelkuchiLocalData.BELKUCHI_CENTER_LNG,
            locationName = "বেলকুচি কেন্দ্র (মুকুন্দগাঁতী)"
        )
    }

    companion object {
        fun calculateDistanceKm(
            lat1: Double,
            lon1: Double,
            lat2: Double,
            lon2: Double
        ): Double {
            val r = 6371.0 // Earth radius in km
            val dLat = Math.toRadians(lat2 - lat1)
            val dLon = Math.toRadians(lon2 - lon1)
            val a = sin(dLat / 2) * sin(dLat / 2) +
                    cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                    sin(dLon / 2) * sin(dLon / 2)
            val c = 2 * atan2(sqrt(a), sqrt(1 - a))
            return (r * c)
        }

        fun formatDistanceBangla(distanceKm: Double): String {
            val formatted = String.format(java.util.Locale.US, "%.1f", distanceKm)
            val banglaDigits = mapOf(
                '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
                '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯', '.' to '.'
            )
            val bnStr = formatted.map { banglaDigits[it] ?: it }.joinToString("")
            return "$bnStr কিমি"
        }
    }
}
