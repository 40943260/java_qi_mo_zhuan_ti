import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class sword  implements ImageObserver
{

	String path = "boss/sword.png";
	public   int x;
	public   int y;

	public   int reference_x;
	public   int reference_y;
	
	public   int reference_floor_WIDTH;
	public   int reference_floor_HEIGHT;
	
	public   int WIDTH;
	public   int HEIGHT;
	public int UNIT_SIZE=100;
	
	public long Invincible_ct = System.currentTimeMillis();
	public int Invincible_delay = 500;
	public void Collision_laser(player f1)
	{

		int f_x_ul = f1.x;//¥ª¤W¨¤
		int f_y_ul = f1.y;
		
		int f_x_ur = f1.x+f1.WIDTH;//¥k¤W¨¤
		int f_y_ur = f1.y;
		
		int f_x_dl = f1.x;
		int f_y_dl = f1.y+f1.HEIGHT;//¥ª¤U¨¤
		
		int f_x_dr = f1.x+f1.WIDTH;;
		int f_y_dr = f1.y+f1.HEIGHT;//¥k¤U¨¤

//---------------------------------------------------------------			
		int to_small=50;
		int bat_x_ul = this.x+to_small;//¥ª¤W¨¤
		int bat_y_ul = this.y+to_small;
		
		int bat_x_ur = this.x+this.WIDTH-to_small;//¥k¤W¨¤
		int bat_y_ur = this.y+to_small;
		
		int bat_x_dl = this.x+to_small;
		int bat_y_dl = this.y+this.HEIGHT-to_small;//¥ª¤U¨¤
		
		int bat_x_dr = this.x+this.WIDTH-to_small;;
		int bat_y_dr = this.y+this.HEIGHT-to_small;//¥k¤U¨¤

		if( f_x_ur >= bat_x_ul && bat_x_ur>=f_x_ul )
		{
			if(bat_y_ul<=f_y_dl && f_y_ul<=bat_y_dl)
				if(!f1.Invincible)
				{
					f1.life-=1;
					f1.Invincible=true;
				}
				if(System.currentTimeMillis()-Invincible_ct>=Invincible_delay)
				{
					f1.Invincible=false;
					Invincible_ct=System.currentTimeMillis();
				}

		}
	}
	Image sword_img;
	public  sword(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT)
	{
		this.x=x*UNIT_SIZE;
		this.y=y*UNIT_SIZE;
		reference_x=x;
		reference_y=y;
		this.reference_floor_WIDTH= reference_floor_WIDTH;
		this.reference_floor_HEIGHT=reference_floor_HEIGHT;
		WIDTH=UNIT_SIZE*reference_floor_WIDTH;
		HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
		sword_img= new ImageIcon(ClassLoader.getSystemResource(path)).getImage();	

	}
	
	public void draw_sword(Graphics g,player f1)
	{
		
		g.drawImage(sword_img,x, y,WIDTH,HEIGHT,this);
		Collision_laser(f1);
	}
	
	
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
