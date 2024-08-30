package founDation.type

import arc.graphics.Color
import arc.scene.ui.layout.Table
import arc.struct.Seq
import mindustry.Vars
import mindustry.content.Blocks.turrets[0]
import mindustry.content.Liquids
import mindustry.game.Team
import mindustry.graphics.Drawf
import mindustry.world.Block
import mindustry.world.blocks.ItemSelection
import mindustry.world.blocks.defense.turrets.Turret
import mindustry.world.blocks.payloads.BuildPayload
import mindustry.world.blocks.storage.CoreBlock

open class TurretCoreBlock(name: String) : CoreBlock(name) {
    var turrets: Seq<Block> = Seq()
    var turretSize = size

    init {
        configurable = true
        update = true
        solid = true
        config(Block::class.java) { build: TurretCoreBuild, turret: Block? ->
            build.turretPayload = BuildPayload(turret ?: turrets[0], Team.derelict)
        }
        configClear { build: TurretCoreBuild -> build.nowTurret = turrets[0] }
    }

    open inner class TurretCoreBuild : CoreBuild() {
        var nowTurret: Block = turrets[0]
        var turretPayload = BuildPayload(nowTurret, Team.derelict)
        override fun buildConfiguration(table: Table?) {
            super.buildConfiguration(table)
            setNowTurret(table)
        }

        override fun updateTile() {
            super.updateTile()
            turretPayload.update(null, this)
            if (turretPayload.block() != nowTurret) turretPayload = BuildPayload(nowTurret, turretPayload.build.team)
            if (turretPayload.build.team != team) turretPayload.build.team = team
            if (turretPayload.build.acceptLiquid(this, Liquids.cryofluid)) turretPayload.build.handleLiquid(this, Liquids.cryofluid,5f)
            items.each { item, amount ->
                if (amount > 0 && turretPayload.build.acceptItem(this, item)) {
                    turretPayload.build.handleItem(this,item)
                    items.remove(item,1)
                }
            }
            turretPayload.set(x,y,turretPayload.build.payloadRotation)
        }

        override fun drawSelect() {
            super.drawSelect()
            Drawf.dashCircle(x,y,(nowTurret as Turret).range, Color.white)
        }

        override fun draw() {
            super.draw()
            turretPayload.build.draw()
        }
        
        fun setNowTurret(table: Table?) {
            ItemSelection.buildTable(
                this.block, table, if (turrets.isEmpty) Vars.content.blocks().select { b: Block? ->
                    b != null && b is Turret && b.size <= size
                } else turrets,
                { nowTurret },
                { value: Block? -> nowTurret = value ?: turrets[0] }, 5, 4
            )
        }
    }
}
