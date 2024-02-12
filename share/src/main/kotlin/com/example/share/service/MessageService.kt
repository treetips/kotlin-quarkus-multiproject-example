package com.example.share.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.text.MessageFormat
import java.util.*

/**
 * Message Service
 *
 * @author treetips
 */
@ApplicationScoped
class MessageService {
  @Inject private lateinit var resourceBundle: ResourceBundle

  fun getMessage(messageKey: String, vararg placeholders: Any): String =
      resourceBundle.getString(messageKey).let {
        if (placeholders.isEmpty()) {
          it
        } else {
          MessageFormat.format(it, *placeholders)
        }
      }
}
