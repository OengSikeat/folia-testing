package org.dino.test.commands

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.dino.test.service.FoodService


class FoodCommand(
    private val foodService: FoodService
) : CommandExecutor {

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

        when (val result = foodService.giveFood(player)) {

            "inventory_full" -> {
                player.sendMessage(
                    MiniMessage.miniMessage().deserialize("<red>Your inventory is full!</red>")
                )
            }

            is String -> {
                if (result.startsWith("cooldown:")) {
                    val time = result.removePrefix("cooldown:")
                    player.sendMessage(
                        MiniMessage.miniMessage().deserialize(
                            "<red>You must wait ${time}s before using /food again.</red>"
                        )
                    )
                } else {
                    player.sendMessage("You got food!")
                }
            }
        }

        return true
    }
}
