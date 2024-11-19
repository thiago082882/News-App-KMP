package org.thiago.project.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.thiago.project.utils.dataStoreFileName
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun dataStorePreferences(): DataStore<Preferences> {
    return createDataStoreWithDefaults(
        path = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        })
}

