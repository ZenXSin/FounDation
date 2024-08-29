#define HIGHP

uniform sampler2D u_texture;
uniform float r_red;
varying vec2 v_texCoords;

void main(){
    vec4 c = texture2D(u_texture, v_texCoords);
    c.r += r_red - c.r * r_red;
    gl_FragColor = c;
}
