@ rem ----- 信息 -----
@ rem @filename StartMemCached64.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description 

@ rem @warning 为了防止中文环境乱码，保存文件的时候，应该保存为ANSI编码格式.
@ rem ################################################################################


@rem call common 
call common.bat

set driver=%MEMCACHED64_HOME:~0,2%

%driver%

cd %MEMCACHED64_HOME%

@ rem 标题
@ title 启动 Memcached 64 位
@ rem ########## begin  ##########

@ rem 关闭显示命令，使所有命令执行前不显示
@ rem @ echo off
@ echo off
@ rem 打开命令显示 @ echo on

@ rem ----- 变量声明区
@rem IP Address (localhost is default)
set ipAddr=127.0.0.1

@rem port number
set port=1234

@rem distribute memory  (unit : M)
set distributeMem=32

@ rem ----- 程序设计区


@rem startServer
memcached -l %ipAddr% -m %distributeMem% -d start

@ rem pause

@ rem

@ rem
@ rem 输出提示信息

::
:: 1) 
:: 2)
:: 3)
:: 4)
:: 5)
:: 6)
:: 7)
:: 8)
:: 9)
:: 10)

:: 在程序的末尾，可以根据执行的结果(成功或失败) 给出提示信息，成功可以直接执行exit，而失败
:: 可以执行pause，然后可以通过控制台输出信息来调试、定位问题.
:: 可以在程序中设置一个成功或失败的标志-布尔值，来决定最后程序的执行流程.

@ rem echo
@ rem exit
@ rem ########## end of ##########

@ rem 注释说明: @ rem 注释内容  或者 :: 注释内容
@ rem rem 或两个冒号 后面 都可以写注释
