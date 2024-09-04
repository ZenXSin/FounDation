package founDation.graphic

import arc.Core.graphics
import arc.Events
import arc.graphics.g2d.Draw
import arc.graphics.gl.FrameBuffer
import arc.util.Log
import mindustry.Vars
import mindustry.game.EventType.Trigger
import mindustry.graphics.Layer

class ModRenderers {
    class ContrastRenderer {
        var frame: FrameBuffer? = null
        var increase: Float = 0f
        init {
            if (!Vars.headless) {
               // frame = FrameBuffer()
                Events.run(Trigger.Draw) {
                   // advancedDraw()
                   // Draw.reset()
                     Draw.blit(ModShaders.contrast)
                }
            }
        }

        private fun advancedDraw() {
            Draw.draw(Layer.background - 0.1f) {
                frame?.resize(graphics.width, graphics.height)
                frame?.begin()
            }

            Draw.draw(Layer.max - 4f) {
                frame?.end()

                ModShaders.contrast.increase = increase
                frame?.blit(ModShaders.contrast)

                frame?.begin()
                Draw.rect()
                frame?.end()
            }
        }
    }

    companion object {
        val contrast: ContrastRenderer = ContrastRenderer()
    }
}