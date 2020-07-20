package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.UnsupportedOperationException
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * The collection of settings for the application, stored as (k, v) pairs for all settings (e.g.
 * (allow_notifications, true) and so on).
 */
@Entity(tableName = "settings_table")
data class Setting(
    @PrimaryKey
    @ColumnInfo(name = "field_name")
    var field: String,

    @ColumnInfo(name = "entry_type")
    var dataType: Int = -1,

    @ColumnInfo(name = "entry_data")
    var data: String
)

/**
 * Converts the unknown data to its correct type and returns and 'Any' data type, to be used at
 * runtime.
 *
 * @param setting   The setting object being checked
 * @return the data in correct form
 */
fun getData(setting: Setting): Any {
    return when(setting.dataType) {
        0 -> {
            // String
            setting.data
        }
        1 -> {
            // Integer
            setting.data.toInt()
        }
        2 -> {
            // Boolean
            when(setting.data) {
                "0" -> false
                else -> true
            }
        }
        else -> throw UnsupportedOperationException("Unknown stored type")
    }
}