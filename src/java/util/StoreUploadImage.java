/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

/**
 *
 * @author HP ADMIN
 */
public class StoreUploadImage {

    public void StoreImage(Part file, String imageFile, String uploadPath) throws IOException{
        InputStream fileContent = file.getInputStream();
        try (OutputStream out = new FileOutputStream(new File(uploadPath))) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println("Successfully uploaded: " + uploadPath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }
    }
}
