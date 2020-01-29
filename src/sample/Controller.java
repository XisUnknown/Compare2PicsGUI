package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Controller {
    private Image image1;
    private Image image2;
    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView outputImg;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private ColorPicker chooseColor;

    public void loadPic1Clicked() throws FileNotFoundException {
        image1=loadPicClass.loadPic(pic1,label1);
    }
    public void loadPic2Clicked() throws FileNotFoundException {
        image2=loadPicClass.loadPic(pic2,label2);
        System.out.println(chooseColor.getValue().toString());
    }
    private int colorToInt(Color c) {
        int r = (int) Math.round(c.getRed() * 255);
        int g = (int) Math.round(c.getGreen() * 255);
        int b = (int) Math.round(c.getBlue() * 255);
        return (r << 16) | (g << 8) | b;
    }
    public void comparePictures(){
        BufferedImage imgA = SwingFXUtils.fromFXImage(image1, null);
        BufferedImage imgB = SwingFXUtils.fromFXImage(image2, null);
        BufferedImage outputImage = new BufferedImage(imgA.getWidth(), imgB.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    int r = (int) Math.round(chooseColor.getValue().getRed() * 255);
                    int g = (int) Math.round(chooseColor.getValue().getGreen() * 255);
                    int b = (int) Math.round(chooseColor.getValue().getBlue() * 255);
                    //BigInteger val = new BigInteger(buff[1]);
                    outputImage.setRGB(x,y,(r << 16) | (g << 8) | b);
                }
                else{
                    outputImage.setRGB(x,y,imgA.getRGB(x, y));
                }
            }
        }
        Image image;
        image = SwingFXUtils.toFXImage(outputImage, null);
        outputImg.setImage(image);
    }
    public void saveResult(){
        Image outputImage = outputImg.getImage();
        BufferedImage outputImageBuff = SwingFXUtils.fromFXImage(outputImage, null);
        File file = new File("output.png");
        System.out.println("File created");
        try {
            ImageIO.write(outputImageBuff, "png", file);
            System.out.println("saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
