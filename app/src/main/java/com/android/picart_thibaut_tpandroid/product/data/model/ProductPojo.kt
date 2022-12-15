package com.android.picart_thibaut_tpandroid.product.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/** Object use for room */
@Entity(tableName = "product")
data class ProductRoom(
    @ColumnInfo(name = "product_text")
    val title: String,

    @ColumnInfo(name = "product_icon_url")
    val iconUrl: String,

    @ColumnInfo(name = "product_id")
    val _id: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for retrofit */
data class ProductRetrofit(
    @Expose
    @SerializedName("name")
    val title: String,

    @Expose
    @SerializedName("id")
    val id: String
)