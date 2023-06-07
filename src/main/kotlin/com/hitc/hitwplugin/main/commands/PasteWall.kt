package com.hitc.hitwplugin.main.commands

import com.hitc.hitwplugin.main.Main
import com.hitc.hitwplugin.main.core.WallBlockType
import com.hitc.hitwplugin.main.utils.MapFileUtils
import com.hitc.hitwplugin.main.utils.blockPosition
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.material.Lever
import org.bukkit.material.Stairs

class PasteWall : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {
        val p = sender as? Player ?: return true
        val wallName = args?.get(0) ?: run {
            p.sendMessage("§cInvalid name given.")
            return true
        }
        if (!MapFileUtils.checkMap(wallName)) {
            p.sendMessage("§cInvalid name given.")
            return true
        }
        val map = MapFileUtils.load(wallName)

        map.blocks.forEach { (x,y) -> pasteBlock(x,y,p) }
        map.facings.forEach { (x,y) -> pasteData(x,y,p) }

        return true
    }

    private fun pasteBlock(location : Location, blockType : WallBlockType, p : Player) {
        location.add(p.blockPosition)
        val block = Main.defaultBlockPalette.blocks[blockType]
        val blockData = Main.defaultBlockPalette.blockData[blockType]
        var currentBlock: Block = p.world.getBlockAt(location)

        currentBlock.setType(block, false)
        currentBlock.data = blockData ?: 0x0
    }

    private fun pasteData(location : Location, facing : BlockFace, p : Player) {
        location.add(p.blockPosition)
        var blockState = p.world.getBlockAt(location).state
        var data = blockState.data
        if (data is Lever) {
            data.data = 0x5
            data.setFacingDirection(facing)
        }
        else if (data is Stairs) {
            data.setFacingDirection(facing.oppositeFace)
        }
        blockState.update(true)
    }


}