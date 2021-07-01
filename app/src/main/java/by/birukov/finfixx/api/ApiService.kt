package by.birukov.finfixx.api

import by.birukov.finfixx.constants.Constants
import by.birukov.finfixx.models.FanficModel
import by.birukov.finfixx.models.ResponseModel
import by.birukov.finfixx.models.UserLoginModel
import by.birukov.finfixx.models.UserRegModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body request: UserLoginModel): Call<Void>

    @Headers("Content-Type: application/json")
    @POST(Constants.SING_UP_URL)
    fun singUp(@Body request: UserRegModel): Call<Void>

    @Headers("Content-Type: application/json")
    @GET(Constants.GET_FANFICS)
    fun findAllFanfics(): Call<List<FanficModel>>
}