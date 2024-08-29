package founDation.environment

import arc.Core
import arc.Events
import mindustry.Vars
import mindustry.core.GameState
import mindustry.game.EventType
import mindustry.game.EventType.Trigger
import mindustry.gen.Groups
import kotlin.random.Random

object WorldHume {
    var benchmarkHume = 100f
    var humeIndex = benchmarkHume
    var endHumeIndex = benchmarkHume
    private var invalid = false
    fun load() {
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
    }

    class HumeN :
        RuntimeException("Your world i Is dbfr a origjois oaincuiadholanIfoiudushbfuioancoalnb askhjfeiuyroawsGUBCJibiJjnNJljnJJJKkjkJnd")
}