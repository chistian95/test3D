package entidades;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camara {
	private Vector3f posicion = new Vector3f();
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camara()	{}
	
	public void mover() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			posicion.z -= 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			posicion.z += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			posicion.x += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			posicion.x -= 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			posicion.y += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			posicion.y -= 0.02f;
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
