package ejecutables;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import librerias.Cuestionario;
import librerias.Pregunta;
import librerias.VistaInstrucciones;
import librerias.VistaMenu;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */
public class VistaControlador extends VistaMenu {

	/**
	 * Los atributos de la clase tienen que ser est�ticos para que podamos acceder a ellos en otras clases sin tener que instanciar un objeto de tipo VistaControlador.
	 * VistaControlador hereda todos los atributos de la VistaMenu.
	 */	
	protected static Cuestionario c;
	protected static String s;
	
	/**
	 * constructor para a�adir los action listeners a los botones y funcionen y leer el archivo. 
	 * Este constructor es al que se llama la primera vez que se corre la aplicaci�n. 
	 */
	public VistaControlador (String t) {
		super(t);
		File ent=new File("preguntas.txt");
		c = new Cuestionario();
		Scanner archivo=null;
		String pregunta, op1, op2, op3, op4,materia;
		int resp = 1, n;
		
		{
		try{
			archivo=new Scanner(ent);
		}catch (FileNotFoundException fnfe){
			System.err.println(fnfe);
			System.exit(-1);
		}
		n=archivo.nextInt();
		archivo.nextLine();
		materia = archivo.nextLine();
		materias.addItem(materia);
		c.altaMateria(materia);
		for (int i=1; i<=n; i++) {
			pregunta = archivo.nextLine();
			op1 = archivo.nextLine();
			op2 = archivo.nextLine();
			op3 = archivo.nextLine();
			op4 = archivo.nextLine();
			String[] opciones = {op1,op2,op3,op4};
			c.altaPregunta((new Pregunta(pregunta,opciones,resp)),materias.getSelectedIndex());
			resp++;
			info.setText(c.toString());
		}
		archivo.close();
		}
		
		btQuiz.addActionListener(new EscuchaBoton());
		btAgregar.addActionListener(new EscuchaAgregar());
		btEliminar.addActionListener(new EscuchaEliminar());
		btInstrucciones.addActionListener(new EscuchaInstrucciones());
		btMateria.addActionListener(new EscuchaAgregarMateria());
		btBajaMateria.addActionListener(new EscuchaEliminarMateria());
		btPos.addActionListener(new EscuchaCambiarPregunta());
		btInvierte.addActionListener(new EscuchaInvertirPregunta());
		infoExamen.append("EX�MENES PASADOS\n");
	}
	/**
	 * Constructor para a�adir los action listeners a los botones y funcionen. 
	 * Este constructor es al que se llama cuando se ha hecho un quiz o m�s. El VistaControladorQuiz pasa un objeto de Cuestionario y un String a VistaControlador. 
	 */
	public VistaControlador (String t, Cuestionario cues, String cad) {
		super(t);
		c = cues;
		s = cad;
		for(int i = 0; i < c.getNumMaterias(); i++) //A�adir las materias en el Cuestionario a la lista desplegable
			materias.addItem(c.getMateria(i));
		info.setText(c.toString());
		btQuiz.addActionListener(new EscuchaBoton());
		btAgregar.addActionListener(new EscuchaAgregar());
		btEliminar.addActionListener(new EscuchaEliminar());
		btInstrucciones.addActionListener(new EscuchaInstrucciones());
		btMateria.addActionListener(new EscuchaAgregarMateria());
		btBajaMateria.addActionListener(new EscuchaEliminarMateria());
		btPos.addActionListener(new EscuchaCambiarPregunta());
		btInvierte.addActionListener(new EscuchaInvertirPregunta());
		infoExamen.append("EX�MENES PASADOS\n"); 
		infoExamen.append(s);	//A�adir la informaci�n de los resultados de ex�menes pasados al Text Area
	}
	/**
	 * Bot�n para pasar a la ventana del quiz. Si no hay preguntas se deben de agregar. 
	 */
	public class EscuchaBoton implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==btQuiz && c.getNp(materias.getSelectedIndex()) != 0) {
				VistaControlador.super.dispose();
				int materia = materias.getSelectedIndex();
				new VistaControladorQuiz("Study Simulator 2049", c, materia);
			} else {
				JOptionPane.showMessageDialog(null,"AGREGA UNA PREGUNTA", "Study Simulator 2049", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Bot�n para agregar una pregunta a la matriz, el rengl�n se determina por la materia seleccionada en la lista plegable.
	 * Se debe de verficar que el valor que se de en los text fields son num�ricos.
	 * Se actualiza la informaci�n del text area.  
	 */
	public class EscuchaAgregar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String pregunta = txPregunta.getText();
			int resp;
			try {
				resp = Integer.parseInt(txRespuesta.getText());
			} catch(NumberFormatException ex) {
				resp = 0;
			}
			String[] opciones = {txOpcion1.getText(),txOpcion2.getText(),txOpcion3.getText(),txOpcion4.getText()};
			if(resp >= 1 && resp <=4) {
				Pregunta p = new Pregunta(pregunta, opciones, resp);
				c.altaPregunta(p,materias.getSelectedIndex());
				info.setText(c.toString());
			} else 
				JOptionPane.showMessageDialog(null,"RESPUESTA NUM�RICA ENTRE 1 Y 4", "Study Simulator 2049", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Bot�n para eliminar una pregunta a la matriz, el rengl�n se determina por la materia seleccionada en la lista plegable. 
	 * Para eliminar, se debe de poner la pregunta en el text field de pregunta y presionar el bot�n.
	 * Se actualiza la informaci�n del text area.
	 */
	public class EscuchaEliminar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String pregunta = txPregunta.getText();
			Pregunta p = new Pregunta(pregunta);
			c.bajaPregunta(p,materias.getSelectedIndex());
			info.setText(c.toString());
		}
	}
	
	/**
	 * Bot�n para abrir una ventana que contiene las instrucciones.
	 * Al presionar el bot�n se instancia una nueva VistaInstrucciones.
	 */
	public class EscuchaInstrucciones implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			new VistaInstrucciones("Instrucciones");
		}
	}
	
	/**
	 * Bot�n para agregar una materia al arreglo, se incrementa el numMaterias que corresponde al n�mero de renglones en la matriz. 
	 * Si se agreg� la materia se a�ade a la lista plegable.
	 * Se actualiza la informaci�n del text area.
	 */
	public class EscuchaAgregarMateria implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String materia = txMateria.getText();
			if(c.altaMateria(materia)) {
				materias.addItem(materia);
				materias.setSelectedItem(materia);
			}
			info.setText(c.toString());
		}
	}
	
	/**
	 * Bot�n para eliminar una materia al arreglo, se decrementa el numMaterias que corresponde al n�mero de renglones en la matriz y se llena la matriz de nulls en ese rengl�n. 
	 * Si se elimin� la materia se elimina de la lista plegable.
	 * Se actualiza la informaci�n del text area.
	 */
	public class EscuchaEliminarMateria implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String materia = txMateria.getText();
			if(c.bajaMateria(materia))
				materias.removeItem(materia);
			info.setText(c.toString());
		}
	}
	
	/**
	 * Bot�n para cambiar la posici�n de una pregunta dado el n�mero de la pregunta y el n�mero al que se quiere cambiar. 
	 * Se verifica si los valores dados en los text fields son num�ricos.
	 * Se actualiza la informaci�n del text area.
	 */
	public class EscuchaCambiarPregunta implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int pos;
			int p;
			int i = materias.getSelectedIndex();
			try {
				pos = Integer.parseInt(txPos.getText());
				p = Integer.parseInt(txCambiar.getText());
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,"PON LOS N�MEROS DE LAS PREGUNTAS EN LAS CASILLAS", "Study Simulator 2049", JOptionPane.WARNING_MESSAGE);
				p = 0;
				pos = 0;
			}
			if(c.cambiarPosPregunta(p, i, pos))
				info.setText(c.toString());
		}
	}
	
	/**
	 * Se invierte el orden de las preguntas.
	 */
	public class EscuchaInvertirPregunta implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			c.invertirPreguntas(materias.getSelectedIndex());
			info.setText(c.toString());
		}
	}
	
	/**
	 * Si el objeto Cuestionario no est� vac�o, se llama al segundo constructor para guardar el cuestionario y resultados anteriores.
	 * SI el objeto Cuestionario s� est� vac�o, se llama al primer constructor que lee el archivo. 
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(c!=null)
			new VistaControlador("Study Simulator 2049",c, s);
		else
			new VistaControlador("Study Simulator 2049");
	}
}
