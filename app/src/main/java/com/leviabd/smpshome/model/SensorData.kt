package com.leviabd.smpshome.model

data class SensorData(
    val name: String,
    val temp: Int,
    val humidity: Int,
    val co2: Int,
    val aqi: Int,
    val facility_outlet: Int,
    val facility_noise: Int,
)