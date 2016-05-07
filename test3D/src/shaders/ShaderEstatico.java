package shaders;

public class ShaderEstatico extends ProgramaShader {
	private static final String ARCHIVO_VERTEX = "src/shaders/vertexShader.txt";
	private static final String ARCHIVO_FRAGMENT = "src/shaders/fragmentShader.txt";
	
	public ShaderEstatico() {
		super(ARCHIVO_VERTEX, ARCHIVO_FRAGMENT);
	}

	@Override
	protected void bindAtributos() {
		super.bindAtributos(0, "posicion");
		super.bindAtributos(1, "coordsTextura");
	}
}
