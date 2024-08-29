package founDation.blocks

import arc.func.Prov
import founDation.type.HumeCore
import mindustry.Vars
import mindustry.content.Items
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.blocks.defense.Wall

//aisle
object Blocks {
    var smallHumeCore: HumeCore? = null
    fun load() {
        smallHumeCore = object :HumeCore("hume-core-small") {
            init {
                size = 8
                category = Category.effect
                requirements(Category.effect, ItemStack.with(Items.copper, 3000, Items.lead, 3000, Items.silicon, 2000))
            }
        }
        /*object : Wall("zha-dang-qi") {
            init {
                buildType = Prov {
                    WallBuild() {

                    }
                }
            }
        }*/
    }
}
