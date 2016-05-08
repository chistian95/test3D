package motorRenderizado;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entidades.Entidad;
import herramientas.Mates;
import modelos.ModeloConTextura;
import modelos.ModeloRaw;
import shaders.ShaderEstatico;
import texturas.TexturaModelo;

public class Renderizador {
	private static final float FOV = 70;
	private static final float PLANO_CERCA = 0.1f;
	private static final float PLANO_LEJOS = 1000;
	
	private Matrix4f matrizProyeccion;	
	private ShaderEstatico shader;
	
	public Renderizador(ShaderEstatico shader) {
		this.shader = shader;
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		crearMatrizProyeccion();
		shader.comenzar();
		shader.cargarMatrizProyeccion(matrizProyeccion);
		shader.parar();
	}
	
	public void preparar() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 0, 1, 1);		
	}
	
	public void render(Map<ModeloConTextura, List<Entidad>> entidades) {
		for(ModeloConTextura modelo : entidades.keySet()) {
			preararModeloContextura(modelo);
			List<Entidad> batch = entidades.get(modelo);
			for(Entidad entidad : batch) {
				prepararInstancia(entidad);
				GL11.glDrawElements(GL11.GL_TRIANGLES, modelo.getModeloRaw().getVertexCont(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindModeloConTextura();
		}
	}
	
	private void preararModeloContextura(ModeloConTextura modelo) {
		ModeloRaw modeloRaw = modelo.getModeloRaw();
		GL30.glBindVertexArray(modeloRaw.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		TexturaModelo textura = modelo.getTextura();
		shader.cargarBrillo(textura.getShineDamper(), textura.getReflejo());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelo.getTextura().getIdTextura());
	}
	
	private void unbindModeloConTextura() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepararInstancia(Entidad entidad) {
		Matrix4f matrizTransformacion = Mates.crearMatrizTransformacion(entidad.getPosicion(), entidad.getRotX(), entidad.getRotY(), entidad.getRotZ(), entidad.getEscala());
		shader.cargarMatrizTransformacion(matrizTransformacion);
	}
	
	private void crearMatrizProyeccion() {
		float ratioAspecto = (float) Display.getWidth() / (float) Display.getHeight();
		float escala_y = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)) * ratioAspecto);
		float escala_x = escala_y / ratioAspecto;
		float longitud_frustum = PLANO_LEJOS - PLANO_CERCA;
		
		matrizProyeccion = new Matrix4f();
		matrizProyeccion.m00 = escala_x;
		matrizProyeccion.m11 = escala_y;
		matrizProyeccion.m22 = -((PLANO_LEJOS + PLANO_CERCA) / longitud_frustum);
		matrizProyeccion.m23 = -1;
		matrizProyeccion.m32 = -((2 * PLANO_CERCA * PLANO_LEJOS) / longitud_frustum);
		matrizProyeccion.m33 = 0;
	}
}
