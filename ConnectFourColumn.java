import acm.graphics.GRect;
/** Organisational structure containing piece arrays
 * 
 * @author ifranci
 *
 */

public class ConnectFourColumn extends GRect{
double width=20;
double height=10;
int colNum;
Piece[] column=new Piece[6];
public int blockSize=70;
	public ConnectFourColumn(double width, double height,int colNum){
		super(width, height);
		this.colNum=colNum;
		for(int i=0;i<column.length;i++){
			column[i]=new Piece(blockSize,blockSize,colNum,i);
		}
	}
	/** Finds the lowest piece in the column that has not been touched
	 * 
	 * @return
	 */
	public Piece findLowest(){
		int i=0;
		while(i<6){
			if(column[i].touched==true ){
				if(i<=0){
					return null;
				}
				return column[--i];
			}
			i++;
		}
		if(i==6)
			return column[5];
		return column[i];
	}
	
}
