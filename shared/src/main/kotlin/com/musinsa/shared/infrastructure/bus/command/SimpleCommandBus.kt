package com.musinsa.shared.infrastructure.bus.command

import com.musinsa.shared.application.bus.command.Command
import com.musinsa.shared.application.bus.command.CommandBus
import com.musinsa.shared.application.bus.command.CommandHandler
import com.musinsa.shared.application.bus.command.CommandHandlerExecutionException
import com.musinsa.shared.application.container.Container
import com.musinsa.shared.domain.service.Service
import kotlin.reflect.KClass

@Service
class SimpleCommandBus(
    private val handlerDiscovery: CommandHandlerDiscovery,
    private val container: Container
) : CommandBus {

    override fun dispatch(command: Command) {
        try {
            @Suppress("UNCHECKED_CAST")
            val handlerClass = handlerDiscovery.discover(command::class) as KClass<CommandHandler<Command>>

            (container.get(handlerClass))?.invoke(command)
        } catch (@Suppress("TooGenericExceptionCaught") e: Throwable) {
            throw CommandHandlerExecutionException(e)
        }
    }
}
