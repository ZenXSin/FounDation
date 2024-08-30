package founDation.type

import arc.Core
import arc.math.Mathf
import arc.scene.style.TextureRegionDrawable
import arc.scene.ui.layout.Table
import arc.struct.Seq
import arc.util.Log
import arc.util.Time
import arc.util.io.Reads
import arc.util.io.Writes
import founDation.environment.Narrates
import founDation.environment.WorldHume
import founDation.graphic.ModRenderers
import mindustry.Vars
import mindustry.content.Blocks
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.content.StatusEffects
import mindustry.content.UnitTypes
import mindustry.entities.Damage
import mindustry.entities.Units
import mindustry.entities.units.StatusEntry
import mindustry.game.Team
import mindustry.gen.Groups
import mindustry.gen.Statusc
import mindustry.gen.Tex
import mindustry.graphics.Pal
import mindustry.ui.Styles
import mindustry.world.blocks.payloads.BuildPayload
import mindustry.world.blocks.storage.CoreBlock

open class HumeCore(name: String) : TurretCoreBlock(name) {
    var turretblock = Blocks.spectre
    init {
        unitType = UnitTypes.quad
    }

    open inner class HumeCoreBuild : TurretCoreBuild() {
        private var narrative: NowNarrative = NowNarrative()
        override fun updateTile() {
            super.updateTile()
        }

        override fun draw() {
            super.draw()
            ModRenderers.contrast.increase = Mathf.sin(Time.time / 15f) * 0.5f + 0.5f
        }

        override fun handleDamage(amount: Float): Float {
            return Damage.applyArmor(
                ((amount * 10).coerceAtMost(amount + block.armor) / 2),
                block.armor
            )
        }

        override fun buildConfiguration(table: Table?) {
            table?.table(Styles.black3) { t ->
                t.add("世界平均休谟：${WorldHume.getAverageHume()}\n半径50格平均休谟：${WorldHume.getAverageHume(tileX(),tileY(),50f)}").row()
                t.button("设置核心炮台") {
                    table.clear()
                    setNowTurret(table)
                }.fill(true)
            }?.row()
        }

        override fun write(write: Writes?) {
            super.write(write)
            write?.let {
            }
        }

        override fun read(read: Reads?, revision: Byte) {
            super.read(read, revision)
            read?.let {
            }
        }
    }
}