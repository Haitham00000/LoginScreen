package fake_image_detector.coder.genuine.com.weka;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.File;

public class WekaClassifier {

    String mImageInfoString;
    Context mContext;
    /* renamed from: o */
    Context f8023o = mContext;
    /* renamed from: p */
    Boolean f8024p = false;
    /* renamed from: q */
    File f8025q = null;
    /* renamed from: r */
    Bitmap f8026r = null;

    public WekaClassifier(Context cntx, String imageInfoString) {

        mContext = cntx;
        mImageInfoString = imageInfoString;

    }

    /* renamed from: fake_image_detector.coder.genuine.com.fakeimagedetector.ui.ElaResult$a */
    class C3037a extends AsyncTask<String, Void, byte[]> {

        /* renamed from: a */
        ProgressDialog f8031a;

        C3037a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public byte[] doInBackground(String... strArr) {
            return getBitmapFromString();
        }


        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);

            feed(bytes);

        }


        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.f8031a.show();
        }

        //TODO : find a better way to realize image decoding!

        /**
         * this function is not reliable way to feed weka with images.
         * Sometimes, Weka can treat same image as fake and genuine.
         * For example, you will check certain image,
         * in first attempt it will till you it is fake and in second attempt it may tell it is genuine!!!
         */
        private byte[] getBitmapFromString() {
            try {
                byte[] encodeByte = Base64.decode(mImageInfoString, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                return encodeByte;
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }


        private void feed(byte[] bytes) {

            new WekaAIFeed(bytes);

        }

    }
}



