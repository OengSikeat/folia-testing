package org.dino.test.service

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.dino.test.manager.CooldownManager

class FoodService (
    private val cooldownManager: CooldownManager){

    private val cooldownKey = "food"

    fun giveFood(player: Player): String {

        if (cooldownManager.isOnCooldown(player.uniqueId, cooldownKey)) {
            val remaining = cooldownManager.getCooldown(player.uniqueId, cooldownKey)
            return "cooldown:$remaining"
        }

        if (player.inventory.firstEmpty() == -1) {
            return "inventory_full"
        }
        player.inventory.addItem(ItemStack(Material.BREAD, 10))
        player.inventory.addItem(ItemStack(Material.COPPER_CHESTPLATE))
        player.inventory.addItem(ItemStack(Material.COPPER_HELMET))
        player.inventory.addItem(ItemStack(Material.COPPER_LEGGINGS))
        player.inventory.addItem(ItemStack(Material.COPPER_BOOTS))

        cooldownManager.addCooldown(player.uniqueId, cooldownKey, 10)

        return "success"
    }
}