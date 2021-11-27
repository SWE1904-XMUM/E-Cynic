package com.example.e_cynic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public static Bitmap imagePathToBitmap(String imagePath) {
        return BitmapFactory.decodeFile(imagePath);
    }

    public static ByteArrayOutputStream getJpegBitmapOutputStream(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, out);
        return out;
    }

    public static byte[] outputStreamToByteArray(ByteArrayOutputStream out) {
        return out.toByteArray();
    }

    public static byte[] imagePathToByteArray(String imagePath) {
        return bitmapToByteArray(imagePathToBitmap(imagePath));
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream out = getJpegBitmapOutputStream(bitmap);
        return outputStreamToByteArray(out);
    }

    public static Bitmap byteArrayToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
