package founDation.type

import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import mindustry.Vars
import mindustry.world.blocks.defense.Wall


class Aisle(name: String?) : Wall(name) {
    var texture: Array<TextureRegion?> = arrayOfNulls(11)

    init {
        update = true
        size = 4
    }

    override fun load() {
        texture[0] = Core.atlas.find("founDation-[废墟]站点十字分叉")
        texture[1] = Core.atlas.find("founDation-[废墟]站点拐角1")
        texture[2] = Core.atlas.find("founDation-[废墟]站点拐角2")
        texture[3] = Core.atlas.find("founDation-[废墟]站点拐角3")
        texture[4] = Core.atlas.find("founDation-[废墟]站点拐角4")
        texture[5] = Core.atlas.find("founDation-[废墟]站点过道")
        texture[6] = Core.atlas.find("founDation-[废墟]站点过道1")
        texture[7] = Core.atlas.find("founDation-[废墟]设施分叉1")
        texture[8] = Core.atlas.find("founDation-[废墟]设施分叉2")
        texture[9] = Core.atlas.find("founDation-[废墟]设施分叉3")
        texture[10] = Core.atlas.find("founDation-[废墟]设施分叉4")
        super.load()
    }

    open inner class AisleBuild : WallBuild() {
        private var ts: TextureRegion? = Core.atlas.find("founDation-[废墟]站点过道").also { texture[5] = it }

        override fun draw() {
            Draw.rect(ts, x, y)
        }

        fun buildTile(x: Int, y: Int): Boolean {
            val block1 = Vars.world.build(x, y)
            return if (block1 == null) {
                false
            } else block1.block === block
        }

        override fun update() {
            if (buildTile(tile.x - 3, tile.y.toInt()) and buildTile(
                    tile.x + 3, tile.y.toInt()
                ) and buildTile(tile.x.toInt(), tile.y - 2) and buildTile(tile.x.toInt(), tile.y + 3)
            ) {
                ts = texture[0]
            } else if (buildTile(tile.x - 3, tile.y.toInt()) and buildTile(tile.x + 3, tile.y.toInt()) and buildTile(
                    tile.x.toInt(), tile.y - 2
                )
            ) {
                ts = texture[10]
            } else if (buildTile(tile.x - 3, tile.y.toInt()) and buildTile(tile.x.toInt(), tile.y - 2) and buildTile(
                    tile.x.toInt(), tile.y + 3
                )
            ) {
                ts = texture[9]
            } else if (buildTile(tile.x - 3, tile.y.toInt()) and buildTile(tile.x.toInt(), tile.y + 3) and buildTile(
                    tile.x + 3, tile.y.toInt()
                )
            ) {
                ts = texture[8]
            } else if (buildTile(tile.x.toInt(), tile.y - 2) and buildTile(tile.x.toInt(), tile.y + 3) and buildTile(
                    tile.x + 3, tile.y.toInt()
                )
            ) {
                ts = texture[7]
            } else if (buildTile(tile.x.toInt(), tile.y - 2) and buildTile(tile.x + 3, tile.y.toInt())) {
                ts = texture[4]
            } else if (buildTile(tile.x.toInt(), tile.y + 3) and buildTile(tile.x - 3, tile.y.toInt())) {
                ts = texture[2]
            } else if (buildTile(tile.x.toInt(), tile.y + 3) and buildTile(tile.x + 3, tile.y.toInt())) {
                ts = texture[1]
            } else if (buildTile(tile.x - 3, tile.y.toInt()) && buildTile(tile.x.toInt(), tile.y - 2)) {
                ts = texture[3]
            } else if (buildTile(tile.x - 3, tile.y.toInt()) || buildTile(tile.x + 3, tile.y.toInt())) {
                ts = texture[6]
            } else if (buildTile(tile.x.toInt(), tile.y + 3) || buildTile(tile.x.toInt(), tile.y - 2)) {
                ts = texture[5]
            }
            super.update()
        }
    }
}
