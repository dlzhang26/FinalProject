import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ReadImages {
    public static Image title;
    public static Image button;
    public static Image soundOnImage;
    public static Image soundOffImage;

    public static Image player1Image;
    public static Image player2Image;
    public static Image player3Image;

    public static Image backButton;
    public static Image playButton;
    public static Image pauseButton;


    public static Image blockImage;
    public static Image blockImage1;
    public static Image blockImage2;
    public ReadImages(){
        try {
            this.title = ImageIO.read(new File("FolderForImages/origTitle.png")); ///reading image file
            this.button = ImageIO.read(new File("FolderForImages/button.png"));
            this.soundOnImage = ImageIO.read(new File("FolderForImages/soundOn.png"));
            this.soundOffImage = ImageIO.read(new File("FolderForImages/soundOff.png"));

            this.backButton = ImageIO.read(new File("FolderForImages/backbutton.png")); //this is used in the third page too
            this.player1Image = ImageIO.read(new File("FolderForImages/player1.jpg"));
            this.player2Image = ImageIO.read(new File("FolderForImages/player2.jpg"));
            this.player3Image = ImageIO.read(new File("FolderForImages/player3.jpg"));

            this.playButton = ImageIO.read(new File("FolderForImages/playbutton.jpg"));
            this.pauseButton = ImageIO.read(new File("FolderForImages/pausebutton.jpg"));
            this.blockImage = ImageIO.read(new File("FolderForImages/BlockPicture.png"));
            this.blockImage1 = ImageIO.read(new File("FolderForImages/RedBlock.png"));
            this.blockImage2 = ImageIO.read(new File("FolderForImages/BlueBlock.png"));

            /*
            //images for first page
            this.title = ImageIO.read(new File("origTitle.png")); ///reading image file
            this.button = ImageIO.read(new File("button.png")); ///reading image file
            this.soundOnImage = ImageIO.read(new File("soundOn.png"));
            this.soundOffImage = ImageIO.read(new File("soundOff.png"));


            //images for second page
            this.backButton = ImageIO.read(new File("backbutton.png")); //this is used in the third page too
            this.player1Image = ImageIO.read(new File("player1.jpg"));
            this.player2Image = ImageIO.read(new File("player2.jpg"));
            this.player3Image = ImageIO.read(new File("player3.jpg"));

            //images for third page
            this.playButton = ImageIO.read(new File("playbutton.jpg"));
            this.pauseButton = ImageIO.read(new File("pausebutton.jpg"));
            this.blockImage = ImageIO.read(new File("BlockPicture.png"));
            this.blockImage1 = ImageIO.read(new File("RedBlock.png"));
            this.blockImage2 = ImageIO.read(new File("BlueBlock.png"));

             */

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}


