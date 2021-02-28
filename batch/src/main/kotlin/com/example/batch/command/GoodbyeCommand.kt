package com.example.batch.command

import picocli.CommandLine

/**
 * @see <a
 * href="https://quarkus.io/guides/picocli#command-line-application-with-multiple-commands">Command
 * line application with multiple Commands</a>
 * @author treetips
 */
@CommandLine.Command(name = "goodbye")
class GoodbyeCommand : Runnable {
  @CommandLine.Option(names = ["--name"], description = ["Guest name"]) var name: String? = null

  @CommandLine.Option(
      names = ["--times", "-t"],
      defaultValue = "1",
      description = ["How many time should we say goodbye"])
  var times = 0

  override fun run() {
    for (i in 0 until times) {
      println("Goodbye ${name}!")
    }
  }
}
