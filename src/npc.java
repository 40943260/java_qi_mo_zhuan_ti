import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class npc  implements ImageObserver
{
	public   int x=0;//參考座標
	public   int y=0;//參考座標
	
	public   int real_x=0;//參考座標
	public   int real_y=0;//參考座標
	
	public   int reference_floor_WIDTH=1;
	public   int reference_floor_HEIGHT=1;
	
	public   int WIDTH=1;
	public   int HEIGHT=1;
	static final int UNIT_SIZE = 100;
	
	public   int level = 1;


	public npc(int floor_x,int floor_y,int reference_floor_HEIGHT,int reference_floor_WIDTH) 
	{
		this.x=floor_x;
		this.y=floor_y;
		this.reference_floor_WIDTH=reference_floor_WIDTH;
		this.reference_floor_HEIGHT=reference_floor_HEIGHT;
		WIDTH=UNIT_SIZE*reference_floor_WIDTH;
		HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
		real_x=floor_x*UNIT_SIZE;
		real_y=floor_y*UNIT_SIZE;
	}
	
	
	public void draw_npc(String path,Graphics g)
	{
		Image npc= new ImageIcon(ClassLoader.getSystemResource(path)).getImage();		
		g.drawImage(npc,x*UNIT_SIZE,y*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE,this);
	}

	public boolean Collision_floor(player f1)
	{

		int f_x_ul = f1.x;//左上角
		int f_y_ul = f1.y;
		
		int f_x_ur = f1.x+f1.WIDTH;//右上角
		int f_y_ur = f1.y;
		
		int f_x_dl = f1.x;
		int f_y_dl = f1.y+f1.HEIGHT;//左下角
		
		int f_x_dr = f1.x+f1.WIDTH;;
		int f_y_dr = f1.y+f1.HEIGHT;//右下角

//---------------------------------------------------------------			
		int bat_x_ul = this.real_x;//左上角
		int bat_y_ul = this.real_y;
		
		int bat_x_ur = this.real_x+this.WIDTH;//右上角
		int bat_y_ur = this.real_y;
		
		int bat_x_dl = this.real_x;
		int bat_y_dl = this.real_y+this.HEIGHT;//左下角
		
		int bat_x_dr = this.real_x+this.WIDTH;;
		int bat_y_dr = this.real_y+this.HEIGHT;//右下角


		if( f_x_ur >= bat_x_ul && bat_x_ur>=f_x_ul )
		{
			if(bat_y_ul<=f_y_dl && f_y_ul<=bat_y_dl)
			{
				return true;
			}
		}
		return false;
	}
	Image box= new ImageIcon(ClassLoader.getSystemResource("npc/button.png")).getImage();	
	static final int SCREEN_WIDTH=1600;
	static final int SCREEN_HEIGHT=800;

	public void  Dialog_box(String txt,player f1,Graphics g)
	{
		if(Collision_floor(f1))
		{
			g.drawImage(box,0,UNIT_SIZE*5,SCREEN_WIDTH,UNIT_SIZE*3,this);
			
		    Font font = new Font("Serif", Font.PLAIN,60);
		    g.setFont(font);
		    g.drawString(txt, UNIT_SIZE+50, UNIT_SIZE*6);
		}
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
