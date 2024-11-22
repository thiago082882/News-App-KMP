package org.thiago.project.di


import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.datastore.dataStorePreferences
import org.thiago.project.utils.AppPreferences
import org.thiago.project.utils.getDatabaseBuilder

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}