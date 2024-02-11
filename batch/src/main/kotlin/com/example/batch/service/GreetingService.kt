package com.example.batch.service

import com.example.share.service.ShareService
import jakarta.enterprise.context.Dependent

/**
 * Greeting Service
 *
 * @see <a href="https://quarkus.io/guides/picocli#simple-command-line-application">Simple command
 *   line application</a>
 * @author treetips
 */
@Dependent
class GreetingService(private val shareService: ShareService) {
  fun sayHello(name: String?) {
    println("${shareService.say()} ${name}!")
  }
}
