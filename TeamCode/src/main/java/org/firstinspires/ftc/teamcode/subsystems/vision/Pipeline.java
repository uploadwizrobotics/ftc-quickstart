package org.firstinspires.ftc.teamcode.subsystems.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline extends OpenCvPipeline {

    public enum Position{
        left,
        middle,
        right
    }

    final Scalar Border = new Scalar(0,255,0);
    final Scalar Fill = new Scalar(0,0,255);

    static final Point REGIONLEFT_TOPLEFT_ANCHOR_POINT = new Point(0,105);
    static final Point REGIONMIDDLE_TOPLEFT_ANCHOR_POINT = new Point(140,105);
    static final Point REGIONRIGHT_TOPLEFT_ANCHOR_POINT = new Point(280,105);
    static final int REGION_WIDTH = 40;
    static final int REGION_HEIGHT = 40;

    Point regionleft_pointA = new Point(
            REGIONLEFT_TOPLEFT_ANCHOR_POINT.x,
            REGIONLEFT_TOPLEFT_ANCHOR_POINT.y);
    Point regionleft_pointB = new Point(
            REGIONLEFT_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGIONLEFT_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point regionmiddle_pointA = new Point(
            REGIONMIDDLE_TOPLEFT_ANCHOR_POINT.x,
            REGIONMIDDLE_TOPLEFT_ANCHOR_POINT.y);
    Point regionmiddle_pointB = new Point(
            REGIONMIDDLE_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGIONMIDDLE_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point regionright_pointA = new Point(
            REGIONRIGHT_TOPLEFT_ANCHOR_POINT.x,
            REGIONRIGHT_TOPLEFT_ANCHOR_POINT.y);
    Point regionright_pointB = new Point(
            REGIONRIGHT_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGIONRIGHT_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Mat regionleft_Cb;
    Mat regionmiddle_Cb;
    Mat regionright_Cb;
    Mat regionleft_Cr;
    Mat regionmiddle_Cr;
    Mat regionright_Cr;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    Mat Cr = new Mat();
    int avg1left = 0;
    int avg1middle = 0;
    int avg1right = 0;
    int avg2left = 0;
    int avg2middle = 0;
    int avg2right = 0;

    public Pipeline.Position position = null;
    public Pipeline.Position Cbposition;
    public Pipeline.Position Crposition;

    void inputToCb(Mat input)
    {
            /* inputToCb method takes the RGB frame, converts to YCrCb, and extracts the Cb channel to the 'Cb' variable
            arguments:
            -- Mat input is the mat being converted to YCrCb
             */
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 1);
    }

    void inputToCr(Mat input)
    {
            /* inputToCb method takes the RGB frame, converts to YCrCb, and extracts the Cb channel to the 'Cb' variable
            arguments:
            -- Mat input is the mat being converted to YCrCb
             */
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cr, 2);
    }

    @Override
    public void init(Mat firstFrame)
    {
            /* init method takes the firstFrame and crops down to focus on the rectangular space over the frame
            arguments:
            -- Mat firstFrame is, as implied, the first frame taken
             */

        inputToCb(firstFrame);
        regionleft_Cb = Cb.submat(new Rect(regionleft_pointA, regionleft_pointB));
        regionmiddle_Cb = Cb.submat(new Rect(regionmiddle_pointA, regionmiddle_pointB));
        regionright_Cb = Cb.submat(new Rect(regionright_pointA, regionright_pointB));
        inputToCr(firstFrame);
        regionleft_Cr = Cr.submat(new Rect(regionleft_pointA, regionleft_pointB));
        regionmiddle_Cr = Cr.submat(new Rect(regionmiddle_pointA, regionmiddle_pointB));
        regionright_Cr = Cr.submat(new Rect(regionright_pointA, regionright_pointB));
    }

    @Override
    public Mat processFrame(Mat input){
        /* processFrame method processes the inputs and sets position to equal correct number of rings
            arguments:
            -- Mat input is the image taken from the webcam
             */

        //COMMENT: Convert to Cb
        //  inputToCb(input);

        //COMMENT: Draw the rectangle (Blue)
        Imgproc.rectangle(
                input, //Buffer to draw on
                regionleft_pointA, //First point which defines the rectangle
                regionleft_pointB, //Second point which defines the rectangle
                Border, //Colour rectangle blue
                2); //Rectangle border lines

        Imgproc.rectangle(
                input, //Buffer to draw on
                regionmiddle_pointA, //First point which defines the rectangle
                regionmiddle_pointB, //Second point which defines the rectangle
                Border, //Colour rectangle blue
                2);

        Imgproc.rectangle(
                input, //Buffer to draw on
                regionright_pointA, //First point which defines the rectangle
                regionright_pointB, //Second point which defines the rectangle
                Border, //Colour rectangle blue
                2);

        //COMMENT: Amount of orange (ring) in the image analyzed
        avg1left = (int) Core.mean(regionleft_Cb).val[0];
        avg1middle = (int) Core.mean(regionmiddle_Cb).val[0];
        avg1right = (int) Core.mean(regionright_Cb).val[0];
        avg2left = (int) Core.mean(regionleft_Cr).val[0];
        avg2middle = (int) Core.mean(regionmiddle_Cr).val[0];
        avg2right = (int) Core.mean(regionright_Cr).val[0];

        //COMMENT: Set the ring positions
        // Record our analysis

        int lmCb = Math.abs(avg1left-avg1middle);
        int lrCb = Math.abs(avg1left-avg1right);
        int mrCb = Math.abs(avg1middle-avg1right);
        int lmCr = Math.abs(avg2left-avg2middle);
        int lrCr = Math.abs(avg2left-avg2right);
        int mrCr = Math.abs(avg2middle-avg2right);
        int Cbchange = 0;
        int Crchange = 0;

        if (lmCb < lrCb && lmCb < mrCb){
            Cbchange = Math.max(lrCb-lmCb,mrCb-lmCb);
            Cbposition = Position.right;
        } else if (lrCb < lmCb && lrCb < mrCb){
            Cbchange = Math.max(lmCb-lrCb,mrCb-lrCb);
            Cbposition = Position.middle;
        } else if (mrCb < lrCb && mrCb < lmCb){
            Cbchange = Math.max(lrCb-mrCb,lmCb-mrCb);
            Cbposition = Position.left;
        }
        if (lmCr < lrCr && lmCr < mrCr){
            Crchange = Math.max(lrCr-lmCr,mrCr-lmCr);
            Crposition = Position.right;
        } else if (lrCr < lmCr && lrCr < mrCr){
            Crchange = Math.max(lmCr-lrCr,mrCr-lrCr);
            Crposition = Position.middle;
        } else if (mrCr < lrCr && mrCr < lmCr){
            Crchange = Math.max(lrCr-mrCr,lmCr-mrCr);
            Crposition = Position.left;
        }
        if (Cbchange < Crchange) {
            position = Crposition;
        } else {
            position = Cbposition;
        }

        //COMMENT: Draw the rectangle (Green)
        Imgproc.rectangle(
                input, // Buffer to draw on
                regionleft_pointA, // First point which defines the rectangle
                regionleft_pointB, // Second point which defines the rectangle
                Fill, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        Imgproc.rectangle(
                input, // Buffer to draw on
                regionmiddle_pointA, // First point which defines the rectangle
                regionmiddle_pointB, // Second point which defines the rectangle
                Fill, // The color the rectangle is drawn in
                -1);

        Imgproc.rectangle(
                input, // Buffer to draw on
                regionright_pointA, // First point which defines the rectangle
                regionright_pointB, // Second point which defines the rectangle
                Fill, // The color the rectangle is drawn in
                -1);

        return input;
    }


    public int getAnalysisleftCb() {
        return avg1left;
    }
    public int getAnalysismiddleCb() {
        return avg1middle;
    }
    public int getAnalysisrightCb() {
        return avg1right;
    }

    public int getAnalysisleftCr() {
        return avg2left;
    }
    public int getAnalysismiddleCr() {
        return avg2middle;
    }
    public int getAnalysisrightCr() {
        return avg2right;
    }
}
