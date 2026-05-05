package com.fastestcryptominer.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fastestcryptominer.data.local.entities.StatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao {
    @Insert
    suspend fun insert(stats: StatsEntity)

    @Update
    suspend fun update(stats: StatsEntity)

    @Delete
    suspend fun delete(stats: StatsEntity)

    @Query("SELECT * FROM stats WHERE id = :id")
    fun getStatsById(id: String): Flow<StatsEntity>

    @Query("SELECT * FROM stats WHERE sessionId = :sessionId ORDER BY timestamp DESC")
    fun getStatsBySession(sessionId: String): Flow<List<StatsEntity>>

    @Query("SELECT * FROM stats WHERE sessionId = :sessionId ORDER BY timestamp DESC LIMIT 1")
    fun getLatestStats(sessionId: String): Flow<StatsEntity?>

    @Query("SELECT * FROM stats ORDER BY timestamp DESC")
    fun getAllStats(): Flow<List<StatsEntity>>
}
