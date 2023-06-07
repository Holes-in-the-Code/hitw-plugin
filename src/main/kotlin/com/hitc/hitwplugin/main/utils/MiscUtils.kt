package com.hitc.hitwplugin.main.utils

import org.bukkit.Location
import org.bukkit.entity.Player

val Player.blockPosition: Location
    get() = this.location.block.location



