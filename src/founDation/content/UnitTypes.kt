package founDation.content

import arc.func.Prov
import arc.graphics.Color
import mindustry.ai.types.BuilderAI
import mindustry.content.Fx
import mindustry.content.StatusEffects
import mindustry.content.UnitTypes
import mindustry.entities.abilities.RepairFieldAbility
import mindustry.entities.abilities.UnitSpawnAbility
import mindustry.entities.bullet.BulletType
import mindustry.entities.bullet.ContinuousLaserBulletType
import mindustry.gen.EntityMapping
import mindustry.gen.Sounds
import mindustry.gen.Unit
import mindustry.graphics.Pal
import mindustry.type.UnitType
import mindustry.type.Weapon
import mindustry.type.weapons.PointDefenseWeapon

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
                armor = 8f

                abilities.add(UnitSpawnAbility(UnitTypes.mono, 60 * 5f, 0.85f, -17.5f),
                    RepairFieldAbility(200f, 60f, 160f))

                constructor = EntityMapping.map("gamma") as Prov<out Unit>?

                weapons.add(object : PointDefenseWeapon("foundation-GAMMA-BT2-裂解") {
                    init {
                        mirror = false
                        x = -12f
                        y = -3f
                        reload = 5f
                        targetInterval = 1f
                        targetSwitchInterval = 4f



                        bullet = object : BulletType() {
                            init {

                                shootEffect = Fx.sparkShoot
                                hitEffect = Fx.pointHit
                                maxRange = 160f
                                damage = 60f
                            }
                        }
                    }
                })

                weapons.add(object : Weapon("foundation-GAMMA-BT2-激光") {
                    init {
                        shadow = 20f
                        controllable = false
                        autoTarget = true
                        mirror = false
                        shake = 3f
                        shootY = 8f
                        rotate = true
                        x = 10f
                        y = -8.5f

                        targetInterval = 20f
                        targetSwitchInterval = 35f

                        rotateSpeed = 6f
                        reload = 170f
                        recoil = 1f
                        shootSound = Sounds.beam
                        continuous = true
                        cooldownTime = reload
                        immunities.add(StatusEffects.burning)

                        bullet = object : ContinuousLaserBulletType() {
                            init {
                                maxRange = 180f
                                damage = 100f
                                length = 180f
                                hitEffect = Fx.hitMeltHeal
                                drawSize = 200f
                                lifetime = 300f
                                shake = 1f

                                shootEffect = Fx.shootHeal
                                smokeEffect = Fx.none
                                width = 4f
                                largeHit = false

                                incendChance = 0.03f
                                incendSpread = 5f
                                incendAmount = 1

                                healPercent = 0.4f
                                collidesTeam = true

                                colors = arrayOf(
                                    Pal.heal.cpy().a(.2f),
                                    Pal.heal.cpy().a(.5f),
                                    Pal.heal.cpy().mul(1.2f),
                                    Color.white
                                )
                            }
                        }
                    }
                })

            }
        }
    }
}