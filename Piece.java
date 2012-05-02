import java.awt.Color;

import acm.graphics.GOval;

/** Lowest structure for organisation in ConnectFour,
 * represents the individual pieces themselves.
 * @author ifranci
 *
 */
public class Piece extends GOval{
public Color pieceColor=Color.WHITE;
int location;
int colIndex;
boolean touched;
	public Piece(double width, double height,int colNum,int colIndex) {
		
		super(width, height);
		location=colNum;
		this.colIndex=colIndex;
		this.setFillColor(pieceColor);
		this.setFilled(true);
		touched=false;
		
	}
	/** This allows the piece to recognise if it has been placed on the board
	 * 
	 */
	public void setTouched(){
		this.touched=true;
	}
	/** Changes the piece color of the selected piece to whoever's turn it is.
	 * 
	 * @param turn
	 */
	public void changeColor(int turn){
		if(turn==-1){
			this.setFillColor(Color.RED);
			this.pieceColor=Color.RED;
			
		}else if(turn==0){
			this.setFillColor(Color.WHITE);
		}else{
			this.setFillColor(Color.BLACK);
			this.pieceColor=Color.BLACK;
		}
		
	}
	
		public String toString(){
			return location+" "+colIndex;
		}
		
		
}
