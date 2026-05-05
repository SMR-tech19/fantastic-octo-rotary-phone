package com.fastestcryptominer.data.models

data class MiningStats(
    val id: String,
    val sessionId: String,
    val hashrate: Double,
    val sharesAccepted: Int,
    val sharesRejected: Int,
    val currentCoin: CoinType,
    val earnings: Double,
    val elapsedTime: Long,
    val cpuUsage: Float,
    val deviceTemperature: Float,
    val timestamp: Long = System.currentTimeMillis()
)
