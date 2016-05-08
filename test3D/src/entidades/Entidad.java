package entidades;

import org.lwjgl.util.vector.Vector3f;

import modelos.ModeloConTextura;

public class Entidad {
	private ModeloConTextura modelo;
	private Vector3f posicion;
	private float rotX, rotY, rotZ;
	private float escala;
	public Entidad(ModeloConTextura modelo, Vector3f posicion, float rotX, float rotY, float rotZ, float escala) {
		super();
		this.modelo = modelo;
		this.posicion = posicion;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.escala = escala;
	}
	
	public void incrementarPosicion(float dx, float dy, float dz) {
		posicion.x += dx;
		posicion.y += dy;
		posicion.z += dz;
	}
	
	public void incrementarRotacion(float dx, float dy, float dz) {
		rotX += dx;
		rotY += dy;
		rotZ += dz;
	}
	
	public ModeloConTextura getModelo() {
		return modelo;
	}
	public void setModelo(ModeloConTextura modelo) {
		this.modelo = modelo;
	}
	public Vector3f getPosicion() {
		return posicion;
	}
	public void setPosicion(Vector3f posicion) {
		this.posicion = posicion;
	}
	public float getRotX() {
		return rotX;
	}
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	public float getEscala() {
		return escala;
	}
	public void setEscala(float escala) {
		this.escala = escala;
	}
	
	
}
