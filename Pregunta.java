package librerias;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */

public class Pregunta implements Comparable<Pregunta> {
	/**
	 * Debemos de escribir implments Comparable<Obj> para que podamos manipular los arreglos o matrices de esta clase con los Manejadores Genéricos. 
	 * Tiene 3 atributos privados. 
	 */
	private String pregunta;
	private String[] opciones;
	private int respuestaCorrecta;

	/**
	 * 2 Constructores. Uno con todos los datos y otro con sólo el String de la pregunta.
	 */
	public Pregunta(String pregunta,String[] opciones,int respuestaCorrecta) {
		this.pregunta = pregunta; //La pregunta en sí 
		this.respuestaCorrecta = respuestaCorrecta; //El número de la respuesta correcta 1-4
		this.opciones = new String[4]; //Arreglo de las opciones que se dan para la pregunta. Son 4 opciones.
		this.opciones = opciones; //Se iguala al arreglo que se da como parámetro.
	}
	
	
	public Pregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	/**
	 * Gets de la clase.
	 */
	public String getPregunta() {
		return pregunta;
	}
	
	public int getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	
	public void setRespuestaCorrecta(int respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	public String getOpcion(int i) { //Se recibe la opción que se quiere obtener 1-4
		return opciones[i-1]; //Se le tiene que restar uno para que corresponda a los índices del arreglo 0-3
	}
	
	/**
	 * Las funcines obligatorias de la clase. 
	 */
	@Override
	public String toString() {
		return pregunta + ", Respuesta Correcta = " + respuestaCorrecta + "\n"; //Sólo regresa la pregunta y la respuesta correcta
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pregunta == null) ? 0 : pregunta.hashCode());
		return result;
	}

	/**
	 * El equals y el compareTo son muy importantes para poder comparar los objetos en los Manejadores posteriormente.
	 * Se hace el equals y compareTo con el String de pregunta. 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pregunta other = (Pregunta) obj;
		if (pregunta == null) {
			if (other.pregunta != null)
				return false;
		} else if (!pregunta.equals(other.pregunta))
			return false;
		return true;
	}

	public int compareTo(Pregunta p) { 
		return pregunta.compareTo(p.pregunta);
	}
	
}
