package org.dino.test.service

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class NightVisionService {
    fun toggleNightVision(player: Player): String {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION)
                return "disabled"
            } else {
                player.addPotionEffect(
                    PotionEffect(
                        PotionEffectType.NIGHT_VISION,
                        Int.MAX_VALUE,
                        0,
                        true,
                        false
                    )
                )
                return "enabled"
            }
    }
}