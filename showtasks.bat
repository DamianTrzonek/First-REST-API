call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:startbrowser
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.