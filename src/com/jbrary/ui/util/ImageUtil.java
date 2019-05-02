package com.jbrary.ui.util;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;

import java.io.*;

public class ImageUtil {
    public static Image decodeImageFromBase64(String base64string) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(base64Decoder.decodeBuffer(base64string));
        Image img = new Image(inputStream,200,0,true, true);

        return img;

    }

    public static String encodeImageToBase64(File imageFile){
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[(int)imageFile.length()];
        try {
            fileInputStreamReader.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encodedfile = java.util.Base64.getEncoder().encodeToString(bytes);
        return encodedfile;
    }



}
