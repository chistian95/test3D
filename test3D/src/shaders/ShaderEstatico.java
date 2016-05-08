package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class ShaderEstatico extends ProgramaShader {
	private static final String ARCHIVO_VERTEX = "src/shaders/vertexShader.txt";
	private static final String ARCHIVO_FRAGMENT = "src/shaders/fragmentShader.txt";
	
	private int posicion_matrizTransformacion;
	private int posicion_matrizProyeccion;
	
	public ShaderEstatico() {
		super(ARCHIVO_VERTEX, ARCHIVO_FRAGMENT);
	}

	@Override
	protected void bindAtributos() {
		super.bindAtributos(0, "posicion");
		super.bindAtributos(1, "coordsTextura");
	}

	@Override
	protected void getAllUniformLocations() {
		posicion_matrizTransformacion = super.getUniformLocation("matrizTransformacion");
		posicion_matrizProyeccion = super.getUniformLocation("matrizProyeccion");
	}
	
	public void cargarMatrizTransformacion(Matrix4f matriz) {
		super.cargarMatriz(posicion_matrizTransformacion, matriz);
	}
	
	public void cargarMatrizProyeccion(Matrix4f proyeccion) {
		super.cargarMatriz(posicion_matrizProyeccion, proyeccion);
	}
}
