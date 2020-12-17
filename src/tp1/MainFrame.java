package tp1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private JTextPane resultTextPane = null;

	public MainFrame() {
		
		setTitle("Algorithme Glouton");
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Algorithme Glouton");
		btnNewButton_1.setBounds(256, 48, 150, 23);

		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Algorithme Arbre ");
		btnNewButton.setBounds(508, 48, 150, 23);
		contentPane.add(btnNewButton);
		
		
	    int min = 4;
	    int max = 24;
	    int step = 1;
	    int initValue = 4;	
	    SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(467, 105, 39, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Taille de l'\u00E9chiquier :");
		lblNewLabel.setBounds(337, 108, 120, 14);
		contentPane.add(lblNewLabel);
		
		JTextPane resultTextPane = new JTextPane();
		this.resultTextPane = resultTextPane;
		resultTextPane.setBounds(0, 153, 891, 525);
		contentPane.add(resultTextPane);
		
		
		//Algorithme Glouton ActionListner
		btnNewButton_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {

                        resultTextPane.selectAll();
                        resultTextPane.replaceSelection("");

                            int x = (int) spinner.getValue();

                        Echiquier e = new Echiquier(x);

                        Calendar calendar = Calendar.getInstance();
                        long timeMilli1 = calendar.getTimeInMillis();
                        ArrayList<Cellule> reines = e.algoGlouton();
                        calendar = Calendar.getInstance();
                        long timeMilli2 = calendar.getTimeInMillis();

                        long executionTime = timeMilli2 - timeMilli1;
                        String display = e.toString();
                        displayResult(display, reines, executionTime);	

                    }


		});
		
		//Algorithme Arbre ActionListener
		btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        resultTextPane.selectAll();
                        resultTextPane.replaceSelection("");

                        int x = (int) spinner.getValue();

                        Echiquier e = new Echiquier(x);

                        ArrayList<Cellule> reines = new  ArrayList<>();

                        Calendar calendar = Calendar.getInstance();
                        long timeMilli1 = calendar.getTimeInMillis();
                        e.algoArbre(reines,0);
                        calendar = Calendar.getInstance();
                        long timeMilli2 = calendar.getTimeInMillis();

                        long executionTime = timeMilli2 - timeMilli1;
                        String display = e.toString();
                        displayResult(display, reines, executionTime);	

                    }
            });

            resultTextPane.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent arg0) {
                        // TODO Auto-generated method stub
                        resultTextPane.setEditable(false);
                }

                @Override
                public void focusLost(FocusEvent arg0) {
                        // TODO Auto-generated method stub
                        resultTextPane.setEditable(true);
                }
	    });
		
	}
	
        private void displayResult(String display,ArrayList<Cellule> reines,long executionTime){
            
            for(int i =0; i<display.length(); i++) {
                if(display.charAt(i) == 'Q') {
                       appendToPane(resultTextPane, ""+display.charAt(i), Color.RED);
                }
                else {
                        appendToPane(resultTextPane, ""+display.charAt(i), Color.BLACK);
                }
            }

            String details = "\n*************************************************\n"
                    + "Temps d'execution = "+executionTime+" ms\n"
                    + "Nombre de reines = "+reines.size()+"\n"
                    + "Emplacements des reines (X,Y)\n";
            for(Cellule reine : reines){
                details += "("+(reine.getX()+1)+","+(reine.getY()+1)+"); ";
            }
            appendToPane(resultTextPane, details, Color.BLACK);
        }
        
	private void appendToPane(JTextPane tp, String msg, Color c) {
            StyleContext sc = StyleContext.getDefaultStyleContext();
            javax.swing.text.AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

            int len = tp.getDocument().getLength();
            tp.setCaretPosition(len);
            tp.setCharacterAttributes(aset, false);
            tp.replaceSelection(msg);
		
	}
	
	
	

	
	
}
