package founDation.world.narrativeLayer

object NarrativeLayers {
    val narrativeLayers: MutableMap<String,NarrativeLayer> = mutableMapOf()
    fun load() {
        narrativeLayers.plus("Reality" to Reality)
        Reality.load()
        narrativeLayers.plus("Fairy" to Fairy)
        Fairy.load()
    }
}