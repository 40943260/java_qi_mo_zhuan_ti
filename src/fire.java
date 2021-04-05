import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


class fire implements ImageObserver
{
			String path = "boss/fire.png";
			public   double x;
			public   double y;

			int dat_x;
			int dat_y;
			
			public   int reference_x;
			public   int reference_y;
			
			public   int reference_floor_WIDTH;
			public   int reference_floor_HEIGHT;
			
			public   int WIDTH;
			public   int HEIGHT;
			public int UNIT_SIZE=100;
			int tosmaLL=0;
			public  fire(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT)
			{
				this.x=x*UNIT_SIZE;
				this.y=y*UNIT_SIZE;
				reference_x=x;
				reference_y=y;
				this.reference_floor_WIDTH= reference_floor_WIDTH;
				this.reference_floor_HEIGHT=reference_floor_HEIGHT;
				WIDTH=UNIT_SIZE*reference_floor_WIDTH;
				HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
				cut_fire(7);
				WIDTH=WIDTH-tosmaLL;
				HEIGHT=HEIGHT-tosmaLL;
				dat_x=(int)this.x;
				dat_y=(int)this.y;
	
			}
			public void set_laser(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT)
			{
				this.x=x*UNIT_SIZE;
				this.y=y*UNIT_SIZE;
				reference_x=x;
				reference_y=y;
				this.reference_floor_WIDTH= reference_floor_WIDTH;
				this.reference_floor_HEIGHT=reference_floor_HEIGHT;
				WIDTH=UNIT_SIZE*reference_floor_WIDTH;
				HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
			}
			public long Invincible_ct = System.currentTimeMillis();
			public int Invincible_delay = 500;
			public void Collision_laser(player f1)
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
				int to_small=50;
				double bat_x_ul = this.x+to_small;//左上角
				double bat_y_ul = this.y+to_small;
				
				double bat_x_ur = this.x+this.WIDTH-to_small;//右上角
				double bat_y_ur = this.y+to_small;
				
				double bat_x_dl = this.x+to_small;
				double bat_y_dl = this.y+this.HEIGHT-to_small;//左下角
				
				double bat_x_dr = this.x+this.WIDTH-to_small;;
				double bat_y_dr = this.y+this.HEIGHT-to_small;//右下角

	
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
		
			
			long fire_animtion_ct=System.currentTimeMillis();
			int fire_ct_delay=50;
			int fire_ct=0;
			public void draw_fire(int how_much_move,Graphics g)
			{
				if(fire_ct>=how_much_move)
					fire_ct=0;
				//draw_map(stage,g);
				g.drawImage(SubImg[fire_ct],(int)x,(int)y,WIDTH,HEIGHT,this);
				if(System.currentTimeMillis()-fire_animtion_ct>=fire_ct_delay)
				{
					fire_animtion_ct=System.currentTimeMillis();
					fire_ct+=1;
				}
			}
			
			public BufferedImage[] SubImg = new  BufferedImage[7];
			
			public void cut_fire(int how_much_move)
			{
				BufferedImage bat =null;
				URL image_path=getClass().getResource("/"+path);
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
			
			public long attack_time_ct =  System.currentTimeMillis();
			public int attack_delay = 1000;
			public boolean attack_ct = true;
			double xspeed;
			double yspeed;
			int fire_speed=2;
			public void 地域業火(Graphics g,player p1)
			{
				if(System.currentTimeMillis()- attack_time_ct>=attack_delay)
				{
					attack_time_ct=System.currentTimeMillis();
					if(attack_ct)
					{
						attack_ct=!attack_ct;
						xspeed=(p1.x-x)/500;
						yspeed=(p1.y-y)/500;
						System.out.println(xspeed);
						System.out.println(yspeed);
					}

				}
				if(!attack_ct)	
				{
					x=x+xspeed;
					y=y+yspeed;
					call_fire(g,p1);
				}
				if(attack_ct)
				{
					call_fire(g,p1);
				}
			}
			public void call_fire(Graphics g,player p1)
			{
				Collision_laser(p1);
				draw_fire(7,g);
			}
			
	
			public  String get_music(String path)
			{
		        File myFile = new File(path);
		        try 
		        {
					System.out.println(myFile.getCanonicalPath());
					return myFile.getCanonicalPath();
				} 
		        catch (IOException e) {}
		        return "";

			}
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
			
		}