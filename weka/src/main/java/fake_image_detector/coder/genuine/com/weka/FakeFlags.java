package fake_image_detector.coder.genuine.com.weka;

public class FakeFlags {
    protected static final int FAKE_FLAGS_HI = 50; // use this for better results
    protected static final int FAKE_FLAGS_MED = 35;

    private boolean flag_1;
    private boolean flag_2;
    private boolean flag_3;
    private boolean flag_4;
    private boolean flag_5;

    public void setFlag(int i) {

        switch (i) {
            case 1:
                setFlag_1(true);
                break;
            case 2:
                setFlag_2(true);
                break;
            case 3:
                setFlag_3(true);
                break;
            case 4:
                setFlag_4(true);
                break;
            case 5:
                setFlag_5(true);
                break;
        }

    }


    public boolean isFlag_1() {
        return flag_1;
    }

    public void setFlag_1(boolean flag_1) {
        this.flag_1 = flag_1;
    }

    public boolean isFlag_2() {
        return flag_2;
    }

    public void setFlag_2(boolean flag_2) {
        this.flag_2 = flag_2;
    }

    public boolean isFlag_3() {
        return flag_3;
    }

    public void setFlag_3(boolean flag_3) {
        this.flag_3 = flag_3;
    }

    public boolean isFlag_4() {
        return flag_4;
    }

    public void setFlag_4(boolean flag_4) {
        this.flag_4 = flag_4;
    }

    public boolean isFlag_5() {
        return flag_5;
    }

    public void setFlag_5(boolean flag_5) {
        this.flag_5 = flag_5;
    }
}
