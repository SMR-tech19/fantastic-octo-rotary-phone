package com.fastestcryptominer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fastestcryptominer.data.local.daos.PoolDao
import com.fastestcryptominer.data.local.daos.SessionDao
import com.fastestcryptominer.data.local.daos.StatsDao
import com.fastestcryptominer.data.local.daos.WalletDao
import com.fastestcryptominer.data.local.entities.PoolEntity
import com.fastestcryptominer.data.local.entities.SessionEntity
import com.fastestcryptominer.data.local.entities.StatsEntity
import com.fastestcryptominer.data.local.entities.WalletEntity

@Database(
    entities = [
        WalletEntity::class,
        PoolEntity::class,
        SessionEntity::class,
        StatsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
    abstract fun poolDao(): PoolDao
    abstract fun sessionDao(): SessionDao
    abstract fun statsDao(): StatsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fastest_crypto_miner.db"
                ).fallbackToDestructiveMigration().build()
                instance = db
                db
            }
        }
    }
}
