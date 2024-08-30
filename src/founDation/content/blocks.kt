package founDation.content

import arc.Core
import arc.struct.Seq
import founDation.world.content.HumeCore
import mindustry.content.Blocks
import mindustry.content.Fx
import mindustry.content.Items
import mindustry.content.StatusEffects
import mindustry.entities.bullet.ContinuousLaserBulletType
import mindustry.gen.Sounds
import mindustry.graphics.Pal
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.Block
import mindustry.world.blocks.defense.turrets.LaserTurret
import mindustry.world.consumers.ConsumeLiquidBase
import mindustry.world.draw.DrawTurret
import mindustry.world.meta.Env

//aisle
object Blocks {
    var smallHumeCore: Block? = null
    var smallHumeCoreTurret: Block? = null
    fun load() {
        smallHumeCoreTurret = object : LaserTurret("hume-core-small-turret") {
            init {
                coolant = object :ConsumeLiquidBase(5f) {}
                description = ""
                shootEffect = Fx.shootBigSmoke2
                shootCone = 40f
                recoil = 4f
                size = 4
                shake = 2f
                range = 595f
                reload = 0f
                firingMoveFract = 0.5f
                shootDuration = 430f
                shootSound = Sounds.laserbig
                loopSound = Sounds.beam
                loopSoundVolume = 2f
                envEnabled = envEnabled or Env.space

                shootType = object : ContinuousLaserBulletType(800f) {
                    init {
                        length = 600f
                        hitEffect = Fx.hitMeltdown
                        hitColor = Pal.meltdownHit
                        status = StatusEffects.melting
                        drawSize = 620f
                        width = 15f
                        incendChance = 0.4f
                        incendSpread = 5f
                        incendAmount = 1
                        ammoMultiplier = 1f
                    }
                }
                drawer = object :DrawTurret() {
                    init {
                        base = Core.atlas.find("founDation-transparent-block-5")
                    }
                }
                scaledHealth = 200f
            }
        }
        smallHumeCore = object : HumeCore("hume-core-small") {
            init {
                turrets = Seq.with(smallHumeCoreTurret,Blocks.spectre)
                health = 400000
                size = 8
                category = Category.effect
                requirements(Category.effect, ItemStack.with(Items.copper, 3000, Items.lead, 3000, Items.silicon, 2000))
                turretblock = smallHumeCoreTurret
            }
        }
    }
}
