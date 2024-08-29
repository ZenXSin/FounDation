package founDation

import arc.Core
import arc.util.Log
import founDation.blocks.Blocks
import founDation.environment.WorldHume
import mindustry.Vars
import mindustry.mod.Mod
import mindustry.mod.Mods.LoadedMod

class FounDation : Mod(){

    override fun loadContent() {
        MOD = Vars.mods.getMod(javaClass)

        super.loadContent()
        Blocks.load()
        WorldHume.load()
    }

    companion object {
        var MOD: LoadedMod? = null
    }
}