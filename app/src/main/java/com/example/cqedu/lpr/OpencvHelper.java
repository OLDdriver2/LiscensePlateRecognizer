package com.example.cqedu.lpr;
import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;



/**
 * Created by Administrator on 2018/4/5/005.
 */

public class OpencvHelper {
    public static Mat Bitmap2Mat(Bitmap bitmap){
        Mat mat = new Mat();
        mat.create(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8U);
        Utils.bitmapToMat(bitmap,mat);
        return mat;
    }
    public static Bitmap Mat2Bitmap(Mat mat){
        Bitmap bitmap = Bitmap.createBitmap(mat.width(),mat.height(), Bitmap.Config.RGB_565);
        Utils.matToBitmap(mat,bitmap);
        return bitmap;
    }
    public static Mat getGaussianMat(Mat mat){
        Mat gaussianMat = new Mat();
        Imgproc.GaussianBlur(mat,gaussianMat,new Size(3,3),0,0);
        return gaussianMat;
    }

    public static Mat getGrayMat(Mat mat){
        Mat grayMat = new Mat();
        Imgproc.cvtColor(mat,grayMat,Imgproc.COLOR_BGR2GRAY);
        return grayMat;
    }
    public static Mat getSobelMat(Mat mat){
        Mat sobelMat = new Mat();
        Imgproc.Sobel(mat,sobelMat,CvType.CV_8U,1,0,3,0.4,128);

        return sobelMat;
    }
    public static Mat getThresholdMat(Mat mat){
        Mat thresholdMat = new Mat();
        Imgproc.threshold(mat,thresholdMat,100,255,Imgproc.THRESH_BINARY_INV);
        return thresholdMat;
    }
    public static Mat getMedianBlur(Mat mat){
        Mat medianMat = new Mat();
        Imgproc.medianBlur(mat,medianMat,3);
        return  medianMat;
    }

    public static void cascade(String filename){
        CascadeClassifier classifier=new CascadeClassifier();
        classifier.load(filename);
    }
}
