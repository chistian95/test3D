package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ProgramaShader {
	private int idPrograma;
	private int idVertexShader;
	private int idFragmentShader;
	
	public ProgramaShader(String archivoVertex, String archivoFragment) {
		idVertexShader = cargarShader(archivoVertex, GL20.GL_VERTEX_SHADER);
		idFragmentShader = cargarShader(archivoFragment, GL20.GL_FRAGMENT_SHADER);
		idPrograma = GL20.glCreateProgram();
		GL20.glAttachShader(idPrograma, idVertexShader);
		GL20.glAttachShader(idPrograma, idFragmentShader);
		bindAtributos();
		GL20.glLinkProgram(idPrograma);
		GL20.glValidateProgram(idPrograma);
	}
	
	public void parar() {
		GL20.glUseProgram(0);
	}
	
	public void comenzar() {
		GL20.glUseProgram(idPrograma);
	}
	
	public void limpiar() {
		parar();
		GL20.glDetachShader(idPrograma, idVertexShader);
		GL20.glDetachShader(idPrograma, idFragmentShader);
		GL20.glDeleteShader(idVertexShader);
		GL20.glDeleteShader(idFragmentShader);
		GL20.glDeleteProgram(idPrograma);
	}
	
	protected abstract void bindAtributos();
	
	protected void bindAtributos(int atributo, String nombreVariable) {
		GL20.glBindAttribLocation(idPrograma, atributo, nombreVariable);
	}
	
	private static int cargarShader(String archivo, int tipo) {
		StringBuilder fuenteShader = new StringBuilder();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while((linea = lector.readLine()) != null) {
				fuenteShader.append(linea).append("\n");
			}
			lector.close();
		} catch(IOException e) {
			System.err.println("No se ha encontrado el archivo");
			e.printStackTrace();
			System.exit(-1);
		}
		int idShader = GL20.glCreateShader(tipo);
		GL20.glShaderSource(idShader, fuenteShader);
		GL20.glCompileShader(idShader);
		if(GL20.glGetShaderi(idShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(idShader, 500));
			System.err.println("No se ha podido compilar el shader");
			System.exit(-1);
		}
		return idShader;
	}
}
