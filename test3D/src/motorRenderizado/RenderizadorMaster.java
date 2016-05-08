package motorRenderizado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Camara;
import entidades.Entidad;
import entidades.Luz;
import modelos.ModeloConTextura;
import shaders.ShaderEstatico;

public class RenderizadorMaster {
	private ShaderEstatico shader = new ShaderEstatico();
	private Renderizador renderizador = new Renderizador(shader);
	
	private Map<ModeloConTextura, List<Entidad>> entidades = new HashMap<ModeloConTextura, List<Entidad>>();
	
	public void render(Luz sol, Camara camara) {
		renderizador.preparar();
		shader.comenzar();
		shader.cargarLuz(sol);
		shader.cargarMatrizCamara(camara);
		renderizador.render(entidades);
		shader.parar();
		entidades.clear();
	}
	
	public void procesarEntidad(Entidad entidad) {
		ModeloConTextura modeloEntidad = entidad.getModelo();
		List<Entidad> batch = entidades.get(modeloEntidad);
		if(batch != null) {
			batch.add(entidad);
		} else {
			List<Entidad> nuevoBatch = new ArrayList<Entidad>();
			nuevoBatch.add(entidad);
			entidades.put(modeloEntidad, nuevoBatch);
		}
	}
	
	public void limpiar() {
		shader.limpiar();
	}
}