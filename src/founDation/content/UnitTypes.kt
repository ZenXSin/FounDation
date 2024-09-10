package founDation.content

import arc.func.Prov
import mindustry.ai.types.BuilderAI
import mindustry.content.UnitTypes
import mindustry.entities.abilities.UnitSpawnAbility
import mindustry.gen.EntityMapping
import mindustry.gen.Unit
import mindustry.type.UnitType

object UnitTypes {
    var GAMMA_BT2: UnitType? = null
    fun load() {
        GAMMA_BT2 = object : UnitType("GAMMA-BT2") {
            init {
                aiController = Prov { BuilderAI() }
                isEnemy = false

                lowAltitude = true
                flying = true
                mineSpeed = 20f
                mineTier = 10
                buildSpeed = 5f
                drag = 0.15f
                speed = 3f
                rotateSpeed = 10f
                accel = 0.30f
                fogRadius = 80f
                itemCapacity = 600
                health = 15000f
                engineOffset = 0f
                engineSize = 10f
                hitSize = 40f

                abilities.add(UnitSpawnAbility(UnitTypes.mono, 60 * 5f, 6f, -17.5f))

                constructor = EntityMapping.map("gamma") as Prov<out Unit>?

                /*weapons.add(object : Weapon("small-mount-weapon") {
                    init {
                        top = false
                        reload = 15f
                        x = 1f
                        y = 2f
                        shoot = object : ShootSpread() {
                            init {
                                shots = 2
                                shotDelay = 3f
                                spread = 2f
                            }
                        }

                        inaccuracy = 3f
                        ejectEffect = Fx.casing1

                        bullet = object : BasicBulletType(3.5f, 11f) {
                            init {
                                width = 6.5f
                                height = 11f
                                lifetime = 70f
                                shootEffect = Fx.shootSmall
                                smokeEffect = Fx.shootSmallSmoke
                                buildingDamageMultiplier = 0.01f
                                homingPower = 0.04f
                            }
                        }
                    }
                })*/
            }
        }
    }
}