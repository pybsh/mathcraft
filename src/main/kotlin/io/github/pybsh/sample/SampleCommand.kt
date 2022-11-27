package io.github.pybsh.sample

import io.github.monun.kommand.node.LiteralNode

/**
 * @author pybsh
 */

object SampleCommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            then("about") { executes { sender.sendMessage("sample by pybsh.") } }
        }
    }
}
