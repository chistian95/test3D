package TestMotor;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entidades.Camara;
import entidades.Entidad;
import entidades.Luz;
import modelos.ModeloConTextura;
import modelos.ModeloRaw;
import motorRenderizado.Cargador;
import motorRenderizado.CargadorOBJ;
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
		
		ModeloRaw modelo = CargadorOBJ.cargarModeloOBJ("dragon", cargador);
		TexturaModelo textura = new TexturaModelo(cargador.cargarTextura("imagen"));
		
		ModeloConTextura modeloConTextura = new ModeloConTextura(modelo, textura);
		
		Entidad entidad = new Entidad(modeloConTextura, new Vector3f(0, 0, -50), 0, 0, 0, 1);
		Luz luz = new Luz(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		
		Camara camara = new Camara();
		
		while(!Display.isCloseRequested()) {
			entidad.incrementarPosicion(0, 0, 0);
			entidad.incrementarRotacion(0, 0.4f, 0);
			camara.mover();
			renderizador.preparar();
			shader.comenzar();
			shader.cargarLuz(luz);
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
