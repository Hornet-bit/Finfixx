package by.birukov.finfixx.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FanficModel(
    @SerializedName("date")
    @Expose
    var date:String,

    @SerializedName("title")
    @Expose
    var title:String,

    @SerializedName("author")
    @Expose
    var author:String,

    @SerializedName("fandom")
    @Expose
    var fandom:String,

    @SerializedName("description")
    @Expose
    var description:String
)
