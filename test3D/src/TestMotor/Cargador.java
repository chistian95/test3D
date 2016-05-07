package TestMotor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import modelos.ModeloRaw;

public class Cargador {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> texturas = new ArrayList<Integer>();
	
	public ModeloRaw cargarEnVAO(float[] posiciones, float[] coordsTextura, int[] indices) {
		int vaoID = crearVAO();
		bindIndicesBuffer(indices);
		guardarDatosEnListaAtributos(0, 3, posiciones);
		guardarDatosEnListaAtributos(1, 2, coordsTextura);
		unbindVAO();
		return new ModeloRaw(vaoID, indices.length);
	}
	
	public int cargarTextura(String nombreArchivo) {
		Texture textura = null;
		try {
			textura = TextureLoader.getTexture("PNG", new FileInputStream("src/res/"+nombreArchivo+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int idTextura = textura.getTextureID();
		texturas.add(idTextura);
		return idTextura;
	}
	
	public void limpiar() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int textura : texturas) {
			GL11.glDeleteTextures(textura);
		}
	}
	
	private int crearVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void guardarDatosEnListaAtributos(int numAtributos, int numCoords, float[] datos) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = guardarDatosEnFloatBuffer(datos);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(numAtributos, numCoords, GL11.GL_FLOAT, false, 0, 0);
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
