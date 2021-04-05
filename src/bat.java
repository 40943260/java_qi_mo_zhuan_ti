import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class bat  extends Thread  implements ImageObserver 
{

	public   int x;
	public   int y;

	public   int reference_x;
	public   int reference_y;
	
	public   int reference_floor_WIDTH;
	public   int reference_floor_HEIGHT;
	
	public   int WIDTH;
	public   int HEIGHT;
	
	String path = "/boss/bat.png";
	public int UNIT_SIZE=100;
	

	public bat(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT,player f1)
	{
		this.x=x*UNIT_SIZE;
		this.y=y*UNIT_SIZE;
		reference_x=x;
		reference_y=y;
		this.reference_floor_WIDTH= reference_floor_WIDTH;
		this.reference_floor_HEIGHT=reference_floor_HEIGHT;
		this.path=path;
		WIDTH=UNIT_SIZE*reference_floor_WIDTH;
		HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
		x_dat = this.x;
		cut_bat(5);
	}

	public long Invincible_ct = System.currentTimeMillis();
	public int Invincible_delay = 500;
	
	int f_x_ul ;//¥ª¤W¨¤
	int f_y_ul ;
	
	int f_x_ur ;//¥k¤W¨¤
	int f_y_ur ;
	
	int f_x_dl ;
	int f_y_dl ;//¥ª¤U¨¤
	
	int f_x_dr ;
	int f_y_dr ;//¥k¤U¨¤
	public void Collision_bat(player f1)
	{
		 f_x_ul = f1.x;//¥ª¤W¨¤
		 f_y_ul = f1.y;
		
		 f_x_ur = f1.x+f1.WIDTH;//¥k¤W¨¤
		 f_y_ur = f1.y;
		
		 f_x_dl = f1.x;
		 f_y_dl = f1.y+f1.HEIGHT;//¥ª¤U¨¤
		
		 f_x_dr = f1.x+f1.WIDTH;
		 f_y_dr = f1.y+f1.HEIGHT;//¥k¤U¨¤

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

	public long bat_animtion_ct= System.currentTimeMillis();
	public int bat_ct_delay = 75;
	public int bat_ct=0;
//	public void draw_bat(int how_much_move,Graphics g)
//	{
//		if(bat_ct>=how_much_move)
//			bat_ct=0;
//		BufferedImage bat =null;
//		URL image_path=getClass().getResource("/boss/bat.png");
//		try 
//		{
//			bat = ImageIO.read(image_path);
//		} 
//		catch (IOException e){}
//
//		int tWidth = bat.getWidth();
//		int tHeight = bat.getHeight();
//		int eWidth=tWidth/how_much_move;
//		int eHeight=tHeight;
//
//		BufferedImage SubImgage =  bat.getSubimage((int)bat_ct*eWidth, 0, eWidth, eHeight);
//		//draw_map(stage,g);
//		g.drawImage(SubImgage,x,y,WIDTH,HEIGHT,this);
//
//
//		
//		if(System.currentTimeMillis()-bat_animtion_ct>=bat_ct_delay)
//		{
//			bat_animtion_ct=System.currentTimeMillis();
//			bat_ct+=1;
//		}
//	}
	
	
	public void draw_bat(int how_much_move,Graphics g)
	{
		if(bat_ct>=how_much_move)
			bat_ct=0;

		//draw_map(stage,g);
		g.drawImage(SubImg[bat_ct],x,y,WIDTH,HEIGHT,this);


		
		if(System.currentTimeMillis()-bat_animtion_ct>=bat_ct_delay)
		{
			bat_animtion_ct=System.currentTimeMillis();
			bat_ct+=1;
		}
	}
	
	public BufferedImage[] SubImg = new  BufferedImage[6];
	
	public void cut_bat(int how_much_move)
	{
		BufferedImage bat =null;
		URL image_path=getClass().getResource("/boss/bat.png");
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
	
	int bat_speed=2;
	int x_dat;
	public void Summon_monsters(player player1,Graphics g)//¦V«e¼Q½¿½»
	{
		x=x-bat_speed;
		draw_bat(5,g);

		if(x<=0)
			x=x_dat;
	  	Collision_bat(player1);
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
