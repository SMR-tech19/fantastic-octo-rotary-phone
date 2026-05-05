# How to Build APK - Fastest Crypto Miner

## Prerequisites

1. **Android Studio** (Latest version recommended)
   - Download from: https://developer.android.com/studio

2. **Java Development Kit (JDK)**
   - JDK 8 or higher required
   - Android Studio bundles JDK by default

3. **Android SDK**
   - Minimum SDK: Android 8.0 (API 26)
   - Target SDK: Android 14 (API 34)
   - Installed via Android Studio SDK Manager

## Step-by-Step Build Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/SMR-tech19/fantastic-octo-rotary-phone.git
cd fantastic-octo-rotary-phone
```

### 2. Open Project in Android Studio

- Open Android Studio
- Click **File → Open**
- Navigate to the cloned directory
- Select the project folder and click **Open**

### 3. Sync Gradle Files

- Android Studio will automatically prompt to sync Gradle files
- Click **Sync Now** if the prompt appears
- Wait for all dependencies to download (may take 5-10 minutes)

### 4. Configure SDK (if needed)

- Go to **File → Project Structure**
- Under **SDK Location**, ensure:
  - Android SDK is properly set
  - JDK is using bundled version or your installed JDK
- Click **OK**

### 5. Build the APK

#### **Option A: Debug APK (Development)**

```bash
# Via Android Studio:
1. Click on Build menu
2. Select "Build Bundle(s) / APK(s)"
3. Click "Build APK(s)"
4. Wait for the build to complete

# Via Command Line:
./gradlew assembleDebug
```

The debug APK will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

#### **Option B: Release APK (Production)**

```bash
# Via Android Studio:
1. Click on Build menu
2. Select "Build Bundle(s) / APK(s)"
3. Click "Build APK(s)"
4. Select "Release" build variant
5. Wait for the build to complete

# Via Command Line:
./gradlew assembleRelease
```

The release APK will be located at:
```
app/build/outputs/apk/release/app-release.apk
```

### 6. Locate Your APK

After a successful build:
- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release.apk`

## Installing APK on Device/Emulator

### **Via Android Studio:**
1. Connect your Android device or start an emulator
2. Go to **Run → Run 'app'**
3. Select your device and click **OK**
4. The app will install and launch automatically

### **Via Command Line:**

```bash
# Install debug APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Install release APK
adb install app/build/outputs/apk/release/app-release.apk
```

### **Via File Manager:**
1. Transfer the APK file to your Android device
2. Open the APK file using your file manager
3. Tap "Install" when prompted

## Troubleshooting

### Build Fails: "Build tools not found"
- Open **Tools → SDK Manager**
- Navigate to **SDK Tools** tab
- Install the latest **Android SDK Build-Tools**

### Build Fails: "Gradle sync failed"
- Click **File → Sync with Gradle Files**
- Or run: `./gradlew clean`
- Then retry the build

### APK Installation Fails: "App not installed"
- Ensure your device has **Unknown Sources** enabled
  - Settings → Security → Unknown Sources (toggle on)
- Or install via Android Studio directly

### Build Fails: "Java version mismatch"
- Ensure JDK 8+ is being used
- Go to **File → Project Structure → SDK Location**
- Select appropriate JDK version

## Building Without Android Studio

If you prefer command-line only:

```bash
# Clone repository
git clone https://github.com/SMR-tech19/fantastic-octo-rotary-phone.git
cd fantastic-octo-rotary-phone

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on connected device
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Release/Production Build Configuration

For production releases on Google Play Store:

1. **Signing Configuration**
   - Create a keystore file (Android Studio handles this)
   - Go to **Build → Generate Signed Bundle / APK**
   - Select APK and proceed

2. **Build Signed APK**
   - Provide your keystore details
   - Select Release build type
   - Complete the signing process

3. **Upload to Play Store**
   - Visit [Google Play Console](https://play.google.com/console)
   - Create/Select your app
   - Upload the signed APK/Bundle

## App Size

- **Debug APK**: ~50-80 MB (includes debugging info)
- **Release APK**: ~25-40 MB (optimized and obfuscated)

## Build Times

- **First Build**: 3-5 minutes (downloads all dependencies)
- **Subsequent Builds**: 1-2 minutes

## Support

For issues during build, refer to:
- [Android Developer Documentation](https://developer.android.com/docs)
- [Gradle Build System](https://developer.android.com/build)
- [GitHub Issues](https://github.com/SMR-tech19/fantastic-octo-rotary-phone/issues)

---

**Happy Mining! 🚀**
