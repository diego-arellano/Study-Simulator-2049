package librerias;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */

public class Cuestionario {
	
	/**
	 * atributos de la clase Custionario. 
	 * Son privados.
	 * */
	private Pregunta[][] preguntas; //Matriz de preguntas
	private Double[][] califs; //Matriz de Doubles que representa las califiaciones
	private int[] numPreguntas; //Arreglo del n�mero actual de preguntas en el rengl�n de la matriz de preguntas. 
	private int[] numCalifs; //N�mero actual de calificaciones
	private int numMaterias; //N�mero actual de materias
	private final int MAX = 10; //N�mero m�ximo de preguntas
	private final int MAXM = 8; //N�mero m�ximo de materias
	private String[] materias; //Arreglo de los nombres de las materias 
	
	/**
	 * Constructor de la clase Cuestionario. Inicializamos los arreglos, matrices y variable de entero en 0. 
	 * */
	public Cuestionario() {
		preguntas = new Pregunta[MAXM][MAX];
		califs = new Double[MAXM][25]; //Podemos almacenar 25 calificaciones de las 8 materias.
		numCalifs = new int[MAXM]; //Arreglo del n�mero de calificaciones de las 8 materias.
		materias = new String[MAXM]; //Arreglo del nombre de las materias
		numPreguntas = new int[MAXM]; //Arreglo del numero de preguntas de las 8 materias.
		numMaterias = 0; //inicializamos el n�mero de materias en 0.
	}
	
	/**
	 * Damos de alta una pregunta en la matriz, dada la pregunta y el rengl�n donde se quiere a�adir.
	 * Se aumenta el n�mero de preguntas. Regresa un booleano, si se aument� el n�mero regresa true. 
	 * */
	public boolean altaPregunta(Pregunta p, int i) {
		boolean res = false;
		int n = numPreguntas[i];
		if(numPreguntas[i] < MAX) {
			numPreguntas[i] = ManejadorArreglosGenerico.insertaAlFinal(preguntas[i], numPreguntas[i], p);
			res = numPreguntas[i] > n;
		}
		return res;
	}
	
	/**
	 * Damos de baja una pregunta en la matriz, dada la pregunta y el rengl�n donde se encuentra.
	 * Se disminuye el n�mero de preguntas. Regresa un booleano, si disminuy� el n�mero regresa true. 
	 * */
	public boolean bajaPregunta(Pregunta p, int i) {
		boolean res = false;
		int n = numPreguntas[i];
		numPreguntas[i] = ManejadorArreglosGenerico.eliminaEnDesordenado(preguntas[i], numPreguntas[i], p);
		res = numPreguntas[i] < n;
		return res;
	}
	
	/**
	 * Damos de alta una materia en el arreglo, dada la materia.
	 * Se aumenta el n�mero de materias. Regresa un booleano, si aument� el n�mero regresa true. 
	 * */
	public boolean altaMateria(String materia) {
		boolean res = false;
		int nm = numMaterias;
		if(numMaterias < MAXM) {
			numMaterias = ManejadorArreglosGenerico.insertaAlFinal(materias, numMaterias, materia);
			res = numMaterias > nm;
		}
		return res;
	}
	
	/**
	 * Damos de baja una materia en el arreglo, dada la materia.
	 * Se disminuye el n�mero de materias. Regresa un booleano, si disminuy� el n�mero regresa true. 
	 * */
	public boolean bajaMateria(String materia) {
		boolean res = false;
		int nm = numMaterias;
		int i = ManejadorArreglosGenerico.buscaSecuencialDesordenada(materias, numMaterias, materia);
		if(i >= 0)
			numMaterias = ManejadorArreglosGenerico.eliminaEnDesordenado(materias, numMaterias, materia);
		res = numMaterias < nm;
		if(res) {
			numPreguntas[i] = 0;
			numCalifs[i] = 0;
		}
		return res;
	}
	
	/**
	 * Damos de alta una calificaci�n en la matriz, dado un Double y el rengl�n donde lo a�adiremos.
	 * Se aumenta el n�mero de calificaciones. Regresa un booleano, si aument� el n�mero regresa true. 
	 * */
	public boolean altaCalif(Double calif, int i) {
		boolean res = false;
		if(numCalifs[i] < 25) {
			califs[i][numCalifs[i]] = calif;
			numCalifs[i]++; 
			res = true;
		}
		return res;
	}
	
