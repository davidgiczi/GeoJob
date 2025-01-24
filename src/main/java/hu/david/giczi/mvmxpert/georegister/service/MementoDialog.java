package hu.david.giczi.mvmxpert.georegister.service;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;


public class MementoDialog {

	
	private JFrame jFrame;
	private List<Note> noteList;
	private Color color = new Color(112, 128, 144);
	private Font font = new Font("Arial", Font.BOLD, 14);
	private JTextField delayInputField;
	private int delayInMin;
	private Timer timer;
	
	public MementoDialog(List<Note> noteList, String title) {
		this.delayInputField = new JTextField();
		this.noteList = noteList;
		createDialog(title);
	}
	
	public void setDelayInSec(int delayInSec) {
		this.delayInMin = delayInSec;
	}



	private void createDialog(String title) {
		jFrame = new JFrame(title);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jFrame.setVisible(false);
			}
		});
		jFrame.setSize(650,  150 + getMementoContentNumberValue() * 100);
		jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
		for (Note note : noteList) {
			addMementoPanel(note);
		}
		setIcon();
		addInputDataPanel();
		jFrame.setLocationRelativeTo(null);
		jFrame.setAlwaysOnTop(true);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
	}
	private int getMementoContentNumberValue() {		int value = 0;
		for (Note note : noteList) {
			value += note.getContentList().size();
		}
		return value;
	}
	private void addMementoPanel(Note note) {
	JPanel panel =  new JPanel();
	panel.setPreferredSize(new Dimension(650, note.getContentList().size() * 30));
	panel.setBackground(Color.WHITE);
	panel.setBorder(BorderFactory
			.createTitledBorder(BorderFactory.createEtchedBorder(),
					Memento.getRemainingDayValues().get(noteList.indexOf(note)), TitledBorder.LEFT, TitledBorder.TOP, font, color));
	
	for (String content : note.getContentList()) {
		JLabel contentLabel = new JLabel("<html>" + content + "</html>", SwingConstants.CENTER);
		contentLabel.setPreferredSize(new Dimension(600, 30));
		panel.add(contentLabel);
	}
	jFrame.add(panel);
}
	
	private void addInputDataPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(650, 10));
		delayInputField.setPreferredSize(new Dimension(320, 30));
		delayInputField.setHorizontalAlignment(JTextField.CENTER);
		delayInputField.setFont(font);
		delayInputField.setForeground(Color.LIGHT_GRAY);
		delayInputField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		delayInputField.setCaretColor(Color.WHITE);
		String instruction = "Hány perccel később emlékeztessen újra?";
		delayInputField.setText(instruction);
		delayInputField.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				if( delayInputField.getText().trim().equals(instruction) ) {
					delayInputField.setForeground(color);
					delayInputField.setText("");
				}	
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if( delayInputField.getText().trim().isEmpty()) {
					delayInputField.setForeground(Color.LIGHT_GRAY);
					delayInputField.setText(instruction);
				}
			}	
		});
		JButton okButton = new JButton("Ok");
		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		okButton.setFont(font);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( delayInputField.getText().trim().equals(instruction) ) {
					jFrame.setVisible(false);
				}
				else {
					try {
						int delayValue = Integer.parseInt(delayInputField.getText().trim());
						if( 0 >= delayValue ) {
							throw new NumberFormatException();
						}
						setDelayInSec(delayValue);
						jFrame.setVisible(false);
						long nextReminderTime = System.currentTimeMillis() + 1000 * 60 * delayInMin;
						timer = new Timer(1000 * 60, new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if( System.currentTimeMillis() > nextReminderTime ) {
									new Memento();
									timer.stop();
								}
								
							}
						});
						timer.start();
					}
					catch (NumberFormatException n) {
						getInfoMessage("Hibás bementi érték",  "A késleltetés perc értéke csak pozitív egész szám lehet.");
					}
				}
				
			}
		});
		panel.add(Box.createVerticalStrut(35));
		panel.add(Box.createHorizontalStrut(150));
		panel.add(delayInputField);
		panel.add(Box.createHorizontalStrut(140));
		panel.add(okButton);
		jFrame.add(panel);
	}
	
	private void getInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(jFrame, "<html><h3>" + message + "</h3></html>", title, JOptionPane.INFORMATION_MESSAGE);
    }
		
	private void setIcon() {
		try {
			File[] file = new File(GeoJobPropertyStore.URL4).listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".jpg") || name.endsWith(".png");
				}
			});
			jFrame.setIconImage(ImageIO.read(file[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		GeoJobPropertyStore.loadPropertiesFromFile();
		new Memento();
	}

}
