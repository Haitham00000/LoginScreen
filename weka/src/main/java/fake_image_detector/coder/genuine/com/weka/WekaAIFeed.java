package fake_image_detector.coder.genuine.com.weka;

import java.util.ArrayList;

import static fake_image_detector.coder.genuine.com.weka.FakeFlags.FAKE_FLAGS_MED;

/**
 * instead of have to put all Weka files in our app we can send data to already existing web lib.
 */
public class WekaAIFeed {

    protected static final long Time = 150; // usually process time take less than 150 milliseconds.
    byte[] fakeSignature = {1, 0, 0, 1, 0, 1, 0, 100};
    ArrayList<Byte> tempBytes = new ArrayList<>();
    ArrayList<FakeFlags> fakeFlagsArrayList = new ArrayList<>();
    private boolean isFake;

    public WekaAIFeed(byte[] bytes) {

        slowFeed(bytes);
    }

    /**
     * feed weka lib data. free access Weka lib available at :
     * ###Note : for free  users link can go down anytime!
     * Premium users can use the second link as mentioned in docs and should send cred.
     *
     * @param bytes
     */
    private void slowFeed(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (isFake) break;
            read(bytes[i]);
        }

        if (isFake) {
            promptFakeImg();
        } else {
            promptGenuineImg();
        }
    }


    private void read(byte aByte) {
        if (tempBytes.size() == fakeSignature.length) {
            analyze();
        } else {
            tempBytes.add(aByte);
        }
    }

    private void analyze() {
        FakeFlags fakeFlags = new FakeFlags();
        for (int i = 0; i < fakeSignature.length; i++) {
            if (tempBytes.get(i) == fakeSignature[i]) {
                fakeFlags.setFlag(i);
            }
        }

        tempBytes.clear();

        if (fakeFlagsArrayList.size() >= FAKE_FLAGS_MED) {
            isFake = true;
        } else {
            fakeFlagsArrayList.add(fakeFlags);
        }


    }

    private void promptFakeImg() {

        addChildToFireBaseToUpdateImageState();
    }


    private void promptGenuineImg() {

    }

    private void addChildToFireBaseToUpdateImageState() {
        // Write a message to the database

    }
}
