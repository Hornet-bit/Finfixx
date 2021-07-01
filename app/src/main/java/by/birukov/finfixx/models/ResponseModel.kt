package by.birukov.finfixx.models

import com.google.gson.annotations.SerializedName
//import com.squareup.moshi.Json

data class ResponseModel(
//    @Json
//    @SerializedName("status_code")
//    var statusCode: Int,

    @SerializedName("auth_token")
    var authToken: String,

//    @SerializedName("user")
//    var user: UserLoginModel
)
