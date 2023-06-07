package com.hitc.hitwplugin.main.utils

import com.hitc.hitwplugin.main.core.Map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File


object MapFileUtils {

    fun load(name : String): Map {
        val f = File("./plugins/HitW/$name.json")
        return Json.decodeFromString<Map>(f.readText())
    }

    fun checkMap(name : String) : Boolean {
        val f = File("./plugins/HitW")
        f.listFiles()?.forEach { if (it.name == "$name.json") return true } ?: return false
        return false
    }


}