package TestMotor;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Cargador {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	
	public ModeloRaw cargarEnVAO(float[] posiciones, int[] indices) {
		int vaoID = crearVAO();
		bindIndicesBuffer(indices);
		guardarDatosEnListaAtributos(0, posiciones);
		unbindVAO();
		return new ModeloRaw(vaoID, indices.length);
	}
	
	public void limpiar() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}
	
	private int crearVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void guardarDatosEnListaAtributos(int numAtributos, float[] datos) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = guardarDatosEnFloatBuffer(datos);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(numAtributos, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = guardarDatosEnIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer guardarDatosEnIntBuffer(int[] datos) {
		IntBuffer buffer = BufferUtils.createIntBuffer(datos.length);
		buffer.put(datos);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer guardarDatosEnFloatBuffer(float[] datos) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(datos.length);
		buffer.put(datos);
		buffer.flip();
		return buffer;
	}
}
