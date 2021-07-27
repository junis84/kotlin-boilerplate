package com.musinsa.shared.application.bus.command

interface CommandHandler<T : Command> {
    operator fun invoke(command: T)
}
