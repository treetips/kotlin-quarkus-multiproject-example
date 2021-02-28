package com.example.batch.command

import com.example.batch.service.GreetingService
import picocli.CommandLine
import picocli.CommandLine.ArgGroup

/**
 * @see <a href="https://quarkus.io/guides/picocli#simple-command-line-application">Simple command
 * line application</a>
 * @author treetips
 */
@CommandLine.Command(name = "hello")
class HelloCommand(private val greetingService: GreetingService) : Runnable {

  @ArgGroup(exclusive = true, multiplicity = "1") var name: Name? = null

  class Name {
    @CommandLine.Option(names = ["--first-name"], description = ["The guest first name"])
    var firstName: String? = null

    @CommandLine.Option(names = ["--nick-name"], description = ["The guest nickname"])
    var nickname: String? = null

    fun value(): String {
      return if (firstName != null) firstName!! else nickname!!
    }
  }

  override fun run() {
    greetingService.sayHello(name!!.value())
  }
}
