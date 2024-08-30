package founDation.environment

import arc.Core
import arc.Events
import arc.util.Log
import mindustry.Vars
import mindustry.game.EventType
import kotlin.math.sqrt

object WorldHume {
    var benchmarkHume = 100f
    private var invalid = false
    private var humeTile: MutableMap<Pair<Int, Int>, Hume> = mutableMapOf()
    fun load() {
        Events.on(EventType.SaveLoadEvent::class.java) {
            Log.log(Log.LogLevel.err, "***")
            Vars.state.map.let {
                try {
                    if (it.tags["hasHumeTile"].toBoolean()) {
                        repeat(it.width) { x ->
                            repeat(it.height) { y ->
                                val pair = x to y
                                humeTile += pair to toHume(it.tags[pair.toString()])
                            }
                        }
                    } else {
                        Log.log(Log.LogLevel.err, "1")
                        repeat(it.width) { x ->
                            repeat(it.height) { y ->
                                Hume(x, y).load()
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.log(Log.LogLevel.err, "2***")
                    Log.log(Log.LogLevel.err, e.toString())
                    repeat(it.width) { x ->
                        repeat(it.height) { y ->
                            Hume(x, y).load()
                        }
                    }
                }
            }
        }
        Events.on(EventType.SaveWriteEvent::class.java) {
            Vars.state.map.tags.let {
                Log.log(Log.LogLevel.err, "3")
                it.put("hasHumeTile", "true")
                humeTile.forEach { (p, h) ->
                    it.put(p.toString(), h.toString())
                }
            }
        }
        Events.run(EventType.Trigger.update) {
            if (invalid) {
                Core.app.exit()
                throw HumeN()
            }
            humeTile.forEach { (p, h) ->

            }
        }
    }

    class Hume(val x: Int, val y: Int) {
        var humeIndex = benchmarkHume
        var endHumeIndex = benchmarkHume
        fun load() {
            humeTile += (x to y) to this
        }

        override fun toString(): String {
            return "$x $y $humeIndex $endHumeIndex"
        }

        fun setHume(f: Float) {
            humeIndex = f
        }
    }

    fun toHume(s: String): Hume {
        val tag = s.split(" ")
        val hume = Hume(tag[0].toInt(), tag[1].toInt())
        hume.humeIndex = tag[2].toFloat()
        hume.endHumeIndex = tag[3].toFloat()
        return hume
    }

    fun toPair(s: String): Pair<Int, Int> {
        val tag = s.replace("(", "").replace(")", "").split(", ").map { it.toInt() }
        return tag[0] to tag[1]
    }

    /**
     * false: f<=,true: f>=
     * */
    fun getHumeTile(f: Float, b: Boolean): Map<Pair<Int, Int>, Hume> {
        return humeTile.filter { (_, h) -> if (b) h.humeIndex <= f else h.humeIndex >= f }
    }

    fun setTileHumeIndex(x: Int, y: Int, index: Float) {
        Log.log(Log.LogLevel.err, humeTile[x to y]?.humeIndex.toString() + "*")
        humeTile[x to y]?.setHume(index)
        Log.log(Log.LogLevel.err, humeTile[x to y]?.humeIndex.toString() + "***")
    }

    fun getTileHume(x: Int, y: Int) = humeTile[x to y]

    fun getAverageEndHume(): Float {
        var average = 0f
        humeTile.forEach { average += it.value.endHumeIndex }
        return average / humeTile.size
    }

    fun getAverageHume(list: List<Hume>): Float {
        var average = 0f
        list.forEach { average += it.humeIndex }
        return average / list.size
    }

    fun getAverageHume(x: Int, y: Int, range: Float): Float {
        var average = 0f
        val map = humeTile.filter { distanceBetweenVectors(x, y, it.value.x, it.value.y) <= range }
        map.forEach { average += it.value.humeIndex }
        return average / map.size
    }

    fun getAverageHume(): Float {
        var average = 0f
        humeTile.forEach { average += it.value.humeIndex }
        return average / humeTile.size
    }

    private fun distanceBetweenVectors(x1: Int, y1: Int, x2: Int, y2: Int): Double {
        val deltaX = (x2 - x1).toDouble()
        val deltaY = y2 - y1
        return sqrt(deltaX * deltaX + deltaY * deltaY)
    }

    class HumeN :
        RuntimeException("Your world i Is dbfr a origjois oaincuiadholanIfoiudushbfuioancoalnb askhjfeiuyroawsGUBCJibiJjnNJljnJJJKkjkJnd")
}

/*    fun load() {
        Events.on(EventType.SaveWriteEvent::class.java) {
            Vars.state.map.tags.let {
                it.put("benchmarkHume", it["benchmarkHume"] ?: benchmarkHume.toString())
                it.put("humeIndex", it["humeIndex"] ?: benchmarkHume.toString())
                it.put("endHumeIndex", it["endHumeIndex"] ?: benchmarkHume.toString())
            }
        }
        Events.on(EventType.SaveLoadEvent::class.java) {
            try {
                benchmarkHume = Vars.state.map.tags["benchmarkHume"].toFloat()
                humeIndex = Vars.state.map.tags["humeIndex"].toFloat()
                endHumeIndex = Vars.state.map.tags["endHumeIndex"].toFloat()
            } catch (_: Exception) {
            }

        }
        Events.run(Trigger.update) {
            if (Vars.state.`is`(GameState.State.playing)) {
                if (humeIndex > 100) {
                    humeIndex -= 0.0001f
                } else if (humeIndex < 100) {
                    humeIndex += 0.0001f
                }
                endHumeIndex = humeIndex + (Groups.unit.size() - Groups.build.size()) / 10000
                if (humeIndex in 110f..200f) {
                    Groups.unit.forEach {
                        it.health = (it.health + humeIndex - 100).coerceAtMost(it.maxHealth)
                    }
                    Groups.build.forEach {
                        it.health = (it.health + humeIndex - 100).coerceAtMost(it.maxHealth)
                    }
                } else if (humeIndex >= 200) {
                    invalid = true
                } else if (humeIndex in 20f..80f) {
                    Groups.unit.forEach {
                        if (Random.nextInt(-500, humeIndex.toInt()) == 21) it.killed()
                    }
                    Groups.build.forEach {
                        if (Random.nextInt(-500, humeIndex.toInt()) == 21) it.killed()
                    }
                } else if (humeIndex < 10) {
                    invalid = true
                }
                if (invalid) {
                    Core.app.exit()
                    throw HumeN()
                }
            }
        }
    }*/