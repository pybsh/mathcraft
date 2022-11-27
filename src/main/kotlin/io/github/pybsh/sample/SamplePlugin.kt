package io.github.pybsh.sample

import io.github.monun.kommand.kommand
import io.github.pybsh.sample.SampleCommand.register
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author pybsh
 */

class SamplePlugin : JavaPlugin() {
    override fun onEnable() {
        kommand {
            register("sample") {
                register(this)
            }
        }
    }
}
