package entidades;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camara {
	private static final float VELOCIDAD_CAMARA = 0.5f;
	
	private Vector3f posicion = new Vector3f();
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camara()	{}
	
	public void mover() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			posicion.z -= VELOCIDAD_CAMARA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			posicion.z += VELOCIDAD_CAMARA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			posicion.x += VELOCIDAD_CAMARA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			posicion.x -= VELOCIDAD_CAMARA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			posicion.y += VELOCIDAD_CAMARA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			posicion.y -= VELOCIDAD_CAMARA;
		}
	}
	
	public Vector3f getPosicion() {
		return posicion;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
