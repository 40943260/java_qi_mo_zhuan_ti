import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	GameFrame()
	{
	
		this.add(new GamePanel());
		this.setTitle("魔法女孩大戰黑魔王");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();//自動縮放
		this.setVisible(true);//畫面開啟
		this.setLocationRelativeTo(null);//畫面致中
	}
}
