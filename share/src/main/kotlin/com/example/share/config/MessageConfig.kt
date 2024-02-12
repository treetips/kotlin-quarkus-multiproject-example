package com.example.share.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import java.util.*

/**
 * Message Config
 *
 * @author treetips
 */
@ApplicationScoped
class MessageConfig {

  @Produces
  fun registerResourceBundle(): ResourceBundle =
      ResourceBundle.getBundle("ValidationMessages", Locale.getDefault())
}
