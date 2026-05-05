package com.fastestcryptominer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class StatsEntity(
    @PrimaryKey
    val id: String,
    val sessionId: String,
    val hashrate: Double,
    val sharesAccepted: Int,
    val sharesRejected: Int,
    val currentCoin: String,
    val earnings: Double,
    val elapsedTime: Long,
    val cpuUsage: Float,
    val deviceTemperature: Float,
    val timestamp: Long
)
