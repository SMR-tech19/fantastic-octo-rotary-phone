# Fastest Crypto Miner - Technical Architecture

## Overview

This document outlines the technical architecture of the Fastest Crypto Miner Android application.

## Architecture Pattern

The application follows **MVVM (Model-View-ViewModel)** architecture with clean separation of concerns:

```
UI Layer (Activities/Fragments) 
    ↓
ViewModel Layer
    ↓
Repository Layer
    ↓
Data Layer (Local Database + Remote API)
```

## Project Structure

### 1. **UI Layer** (`ui/`)
- **Activities**: Main user interface components
  - `MainActivity`: Main mining dashboard
  - `SetupActivity`: Initial wallet and coin configuration
  - `SettingsActivity`: Mining preferences and optimization
  - `MiningActivity`: Detailed mining operations

- **ViewModels**: UI state management
  - `MainViewModel`: Manages main screen state and mining operations

### 2. **Data Layer** (`data/`)

#### Models (`models/`)
- **Crypto Models**: Core data structures
  - `CoinType`: Enumeration of supported cryptocurrencies
  - `MiningPool`: Pool information and metadata
  - `WalletInfo`: User wallet details
  - `MiningStats`: Real-time mining statistics
  - `MiningSession`: Historical mining session data

- **Room Entities**: Database schema
  - `WalletEntity`: User wallets in database
  - `PoolEntity`: Mining pools cache
  - `SessionEntity`: Mining sessions archive
  - `StatsEntity`: Historical statistics

- **API Models**: Network response objects
  - `PoolResponse`: Pool API response
  - `StatsResponse`: Statistics API response
  - `CoinPriceResponse`: Coin price information

#### Local Database (`local/`)
- **AppDatabase**: Room database configuration
  - Manages all entity tables
  - Provides DAO access

- **DAOs** (`Daos.kt`): Data Access Objects
  - `WalletDao`: Wallet CRUD operations
  - `PoolDao`: Pool management
  - `SessionDao`: Session tracking
  - `StatsDao`: Statistics storage

#### Network (`network/`)
- **PoolApi**: Retrofit interface for API calls
  - Mining pool discovery
  - Statistics retrieval
  - Coin price fetching

- **NetworkModule**: Dependency injection
  - OkHttp client configuration
  - Retrofit setup
  - API interceptors

#### Repository (`repository/`)
- **MiningRepository**: Single source of truth
  - Coordinates between local and remote data
  - Manages data flow to UI layer
  - Handles error cases

### 3. **Mining Layer** (`mining/`)
- **MiningEngine**: Core mining orchestration
  - Pool connection management
  - Mining worker coordination
  - State machine for mining lifecycle
  - Statistics collection

- **PoolConnection**: Pool protocol handler
  - Connects to mining pools
  - Manages work distribution
  - Handles pool responses

- **Miner**: Individual mining worker
  - Performs cryptographic computations
  - Submits work to pools
  - Tracks share acceptance

## Data Flow

### Mining Start Flow
```
MainActivity (UI)
    ↓ [User clicks START]
MainViewModel.startMining()
    ↓
MiningEngine.start()
    ↓
PoolConnection.connect()
    ↓
Miner.mine()
    ↓ [Stats collected]
Repository.saveMiningStats()
    ↓ [Stored in database]
```

### Statistics Update Flow
```
MiningEngine [collecting stats]
    ↓
statsCallback (LiveData update)
    ↓
MainViewModel._currentStats.postValue()
    ↓
MainActivity observes LiveData
    ↓ [UI refreshed]
```

### Pool Discovery Flow
```
MainActivity [on load]
    ↓
MainViewModel.loadAvailablePools()
    ↓
Repository.fetchAndSavePools()
    ↓ [API call via PoolApi]
    ↓ [Save to local database]
    ↓ [Emit to UI]
```

## Key Components

### 1. Coroutines & Async Operations
- **CoroutineScope**: Lifecycle-aware coroutines in ViewModel
- **Flow**: Reactive data streams from database and network
- **LiveData**: UI-observable data with lifecycle awareness

### 2. Room Database
- **Version**: 1 (can be incremented for migrations)
- **Fallback Strategy**: Destructive migration (dev/testing)
- **Tables**: 4 (wallets, pools, sessions, stats)

### 3. Network Communication
- **Base URL**: `https://api.pools.crypto/`
- **Timeout**: 30 seconds
- **Logging**: Full HTTP request/response body logging
- **Retry**: Enabled with backoff strategy

### 4. Mining Lifecycle States
```
IDLE → CONNECTING → MINING ↔ PAUSED → STOPPED
         ↓
       ERROR → IDLE
```

## Threading Model

- **Main Thread**: UI updates, LiveData observation
- **Default Dispatcher**: Mining computations, database operations
- **IO Dispatcher**: Network calls (implicit via Retrofit)

## Dependencies

### Core
- Kotlin Coroutines
- Android Lifecycle & LiveData
- Room Database
- Retrofit & OkHttp

### UI
- AndroidX AppCompat
- Material Components
- ConstraintLayout

### Testing
- JUnit 4
- Espresso
- AndroidX Test

## Security Considerations

1. **Wallet Address**: Never transmitted to app servers
2. **Private Keys**: Not stored or handled
3. **Permissions**: Minimal required (INTERNET only)
4. **Data Storage**: Local database (unencrypted by default)

## Performance Optimizations

1. **Database Caching**: Pool list cached locally
2. **Network Efficiency**: Pooled connections via OkHttp
3. **CPU Usage**: Configurable via settings (CPU% slider)
4. **Memory Management**: Proper coroutine cleanup

## Future Enhancements

- [ ] Encrypted database with SQLCipher
- [ ] Hardware acceleration for mining
- [ ] Multi-device pool sharing
- [ ] Advanced analytics dashboard
- [ ] Cloud backup of settings
- [ ] Push notifications for earnings alerts

## Testing Strategy

- **Unit Tests**: ViewModel, Repository logic
- **Integration Tests**: Database operations
- **UI Tests**: Activity interactions
- **End-to-End**: Full mining workflow
