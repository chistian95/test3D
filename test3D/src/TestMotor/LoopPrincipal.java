package TestMotor;

import org.lwjgl.opengl.Display;

import motorRenderizado.ManagerPantalla;

public class LoopPrincipal {
	public static void main(String[] args) {
		ManagerPantalla.crearPantalla();
		
		Cargador cargador = new Cargador();
		Renderizador renderizador = new Renderizador();
		
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
		
		ModeloRaw modelo = cargador.cargarEnVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderizador.preparar();
			
			renderizador.render(modelo);
			ManagerPantalla.actualizarPantalla();
		}		
		ManagerPantalla.cerrarPantalla();
	}
}
