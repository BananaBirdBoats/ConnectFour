
import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class ListenerThread extends Thread{
	private Scanner input;
	private JTextArea textBox;
	public ConnectFour board;
	public ConnectFourBoard curent;
	public int innateTurn;
	
	public ListenerThread(Scanner input, JTextArea textBox,ConnectFour bard,ConnectFourBoard burd,int innateTurn) {
		this.input = input;
		this.textBox = textBox;
		this.curent=burd;
		this.innateTurn=innateTurn;
		this.board=bard;
	}
	/** Allows the client to locate the piece that was clicked on in the host
	 * 
	 * @param location
	 * @param colIndex
	 * @return
	 */
	public Piece findPiece(int location,int colIndex){
		return curent.connectCols[location].column[colIndex];
	}
	public void run(){

//All system processes are sent as text through one thread and one socket.
while(input.hasNextLine()){
	String v=input.nextLine();
	//If this prefix is detected then it will read it as a move and add it to client board.
	if(v.startsWith("^M:O&V#E$ ")){
		String[] s=v.split(" ");
		
		board.lastTurn=Integer.parseInt(s[3]);
		if(innateTurn==-1){
		this.curent.addPiece(findPiece(Integer.parseInt(s[1]),Integer.parseInt(s[2])),1);
		
		}else{
			this.curent.addPiece(findPiece(Integer.parseInt(s[1]),Integer.parseInt(s[2])),-1);
		}
	
	}else{
		//If opponent has won the game then it will display a losing message.
		if(v.equals("~W!I(N*%")){
			ErrorDialog.loser(board.canvas,(int)(Math.random()*5));
			continue;
			
		}else if(v.equals("C%O%N^F&I*R&M")){		
					//This will recieve that the opponent board has reset itself after
					//a win,draw or loss. Then it resets its own board.
					ConnectFourBoard.removePieces(curent);
					board.canvas.drawBoard(curent);
					continue;
				
				
			
			
		}else if(v.equals("*D&R^A&W")){
			//If neither opponent wins, a draw is declared.
			ErrorDialog.draw(board.canvas, (int)(Math.random()*5));
		}
		 
			textBox.append(v +"\n");
		
	
		}
}

	}
}
		
	
	

