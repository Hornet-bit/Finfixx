package by.birukov.finfixx

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.birukov.finfixx.api.ApiService
import by.birukov.finfixx.constants.Constants
import by.birukov.finfixx.models.UserLoginModel
import by.birukov.finfixx.models.UserRegModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationActivity : AppCompatActivity() {

    lateinit var regButton: Button
    lateinit var username: TextView
    lateinit var email: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        regButton = findViewById(R.id.b_registration)
        username = findViewById(R.id.et_reg_username)
        email = findViewById(R.id.et_reg_email)
        password = findViewById(R.id.et_reg_password)

        regButton.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val jsonPlaceHolder = retrofit.create(ApiService::class.java)

            val call = jsonPlaceHolder.singUp(
                UserRegModel(
                    email =email.text.toString(),
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            )

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("TAG", response?.code().toString())

                    if (response.code() == 200) {
                        Toast.makeText(baseContext, "Registration was successful ", Toast.LENGTH_SHORT).show()
                        //todo вставлять не в сессию, а в каждый запрос
                        val intent = Intent(baseContext, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("STATUS ERROR", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
//                TODO("Not yet implemented")
                    Log.d("TAG", t.toString())

                }

            })
        }
    }

    fun registration(view:View){
        email.setText("qqqqq")
    }
}