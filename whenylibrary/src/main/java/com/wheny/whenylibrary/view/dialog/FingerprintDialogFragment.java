package com.wheny.whenylibrary.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.wheny.whenylibrary.R;

import javax.crypto.Cipher;

@SuppressLint("ValidFragment")
public class FingerprintDialogFragment extends BaseDialogFragment {

    private FingerprintManager fingerprintManager;

    private CancellationSignal mCancellationSignal;

    private Cipher mCipher;

    private OnFingerprintPassListener onFingerListener;

    private TextView errorMsg;

    public FingerprintDialogFragment(OnFingerprintPassListener onFingerListener) {
        this.onFingerListener = onFingerListener;
    }

    /**
     * 标识是否是用户主动取消的认证。
     */
    private boolean isSelfCancelled;

    public void setCipher(Cipher cipher) {
        mCipher = cipher;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        onFingerListener = (LoginActivity) getActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fingerprintManager = getContext().getSystemService(FingerprintManager.class);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fingerprint_dialog, container, false);
        errorMsg = v.findViewById(R.id.error_msg);
        TextView cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                dismiss();
                stopListening();
            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();
        // 开始指纹认证监听
        startListening(mCipher);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 停止指纹认证监听
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stopListening();
        }
    }

    @SuppressLint("MissingPermission")
    private void startListening(Cipher cipher) {
        isSelfCancelled = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mCancellationSignal = new CancellationSignal();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager.authenticate(new FingerprintManager.CryptoObject(cipher), mCancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    if (!isSelfCancelled) {
                        errorMsg.setText(errString);
                        if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
//                            Toast.makeText(onFingerListener, errString, Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    errorMsg.setText(helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    onFingerListener.onFingerprintPass();
                    dismiss();
                }

                @Override
                public void onAuthenticationFailed() {
                    errorMsg.setText("指纹认证失败，请再试一次");
                }
            }, null);
        }
    }

    private void stopListening() {
        if (mCancellationSignal != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mCancellationSignal.cancel();
            }
            mCancellationSignal = null;
            isSelfCancelled = true;
        }
    }

    public interface OnFingerprintPassListener{
        void onFingerprintPass();
    }

}
