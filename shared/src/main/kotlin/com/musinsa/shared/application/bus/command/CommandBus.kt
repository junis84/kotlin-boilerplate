package com.musinsa.shared.application.bus.command

interface CommandBus {
    @Throws(CommandHandlerExecutionException::class)
    fun dispatch(command: Command)
}
