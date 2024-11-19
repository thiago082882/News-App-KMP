package org.thiago.project.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.thiago.project.getContext
import org.thiago.project.utils.dataStoreFileName
import java.io.File

actual fun dataStorePreferences(): DataStore<Preferences> {
    return createDataStoreWithDefaults(
        path = {
            File(getContext()!!.filesDir, "datastore/$dataStoreFileName").path
        })
}