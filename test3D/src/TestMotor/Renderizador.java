package TestMotor;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import modelos.ModeloConTextura;
import modelos.ModeloRaw;

public class Renderizador {
	public void preparar() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);		
	}
	
	public void render(ModeloConTextura modeloConTextura) {
		ModeloRaw modelo = modeloConTextura.getModeloRaw();
		GL30.glBindVertexArray(modelo.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modeloConTextura.getTextura().getIdTextura());
		GL11.glDrawElements(GL11.GL_TRIANGLES, modelo.getVertexCont(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
