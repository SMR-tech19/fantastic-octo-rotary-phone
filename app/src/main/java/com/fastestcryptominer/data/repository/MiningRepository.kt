package com.fastestcryptominer.data.repository

import com.fastestcryptominer.data.local.AppDatabase
import com.fastestcryptominer.data.local.entities.PoolEntity
import com.fastestcryptominer.data.local.entities.SessionEntity
import com.fastestcryptominer.data.local.entities.StatsEntity
import com.fastestcryptominer.data.local.entities.WalletEntity
import com.fastestcryptominer.data.network.PoolApi
import kotlinx.coroutines.flow.Flow

class MiningRepository(
    private val database: AppDatabase,
    private val poolApi: PoolApi
) {
    // Wallet Operations
    suspend fun addWallet(wallet: WalletEntity) {
        database.walletDao().insert(wallet)
    }

    fun getActiveWallets(): Flow<List<WalletEntity>> {
        return database.walletDao().getAllActiveWallets()
    }

    // Pool Operations
    suspend fun refreshPools() {
        try {
            val remotePools = poolApi.getPools()
            remotePools.forEach { response ->
                val poolEntity = PoolEntity(
                    id = response.id,
                    name = response.name,
                    url = response.url,
                    coinType = response.coinType,
                    feePercentage = response.feePercentage,
                    minPayout = response.minPayout,
                    payoutInterval = response.payoutInterval,
                    isActive = true,
                    lastUpdated = System.currentTimeMillis()
                )
                database.poolDao().insert(poolEntity)
            }
        } catch (e: Exception) {
            // Handle error
        }
    }

    fun getActivePools(): Flow<List<PoolEntity>> {
        return database.poolDao().getAllActivePools()
    }

    // Session Operations
    suspend fun createSession(session: SessionEntity) {
        database.sessionDao().insert(session)
    }

    fun getActiveSessions(): Flow<List<SessionEntity>> {
        return database.sessionDao().getActiveSessions()
    }

    // Stats Operations
    suspend fun recordStats(stats: StatsEntity) {
        database.statsDao().insert(stats)
    }

    fun getSessionStats(sessionId: String): Flow<List<StatsEntity>> {
        return database.statsDao().getStatsBySession(sessionId)
    }

    fun getLatestStats(sessionId: String): Flow<StatsEntity?> {
        return database.statsDao().getLatestStats(sessionId)
    }
}
