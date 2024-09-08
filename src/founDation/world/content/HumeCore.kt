package founDation.world.content

import arc.Events
import arc.graphics.g2d.Draw
import arc.math.Mathf
import arc.scene.ui.layout.Table
import arc.util.Time
import arc.util.io.Reads
import arc.util.io.Writes
import founDation.environment.Humes
import founDation.environment.WorldNarrate
import founDation.graphic.ModRenderers
import founDation.graphic.ModShaders
import founDation.type.NowNarrative
import founDation.world.narrativeLayer.Fairy
import founDation.world.narrativeLayer.Reality
import mindustry.content.Blocks
import mindustry.content.UnitTypes
import mindustry.entities.Damage
import mindustry.game.EventType
import mindustry.ui.Styles

open class HumeCore(name: String) : TurretCoreBlock(name) {
    open inner class HumeCoreBuild : TurretCoreBuild() {
        override fun buildConfiguration(table: Table?) {
            table?.table(Styles.black3) { t ->
                t.add("世界平均休谟：${Humes.humeIndex}"/*\n半径50格平均休谟：${WorldHume.getAverageHume(tileX(),tileY(),50f)}"*/).row()
                //t.add("叙事稳定度：${WorldNarrate.narrate?.narrativeStability?.times(100)}%\n所在叙事层：${WorldNarrate.narrate?.narrativeLayer?.localName}").row()
                t.button("设置核心炮台") {
                    table.clear()
                    setNowTurret(table)
                }.fill(true).row()
                t.button("set") {
                    Humes.humeIndex = 50f
                }.row()
                t.button("set2") {
                    Humes.humeIndex = 80f
                }.row()
            }
        }
    }
}