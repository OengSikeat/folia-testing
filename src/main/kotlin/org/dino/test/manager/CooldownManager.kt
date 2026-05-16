package org.dino.test.manager

import java.util.UUID

class CooldownManager {
    private val cooldowns: MutableMap<UUID, MutableMap<String, Int>> = HashMap()

    fun addCooldown(uuid: UUID, cooldownName: String, cooldownSeconds: Long) {
        val perPlayer = cooldowns.getOrPut(uuid) { HashMap() }
        perPlayer[cooldownName] = (System.currentTimeMillis() / 1000L + cooldownSeconds).toInt()
    }

    fun isOnCooldown(uuid: UUID, cooldownName: String): Boolean {
        val perPlayer = cooldowns[uuid] ?: return false
        val endsAt = perPlayer[cooldownName] ?: return false
        return endsAt > (System.currentTimeMillis() / 1000L).toInt()
    }

    fun getCooldown(uuid: UUID, cooldownName: String): Int {
        val perPlayer = cooldowns[uuid] ?: return 0
        val endsAt = perPlayer[cooldownName] ?: return 0
        return endsAt - (System.currentTimeMillis() / 1000L).toInt()
    }

    fun removeCooldown(uuid: UUID, cooldownName: String) {
        val perPlayer = cooldowns[uuid] ?: return
        perPlayer.remove(cooldownName)
        if (perPlayer.isEmpty()) {
            cooldowns.remove(uuid)
        }
    }

    fun removeCooldowns(uuid: UUID) {
        cooldowns.remove(uuid)
    }
}

