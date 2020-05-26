import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import org.alicebot.ab.*; // AIML library

public class JooBot extends JFrame implements KeyListener
{
	private JPanel p = new JPanel();
	private JTextArea dialog = new JTextArea(20, 50);
	private JTextArea input = new JTextArea(1, 50);
	private JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private Bot joo = new Bot("Joo Bot");
	private Chat chatSession = new Chat(joo);
	public static void main(String[] args){
		new JooBot();
	}
	
	public JooBot()
	{
		super("Joo Bot");
		setSize(600,400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(0,128,0));
		add(p);
		
        setVisible(true);
		addText("Welcome to Joo Bot!");
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			input.setEditable(false);
			//get user input
			String quote=input.getText();
			input.setText("");
			addText("\n--> You: " + quote + "\n");
			quote.trim();
			
			String jooResponse = chatSession.multisentenceRespond(quote);
			addText("--> Mr.Joo: " + jooResponse);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}
	
	public void addText(String str)
	{
		dialog.setText(dialog.getText()+str);
	}
}