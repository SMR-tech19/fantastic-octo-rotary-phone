@echo off
setlocal
set APP_NAME=Gradle
set DEFAULT_JVM_OPTS=-Xmx64m
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if not defined JAVA_HOME goto findJavaFromPath
if exist "%JAVA_HOME%\bin\java.exe" goto init
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%\bin\java.exe
goto fail
:findJavaFromPath
where java >nul 2>nul
if %ERRORLEVEL%==0 (
    set JAVA_EXE=java
    goto init
)
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
goto fail
:init
set DIRNAME=%~dp0
set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
goto end
:fail
exit /b 1
:end
