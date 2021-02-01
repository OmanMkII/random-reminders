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

    @ColumnInfo(name = "setting_bool")
    var dataType: Boolean,

    @ColumnInfo(name = "setting_string")
    var data: String
)