package com.fastestcryptominer.data.models

enum class CoinType(
    val displayName: String,
    val symbol: String,
    val poolUrl: String
) {
    ETHEREUM("Ethereum", "ETH", "https://ethermine.org"),
    BITCOIN("Bitcoin", "BTC", "https://antpool.com"),
    SOLANA("Solana", "SOL", "https://marinade.finance"),
    LITECOIN("Litecoin", "LTC", "https://litecoinpool.org"),
    DOGECOIN("Dogecoin", "DOGE", "https://dogecoin.com"),
    MONERO("Monero", "XMR", "https://moneroocean.stream")
}
