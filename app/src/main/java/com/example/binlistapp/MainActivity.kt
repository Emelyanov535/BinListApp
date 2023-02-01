package com.example.binlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binlistapp.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = CardAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    fun init(){
        binding.rvList.layoutManager = GridLayoutManager(this@MainActivity,1)
        binding.rvList.adapter = adapter
        binding.buttonConfirm.setOnClickListener{

            val url = "https://lookup.binlist.net/${binding.etBIN.text}"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
                url,
                {response ->
                    val obj = JSONObject(response)
                    val card = Card("SCHEME / NETWORK: ${obj.getString("scheme")} \n",
                        "BRAND: ${obj.getString("brand")} \n",
                        "TYPE: ${obj.getString("type")} \n",
                        "PREPAID: ${obj.getString("prepaid")} \n",
                        "COUNTRY: ${obj.getJSONObject("country").getString("name")} \n",
                        "BANK: ${obj.getJSONObject("bank").getString("name")} \n"
                    )
                    adapter.addCard(card)
                    Log.d("MyLog",card.output())
                },
                {

                }
            )
            queue.add(stringRequest)
        }
    }

//    fun GetResult(bin: String){
//
//        val url = "https://lookup.binlist.net/$bin"
//        val queue = Volley.newRequestQueue(this)
//        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
//            url,
//            {response ->
//                var resultInfo = ""
//                val obj = JSONObject(response)
//
//                if(obj.has("scheme")){
//                    resultInfo += "SCHEME / NETWORK: ${obj.getString("scheme")} \n"
//                }
//                if(obj.has("brand")){
//                    resultInfo += "BRAND: ${obj.getString("brand")} \n"
//                }
//                if(obj.has("type")){
//                    resultInfo += "TYPE: ${obj.getString("type")} \n"
//                }
//                if(obj.has("prepaid")){
//                    resultInfo += "PREPAID: ${obj.getString("prepaid")} \n"
//                }
//                if(obj.has("country")){
//                    resultInfo += "COUNTRY: ${obj.getJSONObject("country").getString("name")} \n"
//                }
//                if(obj.has("bank")){
//                    resultInfo += "BANK: ${obj.getJSONObject("bank").getString("name")} \n"
//                }
////                binding.tvInfo.text = resultInfo
//                val card = Card("SCHEME / NETWORK: ${obj.getString("scheme")} \n",
//                    "BRAND: ${obj.getString("brand")} \n",
//                    "TYPE: ${obj.getString("type")} \n",
//                    "PREPAID: ${obj.getString("prepaid")} \n",
//                    "COUNTRY: ${obj.getJSONObject("country").getString("name")} \n",
//                    "BANK: ${obj.getJSONObject("bank").getString("name")} \n"
//                )
//            },
//            {
////                binding.tvInfo.text = "Такой карты нет!"
//            }
//        )
//        queue.add(stringRequest)
//    }
}




