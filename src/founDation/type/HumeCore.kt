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
import mindustry.content.UnitTypes
import mindustry.entities.Damage
import mindustry.game.Team
import mindustry.gen.Tex
import mindustry.graphics.Pal
import mindustry.ui.Styles
import mindustry.world.blocks.payloads.BuildPayload
import mindustry.world.blocks.storage.CoreBlock

open class HumeCore(name: String) : CoreBlock(name) {
    init {
        update = true
        solid = true
        configurable = true
        unitType = UnitTypes.quad
    }

    open inner class HumeCoreBuild : CoreBuild() {
        private val turret = BuildPayload(Blocks.spectre, Team.derelict)
        private var narrative: NowNarrative = NowNarrative()
        override fun updateTile() {
            super.updateTile()
            if (turret.build.team != team) turret.build.team = team
            turret.update(null, this)
            if (turret.build.acceptItem(this, Items.thorium) && items.get(Items.thorium) >= 1) {
                turret.build.handleItem(this, Items.thorium)
                items.remove(Items.thorium, 1)
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
                it.f(narrative.narrativeStability?: 0f)
                it.str(narrative.narrativeType?.name ?: "")
            }
        }

        override fun read(read: Reads?, revision: Byte) {
            super.read(read, revision)
            read?.let {
                narrative.narrativeStability = it.f()
                narrative.narrativeType = Narrates.findNarrativeByName(it.str())
            }
        }
    }
}