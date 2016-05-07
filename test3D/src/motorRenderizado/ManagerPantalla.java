package motorRenderizado;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class ManagerPantalla {
	private static final int WIDTH = 720;
	private static final int HEIGHT = 720;
	private static final int FPS_TOPE = 120;
	
	public static void crearPantalla() {		
		ContextAttribs atributos = new ContextAttribs(3, 2);
		atributos.withForwardCompatible(true);
		atributos.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), atributos);
			Display.setTitle("Prueba");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);		
	}
	
	public static void actualizarPantalla() {
		Display.sync(FPS_TOPE);
		Display.update();
	}
	
	public static void cerrarPantalla() {		
		Display.destroy();
	}
}
