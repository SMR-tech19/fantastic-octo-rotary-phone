package com.fastestcryptominer.mining

import com.fastestcryptominer.data.models.CoinType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.random

interface MiningCallback {
    fun onStatsUpdate(hashrate: Double, earnings: Double, cpuUsage: Float)
    fun onError(message: String)
    fun onPoolConnectionChange(connected: Boolean)
}

class MiningEngine(private val scope: CoroutineScope) {
    private var miningJob: Job? = null
    private var callback: MiningCallback? = null
    private var isRunning = false
    private var cpuUsageLimit = 80f

    fun setCallback(cb: MiningCallback) {
        callback = cb
    }

    fun start(walletAddress: String, coinTypes: List<CoinType>) {
        if (isRunning) return
        isRunning = true

        miningJob = scope.launch {
            try {
                callback?.onPoolConnectionChange(true)
                var currentCoinIndex = 0

                while (isActive && isRunning) {
                    val currentCoin = coinTypes[currentCoinIndex % coinTypes.size]
                    mineCoin(currentCoin, walletAddress)
                    currentCoinIndex++

                    // Switch coin every 60 seconds
                    kotlinx.coroutines.delay(60000)
                }
            } catch (e: Exception) {
                callback?.onError(e.message ?: "Unknown error")
                callback?.onPoolConnectionChange(false)
            }
        }
    }

    private suspend fun mineCoin(coin: CoinType, walletAddress: String) {
        // Simulate mining with random stats
        val hashrate = (50000..100000).random().toDouble()
        val earnings = (0.0001..0.001).random()
        val cpuUsage = (40..cpuUsageLimit.toInt()).random().toFloat()

        callback?.onStatsUpdate(hashrate, earnings, cpuUsage)
    }

    fun stop() {
        isRunning = false
        miningJob?.cancel()
        callback?.onPoolConnectionChange(false)
    }

    fun setCpuUsageLimit(limit: Float) {
        cpuUsageLimit = limit.coerceIn(10f, 100f)
    }

    fun isActive(): Boolean = isRunning
}
