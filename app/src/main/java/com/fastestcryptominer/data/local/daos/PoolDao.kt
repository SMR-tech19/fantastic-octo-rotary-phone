package com.fastestcryptominer.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fastestcryptominer.data.local.entities.PoolEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoolDao {
    @Insert
    suspend fun insert(pool: PoolEntity)

    @Update
    suspend fun update(pool: PoolEntity)

    @Delete
    suspend fun delete(pool: PoolEntity)

    @Query("SELECT * FROM pools WHERE id = :id")
    fun getPoolById(id: String): Flow<PoolEntity>

    @Query("SELECT * FROM pools WHERE coinType = :coinType AND isActive = 1")
    fun getPoolsByCoinType(coinType: String): Flow<List<PoolEntity>>

    @Query("SELECT * FROM pools WHERE isActive = 1")
    fun getAllActivePools(): Flow<List<PoolEntity>>

    @Query("SELECT * FROM pools")
    fun getAllPools(): Flow<List<PoolEntity>>
}
