package edu.miu.cvbuilderapp.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.adapter.LocalDateAdapter
import java.time.LocalDate

class GsonUtil {

    companion object{
        val gson: Gson = GsonBuilder().registerTypeAdapter(
            LocalDate::class.java,
            LocalDateAdapter()
        ).create()

        inline fun <reified T> fromJson(json: String) = gson.fromJson<T>(json, object: TypeToken<T>() {}.type)
        fun toJson(objectJson: Any): String = gson.toJson(objectJson)
    }
}