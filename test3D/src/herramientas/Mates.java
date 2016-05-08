package herramientas;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Mates {
	public static Matrix4f crearMatrizTransformacion(Vector3f translacion, float rx, float ry, float rz, float escala) {
		Matrix4f matriz = new Matrix4f();
		matriz.setIdentity();
		Matrix4f.translate(translacion, matriz, matriz);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matriz, matriz);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matriz, matriz);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matriz, matriz);
		Matrix4f.scale(new Vector3f(escala, escala, escala), matriz, matriz);
		return matriz;
	}
}
