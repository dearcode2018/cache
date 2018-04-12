@rem title
@title Common
@rem ########## begin  ##########

@rem disk driver of memcached  home directory
set diskDriver=C:
@rem memcached memcached  home directory
set MEMCACHED_HOME=%diskDriver%\Program Files\MemCached

set MEMCACHED32_HOME=D:\Program Files\memcached_en32or64\x86

set MEMCACHED64_HOME=D:\Program Files\memcached_en32or64\x64

@rem cd bat file path
@rem cd %~dp0
@rem enter memcached home directory
@rem cd %MEMCACHED_HOME%
@rem

@rem  call by other bat file, cant not execute exit
@rem exit
@rem ########## end of  ##########