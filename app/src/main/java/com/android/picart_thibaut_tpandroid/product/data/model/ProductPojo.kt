package com.android.picart_thibaut_tpandroid.product.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

/** Object use for room */
@Entity(tableName = "product")
data class ProductRoom(
    @ColumnInfo(name = "product_text")
    val title: String,

    @ColumnInfo(name = "product_icon_url")
    val iconUrl: String,

    @ColumnInfo(name = "product_id")
    val _id: Int,

    @ColumnInfo(name = "product_type")
    val type: String
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
    val id: String,

    @Expose
    @SerializedName("types")
    @JsonAdapter(SpriteDeserializer::class)
    val type: String
)

class SpriteDeserializer : JsonDeserializer<String?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): String {
        //return json.asJsonObject["other"].asJsonObject["official-artwork"].asJsonObject["front_default"].asString
        return json.asJsonArray[0].asJsonObject["type"].asJsonObject["name"].asString
    }
}