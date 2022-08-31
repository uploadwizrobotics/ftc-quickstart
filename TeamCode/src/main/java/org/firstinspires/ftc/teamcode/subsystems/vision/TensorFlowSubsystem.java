package org.firstinspires.ftc.teamcode.subsystems.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.R;

import java.util.List;

public class TensorFlowSubsystem {

    HardwareMap hardwareMap;

    public VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;

    //declare tensorflow variables
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    //declare vuforia info
    private static final String VUFORIA_KEY =
            "ARxx8iD/////AAABmbS2nmcBoEChkoWzG9AGa2EDVhPKPlhaEz6XN4C2VcvZroE1t5+3F8ZoVL77a7Ynof/UlFJ4thqxL+u5pLag9/4VX4JDfVl3S8EEAsvBdcvssq6epPgQ2vDaeFb6hMa2qNqCfkdVY0TnvErz0XWAIzimTJISvctyRtS2JCf3nJQ3FBqNuw6h5mmM5y09NdDEH4oNvN28jQbxkSfgK0BejMp/ElJGU4vawIS9XyOD9i7rBrrUZTyqGnmURDItpOwRNseOtNjtXGIKszEHTLh6q5L5pl/kBybGHi40G2CjomR50XvwU988t4eQ4IXAEcjliYhkLjx3xq1VCfdayZBB0mNP18T7gbW/btNWkLrzXQFk";

    public TensorFlowSubsystem(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        initVuforia();
        initTfod();

        if(tfod !=null) {
            tfod.activate();
            tfod.setZoom(2.5,16.0/9.0);
        }
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Name of Camera on Phone");
        parameters.useExtendedTracking = true;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }

    public String[] GetObjectTypes(){
        String[] noObjects= new String[1];
        noObjects[0] = "Nothing";

        if(tfod !=null){
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if(updatedRecognitions != null) {
                String[]objects = new String[updatedRecognitions.size()];
                int i = 0;
                for( Recognition recognition: updatedRecognitions) {
                    objects[i] = recognition.getLabel();
                    i++;
                }
                return objects;
            }

        }

        return noObjects;
    }



}
