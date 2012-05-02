import javax.swing.JTextArea;

import acm.graphics.GCanvas;
import acm.graphics.GRect;
import acmx.export.javax.swing.JTextField;
/** A simple canvas
 * 
 * @author ifranci
 *
 */

public class ConnectFourCanvas extends GCanvas {
	
	public ConnectFourCanvas(){
	
	}
	/** Draws one connectFour column
	 * 
	 * @param a
	 * @param row
	 */
	public void drawColumn(ConnectFourColumn a,int row){
			for(int j=0;j<a.column.length;j++){
				add(a.column[j],0+a.blockSize*row,0+a.blockSize*j);
			}
		
		//add(a);
	}
	/** Draws the entire board
	 * 
	 * @param board
	 */
	public void drawBoard(ConnectFourBoard board){
		for(int i=0;i<7;i++){
			drawColumn(board.connectCols[i],i);
		}
	
	
	
	}
}
