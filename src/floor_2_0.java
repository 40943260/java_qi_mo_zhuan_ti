import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;




public class floor_2_0 implements ImageObserver
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
	
	public static void setfloor(int x[][],floor_2_0 floor[])
	{
		for(int i=0;i<floor.length;i++)
		{
			floor[i] = new floor_2_0(x[i][0], x[i][1], x[i][2], x[i][3]);
		}	
	}
	public static void drawFloor(String path,floor_2_0 floor[],Graphics g)
	{
		for(int i=0;i<floor.length;i++)
			floor[i].drawFloor(path,g);
	}
	public static String  Collision_floor2(floor_2_0 floor[],player f1)
	{
		for(int i=0;i<floor.length;i++)
		{
			if(floor[i].Collision_floor2(f1)=="碰到左邊"||floor[i].Collision_floor2(f1)=="碰到右邊"||floor[i].Collision_floor2(f1)=="碰到上面"||floor[i].Collision_floor2(f1)=="碰到下面")
				return floor[i].Collision_floor2(f1);
		}
		return "沒碰到";
	}
	
	
	public floor_2_0(int floor_x,int floor_y,int reference_floor_HEIGHT,int reference_floor_WIDTH) 
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
	
	public void set_floor_2_0(int floor_x,int floor_y,int reference_floor_HEIGHT,int reference_floor_WIDTH) 
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
	
	public void drawFloor(String path,Graphics g)
	{
		Image floor= new ImageIcon(ClassLoader.getSystemResource(path)).getImage();	
		for(int i=0;i<reference_floor_WIDTH;i++)
		{
			for(int j=0;j<reference_floor_HEIGHT;j++)	
			{
						g.drawImage(floor,x*UNIT_SIZE+UNIT_SIZE*i,y*UNIT_SIZE+UNIT_SIZE*j,UNIT_SIZE,UNIT_SIZE,this);
			}
		}
	
	}
	
	public boolean Collision_floor(player f1)
	{

		int f_x_ul = f1.x+30;//左上角
		int f_y_ul = f1.y;
		
		int f_x_ur = f1.x+f1.WIDTH-30;//右上角
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

	
		if( f_x_ur > bat_x_ul && bat_x_ur>f_x_ul )
		{
			if(bat_y_ul<f_y_dl && f_y_ul<bat_y_dl)
			{
				return true;
			}
		}
		return false;
	}
	int R_long;
	int L_long;
	public String Collision_floor2(player f1)
	{

		int f_x_ul = f1.x;//左上角
		int f_y_ul = f1.y;
		
		int f_x_ur = f1.x+f1.WIDTH;//右上角
		int f_y_ur = f1.y;
		
		int f_x_dl = f1.x;
		int f_y_dl = f1.y+f1.HEIGHT;//左下角
		
		int f_x_dr = f1.x+f1.WIDTH;
		int f_y_dr = f1.y+f1.HEIGHT;//右下角

		int f_mid = f_x_ul+f1.WIDTH/2;
//---------------------------------------------------------------			
		int bat_x_ul = this.real_x;//左上角
		int bat_y_ul = this.real_y;
		
		int bat_x_ur = this.real_x+this.WIDTH;//右上角
		int bat_y_ur = this.real_y;
		
		int bat_x_dl = this.real_x;
		int bat_y_dl = this.real_y+this.HEIGHT;//左下角
		
		int bat_x_dr = this.real_x+this.WIDTH;;
		int bat_y_dr = this.real_y+this.HEIGHT;//右下角
		if(Collision_floor(f1))
		{
			R_long =Math.abs(bat_x_ur -  f_mid);
			L_long =Math.abs(bat_x_ul -  f_mid);



			if(f_y_dl>=bat_y_ul && f_y_dl<=bat_y_dl-HEIGHT/5 )
				return "碰到上面";
			if(R_long>L_long)
				return "碰到左邊";
			if(R_long<L_long)
				return "碰到右邊";
		}
	
		
		return "沒碰到";
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
