package com.musinsa.shared.infrastructure.bus.command

import com.musinsa.shared.application.bus.command.Command
import com.musinsa.shared.application.bus.command.CommandHandler
import com.musinsa.shared.application.bus.command.CommandNotRegisteredException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.reflections.ReflectionsException

class CommandDummy : Command

class CommandHandlerDummy : CommandHandler<CommandDummy> {
    override fun invoke(command: CommandDummy) {
        // dummy method
    }
}

class CommandHandlerDiscoverySpec : DescribeSpec(
    {
        describe("discover()") {
            it("should return a appropriate handler class when a command class passed") {
                val packageName = CommandDummy::class.java.packageName
                val discovery = CommandHandlerDiscovery(packageName)
                discovery.discover(CommandDummy::class) shouldBe CommandHandlerDummy::class
            }
            it("should throw an exception when a package does not exists") {
                val packageName = "notExistPackage"
                shouldThrow<ReflectionsException> {
                    val discovery = CommandHandlerDiscovery(packageName)
                    discovery.discover(CommandDummy::class)
                }
            }
            it("should throw an exception when an unregistered command class passed") {
                val packageName = CommandDummy::class.java.packageName
                shouldThrow<CommandNotRegisteredException> {
                    val discovery = CommandHandlerDiscovery(packageName)
                    discovery.discover(mockk<Command>()::class)
                }
            }
        }
        describe("constructor()") {
            it("should return a appropriate handler class even if be instantiated by KClass") {
                val discovery = CommandHandlerDiscovery(CommandDummy::class)
                discovery.discover(CommandDummy::class) shouldBe CommandHandlerDummy::class
            }
        }
    }
)
