package com.robosoft.passwordmanagermobile.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetails(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "Mobile_Number") val mobileNumber: String,
    @ColumnInfo(name = "MPin") val mPin: String
)
