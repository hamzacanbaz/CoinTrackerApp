package com.hamzacanbaz.domain.model.favoriteCoinList

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_coinlist_table")
data class FavoriteCoin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val symbol :String = "",
    val price: String = "",
    val change:String = ""
):Parcelable
