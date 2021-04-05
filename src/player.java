import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class player implements ImageObserver
{
		static final int SCREEN_WIDTH=1600;
		static final int SCREEN_HEIGHT=800;
		public   int x;
		public   int y;
	
		public   int reference_floor_WIDTH;
		public   int reference_floor_HEIGHT;
		public  final int UNIT_SIZE=100;
		
		public   int WIDTH;
		public   int HEIGHT;
		
		public   int run_speed=2;
		public   double down_speed=90;
		public   double down_speed_Ct=4.8;
		
		public boolean R=false;
		public boolean L=false;
		public boolean  RN=false;
		public boolean  LN=true;
		public boolean  RU=false;
		public boolean  LU=false;
		public boolean  RRU=false;
		public boolean  LLU=false;
		
		public int life = 10; 
		public boolean Invincible = false; 
		
		boolean jumpset=false;
		int x_dat;
		int y_dat;
		
		player(int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT)
		{
			this.x=x;
			this.y=y;
			this.reference_floor_WIDTH= reference_floor_WIDTH;
			this.reference_floor_HEIGHT=reference_floor_HEIGHT;
			WIDTH=UNIT_SIZE*reference_floor_WIDTH;
			HEIGHT=UNIT_SIZE*reference_floor_HEIGHT;
			cut_gameplaer("cute/L.png",6,LSubImg );
			cut_gameplaer("cute/R.png",6,RSubImg );
			cut_gameplaer("cute/M.png",8,MSubImg );
			cut_gameplaer("cute/N.png",8,NSubImg);
			cut_attack(attackImg,"fireball_blue.png");
			cut_attack(attackImg2,"oppoFire.png");
		}

		String fire_bgm = get_music("gamemusic/RPG Voice Starter Pack/Type 1/attackok.wav");

		String damged_bgm = get_music("gamemusic/RPG Voice Starter Pack/Type 1/damgeok.wav");
		

	    
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
	    
		public void draw_gameplaer(BufferedImage SubImg[],int Sprite_ct,int x,int y,Graphics g) 
		{
			g.drawImage(SubImg[Sprite_ct],x,y,UNIT_SIZE,UNIT_SIZE,this);
		}

		
		public BufferedImage[] LSubImg = new  BufferedImage[6];
		public BufferedImage[] RSubImg = new  BufferedImage[6];
		public BufferedImage[] MSubImg = new  BufferedImage[8];
		public BufferedImage[] NSubImg = new  BufferedImage[8];
		
		public void cut_gameplaer(String Sprite,int how_much_move,BufferedImage Imgage[]) 
		{
			BufferedImage gameplaer =null;
			String path = "/"+Sprite;
			URL image_path=getClass().getResource(path);
			try 
			{
				gameplaer = ImageIO.read(image_path);
			} 
			catch (IOException e){}

			int tWidth = gameplaer.getWidth();
			int tHeight = gameplaer.getHeight();
			int eWidth=tWidth/how_much_move;
			int eHeight=tHeight;
			for(int i=0;i<how_much_move;i++)
				Imgage[i] =  gameplaer.getSubimage(i*eWidth, 0, eWidth, eHeight);

		}
		
		
		public void gravity(boolean ct)//重力是否開啟 
		{
			if(ct)
			{
				y=(int) (y+down_speed*down_speed_Ct);
			}
			if(!ct)
				down_speed = UNIT_SIZE/100;
		}
		int life_dat=life;
		public void draw_heat(Graphics g)//畫剩餘血量
		{
			if(life_dat!=life)
			{
				life_dat=life;
			    music_player player2 = new music_player(damged_bgm);
			    player2.start(); 
			}
			Image heat= new ImageIcon(ClassLoader.getSystemResource("cute/heart.png")).getImage();	
			for(int i=0;i<life;i++)
			{
				g.drawImage(heat,i*30 , 0,30,30,this);
			}
		}
		int RL_anmation_ct=0;
		int N_anmation_ct =0;
		public double jump_v=0.32;
		public boolean gravity_ct;
		public long anmation_time = System.currentTimeMillis();
		public int admation_delay = 80;
		
		public boolean R_stop=false;
		public boolean L_stop=false;
		public boolean D_stop=false;
		public boolean U_stop=false;
		public void floor_check(String ct)
		{
			//System.out.println("R_stop="+R_stop+" L_stop="+L_stop+" D_stop="+D_stop);

			if(ct=="碰到左邊")
				R_stop=true;
			if(ct=="碰到右邊")
				L_stop=true;
			if(ct=="碰到上面")
			{
				D_stop = true;
			}
			if(ct=="沒碰到")
			{
				if(L_stop)
					L_stop=false;
				if(R_stop)
					R_stop=false;
				if(D_stop)
					D_stop=false;

			}
		}
		public BufferedImage[] attackImg = new  BufferedImage[40];
		public BufferedImage[] attackImg2 = new  BufferedImage[40];
		public void cut_attack( BufferedImage img[],String Sprite)
		{
				int how_much_move_y = 10;
				int how_much_move_x = 4;
				BufferedImage gameplaer =null;
				String path = "/cute/"+Sprite;
				URL image_path=getClass().getResource(path);
				try 
				{
					gameplaer = ImageIO.read(image_path);
				} 
				catch (IOException e){}

				int tWidth = gameplaer.getWidth();
				int tHeight = gameplaer.getHeight();
				int eWidth=tWidth/how_much_move_x;
				int eHeight=tHeight/how_much_move_y;
				int ct=0;
				for(int i=0;i<how_much_move_y;i++)
					for(int j=0;j<how_much_move_x;j++)
					{
						img[ct] =  gameplaer.getSubimage(j*eWidth, i*eHeight, eWidth, eHeight);
						ct+=1;
					}
		}
		public boolean attack=false;
		public boolean attackct=false;
		long attack_time= System.currentTimeMillis();
		long attack_update=30;
		long attack_time2= System.currentTimeMillis();
		long attack_update2=1200;
		int attack_animation=0;
		int fire_x;
		int fire_y;
		int fire_W=UNIT_SIZE;
		int fire_H=UNIT_SIZE;
		int fire_speed=20;
		String rl_ct;
		public void attack(Graphics g)
		{
	
			if(attack && !attackct)
			{
				attack_animation=0;
				fire_y=y;
				fire_x=x+100;
				attackct=true;
				attack_time2= System.currentTimeMillis();
			    music_player player1 = new music_player(fire_bgm);
			    player1.start(); 
				if(RN==true || R==true )
					 rl_ct="R";
				else
					 rl_ct="L";
			}
			if(attackct)
			{
				if(rl_ct=="R")
					g.drawImage(attackImg[attack_animation],fire_x , fire_y,fire_W,fire_H,this);
				else
					g.drawImage(attackImg2[attack_animation],fire_x , fire_y,fire_W,fire_H,this);
				if(System.currentTimeMillis() - attack_time>=attack_update)
				{
					attack_time=System.currentTimeMillis();
					attack_animation=(attack_animation+1)%40;
					if(rl_ct=="R")
						fire_x=fire_x+fire_speed;
					else
						fire_x=fire_x-fire_speed;
				}
				if(System.currentTimeMillis()-attack_time2>=attack_update2)
				{
					attack_time2=System.currentTimeMillis();
					attack=false;
					attackct=false;
					fire_y=0;
					fire_x=0;
				}
			}
		}
		public void move(Graphics g)
		{
			attack(g);
			draw_heat(g);
			if(RL_anmation_ct==5)
				RL_anmation_ct=0;
			if(N_anmation_ct==7)
				N_anmation_ct=0;
			if(L==true )
			{
				if(!L_stop)
					x=x-run_speed;
				//draw_gameplaer("cute/L.png",RL_anmation_ct,6,x,y,g);
				draw_gameplaer(LSubImg,RL_anmation_ct,x,y,g); 
				//gravity_ct = Collision_Detection(player1,floorarray);
			}
			if(R==true )
			{
				if(!R_stop)
					x=x+run_speed;
				//draw_gameplaer("cute/R.png",RL_anmation_ct,6,x,y,g);
				draw_gameplaer(RSubImg,RL_anmation_ct,x,y,g); 
				//gravity_ct = Collision_Detection(player1,floorarray);
			}
			if(LN==true)
			{
				//draw_gameplaer("cute/N.png",N_anmation_ct,8,x,y,g);
				draw_gameplaer(NSubImg,N_anmation_ct,x,y,g); 
				//gravity_ct = Collision_Detection(player1,floorarray);
			}
			if(RN==true)
			{
				//draw_gameplaer("cute/M.png",N_anmation_ct,8,x,y,g);
				draw_gameplaer(MSubImg,N_anmation_ct,x,y,g); 
				//gravity_ct = Collision_Detection(player1,floorarray);
			}
			

			if(LU==true || RU==true)//成功 垂直跳
			{

				if(y>y_dat-UNIT_SIZE*2)
				{

					y=(int)(y-25*jump_v);
					if(LU==true )
						draw_gameplaer(NSubImg,1,x,y,g); 
						//draw_gameplaer("cute/L.png",1,6,x,y,g);
					if(RU==true)
						draw_gameplaer(MSubImg,1,x,y,g); 
						//draw_gameplaer("cute/R.png",1,6,x,y,g);
				}
				else
				{
					y_dat=SCREEN_HEIGHT;
				}
			}
			

			if(System.currentTimeMillis()-anmation_time >= admation_delay)
			{
				RL_anmation_ct+=1;
				N_anmation_ct+=1;
				anmation_time=System.currentTimeMillis();
			}
		}

		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
			// TODO Auto-generated method stub
			return false;
		}
}