package io.github.pybsh.mathcraft

import io.github.monun.kommand.kommand
import io.github.pybsh.mathcraft.MathcraftCommand.register
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author pybsh
 */

class MathcraftPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: MathcraftPlugin
            private set
        val problems: ArrayList<MathProblem> = arrayListOf()
    }

    override fun onEnable() {
        instance = this

        server.pluginManager.registerEvents(MathListener(), this)
        kommand {
            register("math") {
                register(this)
            }
        }
    }
}
