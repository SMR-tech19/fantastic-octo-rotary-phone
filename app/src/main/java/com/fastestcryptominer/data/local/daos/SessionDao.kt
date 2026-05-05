package com.fastestcryptominer.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fastestcryptominer.data.local.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: SessionEntity)

    @Update
    suspend fun update(session: SessionEntity)

    @Delete
    suspend fun delete(session: SessionEntity)

    @Query("SELECT * FROM sessions WHERE id = :id")
    fun getSessionById(id: String): Flow<SessionEntity>

    @Query("SELECT * FROM sessions WHERE walletAddress = :walletAddress ORDER BY startTime DESC")
    fun getSessionsByWallet(walletAddress: String): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE isActive = 1")
    fun getActiveSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions ORDER BY startTime DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>
}
