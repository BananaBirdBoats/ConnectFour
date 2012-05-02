import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import acm.program.Program;
import acmx.export.javax.swing.JTextField;


@SuppressWarnings("serial")
public class ConnectFour extends Program{
	public ConnectFourCanvas canvas=new ConnectFourCanvas();
	public ConnectFourBoard current=new ConnectFourBoard();
	public ServerSocket server;
	public Socket socket;
	public int turnNumber=0;
	public int lastTurn=1;
	private JTextField portField;
	private JTextField hostField;
	private JTextField outgoingField;
	public JTextArea incomingField;
	public JOptionPane jPane;
	public PrintStream sendStream;
	private Scanner rcvStream;
	ListenerThread textThread;
	
public ConnectFour(){
	
}

	public void init(){
		resize(620, 640);
		canvas.addMouseListener(this);
		canvas.setBackground(Color.CYAN);
		
		hostField = new JTextField(20);
		add(new JLabel("Host: "), NORTH);
		add(hostField, NORTH);

		portField = new JTextField(6);
		add(new JLabel("Port: "), NORTH);
		add(portField, NORTH);

		add(new JButton("Server"), NORTH);
		add(new JButton("Client"), NORTH);

		outgoingField = new JTextField(40);
		outgoingField.setActionCommand("Send Msg");
		add(outgoingField, SOUTH);
		outgoingField.addActionListener(this);
		
	
		add(new JButton("Send Msg"), SOUTH);
		
		incomingField = new JTextArea(8,42);
	
	
		canvas.add(incomingField, 10, 425);
		
		add(canvas);
		canvas.drawBoard(current);	
		

		addActionListeners();
		

	}
	
	public void mouseClicked(MouseEvent e) {
		//If the last turn is the same as your own. Cant go.
		if(lastTurn==turnNumber){
			ErrorDialog.error(canvas, "It is not your turn!");
		}else{
			//Makes sure you are clicking on a piece slot
			if(canvas.getElementAt(e.getX(),e.getY())==null){
				return;
			}else if(canvas.getElementAt(e.getX(), e.getY()) instanceof Piece){
				Piece c=(Piece) (canvas.getElementAt(e.getX(), e.getY()));
				
				//Adds a piece to c's column
				c=current.addPiece(c,turnNumber);				
				//switches whose turn it is
				this.lastTurn*=-1;
				//Sends to the client that a move occured
				sendStream.println("^M:O&V#E$ "+c.location+" "+c. colIndex+" "+lastTurn);
				//Checks victory
				if(current.isConnectFour(c,current)){
					sendStream.println("~W!I(N*%");
					ErrorDialog.winner(canvas,(int) (Math.random()*5));			
					//This resets the board after a win.
					sendStream.println("C%O%N^F&I*R&M");
					ConnectFourBoard.removePieces(current);
					canvas.drawBoard(current);			
						
							
							
				}else if(current.isFull()){
					sendStream.println("*D&R^A&W");
					ErrorDialog.draw(canvas, (int) (Math.random()*5));
					
					sendStream.println("C%O%N^F&I*R&M");
					ConnectFourBoard.removePieces(current);
					canvas.drawBoard(current);	
				}
		
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("Server")) {
				int port=Integer.parseInt(portField.getText());
				 server=new ServerSocket(port);
				socket=server.accept();
				rcvStream= new Scanner(socket.getInputStream());
				sendStream=new PrintStream(socket.getOutputStream());
			 textThread=new ListenerThread(rcvStream,incomingField,this,current,-1);
			this.turnNumber=-1;
			JOptionPane.showMessageDialog(canvas, "Welcome to Connect Four! You will be playing as Red");
			textThread.start();
			
			} else if (e.getActionCommand().equals("Client")) {
				int port=Integer.parseInt(portField.getText());
				String host=hostField.getText();
				socket=new Socket(host,port);
				rcvStream= new Scanner(socket.getInputStream());
				sendStream=new PrintStream(socket.getOutputStream());	
			textThread=new ListenerThread(rcvStream,incomingField,this,current,1);
			this.turnNumber=1;
			JOptionPane.showMessageDialog(canvas, "Welcome to Connect Four! You will be playing as Black");

				textThread.start();
				
			}else if (e.getActionCommand().equals("Send Msg")) {
				//All system messages are flagged with the prefixes below.
				//If a player were to attempt to cheat by typing the prefix and a system command.
				//The system will spit out an error message and reset.
				if(outgoingField.getText().contains("~W!I(N*%") || outgoingField.getText().contains("C%O%N^F&I*R&M")|| 
						outgoingField.getText().contains("*D&R^A&W") || outgoingField.getText().contains("^M:O&V#E$")){
					JOptionPane.showMessageDialog(canvas, "Cheater!");
					outgoingField.setText("");
					return;
				}else{
				//Otherwise sends chat messages to client.
				sendStream.println("Opponent: "+outgoingField.getText());
				incomingField.append("You: "+outgoingField.getText()+"\n");
				outgoingField.setText("");
				}
			}
		}catch (Exception err) {
				
				ErrorDialog.error(canvas,"Could not connect to opponent!");
			}
		
		
		}
	}

