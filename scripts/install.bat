@echo off

setlocal

set MY_DIR=%~dp0
set APP_NAME=jget
set LIB_DIR=%MY_DIR%..\build\libs
set APP_VER=1.0

if not exist "%USERPROFILE%\bin" mkdir "%USERPROFILE%\bin"
if not exist "%USERPROFILE%\lib" mkdir "%USERPROFILE%\lib"

copy "%MY_DIR%%APP_NAME%.bat" "%USERPROFILE%\bin"
copy "%LIB_DIR%\%APP_NAME%-%APP_VER%.jar" "%USERPROFILE%\lib"

"%MY_DIR%add-to-path" "%USERPROFILE%\bin"

endlocal
