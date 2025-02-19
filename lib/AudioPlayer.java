import java.io.File; 
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
  
public class AudioPlayer  
{ 
    Long currentTimestamp; 
    Clip clip; 
      
    // current status of clip 
    String status;
    int trackNum; 
    
    AudioInputStream audioInputStream;
    File[] allMusicFiles;
  
    // constructor to initialize streams and clip 
    public AudioPlayer(String folderPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException  { 
        
        trackNum = 0;
        allMusicFiles = new File(folderPath).listFiles();
        audioInputStream = AudioSystem.getAudioInputStream(allMusicFiles[0].getAbsoluteFile());

        Collections.shuffle(Arrays.asList(allMusicFiles));
          
        clip = AudioSystem.getClip(); 
        clip.open(audioInputStream);
    } 
  
    public String convertToTimestamp(long microsecond){
        int seconds = (int) (microsecond / 1000000);
        return String.format("%d:%2d", seconds/60, seconds%60).replace(" ", "0");
    }
      
    public void play()  
    {  
        clip.start(); 
        status = "play";
    } 
      
    public void pause()  
    { 
        if (status.equals("paused"))  
        { 
            System.out.println("audio is already paused"); 
            return; 
        } 
        currentTimestamp = clip.getMicrosecondPosition(); 
        clip.stop(); 
        status = "paused"; 
    } 
      
    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException  { 
        if (status.equals("play"))  
        { 
            return; 
        } 
        clip.close(); 
        resetAudioStream(); 
        clip.setMicrosecondPosition(currentTimestamp); 
        this.play(); 
    } 
      
    // Method to restart the audio 
    public void restart() throws IOException, LineUnavailableException, 
                                            UnsupportedAudioFileException  
    { 
        clip.stop(); 
        clip.close(); 
        resetAudioStream(); 
        currentTimestamp = 0L; 
        clip.setMicrosecondPosition(0); 
        this.play(); 
    } 
      
    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    { 
        currentTimestamp = 0L; 
        clip.stop(); 
        clip.close(); 
    } 
      
    // Method to jump over a specific part 
    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
        if (c > 0 && c < clip.getMicrosecondLength())  
        { 
            clip.stop(); 
            clip.close(); 
            resetAudioStream(); 
            currentTimestamp = c; 
            clip.setMicrosecondPosition(c); 
            this.play(); 
        } 
    } 
      
    // Method to reset audio stream 
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
        clip.stop(); 
        clip.close();
        audioInputStream = AudioSystem.getAudioInputStream( 
        allMusicFiles[trackNum].getAbsoluteFile()); 
        clip.open(audioInputStream); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 

    public void skip() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (trackNum + 1 == allMusicFiles.length){
            trackNum = 0;
        } else {
            trackNum++;
        }
        resetAudioStream();
    }

    public void previous() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (trackNum == 0){
            trackNum = allMusicFiles.length - 1;
        } else {
            trackNum--;
        }
        resetAudioStream();
    }

    public String getTrackLength(){
        return convertToTimestamp(clip.getMicrosecondLength());
    }

    public String getCurrentTrackTime(){
        return convertToTimestamp(clip.getMicrosecondPosition());
    }

    public float getCompletionRate() {
        return (float) (clip.getMicrosecondPosition()) / clip.getMicrosecondLength();
    }

    public void shufflePlaylist(){
        Collections.shuffle(Arrays.asList(allMusicFiles));
    }

    private void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException { 
        switch (c)  
        { 
            case 1: 
                pause(); 
                break; 
            case 2: 
                resume(); 
                break; 
            case 3: 
                restart(); 
                break; 
            case 4: 
                stop(); 
                break;
            case 5:
                skip();
                break;
            case 6:
                previous();
                break; 
            case 7: 
                System.out.println("Enter time (" + 0 +  
                ", " + clip.getMicrosecondLength() + ")"); 
                Scanner sc = new Scanner(System.in); 
                long c1 = sc.nextLong(); 
                jump(c1); 
                break;
            case 8:
                System.out.println(getCurrentTrackTime() + " -> " + getTrackLength());
                System.out.printf("%.4f\n", getCompletionRate());
                break;
        } 
    }

    public static void main(String[] args)  
    {   
        try
        { 
            AudioPlayer audioPlayer = new AudioPlayer("../assets/music"); 
              
            audioPlayer.play(); 
            Scanner sc = new Scanner(System.in); 
              
            while (true) 
            { 
                System.out.println("1. pause"); 
                System.out.println("2. resume"); 
                System.out.println("3. restart"); 
                System.out.println("4. stop"); 
                System.out.println("5. skip"); 
                System.out.println("6. previous"); 
                System.out.println("7. Jump to specific time");
                System.out.println("8. Time Stamp"); 
                int c = sc.nextInt(); 
                audioPlayer.gotoChoice(c); 
                if (c == 4) 
                break; 
            } 
            sc.close(); 
        }  
          
        catch (Exception ex)  
        { 
            System.out.println("Error with playing sound."); 
            ex.printStackTrace(); 
          
          } 
    } 
  
} 