package founDation.environment

import arc.Events
import mindustry.Vars
import mindustry.game.EventType
import mindustry.io.SaveFileReader.CustomChunk
import mindustry.io.SaveVersion
import java.io.DataInput
import java.io.DataOutput

object Humes : CustomChunk {
    var benchmarkHume = 100f
    private var TileHume: MutableMap<Pair<Int, Int>, Hume> = mutableMapOf()

    private fun getTileHume(): String {
        var ret = ""
        TileHume.forEach { (_, u) ->
            ret += "$u||"
        }
        return ret
    }

    private fun readTileHume(s: String) {
        s.split("||").forEach {
            readHume(it)
        }
    }

    class Hume(val x: Int, val y: Int) {
        var humeIndex = benchmarkHume
        override fun toString(): String {
            return "$x $y $humeIndex"
        }
    }

    private fun readHume(s: String) {
        val tag = s.split(" ")
        val hume = Hume(tag[0].toInt(), tag[1].toInt())
        hume.humeIndex = tag[2].toFloat()
        TileHume.plus(tag[0].toInt() to tag[1].toInt() to hume)
    }

    override fun read(stream: DataInput?) {
        stream?.let {
            benchmarkHume = it.readFloat()
            readTileHume(it.readUTF())
        }
    }

    override fun write(stream: DataOutput?) {
        stream?.let {
            it.writeFloat(benchmarkHume)
            it.writeUTF(getTileHume())
        }
    }

    fun load() {
        SaveVersion.addCustomChunk("Hume", this)

        Events.on(EventType.SaveLoadEvent::class.java) {
            repeat(Vars.state.map.width) { x ->
                repeat(Vars.state.map.height) { y ->
                    TileHume.plus(x to y to Hume(x, y))

                }
            }
        }
    }

    class HumeN :
        RuntimeException("Your world i Is dbfr a origjois oaincuiadholanIfoiudushbfuioancoalnb askhjfeiuyroawsGUBCJibiJjnNJljnJJJKkjkJnd")
}