package com.fastestcryptominer.data.models

data class MiningPool(
    val id: String,
    val name: String,
    val url: String,
    val coinType: CoinType,
    val feePercentage: Double,
    val minPayout: Double,
    val payoutInterval: String,
    val isActive: Boolean = true,
    val lastUpdated: Long = System.currentTimeMillis()
)
