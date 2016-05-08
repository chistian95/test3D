package TestMotor;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entidades.Entidad;
import modelos.ModeloConTextura;
import modelos.ModeloRaw;
import motorRenderizado.Cargador;
import motorRenderizado.ManagerPantalla;
import motorRenderizado.Renderizador;
import shaders.ShaderEstatico;
import texturas.TexturaModelo;

public class LoopPrincipal {
	public static void main(String[] args) {
		ManagerPantalla.crearPantalla();
		
		Cargador cargador = new Cargador();
		ShaderEstatico shader = new ShaderEstatico();
		Renderizador renderizador = new Renderizador(shader);
		
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
		
		Entidad entidad = new Entidad(modeloConTextura, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		
		while(!Display.isCloseRequested()) {
			entidad.incrementarPosicion(0, 0, -0.01f);
			entidad.incrementarRotacion(0, 1, 0);
			renderizador.preparar();
			shader.comenzar();
			renderizador.render(entidad, shader);
			shader.parar();
			ManagerPantalla.actualizarPantalla();
		}		
		
		shader.limpiar();
		cargador.limpiar();
		ManagerPantalla.cerrarPantalla();
	}
}
