package org.dino.test.service

import org.bukkit.entity.Player
import org.dino.test.manager.CooldownManager

class SeedService(
    private val cooldownManager: CooldownManager
) {
    fun getSeed(player: Player): String {
        val cooldownKey = "seed"
        if (cooldownManager.isOnCooldown(player.uniqueId, cooldownKey)) {
            val remaining = cooldownManager.getCooldown(player.uniqueId, cooldownKey)
            return "cooldown:$remaining"
        }
        cooldownManager.addCooldown(player.uniqueId, cooldownKey, 30)
        return player.world.seed.toString();
    }
}