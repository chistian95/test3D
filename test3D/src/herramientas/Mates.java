package herramientas;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entidades.Camara;

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
	
	public static Matrix4f crearMatrizCamara(Camara camara) {
		Matrix4f matrizCamara = new Matrix4f();
		matrizCamara.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camara.getPitch()), new Vector3f(1, 0, 0), matrizCamara, matrizCamara);
		Matrix4f.rotate((float) Math.toRadians(camara.getYaw()), new Vector3f(0, 1, 0), matrizCamara, matrizCamara);
		Matrix4f.rotate((float) Math.toRadians(camara.getRoll()), new Vector3f(0, 0, 1), matrizCamara, matrizCamara);
		Vector3f camaraPos = camara.getPosicion();
		Vector3f camaraPosNegativo = new Vector3f(-camaraPos.x, -camaraPos.y, -camaraPos.z);
		Matrix4f.translate(camaraPosNegativo, matrizCamara, matrizCamara);
		return matrizCamara;
	}
}
