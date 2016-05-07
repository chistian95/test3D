package TestMotor;

public class ModeloRaw {
	private int vaoID;
	private int vertexCont;
	
	public ModeloRaw(int vaoID, int vertexCont) {
		this.vaoID = vaoID;
		this.vertexCont = vertexCont;
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int getVertexCont() {
		return vertexCont;
	}

	public void setVertexCont(int vertexCont) {
		this.vertexCont = vertexCont;
	}
}
