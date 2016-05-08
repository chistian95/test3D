package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entidades.Camara;
import entidades.Luz;
import herramientas.Mates;

public class ShaderEstatico extends ProgramaShader {
	private static final String ARCHIVO_VERTEX = "src/shaders/vertexShader.txt";
	private static final String ARCHIVO_FRAGMENT = "src/shaders/fragmentShader.txt";
	
	private int posicion_matrizTransformacion;
	private int posicion_matrizProyeccion;
	private int posicion_matrizCamara;
	private int posicion_posicionLuz;
	private int posicion_colorLuz;
	private int posicion_shineDamper;
	private int posicion_reflejo;
	
	public ShaderEstatico() {
		super(ARCHIVO_VERTEX, ARCHIVO_FRAGMENT);
	}

	@Override
	protected void bindAtributos() {
		super.bindAtributos(0, "posicion");
		super.bindAtributos(1, "coordsTextura");
		super.bindAtributos(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		posicion_matrizTransformacion = super.getUniformLocation("matrizTransformacion");
		posicion_matrizProyeccion = super.getUniformLocation("matrizProyeccion");
		posicion_matrizCamara = super.getUniformLocation("matrizCamara");
		posicion_posicionLuz = super.getUniformLocation("posicionLuz");
		posicion_colorLuz = super.getUniformLocation("colorLuz");
		posicion_shineDamper = super.getUniformLocation("shineDamper");
		posicion_reflejo = super.getUniformLocation("reflejo");
	}
	
	public void cargarBrillo(float damper, float reflejo) {
		super.cargarFloat(posicion_shineDamper, damper);
		super.cargarFloat(posicion_reflejo, reflejo);
	}
	
	public void cargarMatrizTransformacion(Matrix4f matriz) {
		super.cargarMatriz(posicion_matrizTransformacion, matriz);
	}
	
	public void cargarLuz(Luz luz) {
		super.cargarVector(posicion_posicionLuz, luz.getPosicion());
		super.cargarVector(posicion_colorLuz, luz.getColor());
	}	
	
	public void cargarMatrizCamara(Camara camara) {
		Matrix4f matrizCamara = Mates.crearMatrizCamara(camara);
		super.cargarMatriz(posicion_matrizCamara, matrizCamara);
	}
	
	public void cargarMatrizProyeccion(Matrix4f proyeccion) {
		super.cargarMatriz(posicion_matrizProyeccion, proyeccion);
	}	
}
