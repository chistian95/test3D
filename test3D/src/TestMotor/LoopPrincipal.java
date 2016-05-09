package TestMotor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import motorRenderizado.RenderizadorMaster;
import texturas.TexturaModelo;

public class LoopPrincipal {
	public static void main(String[] args) {
		ManagerPantalla.crearPantalla();		
		Cargador cargador = new Cargador();		
		
		ModeloRaw modelo = CargadorOBJ.cargarModeloOBJ("dragon", cargador);
		ModeloConTextura modeloCubo = new ModeloConTextura(modelo, new TexturaModelo(
				cargador.cargarTextura("imagen")));
		
		TexturaModelo texturaFinal = modeloCubo.getTextura();
		texturaFinal.setShineDamper(20);
		texturaFinal.setReflejo(0.5f);
		
		Luz luz = new Luz(new Vector3f(0, 20, 20), new Vector3f(1, 1, 1));		
		Camara camara = new Camara();
		
		List<Entidad> entidades = new ArrayList<Entidad>();
		Random rnd = new Random();
		
		for(int i=0; i<15; i++) {
			float x = rnd.nextFloat() * 100 - 50;
			float y = rnd.nextFloat() * 100 - 50;
			float z = rnd.nextFloat() * -300;
			entidades.add(new Entidad(modeloCubo, new Vector3f(x, y, z), rnd.nextFloat() *130f, rnd.nextFloat() * 180f, 0f, 1f));
		}
		
		RenderizadorMaster renderizador = new RenderizadorMaster();
		while(!Display.isCloseRequested()) {			
			camara.mover();
			
			for(Entidad entidad : entidades) {
				entidad.incrementarRotacion(1, 10, 1);
				entidad.incrementarPosicion(0, 0, 0);
				renderizador.procesarEntidad(entidad);
			}
			
			renderizador.render(luz, camara);
			ManagerPantalla.actualizarPantalla();
		}		
		
		renderizador.limpiar();
		cargador.limpiar();
		ManagerPantalla.cerrarPantalla();
	}
}
