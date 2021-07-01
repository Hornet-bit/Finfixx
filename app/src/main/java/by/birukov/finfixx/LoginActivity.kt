package by.birukov.finfixx

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.birukov.finfixx.api.ApiService
import by.birukov.finfixx.api.ApiServiceImpl
import by.birukov.finfixx.api.SessionManager
import by.birukov.finfixx.constants.Constants
import by.birukov.finfixx.models.UserLoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var username: TextView
    lateinit var password: TextView

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.b_logination)
        username = findViewById(R.id.et_login_username)
        password = findViewById(R.id.et_login_password)
        apiClient = ApiServiceImpl()

        sessionManager = SessionManager(this)
        loginButton.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val jsonPlaceHolder = retrofit.create(ApiService::class.java)

            val call = jsonPlaceHolder.login(
                UserLoginModel(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            )

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("TAG", response?.code().toString())

                    val token = response?.headers()[Constants.HEADER_TOKEN]

                    if (response.code() == 200) {
                        //todo вставлять не в сессию, а в каждый запрос

                        sessionManager.saveAuthToken(token.toString())
                        Toast.makeText(baseContext, "login was successful ", Toast.LENGTH_SHORT).show()
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra(Constants.HEADER_TOKEN,token)
                        intent.putExtra("user",username.text.toString())
                        startActivity(intent)
                    } else {
                        Log.d("STATUS ERROR", response.code().toString() )
                        Toast.makeText(baseContext, "Something's wrong ", Toast.LENGTH_LONG).show()
                    }
                    Log.d("BODY_TOKEN", token.toString())

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("TAG", t.toString())

                }

            })
        }
    }

    fun printPoint(id : Int){
        Toast.makeText(baseContext, id, Toast.LENGTH_LONG).show()
    }
}

