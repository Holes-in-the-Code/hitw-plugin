package com.hitc.hitwplugin.main

import com.hitc.hitwplugin.main.commands.PasteWall
import com.hitc.hitwplugin.main.core.BlockPalette
import com.hitc.hitwplugin.main.core.WallBlockType
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.TreeSpecies
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.material.MaterialData
import org.bukkit.material.Tree
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Files
import kotlin.collections.HashMap
import kotlin.io.path.Path

class Main: JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        registerCommands()
        Files.createDirectories(Path("./plugins/HitW"))
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent?) {
        event?.player?.sendMessage("Boop!")
    }

    private fun registerCommands() {
        getCommand("pastewall").executor = PasteWall()

    }

}

object MainObject {
    private val paletteData = HashMap<WallBlockType,Byte>()
    private val palette = HashMap<WallBlockType,Material>()
    init {
        paletteData[WallBlockType.WALL] = DyeColor.WHITE.data
        paletteData[WallBlockType.WALL2] = DyeColor.WHITE.data
        paletteData[WallBlockType.WALL_UNUSED] = DyeColor.WHITE.data
        paletteData[WallBlockType.SUPPORT_START] = 0x0
        paletteData[WallBlockType.SUPPORT_END] = DyeColor.WHITE.data
        paletteData[WallBlockType.SUPPORT] = 0x0
        paletteData[WallBlockType.LEVER] = 0x5
        paletteData[WallBlockType.WOOD1] = TreeSpecies.GENERIC.data // oak
        paletteData[WallBlockType.WOOD2] = TreeSpecies.REDWOOD.data // spruce
        paletteData[WallBlockType.WOOD_STAIR] = 0x0
        paletteData[WallBlockType.GLASS] = DyeColor.WHITE.data
        paletteData[WallBlockType.CARPET] = DyeColor.WHITE.data

        palette[WallBlockType.WALL] = Material.STAINED_CLAY
        palette[WallBlockType.WALL2] = Material.STAINED_CLAY
        palette[WallBlockType.WALL_UNUSED] = Material.STAINED_CLAY
        palette[WallBlockType.SUPPORT_START] = Material.COBBLE_WALL
        palette[WallBlockType.SUPPORT_END] = Material.STAINED_GLASS
        palette[WallBlockType.SUPPORT] = Material.COBBLE_WALL
        palette[WallBlockType.LEVER] = Material.LEVER
        palette[WallBlockType.WOOD1] = Material.WOOD
        palette[WallBlockType.WOOD2] = Material.WOOD
        palette[WallBlockType.WOOD_STAIR] = Material.SPRUCE_WOOD_STAIRS
        palette[WallBlockType.GLASS] = Material.STAINED_GLASS
        palette[WallBlockType.CARPET] = Material.CARPET

    }
    val defaultBlockPalette = BlockPalette(paletteData, palette)
}