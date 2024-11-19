package org.thiago.project.utils

import android.content.Intent
import org.thiago.project.getActivity
import org.thiago.project.getContext
import java.util.UUID

actual fun getType(): Type {
   return Type.Mobile
}

actual fun getRandomId(): String {
   return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
   val sendIntent = Intent(Intent.ACTION_SEND).apply {
      putExtra(Intent.EXTRA_TEXT, url)
      type = "text/plain"
   }
   val shareIntent = Intent.createChooser(sendIntent, "Share Link")
   getContext()?.let {
      getActivity()?.startActivity( shareIntent)
   }
}