import java.applet.Applet;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MakamApplet extends Applet {
	public MakamApplet() {
	}
	/*
	<applet code="MakamApplet" width=300 height=200>
	</applet>	 
*/
	private static final long serialVersionUID = 6222129871010116447L;
	private JTextField txtTypeMessage;
	
	public void init() {
		setLayout(null);
		
		JButton btnActinButton = new JButton("Actin Button");
		btnActinButton.setBounds(6, 33, 117, 29);
		add(btnActinButton);
		
		txtTypeMessage = new JTextField();
		txtTypeMessage.setText("type message");
		txtTypeMessage.setBounds(141, 33, 130, 26);
		add(txtTypeMessage);
		txtTypeMessage.setColumns(10);
		
		JTextPane txtpnShowYourMessage = new JTextPane();
		txtpnShowYourMessage.setText("show your message");
		txtpnShowYourMessage.setBounds(16, 74, 107, 159);
		add(txtpnShowYourMessage);
	}
}
