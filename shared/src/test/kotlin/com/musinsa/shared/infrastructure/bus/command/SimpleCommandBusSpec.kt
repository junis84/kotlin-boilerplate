package com.musinsa.shared.infrastructure.bus.command

import com.musinsa.shared.application.bus.command.Command
import com.musinsa.shared.application.bus.command.CommandHandler
import com.musinsa.shared.application.bus.command.CommandHandlerExecutionException
import com.musinsa.shared.application.container.Container
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

private fun createCommand(): Command {
    return mockk<Command>()
}

private fun createCommandHandler(): CommandHandler<Command> {
    return mockk<CommandHandler<Command>>()
}

private fun createCommandHandlerDiscovery(): CommandHandlerDiscovery {
    return mockk<CommandHandlerDiscovery>()
}

private fun createContainer(): Container {
    return mockk<Container>()
}

class SimpleCommandBusSpec : DescribeSpec(
    {
        val command = createCommand()
        var commandHandler: CommandHandler<Command>
        var commandHandlerDiscovery: CommandHandlerDiscovery
        var container: Container
        var commandBus: SimpleCommandBus

        describe("dispatch()") {
            it("should invoke a appropriate handler when a command passed") {
                commandHandler = createCommandHandler()
                every { commandHandler.invoke(command) } returns Unit
                commandHandlerDiscovery = createCommandHandlerDiscovery()
                every { commandHandlerDiscovery.discover(command::class) } returns commandHandler::class
                container = createContainer()
                every { container.get(commandHandler::class) } returns commandHandler
                commandBus = SimpleCommandBus(commandHandlerDiscovery, container)

                commandBus.dispatch(command)
                verify {
                    commandHandler.invoke(command)
                }
            }
            it("should return null if couldn't get a handler from a container") {
                commandHandler = createCommandHandler()
                every { commandHandler.invoke(command) } returns Unit
                commandHandlerDiscovery = createCommandHandlerDiscovery()
                every { commandHandlerDiscovery.discover(command::class) } returns commandHandler::class
                container = createContainer()
                every { container.get(commandHandler::class) } returns null
                commandBus = SimpleCommandBus(commandHandlerDiscovery, container)

                commandBus.dispatch(command)
                verify(exactly = 0) {
                    commandHandler.invoke(command)
                }
            }
            it("should throw when an exception occurs during execution") {
                commandHandler = createCommandHandler()
                every { commandHandler.invoke(command) }.throws(RuntimeException())
                commandHandlerDiscovery = createCommandHandlerDiscovery()
                every { commandHandlerDiscovery.discover(command::class) } returns commandHandler::class
                container = createContainer()
                every { container.get(commandHandler::class) } returns commandHandler
                commandBus = SimpleCommandBus(commandHandlerDiscovery, container)

                shouldThrow<CommandHandlerExecutionException> {
                    commandBus.dispatch(command)
                }
            }
        }
    }
)
