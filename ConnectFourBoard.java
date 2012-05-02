import java.awt.Color;
/** The physical board.
 * Contains checks for victory,loss and draw
 * Also contains the method to add pieces to it.
 * @author ifranci
 *
 */

public class ConnectFourBoard {
	ConnectFourColumn[] connectCols;
	public ConnectFourBoard(){
		this.connectCols=new ConnectFourColumn[7];
		fillColumns();
		
	}
	/** Initializes the board's columns
	 * 
	 * @return
	 */
	public ConnectFourColumn[] fillColumns(){

		for(int i=0;i<7;i++){
			this.connectCols[i]=new ConnectFourColumn(90,90*6,i);
		}
		return this.connectCols;
	}
	/** Checks to see if the board has any empty spaces. Is only activated if the selected piece is 
	 * one of the top pieces
	 * @return
	 */
	public boolean isFull(){
		int i=0;
		while(i<7){
			if(this.connectCols[i].column[0].touched==true){
				i++;
				continue;
			}else{
				return false;
			}
		}	
		return true;

	}
/** Adds a piece to the board, with its corresponding color
 * 
 * @param clickedOn
 * @param turnNumb
 * @return
 */
	public Piece addPiece(Piece clickedOn, int turnNumb){
		Piece x=connectCols[clickedOn.location].findLowest();
		if(x==null){
			return null;
		}
		x.changeColor(turnNumb);
		x.touched=true;
		
		
			return x;
			
		
	}
	/** Removes all the pieces on the board
	 * 
	 * @param board
	 */
	public static void removePieces(ConnectFourBoard board){
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				board.connectCols[i].column[j].touched=false;
				board.connectCols[i].column[j].changeColor(0);
				

			}
		}
	}
	/** Calls all directional connect four methods to see if there is a connect Four
	 * 
	 * @param clickedOn
	 * @param board
	 * @return
	 */
	public boolean isConnectFour(Piece clickedOn, ConnectFourBoard board){
		Piece x=clickedOn;
		if(findUpFour(x,this))
			return true;
		if(findLR(x,this))
			return true;
		if(findDowniagFour(x,this))
			return true;
		if(findUpiagFour(x,this))
			return true;
		return false;
	}
	/** Checks the clicked piece in the downwards diagonal direction.
	 * 
	 * @param clickedOn
	 * @param board
	 * @return
	 */
	public boolean findDowniagFour(Piece clickedOn, ConnectFourBoard board){
		int length=0;
		int cFourColumn=clickedOn.location;
		int cFourIndex=clickedOn.colIndex;
		while(cFourColumn>0 && cFourIndex>0){
			if(clickedOn.pieceColor==board.connectCols[(--cFourColumn)].column[--cFourIndex].pieceColor){
				continue;
			}else{
				cFourColumn++;
				cFourIndex++;
				break;
			}
			
		}
		
		while(cFourColumn<=6 && cFourIndex<=5){
			if(clickedOn.pieceColor==board.connectCols[cFourColumn].column[cFourIndex].pieceColor){
				 length++;
				 cFourColumn++;
				 cFourIndex++;
			 }else{
				 break;
			 }
		}
		if(length>=4){
			//System.out.println("There is a connect four that goes diagDown");
			return true;
		}
		//System.out.println("There is not a connect four that goes diagDown");
		return false;
	}
	/** Checks if there is a connectFour in the upwards diagonal direction
	 * 
	 * @param clickedOn
	 * @param board
	 * @return
	 */
	public boolean findUpiagFour(Piece clickedOn,ConnectFourBoard board){
		int length=0;
		int cFourColumn=clickedOn.location;
		int cFourIndex=clickedOn.colIndex;
		while(cFourColumn>0 && cFourIndex<5){
			if(clickedOn.pieceColor==board.connectCols[(--cFourColumn)].column[++cFourIndex].pieceColor){
				continue;
			}else{
				cFourColumn++;
				cFourIndex++;
				break;
			}
			
		}
		
		while(cFourColumn<=6 && cFourIndex<=5 && cFourColumn>=0 && cFourIndex>=0){
			if(clickedOn.pieceColor==board.connectCols[cFourColumn].column[cFourIndex].pieceColor){
				 length++;
				 cFourColumn++;
				 cFourIndex--;
			 }else{
				 break;
			 }
		}
		if(length>=4){
		//	System.out.println("There is a connect four that goes diagUP");
			return true;
		}
		//System.out.println("There is not a connect four that goes diagUP");
		return false;
	}
	/** Checks to see if there is a connect four in the down direction. 
	 * 
	 * @param clickedOn
	 * @param board
	 * @return
	 */
	public boolean findUpFour(Piece clickedOn,ConnectFourBoard board){
		int length=0;
		int cFourIndex=clickedOn.colIndex;
	
		
		while(cFourIndex<=5){
			if(clickedOn.pieceColor==board.connectCols[clickedOn.location].column[cFourIndex].pieceColor){
				 length++;
				 cFourIndex++;
			 }else{
				 break;
			 }
		}
		
	
		if(length>=4){
			//System.out.println("There is a connect four that goes up/down");
			return true;
		}
		//System.out.println("There is not a connect four that goes up/down");
		return false;
		
	}
	/** Checks to see if there is a connect four that goes east/west
	 * 
	 * @param clickedOn
	 * @param board
	 * @return
	 */
	public boolean findLR(Piece clickedOn,ConnectFourBoard board){
		int cFourColumn=clickedOn.location;
		int length=0;
		
		while(cFourColumn>0){
			if(clickedOn.pieceColor==board.connectCols[(--cFourColumn)].column[clickedOn.colIndex].pieceColor){
				continue;
			}else{
				cFourColumn++;
				break;
			}
			
		}
		
		while(cFourColumn<=6){
			if(clickedOn.pieceColor==board.connectCols[cFourColumn].column[clickedOn.colIndex].pieceColor){
				 length++;
				 cFourColumn++;
			 }else{
				 break;
			 }
		}
			
		
	
		if(length>=4){
			//System.out.println("There is a connect four east/west");
				return true;
			}
		//System.out.println("There is not a connect four east/west");

		return false;
	}
public ConnectFourBoard newBoard(){
	ConnectFourBoard knew=new ConnectFourBoard();
		return knew;
}
}
