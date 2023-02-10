import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class MusicBG {//extends JFrame{
    public static int should_I_stop = 0;

    public void playMusic(String musicLocation){
        try {
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);  //要打開才能撥放
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);   //循環播放

                long clipTimePosition = clip.getMicrosecondPosition();

                while(true){
                    if ( try01Code.music_number % 2 == 1 ){
                        System.out.println("1111111111111");
                    }
                    if (try01Code.music_number % 2 == 0) {
                        System.out.println("000000000000");
                    }
                    while (should_I_stop == 0) {
                        System.out.println("CCCCCCCCC" + try01Code.music_number);
                        if (try01Code.music_number % 2 == 1) {
                            // System.out.println("music_number" + try01Code.music_number);
                            System.out.println("NNNNNNNNNN");
                            clipTimePosition = clip.getMicrosecondPosition();
                            clip.stop();
                            should_I_stop = 1;

                        } else {
                            // System.out.println("music_number" + try01Code.music_number);
                            clip.setMicrosecondPosition(clipTimePosition);
                            clip.start();
                            clip.loop(Clip.LOOP_CONTINUOUSLY); // 循環播放
                            should_I_stop = 1;
                        }
                    }
                }
            }
            else
			{
				JOptionPane.showMessageDialog(null,"ERROR");
			}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // public static void main(String[] args){
    //     String filepath = "因為喜歡你.wav";
    //     MusicBG musicObject = new MusicBG();
    //     musicObject.playMusic(filepath);
    // }
    
}
