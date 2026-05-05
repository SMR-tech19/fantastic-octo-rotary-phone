package com.fastestcryptominer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey
    val id: String,
    val walletAddress: String,
    val startTime: Long,
    val endTime: Long?,
    val coinsMinedList: String, // JSON string
    val totalEarnings: Double,
    val totalHashrate: Double,
    val isActive: Boolean
)
