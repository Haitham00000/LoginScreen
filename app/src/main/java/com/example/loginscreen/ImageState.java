package com.example.loginscreen;

public class ImageState {
    private String adminMsg;
    private String userMsg;
    private boolean colorRed;

    public ImageState() {
    }


    public ImageState(String adminMsg, String userMsg, boolean colorRed) {
        this.adminMsg = adminMsg;
        this.userMsg = userMsg;
        this.colorRed = colorRed;
    }

    public String getAdminMsg() {
        return adminMsg;
    }

    public void setAdminMsg(String adminMsg) {
        this.adminMsg = adminMsg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public boolean getColorRed() {
        return colorRed;
    }

    public void setColorRed(boolean colorRed) {
        this.colorRed = colorRed;
    }
}
