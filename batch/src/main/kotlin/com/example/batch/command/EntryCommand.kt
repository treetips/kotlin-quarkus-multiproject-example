package com.example.batch.command

import io.quarkus.picocli.runtime.annotations.TopCommand
import picocli.CommandLine

/**
 * <a
 * href="https://quarkus.io/guides/picocli#command-line-application-with-multiple-commands">Command
 * line application with multiple Commands</a>
 * @author treetips
 */
@TopCommand
@CommandLine.Command(
    mixinStandardHelpOptions = true,
    subcommands =
        [
            HelloCommand::class,
            GoodbyeCommand::class,
        ])
class EntryCommand
