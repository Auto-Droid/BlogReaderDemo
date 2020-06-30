package com.sourabhkarkal.blogreaderdemo.repo.storage.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sourabhkarkal.blogreaderdemo.model.MediaDTO
import com.sourabhkarkal.blogreaderdemo.model.UserDTO
import java.util.*

class RoomConverter {

    @TypeConverter
    fun fromMediaDTOString(value: String?): List<MediaDTO?>? {
        val listType =
            object : TypeToken<ArrayList<MediaDTO?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMediaDTOArrayList(list: List<MediaDTO?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromUserDTOString(value: String?): List<UserDTO?>? {
        val listType =
            object : TypeToken<ArrayList<UserDTO?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUserDTOArrayList(list: List<UserDTO?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}