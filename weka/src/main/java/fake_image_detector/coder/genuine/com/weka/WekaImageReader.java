package fake_image_detector.coder.genuine.com.weka;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WekaImageReader {

    private String ImageInfo;

    public String getImageInfo() {
        return ImageInfo;
    }

    public void setImageInfo(String imagePath) {
        ImageInfo = getFileDataAsString(imagePath);
    }

    private String getFileDataAsString(String path) {


//Get the text file
        File file = new File(path);

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;


            while ((line = br.readLine()) != null) {

                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }

        return text.toString();
    }


    public void startWekaClassifier(Context context) {
        new WekaClassifier(context, ImageInfo);
    }

    public long getTime() {
        return WekaAIFeed.Time;
    }
}
