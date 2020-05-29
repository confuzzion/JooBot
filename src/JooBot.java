import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;

import org.alicebot.ab.*; // AIML library

public class JooBot extends JFrame implements KeyListener
{
	private JPanel panel = new JPanel();
	private JTextArea dialog = new JTextArea(20, 50);
	private JTextArea input = new JTextArea(1, 50);
	private JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	private Bot joo;
	private Chat chatSession;
	public static void main(String[] args){
		new JooBot();
	}
	
	public JooBot()
	{
		super("Joo Bot");
		setSize(600, 400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		panel.add(scroll);
		panel.add(input);
		panel.setBackground(new Color(0,128,0));
		add(panel);
		
        setVisible(true);
		addText("Welcome to Joo Bot!");
		MagicBooleans.trace_mode = true;
		MagicBooleans.jp_tokenize = false;
		joo = new Bot("joobot", getResourcesPath(), "chat");
		chatSession = new Chat(joo);
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input.setEditable(false);
			//get user input
			String quote = input.getText();
			input.setText("");
			addText("\nYou: " + quote + "\n");
			quote.trim();
			if(quote.equalsIgnoreCase("clear"))
			{
				dialog.setText("Terminal Reset.");
			}
			else if(quote.equalsIgnoreCase("exit"))
			{
				System.exit(0);
			}
			else
			{
				String jooResponse = chatSession.multisentenceRespond(quote);
				addText("Mr.Joo: " + jooResponse);
			}
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
		dialog.setText(dialog.getText() + str);
	}

	private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        // System.out.println(path);
        return path;
    }
}