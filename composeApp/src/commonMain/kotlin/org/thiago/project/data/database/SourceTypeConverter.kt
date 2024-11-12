package org.thiago.project.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.thiago.project.data.model.Source

class SourceTypeConverter {

    @TypeConverter
    fun fromSourceToString(value: Source): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value: String): Source {
        return Json.decodeFromString(value)
    }

}