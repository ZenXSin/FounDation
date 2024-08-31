package founDation.world.narrativeLayer

import arc.Core

abstract class NarrativeLayer(val name: String) {
    var localName = ""

    init {
        localName = Core.bundle.get("NarrativeLayer.$name")
    }

    open fun load() {}
    open fun begin() {}
    open fun end() {}
}