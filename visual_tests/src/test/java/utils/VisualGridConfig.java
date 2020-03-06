package utils;

import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;

public class VisualGridConfig {

    public static Configuration getGrid(){
        Configuration vgConfig = new Configuration();

        //Browsers
        vgConfig.addBrowser(800,  600, BrowserType.FIREFOX);


        //Devices
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X);
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.LANDSCAPE);

        return vgConfig;
    }

}