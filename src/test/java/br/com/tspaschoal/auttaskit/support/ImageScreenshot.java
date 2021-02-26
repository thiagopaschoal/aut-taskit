package br.com.tspaschoal.auttaskit.support;

import br.com.tspaschoal.auttaskit.support.utils.DataUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ImageScreenshot {

    private ImageScreenshot() {
    }

    public static void takePicture(WebDriver webDriver, String testName) {
        final File image = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(image, new File("reports/" + DataUtils.getTimestamp() + "_" + testName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("error: image could not processed: " + e.getMessage());
        }
    }
}
