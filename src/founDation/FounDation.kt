package founDation

import arc.Events
import founDation.content.Blocks
import founDation.content.UnitTypes
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
        Humes.load()
        UnitTypes.load()
        Blocks.load()
    }

    companion object {
        var MOD: LoadedMod? = null
    }
}