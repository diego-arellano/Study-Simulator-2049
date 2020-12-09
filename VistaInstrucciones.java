package librerias;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class VistaInstrucciones extends JFrame {
	
	/**
	 * atributos de la VistaMenu. La VistaMenu hereda las funciones y atributos de la clase JFrame. 
	 * Todos los atributos son privados, ya que esta vista no tiene funcionalidad. 
	 * */
	
	private JPanel instruccionesPanel;
	private JLabel lbInstrucciones;
	private JTextArea explicacion;
	
	/**
	 * constructor para instanciar los componentes de la vista.
	 * Recibe un String que corresponde al t�tulo que se observa en la ventana en la pare superior. 
	 * */
	
	public VistaInstrucciones(String t) {
		super(t);
		ImageIcon logo = new ImageIcon("dinob.jpg");
		
		//En el panel se a�ade un label que contiene el t�tulo.  
		instruccionesPanel = new JPanel();
		instruccionesPanel.setBackground(Color.BLUE);
		instruccionesPanel.setBounds(0, 0, 800, 50);
		
		lbInstrucciones = new JLabel("Instrucciones");
		lbInstrucciones.setBounds(10, 10, 800, 50);
		lbInstrucciones.setFont(new Font("Arial", Font.ITALIC, 25));
		lbInstrucciones.setForeground(Color.WHITE);
		instruccionesPanel.add(lbInstrucciones);
		
		//La explicaci�n se pone en una �rea de texto que no se puede cambiar. Se a�ade directo al JFrame. 
		explicacion = new JTextArea("1. Escribe los datos de la pregunta en las casilla (pregunta, respuesta y opciones) \n2. Presiona a�adir "
				+ "\n3. Si ya a�adiste todas la preguntas necesarias, presiona tomar quiz\n4. Pon a prueba tu conocimiento y eval�a tus resultados\n"
				+ "5. Tambi�n puedes a�adir preguntas de otras materias, a�adiendo una materia y seleccion�ndola en la lista plegable");
		explicacion.setFont(new Font("Sim Sun", Font.PLAIN, 14));
		explicacion.setBounds(2, 50, 800, 200);
		explicacion.setEditable(false);
		this.add(explicacion);
		
		this.add(instruccionesPanel);
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(120,120,800,200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setIconImage(logo.getImage());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new VistaInstrucciones("Instrucciones");
	}
}
