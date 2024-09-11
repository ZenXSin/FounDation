package founDation.world.content

import arc.scene.ui.layout.Table
import mindustry.ui.Styles

open class HumeCore(name: String) : TurretCoreBlock(name) {
    open inner class HumeCoreBuild : TurretCoreBuild() {
        override fun buildConfiguration(table: Table?) {
            /*table?.table(Styles.black3) { t ->
                t.add("世界平均休谟："/*\n半径50格平均休谟：${WorldHume.getAverageHume(tileX(),tileY(),50f)}"*/).row()
                //t.add("叙事稳定度：${WorldNarrate.narrate?.narrativeStability?.times(100)}%\n所在叙事层：${WorldNarrate.narrate?.narrativeLayer?.localName}").row()
                t.button("设置核心炮台") {
                    table.clear()
                    setNowTurret(table)
                }.fill(true).row()
                t.button("set") {
                }.row()
                t.button("set2") {
                }.row()
            }*/
        }
    }
}