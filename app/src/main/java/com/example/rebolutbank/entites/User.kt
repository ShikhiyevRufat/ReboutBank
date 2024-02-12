package com.example.rebolutbank.entites

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity
@Parcelize
data class User (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo("name") var name: String,
    @ColumnInfo("lastname") var lastname: String,
    @ColumnInfo("birthdate") var birthdate: Int,
    @ColumnInfo("country") var country: String,
    @ColumnInfo("passcode") var passcode: Int,
): Parcelable{

}