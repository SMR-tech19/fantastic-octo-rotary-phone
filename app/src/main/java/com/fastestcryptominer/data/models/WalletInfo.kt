package com.fastestcryptominer.data.models

data class WalletInfo(
    val id: String,
    val walletAddress: String,
    val coinType: CoinType,
    val trustWalletAddress: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)
