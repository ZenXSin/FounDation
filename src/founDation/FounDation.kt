package founDation

import founDation.content.Blocks
import founDation.environment.WorldHume
import founDation.environment.WorldNarrate
import founDation.world.narrativeLayer.NarrativeLayers
import mindustry.Vars
import mindustry.mod.Mod
import mindustry.mod.Mods.LoadedMod

class FounDation : Mod(){

    override fun loadContent() {
        MOD = Vars.mods.getMod(javaClass)

        super.loadContent()
        Blocks.load()
        WorldHume.load()
        WorldNarrate.load()
        NarrativeLayers.load()
    }

    companion object {
        var MOD: LoadedMod? = null
    }
}