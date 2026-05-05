package com.fastestcryptominer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastestcryptominer.data.models.CoinType
import com.fastestcryptominer.data.models.MiningStats
import com.fastestcryptominer.data.repository.MiningRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Main Activity
 * Manages mining state, statistics, and user interactions
 */
class MainViewModel(private val repository: MiningRepository) : ViewModel() {

    // Mining State
    private val _miningState = MutableLiveData<MiningState>(MiningState.IDLE)
    val miningState: LiveData<MiningState> = _miningState

    // Current Mining Stats
    private val _currentStats = MutableLiveData<MiningStats?>(null)
    val currentStats: LiveData<MiningStats?> = _currentStats

    // Selected Coin
    private val _selectedCoin = MutableLiveData<CoinType>(CoinType.ETHEREUM)
    val selectedCoin: LiveData<CoinType> = _selectedCoin

    // Wallet Address
    private val _walletAddress = MutableLiveData<String>("")
    val walletAddress: LiveData<String> = _walletAddress

    // Auto-switch enabled
    private val _autoSwitchEnabled = MutableLiveData<Boolean>(false)
    val autoSwitchEnabled: LiveData<Boolean> = _autoSwitchEnabled

    // Error messages
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    // CPU Usage
    private val _cpuUsagePercent = MutableLiveData<Int>(50)
    val cpuUsagePercent: LiveData<Int> = _cpuUsagePercent

    // Total Earnings
    private val _totalEarnings = MutableLiveData<Double>(0.0)
    val totalEarnings: LiveData<Double> = _totalEarnings

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                repository.fetchAndSavePools()
            } catch (e: Exception) {
                _errorMessage.postValue("Failed to load pools: ${e.message}")
            }
        }
    }

    fun startMining(walletAddress: String, coinType: CoinType) {
        if (walletAddress.isEmpty()) {
            _errorMessage.postValue("Please enter a valid wallet address")
            return
        }

        _walletAddress.postValue(walletAddress)
        _selectedCoin.postValue(coinType)
        _miningState.postValue(MiningState.CONNECTING)

        viewModelScope.launch {
            try {
                // Start mining session
                val sessionId = repository.startMiningSession(
                    coinType = coinType.symbol,
                    poolUrl = coinType.poolUrl,
                    walletAddress = walletAddress
                )

                _miningState.postValue(MiningState.MINING)
                // Simulate mining stats update
                simulateMiningStats()

            } catch (e: Exception) {
                _errorMessage.postValue("Failed to start mining: ${e.message}")
                _miningState.postValue(MiningState.ERROR)
            }
        }
    }

    fun stopMining() {
        _miningState.postValue(MiningState.STOPPED)
    }

    fun pauseMining() {
        _miningState.postValue(MiningState.PAUSED)
    }

    fun resumeMining() {
        _miningState.postValue(MiningState.MINING)
    }

    fun setAutoSwitch(enabled: Boolean) {
        _autoSwitchEnabled.postValue(enabled)
    }

    fun setCpuUsage(percent: Int) {
        _cpuUsagePercent.postValue(percent.coerceIn(10, 100))
    }

    fun setSelectedCoin(coin: CoinType) {
        _selectedCoin.postValue(coin)
    }

    private fun simulateMiningStats() {
        viewModelScope.launch {
            repeat(100) {
                val stats = MiningStats(
                    hashRate = 1000.0 + (Math.random() * 500),
                    cpuUsage = _cpuUsagePercent.value?.toDouble() ?: 50.0,
                    temperature = 45.0 + (Math.random() * 15),
                    batteryLevel = 80 - (it / 10),
                    networkLatency = 20 + (Math.random() * 30).toInt(),
                    totalShares = it,
                    acceptedShares = (it * 0.95).toInt(),
                    rejectedShares = (it * 0.05).toInt(),
                    totalEarnings = it * 0.000001,
                    timestamp = System.currentTimeMillis()
                )

                _currentStats.postValue(stats)
                _totalEarnings.postValue(stats.totalEarnings)

                kotlinx.coroutines.delay(1000) // Update every second
            }
        }
    }

    fun clearError() {
        _errorMessage.postValue(null)
    }
}

/**
 * Mining state enum
 */
enum class MiningState {
    IDLE,
    CONNECTING,
    MINING,
    PAUSED,
    STOPPED,
    ERROR
}
