package founDation.type

import arc.Core
import arc.math.Mathf
import arc.scene.style.TextureRegionDrawable
import arc.scene.ui.layout.Table
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
import mindustry.content.UnitTypes
import mindustry.entities.Damage
import mindustry.game.Team
import mindustry.gen.Tex
import mindustry.graphics.Pal
import mindustry.ui.Styles
import mindustry.world.blocks.payloads.BuildPayload
import mindustry.world.blocks.storage.CoreBlock

open class HumeCore(name: String) : CoreBlock(name) {
    var turretblock = Blocks.spectre
    init {
        update = true
        solid = true
        configurable = true
        unitType = UnitTypes.quad
    }

    open inner class HumeCoreBuild : CoreBuild() {
        var turret:BuildPayload = BuildPayload(turretblock, Team.derelict)
        private var narrative: NowNarrative = NowNarrative()
        override fun updateTile() {
            super.updateTile()
            if (turret.build.team != team) turret.build.team = team
            turret.update(null, this)
            if (turret.build.acceptLiquid(this, Liquids.cryofluid)) {
                turret.build.handleLiquid(this, Liquids.cryofluid,1f)
            }
            turret.set(x, y, payloadRotation)
        }

        override fun draw() {
            super.draw()
            ModRenderers.contrast.increase = Mathf.sin(Time.time / 15f) * 0.5f + 0.5f
            turret.draw()
        }

        override fun handleDamage(amount: Float): Float {
            return Damage.applyArmor(
                ((amount * 10).coerceAtMost(amount + block.armor) / 2),
                block.armor
            )
        }

        override fun buildConfiguration(table: Table?) {
            super.buildConfiguration(table)
            table?.let {
                it.table(Styles.black6) { bt ->
                    bt.add(WorldHume.humeIndex.toString()).row()
                    bt.button("set") {
                        WorldHume.humeIndex = 70f
                    }.row()
                }
            }
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