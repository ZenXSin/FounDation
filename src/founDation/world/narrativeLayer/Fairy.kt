package founDation.world.narrativeLayer

import arc.Events
import arc.math.Mathf
import arc.util.Time
import founDation.environment.WorldNarrate
import founDation.graphic.ModRenderers
import mindustry.game.EventType

object Fairy:NarrativeLayer("Fairy") {
    override fun begin() {
        ModRenderers.contrast.increase = 5 - (WorldNarrate.narrate?.narrativeStability ?: 0f)
    }

    override fun end() {
        ModRenderers.contrast.increase = 1f
    }
     override fun load() {
         Events.on(EventType.Trigger.draw::class.java) {
             //ModRenderers.contrast.advancedDraw()
         }
     }
}