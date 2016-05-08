package texturas;

public class TexturaModelo {
	private int idTextura;
	
	private float shineDamper = 1;
	private float reflejo = 0;
	
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflejo() {
		return reflejo;
	}

	public void setReflejo(float reflejo) {
		this.reflejo = reflejo;
	}

	public TexturaModelo(int id) {
		idTextura = id;
	}
	
	public int getIdTextura() {
		return idTextura;
	}
}
