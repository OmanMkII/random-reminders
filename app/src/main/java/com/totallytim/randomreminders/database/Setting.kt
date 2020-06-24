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
        var field: String,

        @ColumnInfo
        var data: String) {

}