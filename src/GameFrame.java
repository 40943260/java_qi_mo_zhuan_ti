import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	GameFrame()
	{
	
		this.add(new GamePanel());
		this.setTitle("�]�k�k�Ĥj�Զ��]��");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();//�۰��Y��
		this.setVisible(true);//�e���}��
		this.setLocationRelativeTo(null);//�e���P��
	}
}
