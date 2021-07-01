package by.birukov.finfixx.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserLoginModel(

    @SerializedName("username")
    @Expose
    var username:String,

    @SerializedName("password")
    @Expose
    var password:String
)
