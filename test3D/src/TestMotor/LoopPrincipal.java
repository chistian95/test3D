package TestMotor;

import org.lwjgl.opengl.Display;

import modelos.ModeloConTextura;
import modelos.ModeloRaw;
import motorRenderizado.ManagerPantalla;
import shaders.ShaderEstatico;
import texturas.TexturaModelo;

public class LoopPrincipal {
	public static void main(String[] args) {
		ManagerPantalla.crearPantalla();
		
		Cargador cargador = new Cargador();
		Renderizador renderizador = new Renderizador();
		ShaderEstatico shader = new ShaderEstatico();
		
		float[] vertices = { 
				-0.5f, 0.5f, 0f, //V0
				-0.5f, -0.5f, 0f,  //V1
				0.5f, -0.5f, 0f,  //V2
				0.5f, 0.5f, 0f, //V3
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] coordsTextura = {
				0, 0, //V0
				0, 1, //V1
				1, 1, //V2
				1, 0 //V3
		};
		
		ModeloRaw modelo = cargador.cargarEnVAO(vertices, coordsTextura, indices);
		TexturaModelo textura = new TexturaModelo(cargador.cargarTextura("imagen"));
		ModeloConTextura modeloConTextura = new ModeloConTextura(modelo, textura);
		
		while(!Display.isCloseRequested()) {
			renderizador.preparar();
			shader.comenzar();
			renderizador.render(modeloConTextura);
			shader.parar();
			ManagerPantalla.actualizarPantalla();
		}		
		
		shader.limpiar();
		cargador.limpiar();
		ManagerPantalla.cerrarPantalla();
	}
}
