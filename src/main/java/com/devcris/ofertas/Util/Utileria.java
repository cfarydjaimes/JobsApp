package com.devcris.ofertas.Util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal = nombreOriginal.replace(" ", "-");
        String nombreF = random(8) + nombreOriginal;
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta + nombreF);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            // Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreF;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    public static String random(int temp){
        String CHART = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder bul = new StringBuilder();
        while(temp-- != 0){
            int e = (int) (Math.random() * CHART.length());
            bul.append(CHART.charAt(e));
        }
        return bul.toString();
    }
}
