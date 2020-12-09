package librerias;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
/**
 * @author Diego Arellano
 * @version 1.0
 * @date 23/11/20
 * */

public class VistaQuiz extends JFrame {
	/**
	 * atributos de la VistaQuiz. La VistaQuiz hereda las funciones y atributos de la clase JFrame.
	 * Tenemos atributos estáticos para que podamos acceder a ellos sin tener que crear una instancia de la clase. 
	 * */
	protected JRadioButton op1,op2,op3,op4;
	protected JButton btChecar,btSiguiente;
	protected ButtonGroup bgroup;
	protected JLabel lbPregunta, lbRespCorrectas;
	protected static Cuestionario cues;
	protected static StringBuilder cad = new StringBuilder();
	private JPanel panelOp, panelTit;
	protected int numPregunta, numCorrectas;
	protected static int materia;
	
	/**
	 * Constructor de VistaQuiz, tiene como parámetros un objeto de Cuestionario y un entero.
	 * Igualamos los valores que nos dan en el contructor con los atributos correspondiente.
	 * Instanciamos los componentes necesarios para la vista.
	 * */
	public VistaQuiz(String t, Cuestionario c, int ren) {
		super(t);
		ImageIcon logo = new ImageIcon("dinob.jpg");
		Border border = BorderFactory.createEtchedBorder(Color.BLUE, Color.LIGHT_GRAY);
		cues = c;
		materia = ren;
		
		//Creamos una label y un panel que contenga labels con la pregunta y su número y la cantidad de respuestas correctas.
		lbPregunta=new JLabel(numPregunta+1 +". "+ cues.getPregunta(ren,0));
		lbPregunta.setFont(new Font("SimSun",Font.BOLD,18));
		lbPregunta.setHorizontalAlignment(JLabel.CENTER);
		lbRespCorrectas =  new JLabel("Respuestas correctas: "+ numCorrectas);
		lbRespCorrectas.setFont(new Font("SimSun", Font.ITALIC,12));
		
		panelTit = new JPanel();
		panelTit.setBackground(Color.WHITE);
		panelTit.setBounds(0,0,500,80);
		panelTit.setBorder(border);
		panelTit.setLayout(new GridLayout(2,1));
		panelTit.add(lbPregunta);
		panelTit.add(lbRespCorrectas);
		this.add(panelTit);
		
		//Los radio buttons sirven para elegir una opción dependiente de la pregunta.
		op1=new JRadioButton(cues.getOpcion(ren,0,1));
		op1.setBackground(Color.WHITE);

		op2=new JRadioButton(cues.getOpcion(ren,0,2));
		op2.setBackground(Color.WHITE);
		
		op3=new JRadioButton(cues.getOpcion(ren,0,3));
		op3.setBackground(Color.WHITE);
		
		op4=new JRadioButton(cues.getOpcion(ren,0,4));
		op4.setBackground(Color.WHITE);
		
		//El button group sirve para que los radio buttons se comporten como una unidad. Sólo se puede elegir un radio button.
		bgroup = new ButtonGroup();
		bgroup.add(op1);
		bgroup.add(op2);
		bgroup.add(op3);
		bgroup.add(op4);
		
		//Instanciamos un panel con un grid layout. Añadimos los radio buttons.
		panelOp = new JPanel();
		panelOp.setLayout(new GridLayout(3,2));
		panelOp.setBounds(0,80,500,282);
		panelOp.add(op1);
		panelOp.add(op2);
		panelOp.add(op3);
		panelOp.add(op4);
		this.add(panelOp);
		
		//Creamos dos botones y los añadimos al panel
		btChecar=new JButton("CHECAR");
		btChecar.setBounds(100,300,100,30);
		btChecar.setBackground(Color.WHITE);
		btChecar.setBorder(border);
		panelOp.add(btChecar);

		btSiguiente=new JButton("SIGUIENTE");
		btSiguiente.setBounds(350,300,100,30);
		btSiguiente.setBackground(Color.WHITE);
		btSiguiente.setBorder(border);
		panelOp.add(btSiguiente);
		
		this.setLayout(null);
		this.setBounds(150,150,516,401);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(logo.getImage());
	}
	
	
}
