package founDation.type

import arc.Core

class Narrative(val name: String, val begi: (t: Float) -> Unit, val en: (t: Float) -> Unit) {
    fun begin(degree: Float) = begi(degree)
    fun end(degree: Float) = en(degree)
    var localName: String = Core.bundle.get("narrative.$name")
}

class NowNarrative {
    var narrativeStability: Float? = null
    var narrativeType: Narrative? = null
        set(value) {
            if (narrativeType != null) narrativeType?.end(narrativeStability ?: 100f)
            field = value
            if (narrativeType != null) narrativeType?.begin(narrativeStability ?: 100f)
        }
}