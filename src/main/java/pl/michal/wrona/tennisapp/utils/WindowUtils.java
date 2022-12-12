package pl.michal.wrona.tennisapp.utils;

import pl.michal.wrona.tennisapp.service.MainService;

import java.awt.*;

public class WindowUtils {

    private double windowWidth;
    private double windowHeight;

    public WindowUtils() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowWidth = screenSize.getWidth();
        windowHeight = screenSize.getHeight();
    }

    public int getX(int width){
        return (int) (windowWidth/2 - width/2);
    }

    public int getY(int height){
        return (int) (windowHeight/2 - height/2);
    }
}
