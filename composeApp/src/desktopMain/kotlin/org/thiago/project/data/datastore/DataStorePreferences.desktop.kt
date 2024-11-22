package org.thiago.project.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.thiago.project.utils.dataStoreFileName



actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}