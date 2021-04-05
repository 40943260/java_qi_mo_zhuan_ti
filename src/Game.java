import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Game 
{

	public static String get_music(String path)
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
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
			new GameFrame();

			String path = "gamemusic/bgm.wav"; 
			String audioFilePath = get_music(path);
		    
		    music_player player = new music_player(audioFilePath);
		    player.start(); 

		 
	}

}
