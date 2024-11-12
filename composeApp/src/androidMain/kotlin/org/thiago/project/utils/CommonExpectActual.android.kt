package org.thiago.project.utils

import java.util.UUID

actual fun getType(): Type {
   return Type.Mobile
}

actual fun getRandomId(): String {
   return UUID.randomUUID().toString()
}