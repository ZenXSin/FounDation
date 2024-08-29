package founDation.environment

import arc.Events
import mindustry.Vars
import mindustry.game.EventType

object WorldHume {
    var benchmarkHume = 100f
    private var invalid = false
    private val humeTile: MutableMap<Pair<Int, Int>, Hume> = mutableMapOf()
    fun load() {
        Events.on(EventType.SaveLoadEvent::class.java) {
            Vars.state.map.let {
                try {
                    if (it.tags["hasHumeTile"].toBoolean()) return@on
                } catch (_: Exception) {
                }
                repeat(it.width) { x ->
                    repeat(it.height) { y ->
                        Hume(x, y).load()
                    }
                }
            }
        }
        Events.on(EventType.SaveWriteEvent::class.java) {
            Vars.state.map.tags.let {
                it.put("hasHumeTile", "true")
                humeTile.forEach { (p, h) ->
                    it.put("hasHumeTile", "true")

                }
            }
        }
        Events.run(EventType.Trigger.update) {
            humeTile.forEach { (p,h) ->

            }
        }
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
    class Hume(val x: Int, val y: Int) {
        var humeIndex = benchmarkHume
        var endHumeIndex = benchmarkHume
        fun load() {
            humeTile.plus((x to y) to this)
        }

        override fun toString(): String {
            return "$x $y $humeIndex $endHumeIndex"
        }
    }

    fun toHume(s: String): Hume {
        val tag = s.split(" ")
        val hume = Hume(tag[0].toInt(), tag[1].toInt())
        hume.humeIndex = tag[2].toFloat()
        hume.endHumeIndex = tag[3].toFloat()
        return hume
    }
    fun toPair(s:String):Pair<Int,Int> {
        val tag = s.split(", ").map { it.toInt() }
        return tag[0] to tag[1]
    }
    /**
     * false: f<=,true: f>=
     * */
    fun getHumeTile(f: Float, b: Boolean): Map<Pair<Int, Int>, Hume> {
        return humeTile.filter { (_, h) -> if (b) h.humeIndex <= f else h.humeIndex >= f }
    }

    fun setTileHumeIndex(x: Int, y: Int, index: Float) {
        humeTile[x to y]?.humeIndex = index
    }

    fun getTileHume(x: Int, y: Int) = humeTile[x to y]
    class HumeN :
        RuntimeException("Your world i Is dbfr a origjois oaincuiadholanIfoiudushbfuioancoalnb askhjfeiuyroawsGUBCJibiJjnNJljnJJJKkjkJnd")
}