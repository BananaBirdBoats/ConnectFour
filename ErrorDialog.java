import javax.swing.JOptionPane;
/**Class that deals with win,loss, and draw messages
 * 
 * @author ifranci
 *
 */

@SuppressWarnings("serial")
public class ErrorDialog{
	public static void error(ConnectFourCanvas a,String s){
		JOptionPane.showMessageDialog(a, s);
	}
	/** 
	 * Selects one of the  messages to display to the winner of the match. Designed to be used with a random number as i so a varied message
	 * is displayed.
	 * @param a
	 * @param i
	 */
	public static void winner(ConnectFourCanvas a,int i){
		switch(i){
		case 0:JOptionPane.showMessageDialog(a, "You ALMOST lost. Except not. Winner!");
		break;
		case 1:JOptionPane.showMessageDialog(a, "Congratulations! You Win!");
		break;
		case 2:JOptionPane.showMessageDialog(a, "You win! You should go pro!");
		break;
		case 3:JOptionPane.showMessageDialog(a, "An epic victory!");
		break;
		case 4:JOptionPane.showMessageDialog(a, "VICTORY!");
		break;


		
		}
		
	}
	/** Selects a message based on the random int i to the loser of the match.
	 * 
	 * @param a
	 * @param i
	 */
	public static void loser(ConnectFourCanvas a,int i){
		switch(i){
		case 0:JOptionPane.showMessageDialog(a, "You ALMOST won. Except not. Loser!");
		break;
		case 1:JOptionPane.showMessageDialog(a, "Sorry! You did not win!");
		break;
		case 2:JOptionPane.showMessageDialog(a, "You lose! I would practice more!");
		break;
		case 3:JOptionPane.showMessageDialog(a, "An epic loss!");
		break;
		case 4:JOptionPane.showMessageDialog(a, "DEFEAT!");
		break;


		
		}
	}
	/** Selects a message based on the random int i to both players of the match. It is not designed to
	 * display the same draw message to each player.
	 * @param a
	 * @param i
	 */
		public static void draw(ConnectFourCanvas a,int i){
			switch(i){
			case 0:JOptionPane.showMessageDialog(a, "Tie!");
			break;
			case 1:JOptionPane.showMessageDialog(a, "Draw! You both lose!");
			break;
			case 2:JOptionPane.showMessageDialog(a, "Draw! You both win!");
			break;
			case 3:JOptionPane.showMessageDialog(a, "An epic draw!");
			break;
			case 4:JOptionPane.showMessageDialog(a, "NO ONE WINS!");
			break;


			
			}
	}
}
