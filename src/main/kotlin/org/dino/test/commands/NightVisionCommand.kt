package org.dino.test.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.dino.test.service.NightVisionService

class NightVisionCommand(private val nightVisionService: NightVisionService): CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val player = sender as? Player ?: run {
            sender.sendMessage("Only players can use this command!")
            return true
        }
        when (nightVisionService.toggleNightVision(player)) {
            "enabled" -> player.sendMessage("Night vision enabled!")
            "disabled" -> player.sendMessage("Night vision disabled!")
        }
        return true
    }
}