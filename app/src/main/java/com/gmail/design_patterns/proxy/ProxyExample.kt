package com.gmail.design_patterns.proxy

import java.io.IOException

//создадим простой интерфейс и конфигурацию для этого объекта
interface ExpensiveObject {
    fun process()
}

class ExpensiveObjectImpl : ExpensiveObject {
    override fun process() {
       println("processing complete.")
    }

    private fun heavyInitialConfiguration() {
        println("Loading initial configuration...")
    }

    init {
        heavyInitialConfiguration()
    }
}

//Теперь мы будем использовать шаблон Proxy и инициализировать наш объект по требованию
class ExpensiveObjectProxy : ExpensiveObject {
    override fun process() {
        if (`object` == null) {
            `object` = ExpensiveObjectImpl()
        }
        `object`!!.process()
    }

    companion object {
        private var `object`: ExpensiveObject? = null
    }
}

//example 2 https://www.journaldev.com/1572/proxy-design-pattern
open interface CommandExecutor {
    @Throws(Exception::class)
    fun runCommand(cmd: String?)
}

class CommandExecutorImpl : CommandExecutor {
    @Throws(IOException::class)
    override fun runCommand(cmd: String?) {
        //some heavy implementation
        Runtime.getRuntime().exec(cmd)
        println("'$cmd' command executed.")
    }
}

class CommandExecutorProxy(user: String, pwd: String) :
    CommandExecutor {
    private var isAdmin = false
    private val executor: CommandExecutor

    @Throws(java.lang.Exception::class)
    override fun runCommand(cmd: String?) {
        if (isAdmin) {
            executor.runCommand(cmd)
        } else {
            if (cmd!!.trim { it <= ' ' }.startsWith("rm")) {
                throw java.lang.Exception("rm command is not allowed for non-admin users.")
            } else {
                executor.runCommand(cmd)
            }
        }
    }

    init {
        if ("Pankaj" == user && "J@urnalD\$v" == pwd) isAdmin = true
        executor = CommandExecutorImpl()
    }
}