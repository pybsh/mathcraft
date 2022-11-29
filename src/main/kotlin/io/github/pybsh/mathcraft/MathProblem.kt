package io.github.pybsh.mathcraft

import io.github.pybsh.mathcraft.MathcraftPlugin.Companion.problems
import org.bukkit.entity.LivingEntity
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class MathProblem(var entity: LivingEntity, var problem: String, var answer: Int){
    fun generate(){
        val _1 = Random.nextInt(1,10)
        val _2 = Random.nextInt(1,10)
        val operator = Random.nextInt(3)

        when {
            operator == 0 -> {
                this.problem = "$_1 + $_2"
                this.answer = _1 + _2
            }
            operator == 1 -> {
                val max = max(_1, _2)
                val min = min(_1, _2)

                this.problem = "$max - $min"
                this.answer = max - min
            }
            operator == 2 -> {
                this.problem = "$_1 × $_2"
                this.answer = _1 * _2
            }
        }

        if(problems.any{it.answer == this.answer}) generate()
    }
}