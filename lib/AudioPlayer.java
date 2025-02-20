package lib;

import java.io.File; 
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
  
public class AudioPlayer  
{ 
    private Long currentTimestamp; 
    private Clip clip; 
      
    // current status of clip 
    private String status;
    private int trackNum;
    private int numOfTracks;
    
    private AudioInputStream audioInputStream;
    private File[] allMusicFiles;
    private LineListener endOfTrackListener;
  
    // constructor to initialize streams and clip 
    public AudioPlayer(String folderPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException  { 
        trackNum = 0;
        allMusicFiles = new File(folderPath).listFiles();
        numOfTracks = allMusicFiles.length;

        // Shuffle the array with all music
        Collections.shuffle(Arrays.asList(allMusicFiles));
        // Add the first on the shuffled list to the audio steam
        audioInputStream = AudioSystem.getAudioInputStream(allMusicFiles[0].getAbsoluteFile());
          
        clip = AudioSystem.getClip(); 
        clip.open(audioInputStream);

        // Initialize the listener once
        endOfTrackListener = new LineListener() {
            @Override
            public void update(LineEvent evt) {
                if (evt.getType() == LineEvent.Type.STOP) {
                    try {
                        // Check if we're at end of track (within 50ms margin)
                        if (status.equals("play") && clip.getMicrosecondPosition() >= clip.getMicrosecondLength() - 50000) {
                            System.out.printf("Track ended: %d / %d%n", clip.getMicrosecondPosition(), clip.getMicrosecondLength());
                            skip();
                            play();
                        }
                    } catch (Exception ex) { 
                        System.out.println("Error with auto-skip functionality."); 
                        ex.printStackTrace(); 
                    } 
                }
            }
        };

        clip.addLineListener(endOfTrackListener);
    } 
  
    public String convertToTimestamp(long microsecond){
        int seconds = (int) (microsecond / 1000000);
        return String.format("%d:%2d", seconds/60, seconds%60).replace(" ", "0");
    }
      
    public void play() {
        status = "play";
        clip.start();

        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent evt) {
                if (evt.getType() == LineEvent.Type.STOP){
                    try {
                        if (clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
                            System.out.printf("%d / %d", clip.getMicrosecondPosition(), clip.getMicrosecondLength());
                            skip();
                            play();
                        }
                    } catch (Exception ex) { 
                        System.out.println("Error with playing sound."); 
                        ex.printStackTrace(); 
                    } 
                }
            }
        });
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
        if (status.equals("play")) { 
            return; 
        } 
        clip.close(); 
        resetAudioStream(); 
        clip.setMicrosecondPosition(currentTimestamp); 
        this.play();
        status = "play";
    } 
      
    // Method to restart the audio 
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException { 
        clip.stop(); 
        clip.close(); 
        resetAudioStream(); 
        currentTimestamp = 0L; 
        clip.setMicrosecondPosition(0); 
        this.play(); 
    } 
      
    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
        currentTimestamp = 0L; 
        clip.stop(); 
        clip.close(); 
    } 
      
    // Method to jump over a specific part 
    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
        if (c > 0 && c < clip.getMicrosecondLength()) { 
            clip.stop();
            resetAudioStream(); 
            currentTimestamp = c; 
            clip.setMicrosecondPosition(c); 
            this.play(); 
        } 
    } 
      
    // Method to reset audio stream 
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop(); 
        clip.close();
        audioInputStream = AudioSystem.getAudioInputStream(allMusicFiles[trackNum].getAbsoluteFile()); 
        clip.open(audioInputStream);
    } 

    // Method to skip to the next song
    public void skip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (trackNum + 1 == numOfTracks){
            trackNum = 0;
        } else {
            trackNum++;
        }
        resetAudioStream();
        play();
    }

    // Method to got back to the previous song
    public void previous() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        if (trackNum == 0){
            trackNum = numOfTracks - 1;
        } else {
            trackNum--;
        }
        resetAudioStream();
        play();
    }

    // Shuffle all the songs on front of current song
    public void shuffle(){
        // If we are not on the last track then we shuffle
        if (trackNum != numOfTracks - 1){
            File[] tracksAhead = new File[numOfTracks - trackNum - 1];

            // Place all the tracks ahead of the current track in our new array
            for (int i = 0; i < tracksAhead.length; i++) {
                tracksAhead[i] = allMusicFiles[trackNum + i + 1];
            }

            // Shuffle the array with all the tracks we need to shuffle
            Collections.shuffle(Arrays.asList(tracksAhead));
            
            // Place the shuffled list back into the playlist
            for (int i = 0; i < tracksAhead.length; i++) {
                allMusicFiles[trackNum + i + 1] = tracksAhead[i];
            }
        }
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

    public String getName() {
        String fileName = allMusicFiles[trackNum].getName(); 
        return fileName.substring(0, fileName.length()-4);
    }

    public String[] getPlaylist() {
        String[] playlist = new String[allMusicFiles.length];

        for (int i = 0; i < allMusicFiles.length; i++) {
            String fileName = allMusicFiles[i].getName();
            String name = fileName.substring(0, fileName.length()-4);
            playlist[i] = name;
        }

        return playlist;
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
            case 9:
                for (String song : getPlaylist()) {
                    System.out.println(song);
                }
                break;
            case 10:
                shuffle();
        } 
    }

    // Main method for testing out the class
    public static void main(String[] args)  
    {   
        try
        { 
            AudioPlayer audioPlayer = new AudioPlayer("../assets/music"); 
              
            audioPlayer.play(); 
            Scanner sc = new Scanner(System.in); 
              
            while (true) 
            {
                System.out.println(audioPlayer.getName());
                System.out.println("1. pause"); 
                System.out.println("2. resume"); 
                System.out.println("3. restart"); 
                System.out.println("4. stop"); 
                System.out.println("5. skip"); 
                System.out.println("6. previous"); 
                System.out.println("7. jump");
                System.out.println("8. time stamp"); 
                System.out.println("9. playlist"); 
                System.out.println("10. shuffle");
                int c = sc.nextInt(); 
                audioPlayer.gotoChoice(c); 
                if (c == 4) 
                break; 
            } 
            sc.close(); 
        }  
          
        catch (Exception ex) { 
            System.out.println("Error with playing sound."); 
            ex.printStackTrace(); 
        } 
    } 
  
} 