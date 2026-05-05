package com.fastestcryptominer.data.network

import retrofit2.http.GET
import retrofit2.http.Query

data class PoolResponse(
    val id: String,
    val name: String,
    val url: String,
    val coinType: String,
    val feePercentage: Double,
    val minPayout: Double,
    val payoutInterval: String
)

data class StatsResponse(
    val hashrate: Double,
    val sharesAccepted: Int,
    val sharesRejected: Int,
    val earnings: Double
)

interface PoolApi {
    @GET("api/pools")
    suspend fun getPools(): List<PoolResponse>

    @GET("api/pools")
    suspend fun getPoolsByCoin(@Query("coin") coin: String): List<PoolResponse>

    @GET("api/stats")
    suspend fun getStats(@Query("walletAddress") walletAddress: String): StatsResponse

    @GET("api/discover")
    suspend fun discoverPools(): List<PoolResponse>
}
