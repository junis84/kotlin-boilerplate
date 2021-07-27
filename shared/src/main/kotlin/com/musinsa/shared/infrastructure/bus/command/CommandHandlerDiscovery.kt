package com.musinsa.shared.infrastructure.bus.command

import com.musinsa.shared.application.bus.command.Command
import com.musinsa.shared.application.bus.command.CommandHandler
import com.musinsa.shared.application.bus.command.CommandNotRegisteredException
import com.musinsa.shared.domain.service.Service
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import org.reflections.Reflections

@Service
class CommandHandlerDiscovery(packageName: String) {
    private var indexedCommandHandlers: HashMap<KClass<out Command>, KClass<out CommandHandler<*>>>

    constructor(packageClass: KClass<*>) : this(packageClass.java.packageName)

    init {
        val reflections = Reflections(packageName)
        val classes: Set<KClass<out CommandHandler<*>>> =
            reflections.getSubTypesOf(CommandHandler::class.java).map { it.kotlin }.toSet()
        indexedCommandHandlers = formatHandlers(classes)
    }

    @Throws(CommandNotRegisteredException::class)
    fun discover(commandClass: KClass<out Command>): KClass<out CommandHandler<*>> {
        return indexedCommandHandlers[commandClass]
            ?: throw CommandNotRegisteredException(commandClass)
    }

    private fun formatHandlers(
        commandHandlers: Set<KClass<out CommandHandler<*>>>
    ): HashMap<KClass<out Command>, KClass<out CommandHandler<*>>> {
        val handlers: HashMap<KClass<out Command>, KClass<out CommandHandler<*>>> = HashMap()
        for (handler in commandHandlers) {
            val paramType = handler.java.genericInterfaces[0] as ParameterizedType

            @Suppress("UNCHECKED_CAST")
            val commandClass = paramType.actualTypeArguments[0] as Class<out Command?>
            handlers[commandClass.kotlin] = handler
        }
        return handlers
    }
}
