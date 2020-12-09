package ejecutables;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import librerias.Cuestionario;
import librerias.VistaQuiz;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */

public class VistaControladorQuiz extends VistaQuiz {
	
	/**
	 * Constructor para a�adir los action listeners a los botones y funcionen. 
	 * Este constructor es al que se llama cuando se presiona el bot�n de "Tomar quiz" en la VistaMenu. 
	 * Como par�metros se recibe un cuestionario y un entero, se pasan por la VistaQuiz para igualar el cuestionario y el rengl�n de la VistaMenu. 
	 */
	public VistaControladorQuiz(String t, Cuestionario c, int ren) {
		super(t, c, ren);
		btChecar.addActionListener(new EscuchaBotonChecar());
		btSiguiente.addActionListener(new EscuchaBotonSiguiente());
	}
	
	/**
	 * Obtenemos el bot�n que el usuario seleccion� y revisamos si corresponde con la respuesta de la pregunta.
	 * Actualizamos la pregunta y respuestas.
	 */
	public class EscuchaBotonChecar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int resp = 0; //Revisamos cada bot�n para ver si fue seleccionado, dependiendo de c�al fue seleccionado se inicializa la variable resp.
			if(op1.isSelected())
				resp= 1;
			else if(op2.isSelected())
				resp= 2;
			else if(op3.isSelected())
				resp= 3;
			else if(op4.isSelected())
				resp= 4;
				
			if(resp == cues.getRespuestaCorrecta(materia,numPregunta)) { //Llamamos la funci�n que nos da la respuesta correcta de la pregunta. Recibimos el rengl�n y la columna correspondiente de la matriz.
				JOptionPane.showMessageDialog(null,"CORRECTO", "STUDY", JOptionPane.QUESTION_MESSAGE); //Le ense�amos un mensaje que indica que esta bien.
				numCorrectas++;
				lbRespCorrectas.setText("Respuestas correctas: " + numCorrectas); //Si la respuesta es correcta aumentamos el n�mero de respuestas correctas y actualizamos la label
			}
			else
				JOptionPane.showMessageDialog(null,"INCORRECTO", "STUDY", JOptionPane.ERROR_MESSAGE); //Si es incorrecta le ensa�amos un mensaje que indica que la respuesta es incorrecta. 
			
			EscuchaBotonSiguiente sig = new EscuchaBotonSiguiente(); //Llamamos la funci�n de la clase EscuchaBotonSiguiente que actualiza la pregunta y respuestas indipendientemente de la respuesta.
			sig.actionPerformed(ae);
		}
	}
	
	/**
	 * Cambia la informaci�n de los radio buttons y la label de la pregunta. 
	 * Si el numPregunta es igual al n�mero total de preguntas - 1 se vuelve a la vista principal, si no, se actualiza la informaci�n. 
	 */
	public class EscuchaBotonSiguiente implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(numPregunta == cues.getNp(materia)-1) {
				JOptionPane.showMessageDialog(null,"HAS COMPLETADO EL QUIZ\nCORRECTAS: " + numCorrectas + " DE " + (numPregunta+1), "STUDY", JOptionPane.PLAIN_MESSAGE); //Le muestra al usuario cuantas preguntas correctas tuvo
				cad.append(cues.getMateria(materia).toUpperCase() + " CORRECTAS: " + numCorrectas + " DE " + (numPregunta+1) + "\n"); //El StringBuilder tiene la informaci�n del examen. La materia y los resultados. 
				cues.altaCalif(Double.parseDouble(numCorrectas+"")/(numPregunta+1)*10, materia); //Se debe de dar de alta la calificaci�n obtenida en la matriz de calificaciones. Se da la calificaci�n y el rengl�n. 
				new VistaControlador("Study Simulator 2049", cues, cad.toString()); //Instanciamos una VistaControlador con el cuestionario y el String que usamos. 
				VistaControladorQuiz.super.dispose(); //Cerramos la Vista
			} else {
				bgroup.clearSelection();
				numPregunta++; //aumentamos el n�mero de pregunta
				lbPregunta.setText(numPregunta+1 + ". " +cues.getPregunta(materia,numPregunta)); ////Actualizamos la pregunta. 
				op1.setText(cues.getOpcion(materia,numPregunta,1)); //Actualizamos las opciones.
				op2.setText(cues.getOpcion(materia,numPregunta,2));
				op3.setText(cues.getOpcion(materia,numPregunta,3));
				op4.setText(cues.getOpcion(materia,numPregunta,4));
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VistaControladorQuiz("Study Simulator 2049", cues, materia);
	}
}
		
