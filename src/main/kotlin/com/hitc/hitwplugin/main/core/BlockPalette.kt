package com.hitc.hitwplugin.main.core

import org.bukkit.Material
import java.util.*

data class BlockPalette(
    var blockData: HashMap<WallBlockType, Byte>,
    var blocks: HashMap<WallBlockType, Material>
)
