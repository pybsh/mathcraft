package io.github.pybsh.mathcraft

import io.github.pybsh.mathcraft.MathcraftPlugin.Companion.problems
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.entity.SpectralArrow
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MathListener : Listener{
    var f = true
    @EventHandler
    fun AsyncChatEvent.onChat(){
        val scheduler = MathcraftPlugin.instance.server.scheduler
        if((message() as TextComponent).content().all { c -> c.isDigit() }){
            val correct = problems.filter { p -> p.answer == Integer.parseInt((message() as TextComponent).content()) }
            correct.forEach {
                f = false
                scheduler.scheduleSyncDelayedTask(
                    MathcraftPlugin.instance,
                    Runnable { it.entity.damage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.value, player) },
                0L)

                val problem = MathProblem(it.entity, "loading", 0)
                problem.generate()
                it.entity.customName(Component.text(problem.problem))
                problems.add(problem)
                problems.remove(it)

                isCancelled = true
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 70.0f, 1.0f)
            }
        }
    }

    @EventHandler
    fun EntityDamageByEntityEvent.onDamage(){
        if(f){
            if(damager is Player ||
                (damager is Arrow && (damager as Arrow).shooter is Player) ||
                (damager is SpectralArrow && (damager as SpectralArrow).shooter is Player) ) isCancelled = true
        }
        else { f = true }
    }
}