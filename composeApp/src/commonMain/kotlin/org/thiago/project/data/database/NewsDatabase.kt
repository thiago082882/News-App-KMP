package org.thiago.project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.thiago.project.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun newsDao(): NewsDao
    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}