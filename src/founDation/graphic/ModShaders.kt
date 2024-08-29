package founDation.graphic

import arc.files.Fi
import arc.graphics.gl.Shader
import founDation.FounDation
import mindustry.mod.Mods
import mindustry.graphics.Shaders

class ModShaders {
    open class ModShader(vert: String, frag: String):
        Shader(getShaderFi("$vert.vert"), getShaderFi("$frag.frag"))

    open class ContrastShader: ModShader("screenspace", "contrast") {
        var increase = 0.2f

        override fun apply() {
            setUniformf("r_red", increase)
        }
    }

    companion object {
        val contrast: ContrastShader = ContrastShader()

        fun getShaderFi(file: String): Fi? {
            val mod: Mods.LoadedMod? = FounDation.MOD

            val shaders: Fi = mod!!.root.child("shaders")
            if (shaders.exists())
                if (shaders.child(file).exists())
                    return shaders.child(file)

            return Shaders.getShaderFi(file)
        }
    }
}