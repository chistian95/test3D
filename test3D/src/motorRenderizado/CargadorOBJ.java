package motorRenderizado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import modelos.ModeloRaw;

public class CargadorOBJ {
	public static ModeloRaw cargarModeloOBJ(String archivo, Cargador cargador) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/res/"+archivo+".obj"));
		} catch (FileNotFoundException e) {
			System.err.println("Objeto no encontrado");
			e.printStackTrace();
		}
		BufferedReader lector = new BufferedReader(fr);
		String linea;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> texturas = new ArrayList<Vector2f>();
		List<Vector3f> normales = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] arrayVertices = null;
		float[] arrayTexturas = null;
		float[] arrayNormales = null;
		int[] arrayIndices = null;
		
		try {
			while(true) {
				linea = lector.readLine();
				String[] lineaActual = linea.split(" ");
				if(linea.startsWith("v ")) {
					Vector3f vertex = new Vector3f(Float.parseFloat(lineaActual[1]), Float.parseFloat(lineaActual[2]), Float.parseFloat(lineaActual[3]));
					vertices.add(vertex);
				} else if(linea.startsWith("vt ")) {
					Vector2f textura = new Vector2f(Float.parseFloat(lineaActual[1]), Float.parseFloat(lineaActual[2]));
					texturas.add(textura);
				} else if(linea.startsWith("vn ")) {
					Vector3f normal = new Vector3f(Float.parseFloat(lineaActual[1]), Float.parseFloat(lineaActual[2]), Float.parseFloat(lineaActual[3]));
					normales.add(normal);
				} else if(linea.startsWith("f ")) {
					arrayTexturas = new float[vertices.size() * 2];
					arrayNormales = new float[vertices.size() * 3];
					break;
				}
			}
			
			while(linea != null) {
				if(!linea.startsWith("f ")) {
					linea = lector.readLine();
					continue;
				}
				String[] lineaActual = linea.split(" ");
				String[] vertex1 = lineaActual[1].split("/");
				String[] vertex2 = lineaActual[2].split("/");
				String[] vertex3 = lineaActual[3].split("/");
				
				procesarVertex(vertex1, indices, texturas, normales, arrayTexturas, arrayNormales);
				procesarVertex(vertex2, indices, texturas, normales, arrayTexturas, arrayNormales);
				procesarVertex(vertex3, indices, texturas, normales, arrayTexturas, arrayNormales);
				
				linea = lector.readLine();
			}
			lector.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		arrayVertices = new float[vertices.size()*3];
		arrayIndices = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vertex : vertices) {
			arrayVertices[vertexPointer++] = vertex.x;
			arrayVertices[vertexPointer++] = vertex.y;
			arrayVertices[vertexPointer++] = vertex.z;
		}
		
		for(int i=0; i<indices.size(); i++) {
			arrayIndices[i] = indices.get(i);
		}
		
		return cargador.cargarEnVAO(arrayVertices, arrayTexturas, arrayNormales, arrayIndices);
	}
	
	private static void procesarVertex(String[] vertex, List<Integer> indices, List<Vector2f> texturas, List<Vector3f> normales, float[] arrayTexturas, float[] arrayNormales) {
		int vertexActual = Integer.parseInt(vertex[0]) -1;
		indices.add(vertexActual);
		Vector2f texturaActual = texturas.get(Integer.parseInt(vertex[1]) - 1);
		arrayTexturas[vertexActual*2] = texturaActual.x;
		arrayTexturas[vertexActual*2 + 1] = 1 - texturaActual.y;
		Vector3f normalActual = normales.get(Integer.parseInt(vertex[2]) - 1);
		arrayNormales[vertexActual*3] = normalActual.x;
		arrayNormales[vertexActual*3 + 1] = normalActual.y;
		arrayNormales[vertexActual*3 + 2] = normalActual.z;
	}
}
