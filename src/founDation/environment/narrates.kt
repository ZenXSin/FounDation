package founDation.environment

import arc.Core
import arc.graphics.Color
import arc.graphics.gl.Shader
import arc.struct.ObjectMap
import founDation.type.Narrative
import mindustry.Vars
import mindustry.entities.Effect
import mindustry.graphics.Drawf
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty1

object Narrates {
    val map: ObjectMap<String, Narrative> = ObjectMap()

    init {
        Narrates::class.members
            .filterIsInstance<KProperty1<Narrates, Narrative>>()
            .forEach {
                map.put(it.name, it.get(Narrates))
            }
    }

    val reality = Narrative("reality", {}) {}
    val fairy = Narrative("fairy", {}) {}
    val dream = Narrative("dream", {}) {}

    val error = Narrative("Error", {}) {}

    fun findNarrativeByName(name: String): Narrative =
        if (map.containsKey(name)) map[name] else error
}
