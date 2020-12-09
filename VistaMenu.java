package librerias;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */
public class VistaMenu extends JFrame {
	
	/**
	 * atributos de la VistaMenu. La VistaMenu hereda las funciones y atributos de la clase JFrame. 
	 * */
		protected JButton btAgregar, btEliminar, btQuiz, btInstrucciones,btMateria, btBajaMateria, btPos, btInvierte;
		private JPanel opcionesPanel, menuPanel, altasPanel, materiasPanel;
		private JLabel lbTitulo, lbPregunta, lbOpcion1, lbOpcion2, lbOpcion3, lbOpcion4, lbRespuesta, lbMateria, lbPos;
		protected JTextField txPregunta, txOpcion1, txOpcion2, txOpcion3, txOpcion4, txRespuesta, txMateria, txPos, txCambiar;
		protected JTextArea info, infoExamen;
		private JScrollPane infos, infosExamen;
		protected JComboBox materias;
		
		/**
		 * constructor para instanciar los componentes de la vista.
		 * Recibe un String que corresponde al título que se observa en la ventana en la pare superior. 
		 * */
		
		public VistaMenu(String t) {
			super(t);
			
			ImageIcon logo = new ImageIcon("dinob.jpg"); //Esta imagen es la que aparece en la parte superior izquierda de la ventana.
			Border border = BorderFactory.createEtchedBorder(Color.BLUE, Color.LIGHT_GRAY); //Se define un borde que usaremos posteriormente. 
			
			/**
			 * Usaremos 4 paneles en los que añadiremos diferentes componentes
			 * */
			
			opcionesPanel = new JPanel();
			opcionesPanel.setBackground(Color.BLUE);
			opcionesPanel.setBounds(0,0,150,400);
			opcionesPanel.setBorder(BorderFactory.createEtchedBorder());
			opcionesPanel.setLayout(new GridLayout(4,1));
			
			menuPanel = new JPanel();
			menuPanel.setBackground(Color.WHITE);
			menuPanel.setBounds(150, 0, 634, 65);
			menuPanel.setBorder(border);
			
			altasPanel = new JPanel();
			altasPanel.setBackground(Color.WHITE);
			altasPanel.setBounds(150,65,334,335);
			altasPanel.setBorder(border);
			altasPanel.setLayout(new GridLayout(3,2));
			
			materiasPanel = new JPanel();
			materiasPanel.setBackground(Color.BLUE);
			materiasPanel.setBounds(784,0,300,400);
			
			/**
			 * Se inicializan los diferentes componentes que posteriormente se añadiran al panel llamado altaPanel
			 * */
			//Labels que se añadirán al panel, indican los atributos de la pregunta.
			lbPregunta = new JLabel("Pregunta");
			lbPregunta.setBorder(border);
			lbPregunta.setHorizontalAlignment(JLabel.CENTER);
			lbRespuesta = new JLabel("Respuesta");
			lbRespuesta.setBorder(border);
			lbRespuesta.setHorizontalAlignment(JLabel.CENTER);
			lbOpcion1 = new JLabel("Opción 1");
			lbOpcion1.setBorder(border);
			lbOpcion1.setHorizontalAlignment(JLabel.CENTER);
			lbOpcion2 = new JLabel("Opción 2");
			lbOpcion2.setBorder(border);
			lbOpcion2.setHorizontalAlignment(JLabel.CENTER);
			lbOpcion3 = new JLabel("Opción 3");
			lbOpcion3.setBorder(border);
			lbOpcion3.setHorizontalAlignment(JLabel.CENTER);
			lbOpcion4 = new JLabel("Opción 4");
			lbOpcion4.setBorder(border);
			lbOpcion4.setHorizontalAlignment(JLabel.CENTER);
			
			//Los text fields en donde se escribirán los atributos de la pregunta.
			txPregunta = new JTextField(50);
			txRespuesta = new JTextField(1);
			txOpcion1 = new JTextField(50);
			txOpcion2 = new JTextField(50);
			txOpcion3 = new JTextField(50);
			txOpcion4 = new JTextField(50);
			
			//Se añaden los componentes en el panel
			altasPanel.add(lbPregunta);
			altasPanel.add(txPregunta);
			altasPanel.add(lbRespuesta);
			altasPanel.add(txRespuesta);
			altasPanel.add(lbOpcion1);
			altasPanel.add(txOpcion1);
			altasPanel.add(lbOpcion2);
			altasPanel.add(txOpcion2);
			altasPanel.add(lbOpcion3);
			altasPanel.add(txOpcion3);
			altasPanel.add(lbOpcion4);
			altasPanel.add(txOpcion4);
			
			/**
			 * Se inicializan los diferentes componentes que posteriormente se añadiran al panel llamado opcionesPanel.
			 * 4 botones que agregarán o eliminar una pregunta, inciará el quiz o mostrará las instrucciones. 
			 * */
			btAgregar = new JButton("Añadir");
			btAgregar.setBackground(Color.WHITE);
			btAgregar.setBorder(border);
			
			btEliminar = new JButton("Eliminar");
			btEliminar.setBackground(Color.WHITE);
			btEliminar.setBorder(border);
			
			btQuiz = new JButton("Tomar quiz");
			btQuiz.setBackground(Color.WHITE);
			btQuiz.setBorder(border);
			
			btInstrucciones = new JButton("Instrucciones");
			btInstrucciones.setBackground(Color.WHITE);
			btInstrucciones.setBorder(border);
			
			//Se agregan los botons al panel que tiene el GridLayout
			opcionesPanel.add(btAgregar);
			opcionesPanel.add(btEliminar);
			opcionesPanel.add(btQuiz);
			opcionesPanel.add(btInstrucciones);
			
			//Se inicializa un label que tiene el título y se agrega al panel menuPanel
			lbTitulo = new JLabel("Study Simulator 2049");
			lbTitulo.setFont(new Font("SimSun",Font.ITALIC, 32));
			menuPanel.add(lbTitulo);
			
			/**
			 * Se añade un TextArea a un Scroll Pane que muestra la información de la clase. 
			 * */
			info = new JTextArea(50,50);
			infos = new JScrollPane(info);
			infos.setBounds(485,65,300,336);
			info.setEditable(false);
			
			//Se añade el Scroll Pane directamente al Frame
			this.add(infos);
			
			/**
			 * Se añaden nuevos componentes que ayudan a añadir o eliminar materias, cambiar el orden de las preguntas y mostrar los resultados de exámenes anteriores. 
			 * */
			materias = new JComboBox(); //La lista plegable añade todas las materias en la matriz, según la materia seleccionada se añade o elimina la pregunta en ese renglón. 
			materias.setBounds(830, 25, 200, 30);
			this.add(materias);
			lbMateria = new JLabel("Materia");
			lbMateria.setBounds(840, 280, 50, 30);
			lbMateria.setForeground(Color.WHITE);
			this.add(lbMateria);
			txMateria = new JTextField(20);
			txMateria.setBounds(890, 280, 120, 30);
			this.add(txMateria);
			btMateria = new JButton("Agregar Materia");
			btMateria.setBounds(860, 320, 130, 30);
			btMateria.setBackground(Color.WHITE);
			this.add(btMateria);
			btBajaMateria = new JButton("Eliminar Materia");
			btBajaMateria.setBounds(860, 360, 130, 30);
			btBajaMateria.setBackground(Color.WHITE);
			this.add(btBajaMateria);
			
			lbPos = new JLabel("Cambiar");
			lbPos.setBounds(880,180,87,20);
			lbPos.setForeground(Color.WHITE);
			this.add(lbPos);
			txCambiar = new JTextField(2);
			txCambiar.setBounds(932, 180, 20, 20);
			this.add(txCambiar);
			txPos = new JTextField(2);
			txPos.setBounds(972, 180, 20, 20);
			this.add(txPos);
			
			btPos = new JButton("Cambiar posición");
			btPos.setBounds(860, 210, 140, 20);
			this.add(btPos);
			
			btInvierte = new JButton("Invertir preguntas");
			btInvierte.setBounds(860,235,140,20);
			this.add(btInvierte);

			//Se añade el Scroll Pane directamente al Frame
			infoExamen = new JTextArea(20,20);
			infosExamen = new JScrollPane(infoExamen);
			infosExamen.setBounds(830,65,200,100);
			infoExamen.setEditable(false);
			this.add(infosExamen);
			
			/**
			 * Modificaciones correspondientes al Frame, se añaden todos los paneles que utilizamos, hacemos que el usuario pueda cambiar su tamaño,  cambiamos su posición y tamaño y lo hacemos visible. 
			 * */
			this.add(opcionesPanel);
			this.add(menuPanel);
			this.add(altasPanel);
			this.add(materiasPanel);
			this.setResizable(false);
			this.setLayout(null);
			this.setBounds(120,120,1100,439);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
			
			this.setIconImage(logo.getImage()); //Añadimos el ícono al Frame
		}

}
