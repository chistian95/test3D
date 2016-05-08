package motorRenderizado;

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

public class Renderizador {
	private static final float FOV = 70;
	private static final float PLANO_CERCA = 0.1f;
	private static final float PLANO_LEJOS = 1000;
	
	private Matrix4f matrizProyeccion;	
	
	public Renderizador(ShaderEstatico shader) {
		crearMatrizProyeccion();
		shader.comenzar();
		shader.cargarMatrizProyeccion(matrizProyeccion);
		shader.parar();
	}
	
	public void preparar() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);		
	}
	
	public void render(Entidad entidad, ShaderEstatico shader) {
		ModeloConTextura modelo = entidad.getModelo();
		ModeloRaw modeloRaw = modelo.getModeloRaw();
		GL30.glBindVertexArray(modeloRaw.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f matrizTransformacion = Mates.crearMatrizTransformacion(entidad.getPosicion(), entidad.getRotX(), entidad.getRotY(), entidad.getRotZ(), entidad.getEscala());
		shader.cargarMatrizTransformacion(matrizTransformacion);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelo.getTextura().getIdTextura());
		GL11.glDrawElements(GL11.GL_TRIANGLES, modeloRaw.getVertexCont(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
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