	/**
	 * Llamamos el Manejador de Matrices Gen�rico para obtener la posici�n del dato mayor en el rengl�n.
	 * */
	public Double mayorCalifMateria(int i) {
		return califs[i][ManejadorMatricesGenerico.mayRen(califs, numCalifs[i], i)];
	}
	
	/**
	 * Llamamos el Manejador de Matrices Gen�rico para obtener la posici�n del dato menor en el rengl�n.
	 * */
	public Double menorCalifMateria(int i) {
		return califs[i][ManejadorMatricesGenerico.minRen(califs, numCalifs[i], i)];
	}
	
	/**
	 * Pedimos la posici�n de la pregunta y la posici�n a la que la queremos cambiar. Tambi�n el rengl�n. 
	 * Si la posici�n de la pregunta es mayor a la deseada se tiene que hacer un corrimiento a la derecha.
	 * Al contrario, se tiene que hacer uno a la izquierda.
	 * Se regresa un booleano.
	 * */
	public boolean cambiarPosPregunta(int p, int i, int pos) { 
		boolean res = false;
		pos--;
		p--;
		if(p >= 0 && pos >=0 && pos < numPreguntas[i] && p < numPreguntas[i]) {
			Pregunta aux = preguntas[i][p];
			if(p > pos) 
				ManejadorArreglosGenerico.unCorrimientoDer(preguntas[i], p, pos);
			else 
				ManejadorArreglosGenerico.unCorrimientoIzq(preguntas[i], pos + 1, p);
			preguntas[i][pos] = aux;
			res = true;
		}
		return res;
	}
	
	/**
	 * Llamamos el Manejador de Arreglos Gen�rico para invertir la posici�n de las preguntas. Se le da el rengl�n. 
	 * */
	public void invertirPreguntas(int i) {
		ManejadorArreglosGenerico.invierte(preguntas[i], numPreguntas[i]);;
	}
	
	/**
	 * Da la materia con m�s n�mero de preguntas.
	 * */
	public String materiaMasPreguntas() {
		int max = numPreguntas[0];
		int materia = 0;
		for(int i = 1; i < numMaterias; i++)
			if(max < numPreguntas[i]) {
				max = numPreguntas[i];
				materia = i;
			}
		return materias[materia];
	}
	
	/**
	 * Suma los valores de un rengl�n de la matriz.
	 * */
	public Double sumaRen(int i) {
		Double suma = 0.0;
		for(int j = 0; j < numCalifs[i]; j++)
			suma += califs[i][j];
		return suma;
	}
	
	/**
	 * Gets de la clase.
	 * */
	public String getPregunta(int i, int j) {
		return preguntas[i][j].getPregunta();
	}
	public String getOpcion(int i, int j, int op) {
		return preguntas[i][j].getOpcion(op);
	}
	
	public int getRespuestaCorrecta(int i, int j) {
		return preguntas[i][j].getRespuestaCorrecta();
	}

	public int getNp(int i) {
		return numPreguntas[i];
	}
	
	public int getNumMaterias() {
		return numMaterias;
	}
	
	public String getMateria(int i) {
		return materias[i];
	}
	
	/**
	 * toString() de la clase. Recorre las dos matrices y da sus datos. Al igual que algunos valores de las funciones propias.
	 * */
	public String toString() {
		StringBuilder cad = new StringBuilder();
		for(int i = 0; i < numMaterias; i++) {
			cad.append(materias[i]+"\n");
			cad.append("N�mero de preguntas = " + numPreguntas[i] + "\n");
			for(int j = 0; j < numPreguntas[i]; j++)
				cad.append("Pregunta " + (j+1) + " = " + preguntas[i][j] + "\n");
		}
		cad.append("\nHay " + numMaterias + " materias");
		cad.append("\nLa materia con m�s n�mero de preguntas es: " + materiaMasPreguntas());
		cad.append("\n\nCalificaciones de las materias\n");
		for(int i = 0; i < numMaterias; i++) {
			cad.append("\n"+materias[i]+"\n");
			if(numCalifs[i] != 0) {
				cad.append("Calificaci�n m�s alta --->" + mayorCalifMateria(i) + "\n");
				cad.append("Calificaci�n m�s baja --->" + menorCalifMateria(i) + "\n");
				cad.append("Promedio --->" + sumaRen(i)/numCalifs[i] + "\n");
			}
			for(int j = 0; j < numCalifs[i]; j++)
				cad.append((j+1) + " quiz = " + califs[i][j] + "\n");
		}
		return cad.toString();
	}
}
