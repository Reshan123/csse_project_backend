package com.HospitalManagementSystem.HospitalManagementSystem.util;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

@Component
public class QRCodeGenerator {

    private static QRCodeGenerator instance;
    private QRCodeGenerator() {
    }

    public static synchronized QRCodeGenerator getInstance() {
        if (instance == null) {
            instance = new QRCodeGenerator();
        }
        return instance;
    }

    public ByteArrayOutputStream generateQRCode(String text) throws IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 250;
        int height = 250;
        BufferedImage bufferedImage;
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        try {
            com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "PNG", pngOutputStream);
        } catch (WriterException e) {
            throw new RuntimeException("Error occurred while generating QR code", e);
        }

        return pngOutputStream;
    }
}
