package brickBreaker;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame jframe = new JFrame();
		GamePlay gamePlay = new GamePlay();
		jframe.setBounds(10,10,800,800);
		jframe.setTitle("Brick Breaker Ball");
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePlay);

	}

}
