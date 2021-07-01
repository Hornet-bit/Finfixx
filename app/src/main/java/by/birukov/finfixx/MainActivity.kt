package by.birukov.finfixx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.birukov.finfixx.api.ApiService
import by.birukov.finfixx.constants.Constants
import by.birukov.finfixx.models.FanficModel
import by.birukov.finfixx.recyrcle.CustomRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CustomRecyclerAdapter.OnElementClick {

    lateinit var textView: TextView
    lateinit var buttonLogin: MenuItem
    lateinit var buttonRegistration: MenuItem
    lateinit var view: WebView
    lateinit var recyclerView: RecyclerView
    lateinit var newItem: RelativeLayout
    lateinit var fanfics : List<FanficModel>
    lateinit var mElement : CustomRecyclerAdapter.OnElementClick

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
//        newItem = findViewById(R.id.addNewItem)
//        newItem.setOnClickListener{
//            Toast.makeText(this, "нажатие", Toast.LENGTH_SHORT).show()
//        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolder = retrofit.create(ApiService::class.java)

        val call = jsonPlaceHolder.findAllFanfics()

        call.enqueue(object : Callback<List<FanficModel>> {
            override fun onResponse(call: Call<List<FanficModel>>, response: Response<List<FanficModel>>) {
                Log.d("TAG", response?.code().toString())

                val token = response?.headers()[Constants.HEADER_TOKEN]

                if (response.code() == 200) {
//                    Toast.makeText(baseContext, " ", Toast.LENGTH_SHORT).show()
//                    newItem = findViewById(R.id.addNewItem)
//                    recyclerView.adapter = CustomRecyclerAdapter()
                } else {
                    Log.d("STATUS ERROR", response.code().toString() )
                }
                fanfics = response.body()!!//todo переделать
                if (fanfics != null) {
                    recyclerView.adapter = CustomRecyclerAdapter(fanfics)
                    print("I poluchil "+fanfics.get(0).description)
                }
            //                Log.d("First object - ", response.body()?.get(1))
            }

            override fun onFailure(call: Call<List<FanficModel>>, t: Throwable) {
                Log.d("TAG", t.toString())

            }

        })
//        newItem = findViewById(R.id.addNewItem)
//        newItem.setOnClickListener{
//            Toast.makeText(this, "нажатие", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("\$i element") }
        return data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var option = item.itemId

        if (option == R.id.sing_in) {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return true
        }
        if (option == R.id.sing_up) {
//            Toast.makeText(this, "registration", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            return true
        }

        if (option == R.id.settings) {
//            Toast.makeText(this, "registration", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        Toast.makeText(this, option.toString(), Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onClick(position: Int) {
        fanfics.get(position)
        val intent = Intent(baseContext, TextActiviry::class.java)
        startActivity(intent)
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
    }



}