# ProGuard Rules for Fastest Crypto Miner

# Keep application classes
-keep class com.fastestcryptominer.** { *; }

# Keep all model classes
-keep class com.fastestcryptominer.data.models.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keep class * implements com.google.gson.JsonDeserializable

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class * { *; }
-dontwarn androidx.room.**

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**
