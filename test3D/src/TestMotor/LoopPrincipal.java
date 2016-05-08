package TestMotor;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entidades.Camara;
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
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f				
		};
		
		float[] coordsTextura = {				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0			
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};
		
		ModeloRaw modelo = cargador.cargarEnVAO(vertices, coordsTextura, indices);
		TexturaModelo textura = new TexturaModelo(cargador.cargarTextura("imagen"));
		ModeloConTextura modeloConTextura = new ModeloConTextura(modelo, textura);
		
		Entidad entidad = new Entidad(modeloConTextura, new Vector3f(0, 0, -8), 0, 0, 0, 1);
		
		Camara camara = new Camara();
		
		while(!Display.isCloseRequested()) {
			//entidad.incrementarPosicion(0, 0, -0.01f);
			entidad.incrementarRotacion(0.2f, 0.2f, 0.2f);
			camara.mover();
			renderizador.preparar();
			shader.comenzar();
			shader.cargarMatrizCamara(camara);
			renderizador.render(entidad, shader);
			shader.parar();
			ManagerPantalla.actualizarPantalla();
		}		
		
		shader.limpiar();
		cargador.limpiar();
		ManagerPantalla.cerrarPantalla();
	}
}
