@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  WalmartTicketingService startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and WALMART_TICKETING_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\WalmartTicketingService.jar;%APP_HOME%\lib\spring-boot-starter-cache-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-freemarker-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-groovy-templates-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-hateoas-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jersey-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-thymeleaf-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-velocity-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-security-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-data-jpa-1.9.2.RELEASE.jar;%APP_HOME%\lib\javaee-api-7.0.jar;%APP_HOME%\lib\commons-lang3-3.4.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\spring-boot-starter-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-context-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-context-support-4.2.4.RELEASE.jar;%APP_HOME%\lib\freemarker-2.3.23.jar;%APP_HOME%\lib\groovy-templates-2.4.4.jar;%APP_HOME%\lib\spring-hateoas-0.19.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-core-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-validation-1.3.1.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.6.4.jar;%APP_HOME%\lib\spring-web-4.2.4.RELEASE.jar;%APP_HOME%\lib\jersey-server-2.22.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.22.1.jar;%APP_HOME%\lib\jersey-container-servlet-2.22.1.jar;%APP_HOME%\lib\jersey-bean-validation-2.22.1.jar;%APP_HOME%\lib\jersey-spring3-2.22.1.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.22.1.jar;%APP_HOME%\lib\thymeleaf-spring4-2.1.4.RELEASE.jar;%APP_HOME%\lib\thymeleaf-layout-dialect-1.3.1.jar;%APP_HOME%\lib\commons-beanutils-1.9.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\commons-digester-2.1.jar;%APP_HOME%\lib\velocity-1.7.jar;%APP_HOME%\lib\velocity-tools-2.0.jar;%APP_HOME%\lib\spring-webmvc-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-1.3.1.RELEASE.jar;%APP_HOME%\lib\hibernate-entitymanager-4.3.11.Final.jar;%APP_HOME%\lib\javax.transaction-api-1.2.jar;%APP_HOME%\lib\spring-aspects-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-aop-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-security-config-4.0.3.RELEASE.jar;%APP_HOME%\lib\spring-security-web-4.0.3.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-1.11.2.RELEASE.jar;%APP_HOME%\lib\spring-orm-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-tx-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-core-4.2.4.RELEASE.jar;%APP_HOME%\lib\aspectjrt-1.8.7.jar;%APP_HOME%\lib\slf4j-api-1.7.13.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.13.jar;%APP_HOME%\lib\javax.mail-1.5.4.jar;%APP_HOME%\lib\spring-boot-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.3.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.3.1.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.16.jar;%APP_HOME%\lib\spring-expression-4.2.4.RELEASE.jar;%APP_HOME%\lib\groovy-2.4.4.jar;%APP_HOME%\lib\groovy-xml-2.4.4.jar;%APP_HOME%\lib\tomcat-embed-core-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-el-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-logging-juli-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.0.30.jar;%APP_HOME%\lib\hibernate-validator-5.2.2.Final.jar;%APP_HOME%\lib\jackson-annotations-2.6.4.jar;%APP_HOME%\lib\jackson-core-2.6.4.jar;%APP_HOME%\lib\jersey-common-2.22.1.jar;%APP_HOME%\lib\jersey-client-2.22.1.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\jersey-media-jaxb-2.22.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.4.0-b31.jar;%APP_HOME%\lib\javax.inject-2.4.0-b31.jar;%APP_HOME%\lib\hk2-locator-2.4.0-b31.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\hk2-2.4.0-b31.jar;%APP_HOME%\lib\spring-bridge-2.4.0-b31.jar;%APP_HOME%\lib\jersey-entity-filtering-2.22.1.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.5.4.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.5.4.jar;%APP_HOME%\lib\thymeleaf-2.1.4.RELEASE.jar;%APP_HOME%\lib\commons-lang-2.4.jar;%APP_HOME%\lib\commons-chain-1.1.jar;%APP_HOME%\lib\commons-validator-1.3.1.jar;%APP_HOME%\lib\oro-2.0.8.jar;%APP_HOME%\lib\sslext-1.2-0.jar;%APP_HOME%\lib\struts-core-1.3.8.jar;%APP_HOME%\lib\struts-taglib-1.3.8.jar;%APP_HOME%\lib\struts-tiles-1.3.8.jar;%APP_HOME%\lib\aspectjweaver-1.8.7.jar;%APP_HOME%\lib\tomcat-jdbc-8.0.30.jar;%APP_HOME%\lib\spring-jdbc-4.2.4.RELEASE.jar;%APP_HOME%\lib\jboss-logging-3.3.0.Final.jar;%APP_HOME%\lib\jboss-logging-annotations-1.2.0.Beta1.jar;%APP_HOME%\lib\hibernate-core-4.3.11.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-4.0.5.Final.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\javassist-3.18.1-GA.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\spring-security-core-4.0.3.RELEASE.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\logback-classic-1.1.3.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.13.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.13.jar;%APP_HOME%\lib\classmate-1.1.0.jar;%APP_HOME%\lib\jersey-guava-2.22.1.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.4.0-b31.jar;%APP_HOME%\lib\aopalliance-repackaged-2.4.0-b31.jar;%APP_HOME%\lib\config-types-2.4.0-b31.jar;%APP_HOME%\lib\hk2-core-2.4.0-b31.jar;%APP_HOME%\lib\hk2-config-2.4.0-b31.jar;%APP_HOME%\lib\hk2-runlevel-2.4.0-b31.jar;%APP_HOME%\lib\class-model-2.4.0-b31.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.5.4.jar;%APP_HOME%\lib\ognl-3.0.8.jar;%APP_HOME%\lib\unbescape-1.1.0.RELEASE.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\tomcat-juli-8.0.30.jar;%APP_HOME%\lib\jandex-1.1.0.Final.jar;%APP_HOME%\lib\logback-core-1.1.3.jar;%APP_HOME%\lib\tiger-types-1.4.jar;%APP_HOME%\lib\asm-all-repackaged-2.4.0-b31.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\xml-apis-1.0.b2.jar

@rem Execute WalmartTicketingService
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %WALMART_TICKETING_SERVICE_OPTS%  -classpath "%CLASSPATH%" com.walmart.main.WalmartSeatingApp %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable WALMART_TICKETING_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%WALMART_TICKETING_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
