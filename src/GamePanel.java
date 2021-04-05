import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;






public class GamePanel extends JPanel implements ActionListener
{
	static final int SCREEN_WIDTH=1600;
	static final int SCREEN_HEIGHT=800;
	static final int UNIT_SIZE = 100;
	static final int DELAY = 3;
	Timer timer;


	public player player1=new player(0,UNIT_SIZE*4,1,1);
	boss boss1= new boss(player1,11,4,1,1,"/boss/boss_strip4.png");
	


	GamePanel()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MykeyAdapter());
		timer = new Timer(DELAY,this);
		timer.start();

		floor_2_0.setfloor(x,box);//設定方塊陣列
	}
	public long fps_time = System.currentTimeMillis();
	public long fps_cout =0;
	public long fps_dat=100000;
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		draw(g);
		fps_cout=fps_cout+1;
		if(System.currentTimeMillis() - fps_time>=1000)
		{
			fps_dat=fps_cout;
			fps_cout=0;
			fps_time=System.currentTimeMillis();
		}
	    Font font = new Font("Serif", Font.PLAIN,20);
	    g.setFont(font);
		g.drawString(String.valueOf(fps_dat), 100,100);

	}
	public void draw(Graphics g)
	{
		if(magic_door.level==1)
			draw_map(g);	
		if(magic_door.level==2)
			draw_map2(g);


		player1.move(g);	
		//boss1.boss_attack(g);
	}
	
	Image backgram= new ImageIcon(ClassLoader.getSystemResource("Background/background.png")).getImage();
	
	floor_2_0 test_floor = new floor_2_0(5,3,1,2);
	floor_2_0 test_floor2 = new floor_2_0(4,4,1,3);
	floor_2_0 test_floor3 = new floor_2_0(6,2,1,1);
	int x[][]= {{5,3,1,2},{5,4,1,2},{8,2,1,2}};		
	floor_2_0 box[] = new floor_2_0[3];

	floor Grass_floor=new  floor(0,5,1,16);
	floor soil_floor=new  floor(0,6,2,16);
	magic_door door=new magic_door(15,4,1,1);
	String floor2_ct;
	npc txt = new npc(2,4,1,1);
	npc txt2 = new npc(6,2,1,1);
	trap trap1= new	trap(7,4,1,1);
	trap trap2= new	trap(8,4,1,1);
	trap trap3= new	trap(9,4,1,1);
	trap trap4= new	trap(10,4,1,1);
	public void draw_map(Graphics g)
	{
		drawBackground(backgram, g);

		Grass_floor.drawFloor("Ground Grass/5.png",g);
		player1.gravity(!(Grass_floor.Collision_floor(player1) || player1.D_stop));//沒碰到掉下去
		
		soil_floor.drawFloor("Ground Grass/4.png",g);		
//		test_floor.drawFloor("Ground Grass/box.png",g);
//		test_floor2.drawFloor("Ground Grass/box.png",g);
//		test_floor3.drawFloor("Ground Grass/box.png",g);
	
		floor_2_0.drawFloor("Ground Grass/box.png",box,g);

		player1.floor_check(floor_2_0.Collision_floor2(box,player1));//沒碰到掉下去 有撞牆功能
		
		txt.draw_npc("npc/plank.png", g);
		txt.Dialog_box("上下左右鍵移動",player1,g);
		
		txt2.draw_npc("npc/plank.png", g);
		txt2.Dialog_box("避開危險",player1,g);
		
		trap1.draw_trap(8,player1,g);
		trap2.draw_trap(8,player1,g);
		trap3.draw_trap(8,player1,g);
		trap4.draw_trap(8,player1,g);
		
		door.draw_door("Ground Grass/circle4 glow.png", g);
		door.level_change(player1);
		drawAuxiliary_line(g);
	}

	public void draw_map2(Graphics g)
	{
		drawBackground(backgram, g);

		Grass_floor.drawFloor("Ground Grass/5.png",g);
		player1.gravity(!(Grass_floor.Collision_floor(player1) || player1.D_stop));//沒碰到掉下去
		
		soil_floor.drawFloor("Ground Grass/4.png",g);		
		
		txt.draw_npc("npc/plank.png", g);
		txt.Dialog_box("空白建功擊魔物",player1,g);
		boss1.boss_attack(g);
	}
	
	public void drawAuxiliary_line(Graphics g)
	{

		for(int i=0;i<SCREEN_WIDTH/UNIT_SIZE;i++)
		{
			g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
		}
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++)
		{
			g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
		}
	}
	public void drawBackground(String path,Graphics g)
	{


		g.drawImage(backgram,0, 0,SCREEN_WIDTH,SCREEN_HEIGHT,this);

	}	

	public void drawBackground(Image backgram,Graphics g)
	{
		g.drawImage(backgram,0, 0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
	}	
	

	public class MykeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_SPACE:
					player1.attack=true;
					break;
				case KeyEvent.VK_LEFT:
					player1.LN= false;
					player1.RN= false;
					player1.R= false;
					player1.L = true;
					break;
				case KeyEvent.VK_RIGHT:
					player1.LN= false;
					player1.RN= false;
					player1.L= false;
					player1.R = true;
					break;
				case KeyEvent.VK_UP:
					if(!player1.jumpset)
					{
						if( player1.RN ==true || player1.RU == true || 	player1.R == true)
							player1.RU =true;
						if( player1.LN ==true || player1.LU == true ||	player1.L == true)
							player1.LU =true;
						
	
						player1.jumpset = true;
						player1.x_dat=player1.x;
						player1.y_dat=player1.y;;

					}
					else
					{
						if( player1.RN ==true || player1.RU == true)
							player1.RU =true;
						if( player1.LN ==true || player1.LU == true)
							player1.LU =true;
					}
					break;
			}
		}
		public void keyReleased(KeyEvent e) 
		{
			  if(player1.LU==true)
	    	  {
	    		  player1.LU=false; 
	    		  player1.jumpset = false;
	    	  }
			  else if(player1.RU==true )
	    	  {
	    		  player1.RU =false;
	    		  player1.jumpset = false;
	    	  }
			  else if(player1.L==true )
	    	  {
				player1.RN= false;
				player1.L= 	false;
	    		player1.LN = true;
	    	  }
			  else if(player1.R==true )
	    	  {
				 player1.LN= false;
				 player1.R=  false;
	    		 player1.RN = true;
	    	  } 	  
		}
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		repaint();
	}

}
