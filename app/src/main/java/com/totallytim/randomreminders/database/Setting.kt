package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    var data: String)