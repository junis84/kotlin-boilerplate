package com.musinsa.shared.application.bus.command

import kotlin.reflect.KClass

class CommandNotRegisteredException(command: KClass<out Command>) :
    Exception("The command <$command> hasn't a command handler associated")
