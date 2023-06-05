package com.mdq.mdayangcomovies.data.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mdq.mdayangcomovies.data.model.Rating
import java.lang.reflect.Type

class RatingConverter {
    @TypeConverter
    fun fromList(list: List<Rating>?): String?{
        if (list == null)   return null
        val gson = Gson()
        val type: Type = object : TypeToken<List<Rating>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toList(str: String?): List<Rating>?{
        if (str == null) {
            return arrayListOf()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Rating>?>() {}.type
        return gson.fromJson(str, type)
    }
}