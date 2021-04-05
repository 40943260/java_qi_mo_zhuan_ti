import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class boss  implements ImageObserver
{
	public int boos_life=3;
	String path ;
	public   int x;
	public   int y;

	public   int reference_x;
	public   int reference_y;
	
	public   int reference_floor_WIDTH;
	public   int reference_floor_HEIGHT;
	
	public   int WIDTH;
	public   int HEIGHT;
	public final int UNIT_SIZE =100;
	player player1;
	
	public String music_path;
	public String audioFilePath;
	public boss(player player1,int x,int y,int reference_floor_WIDTH,int reference_floor_HEIGHT,String path)
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
		this.player1=player1;
		cut_boss(4) ;
		music_path = "gamemusic/heal.wav"; 
		audioFilePath = get_music(music_path);
		datx=this.x;

	}
	laser laser0 = new laser(2,0,1,5);
	
	laser laser1 = new laser(0,0,1,5);
	laser laser2 = new laser(2,0,1,5);
	laser laser3 = new laser(4,0,1,5);
	laser laser4 = new laser(6,0,1,5);
	laser laser5 = new laser(8,0,1,5);
	laser laser6 = new laser(10,0,1,5);
	laser laser7 = new laser(12,0,1,5);
	laser laser8 = new laser(14,0,1,5);
	
	laser glaser1 = new laser(1,0,1,5);
	laser glaser2 = new laser(3,0,1,5);
	laser glaser3 = new laser(5,0,1,5);
	laser glaser4 = new laser(7,0,1,5);
	laser glaser5 = new laser(9,0,1,5);
	laser glaser6 = new laser(11,0,1,5);
	laser glaser7 = new laser(13,0,1,5);
	laser glaser8 = new laser(15,0,1,5);
	
	bat bat1 = new bat(10,4,1,1,player1);
	sword sword1 = new sword(0,0,2,1);
	fire fire1 = new fire(14,2,1,1);
	fire fire2 = new fire(13,2,1,1);
	fire fire3 = new fire(12,2,1,1);
	int boss_anmation_ct = 0;
	public long anmation_time = System.currentTimeMillis();
	public int admation_delay = 180;

	
	public void draw_boss(int how_much_move,Graphics g) 
	{

		g.drawImage(SubImg[boss_anmation_ct],x, y,WIDTH,HEIGHT,this);
		
		if(boss_anmation_ct>=how_much_move-1)
			boss_anmation_ct=0;
		if(System.currentTimeMillis()-anmation_time >= admation_delay)
		{
			boss_anmation_ct+=1;
			anmation_time=System.currentTimeMillis();
		}
	}
	public BufferedImage[] SubImg = new  BufferedImage[4];
	public void cut_boss(int how_much_move) 
	{
		BufferedImage boss =null;
		URL image_path=getClass().getResource(path);
		try 
		{
			boss = ImageIO.read(image_path);
		} 
		catch (IOException e){}

		int tWidth = boss.getWidth();
		int tHeight = boss.getHeight();
		int eWidth=tWidth/how_much_move;
		int eHeight=tHeight;

		for(int i=0;i<how_much_move;i++)
			SubImg[i] = boss.getSubimage(i*eWidth, 0, eWidth, eHeight);
		
	}
	
	public long boss_time_attack = System.currentTimeMillis();
	public int boss_time_delay = 5000;//–10ち传
	int attack_ct=1;
	int datx;
	public void boss_attack(Graphics g) 
	{
		draw_boss(4,g);

		if(attack_ct==1)
		{
			attack_update(10500);//10500
			bat1.Summon_monsters(player1,g);
		}
		if(attack_ct==2)
		{
			attack_update(1500);
		}
		if(attack_ct==3)
		{
			bat1.x=bat1.x_dat;
			attack_update(4800);//4800
			laser0.SHAN_DIAN_WU_LIAN_BIAN(player1,g);
		}
		if(attack_ct==4)
		{
			attack_update(1500);
		}
		if(attack_ct==5)
		{
		attack_update(2000);//4800
			laser1.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser2.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser3.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser4.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser5.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser6.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser7.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			laser8.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
		}
		if(attack_ct==6)
		{
			attack_update(1500);
		}
		if(attack_ct==7)
		{
			attack_update(2000);//4800
			glaser1.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser2.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser3.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser4.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser5.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser6.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser7.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
			glaser8.SHAN_DIAN_WU_LIAN_BIAN2(player1,g);
		}
		if(attack_ct==8)
		{
			attack_update(1500);
			sword1.x=this.x-120;
			sword1.y=this.y+20;
			sword1.draw_sword(g, player1);
		}
		if(attack_ct==9)
		{
			attack_update(1000);//4800
			sword1.draw_sword(g, player1);
			sword1.x=this.x-120;
			sword1.y=this.y+20;
			sword1.draw_sword(g, player1);
			this.x=this.x-boos_speed;
		}
		if(attack_ct==10)
		{
			this.x=datx;
			attack_update(1500);
			fire1.x=fire1.dat_x;
			fire1.y=fire1.dat_y;
			
			fire2.x=fire2.dat_x;
			fire2.y=fire2.dat_y;
			
			fire3.x=fire3.dat_x;
			fire3.y=fire3.dat_y;
		}
		if(attack_ct==11)
		{
			attack_update(5000);
			fire1.办穨(g,player1);
			fire2.办穨(g,player1);
			fire3.办穨(g,player1);
		}
	}
	
	int boos_speed=4;
	int attack_cout = 12;

	public void attack_update(int delay_time)
	{
		if(System.currentTimeMillis()-boss_time_attack>=delay_time)
		{
			attack_ct=attack_ct+1;
			boss_time_attack = System.currentTimeMillis();
			if(attack_ct==attack_cout)
				attack_ct=1;
		    music_player player = new music_player(audioFilePath);
		    player.start(); 
		}
	}
	public void attack_update()
	{
			attack_ct=attack_ct+1;
			boss_time_attack = System.currentTimeMillis();
			if(attack_ct==attack_cout)
				attack_ct=1;
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

