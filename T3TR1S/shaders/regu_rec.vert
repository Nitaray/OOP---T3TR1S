#version 330 core

layout (location = 0) in vec4 pos;
layout (location = 1) in vec2 tc;

uniform mat4 pr_matrix;
uniform mat4 model_matrix;

out DATA {
    vec2 tc;
} vs_out;


void main() {
    gl_Position = pr_matrix * pos * model_matrix;
    vs_out.tc = tc;
}