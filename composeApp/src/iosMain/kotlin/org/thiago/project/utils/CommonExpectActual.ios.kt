package org.thiago.project.utils


import platform.Foundation.NSUUID

actual fun getType(): Type {
   return Type.Mobile
}

actual fun getRandomId(): String {
   return NSUUID().UUIDString()
}