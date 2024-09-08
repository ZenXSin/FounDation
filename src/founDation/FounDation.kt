package founDation

import arc.Events
import founDation.content.Blocks
import founDation.environment.Humes
import founDation.environment.WorldNarrate
import founDation.graphic.ModRenderers
import founDation.world.narrativeLayer.NarrativeLayers
import mindustry.Vars
import mindustry.game.EventType
import mindustry.mod.Mod
import mindustry.mod.Mods.LoadedMod

class FounDation : Mod(){

    override fun loadContent() {
        MOD = Vars.mods.getMod(javaClass)

        super.loadContent()
        Blocks.load()
        Humes
        WorldNarrate.load()
        NarrativeLayers.load()
    }

    companion object {
        var MOD: LoadedMod? = null
    }
}