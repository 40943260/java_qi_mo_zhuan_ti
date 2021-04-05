import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class trap implements ImageObserver 
{
	public   int x;
	public   int y;

	public   int reference_x;
	public   int reference_y;
	
	public   int reference_floor_WIDTH;
	public   int reference_floor_HEIGHT;
	
	public   int WIDTH;
	public   int HEIGHT;
	

	public int UNIT_SIZE=100;
	

	public trap(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT)
	{
		this.x=x*UNIT_SIZE;
		this.y=y*UNIT_SIZE;
		reference_x=x;
		reference_y=y;
		this.reference_floor_WIDTH= reference_floor_WIDTH;
		this.reference_floor_HEIGHT=reference_floor_HEIGHT;
		WIDTH=UNIT_SIZE*reference_floor_WIDTH;
		HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
		cut_trap(8);
	}
	
	public BufferedImage[] SubImg ;
	
	public void cut_trap(int how_much_move)
	{
		SubImg = new  BufferedImage[how_much_move];
		BufferedImage bat =null;
		URL image_path=getClass().getResource("/trap/spike_trap_full.png");
		try 
		{
			bat = ImageIO.read(image_path);
		} 
		catch (IOException e){}

		int tWidth = bat.getWidth();
		int tHeight = bat.getHeight();
		int eWidth=tWidth/how_much_move;
		int eHeight=tHeight;
		for(int i=0;i<how_much_move;i++)
		{
			SubImg[i] =  bat.getSubimage(i*eWidth, 0, eWidth, eHeight);
		}
	}
	int trap_ct=0;
	long trap_animtion_ct = System.currentTimeMillis();
	long trap_ct_delay=75;
	int oppo_draw = 1;
	public void draw_trap(int how_much_move,player f1,Graphics g)
	{

		g.drawImage(SubImg[trap_ct],x,y,WIDTH,HEIGHT,this);	
		Collision_bat(f1);
		if(System.currentTimeMillis()-trap_animtion_ct>=trap_ct_delay)
		{
			trap_animtion_ct=System.currentTimeMillis();
			trap_ct+=oppo_draw;
		}
		if(trap_ct==how_much_move-1 || trap_ct==0)
		{
			oppo_draw=oppo_draw*-1;
			if(trap_ct==how_much_move-1)
				trap_ct=trap_ct-1;
			if(trap_ct==0)
				trap_ct=trap_ct+1;
		}
	}
	public long Invincible_ct = System.currentTimeMillis();
	public int Invincible_delay = 500;
	public void Collision_bat(player f1)
	{
		int f_x_ul = f1.x;//¥ª¤W¨¤
		int f_y_ul = f1.y;
		
		int f_x_ur = f1.x+f1.WIDTH;//¥k¤W¨¤
		int f_y_ur = f1.y;
		
		int f_x_dl = f1.x;
		int f_y_dl = f1.y+f1.HEIGHT;//¥ª¤U¨¤
		
		int f_x_dr = f1.x+f1.WIDTH;
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
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
