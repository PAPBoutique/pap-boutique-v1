@echo off

REM Navigate to the desktop directory
cd /d %userprofile%\Desktop

REM Remove existing project directory
rmdir /s /q project

REM Create a new directory
mkdir project

REM Move into the project directory
cd project

REM Clone the project
git clone -b pb-23 https://github.com/PAPBoutique/pap-boutique-v1.git

REM Move into the backend directory
cd pap-boutique-v1/pap-backend/

REM Run the backend in a new command prompt window
start cmd /k gradlew :launcher:bootrun

REM Move into the frontend repository directory
cd ..\pap-frontend

REM Install frontend packages
call npm i
call npm fund
call npm audit fix

REM Run the frontend in a new command prompt window
ng serve
