package com.robosoft.passwordmanagermobile.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "site_table")
data class SiteDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Int?,
    @ColumnInfo val userPhoneNo: String,
    @ColumnInfo val url: String,
    @ColumnInfo val siteName: String,
    @ColumnInfo val selector: String,
    @ColumnInfo val userName: String,
    @ColumnInfo val sitePassword: String,
    @ColumnInfo val notes: String
)
