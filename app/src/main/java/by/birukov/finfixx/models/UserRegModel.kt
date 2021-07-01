package by.birukov.finfixx.models

import com.google.gson.annotations.SerializedName

data class UserRegModel(

    @SerializedName("email")
    var email: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String

)
