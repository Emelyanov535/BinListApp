package com.example.binlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binlistapp.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonConfirm.setOnClickListener{
            GetResult(binding.etBIN.text.toString())
        }

    }

    fun GetResult(bin: String){
        val url = "https://lookup.binlist.net/$bin"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
            url,
            {response ->
                var resultInfo = ""
                val obj = JSONObject(response)

                if(obj.has("scheme")){
                    resultInfo += "SCHEME / NETWORK: ${obj.getString("scheme")} \n"
                }
                if(obj.has("brand")){
                    resultInfo += "BRAND: ${obj.getString("brand")} \n"
                }
                if(obj.has("type")){
                    resultInfo += "TYPE: ${obj.getString("type")} \n"
                }
                if(obj.has("prepaid")){
                    resultInfo += "PREPAID: ${obj.getString("prepaid")} \n"
                }
                if(obj.has("country")){
                    resultInfo += "COUNTRY: ${obj.getJSONObject("country").getString("name")} \n"
                }
                if(obj.has("bank")){
                    resultInfo += "BANK: ${obj.getJSONObject("bank").getString("name")} \n"
                }
                binding.tvInfo.text = resultInfo
            },
            {
                binding.tvInfo.text = "Такой карты нет!"
            }
        )
        queue.add(stringRequest)
    }
}


