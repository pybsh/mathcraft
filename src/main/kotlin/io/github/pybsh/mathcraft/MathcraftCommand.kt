package io.github.pybsh.mathcraft

import io.github.monun.kommand.node.LiteralNode
import io.github.pybsh.mathcraft.MathcraftPlugin.Companion.problems
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 * @author pybsh
 */

object MathcraftCommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            then("about") {
                executes { sender.sendMessage("sample by pybsh.") }

            }
            then("start"){
                executes {
                    val server = MathcraftPlugin.instance.server
                    val scheduler = server.scheduler

                    scheduler.runTaskTimer(
                        MathcraftPlugin.instance,
                        Runnable {
                            server.onlinePlayers.forEach {
                                it.getNearbyEntities(10.0, 10.0, 10.0).forEach { e ->
                                    if(e is LivingEntity && e !is Player && !e.isCustomNameVisible){
                                        val problem = MathProblem(e, "loading", 0)
                                        problem.generate()
                                        e.customName(text(problem.problem))
                                        e.isCustomNameVisible = true

                                        problems.add(problem)
                                    }
                                }
                            }
                        },
                        0L, 0L
                    )
                }
            }
            then("stop") {
                executes {
                    MathcraftPlugin.instance.server.scheduler.cancelTasks(MathcraftPlugin.instance)
                }
            }
        }
    }
}
