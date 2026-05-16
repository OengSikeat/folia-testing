package org.dino.test

import org.bukkit.plugin.java.JavaPlugin
import org.dino.test.commands.FoodCommand
import org.dino.test.commands.NightVisionCommand
import org.dino.test.commands.SeedCommand
import org.dino.test.manager.CooldownManager
import org.dino.test.service.FoodService
import org.dino.test.service.NightVisionService
import org.dino.test.service.SeedService

class Test : JavaPlugin() {
    val cooldownManager = CooldownManager()
    val foodService = FoodService(cooldownManager)
    val nightVisionService = NightVisionService()
    val seedService = SeedService(cooldownManager)

    override fun onEnable() {
        getCommand("food")?.setExecutor(FoodCommand(foodService))
        getCommand("nv")?.setExecutor(NightVisionCommand(nightVisionService))
        getCommand("seed")?.setExecutor(SeedCommand(seedService))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
