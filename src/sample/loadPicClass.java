package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class loadPicClass {

    public static Image loadPic(ImageView pic, Label label) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(new FileInputStream(selectedFile));
        double width = image.getWidth();
        double height = image.getHeight();
        pic.setImage(image);
        label.setText("Filename: "+selectedFile.getName()+"\n"+"width: "+(int)width+"px\n"+"height: "+(int)height+"px");
        return image;
    }
}
