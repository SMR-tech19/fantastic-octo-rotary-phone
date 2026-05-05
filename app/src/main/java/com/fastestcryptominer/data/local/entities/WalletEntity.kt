package com.fastestcryptominer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class WalletEntity(
    @PrimaryKey
    val id: String,
    val walletAddress: String,
    val coinType: String,
    val trustWalletAddress: String,
    val createdAt: Long,
    val isActive: Boolean
)
