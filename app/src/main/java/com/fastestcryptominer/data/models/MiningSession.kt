package com.fastestcryptominer.data.models

import java.util.Date

data class MiningSession(
    val id: String,
    val walletAddress: String,
    val startTime: Date,
    val endTime: Date? = null,
    val coinsMinedList: List<CoinType>,
    val totalEarnings: Double = 0.0,
    val totalHashrate: Double = 0.0,
    val isActive: Boolean = true
)
