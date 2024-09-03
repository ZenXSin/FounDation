package founDation.environment

import arc.Events
import arc.math.Mathf
import arc.util.Time
import founDation.graphic.ModRenderers
import founDation.world.narrativeLayer.NarrativeLayer
import founDation.world.narrativeLayer.NarrativeLayers
import founDation.world.narrativeLayer.Reality
import mindustry.Vars
import mindustry.game.EventType

object WorldNarrate {
    var narrate: Narrate? = null
    private var inGame = false
    fun load() {
        narrate = Narrate()
    }

    class Narrate {
        var narrativeStability: Float = 1f
        var narrativeLayer: NarrativeLayer = Reality
        fun load() {
            Events.on(EventType.SaveWriteEvent::class.java) {
                inGame = false
                Vars.state.map.tags.let {
                    it.put("hasWorldNarrate", "true")
                    it.put("narrativeStability", narrativeStability.toString())
                    it.put("narrativeLayer", narrativeLayer.name)
                }
            }
            Events.on(EventType.SaveLoadEvent::class.java) {
                inGame = true
                Vars.state.map.tags.let {
                    try {
                        if (it["hasWorldNarrate"].toBoolean()) {
                            narrativeStability = it["narrativeStability"]?.toFloat() ?: 1f
                            narrativeLayer = NarrativeLayers.narrativeLayers[it["narrativeLayer"]] ?: Reality
                        }
                    } catch (_: Exception) {
                    }
                }
            }
        }
    }
}