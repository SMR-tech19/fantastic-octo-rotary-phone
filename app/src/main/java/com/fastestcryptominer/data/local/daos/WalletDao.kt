package com.fastestcryptominer.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fastestcryptominer.data.local.entities.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Insert
    suspend fun insert(wallet: WalletEntity)

    @Update
    suspend fun update(wallet: WalletEntity)

    @Delete
    suspend fun delete(wallet: WalletEntity)

    @Query("SELECT * FROM wallets WHERE id = :id")
    fun getWalletById(id: String): Flow<WalletEntity>

    @Query("SELECT * FROM wallets WHERE isActive = 1")
    fun getAllActiveWallets(): Flow<List<WalletEntity>>

    @Query("SELECT * FROM wallets")
    fun getAllWallets(): Flow<List<WalletEntity>>
}
