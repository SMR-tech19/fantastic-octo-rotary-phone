package com.fastestcryptominer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pools")
data class PoolEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    val coinType: String,
    val feePercentage: Double,
    val minPayout: Double,
    val payoutInterval: String,
    val isActive: Boolean,
    val lastUpdated: Long
)
