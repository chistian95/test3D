package modelos;

import texturas.TexturaModelo;

public class ModeloConTextura {
	private ModeloRaw modeloRaw;
	private TexturaModelo textura;
	
	public ModeloConTextura(ModeloRaw modelo, TexturaModelo textura) {
		modeloRaw = modelo;
		this.textura = textura;
	}

	public ModeloRaw getModeloRaw() {
		return modeloRaw;
	}

	public void setModeloRaw(ModeloRaw modeloRaw) {
		this.modeloRaw = modeloRaw;
	}

	public TexturaModelo getTextura() {
		return textura;
	}

	public void setTextura(TexturaModelo textura) {
		this.textura = textura;
	}

}
