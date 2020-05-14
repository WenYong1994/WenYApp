package com.example.wenyapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import androidx.databinding.adapters.ListenerUtil;
import androidx.databinding.adapters.TextViewBindingAdapter;

import com.example.commonlibrary.utils.StringUtils;
import com.example.commonlibrary.utils.ToastUtils;
import com.example.wenyapplication.R;




public class InputTextLine extends FrameLayout {

    TextView isMustInput;
    TextView title;
    EditText inputEdit;

    boolean isMust,enableEditor;
    String inputHintStr,titleStr,inputTxt;
    int inputType;

    public InputTextLine(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
        initView();
    }

    private void initAttrs(@NonNull Context context, AttributeSet attrs) {
        if (attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputTextLine);
            isMust = typedArray.getBoolean(R.styleable.InputTextLine_single_is_must_input,true);
            inputHintStr = typedArray.getString(R.styleable.InputTextLine_single_input_hint);
            titleStr = typedArray.getString(R.styleable.InputTextLine_single_input_title);
            enableEditor = typedArray.getBoolean(R.styleable.InputTextLine_single_enable_editor,true);
            inputTxt = typedArray.getString(R.styleable.InputTextLine_single_input_text);
            inputType = typedArray.getInt(R.styleable.InputTextLine_single_input_type,InputType.TYPE_CLASS_TEXT);
            typedArray.recycle();
        }else {
            isMust = true;
            inputHintStr = "请输入";
            titleStr ="标题";
            enableEditor = true;
            inputTxt = "";
            inputType= InputType.TYPE_CLASS_TEXT;
        }

    }

    private void initView() {
        View.inflate(getContext(), R.layout.input_txt_line_layout, this);
        isMustInput = findViewById(R.id.is_must_input);
        title = findViewById(R.id.input_title);
        inputEdit = findViewById(R.id.input_edit);
        inputEdit.setInputType(inputType);
        updataView();
    }

    private void updataView() {
        isMustInput.setVisibility(isMust?VISIBLE:INVISIBLE);
        title.setText(titleStr);
        inputEdit.setHint(inputHintStr);
        inputEdit.setEnabled(enableEditor);
        inputEdit.setText(inputTxt);
        inputEdit.setFocusable(enableEditor);
        inputEdit.setFocusableInTouchMode(enableEditor);
    }


    public void setInputStr(String inputStr){
        this.inputTxt = StringUtils.getNotNullString2(inputStr);
        inputEdit.setText(inputTxt);
    }


    public String getInputStr(){
        Editable text = inputEdit.getText();
        return text == null?"":text.toString().trim();
    }

    public boolean isPhone(boolean showToast){
        if(inputType == InputType.TYPE_CLASS_PHONE){
            String inputStr = getInputStr();
            if(StringUtils.isEmpty(inputStr)&&(!isMust)){
                return true;
            }
            if(inputStr.length() == 11&&inputStr.startsWith("1")){
                return true;
            }
            if(showToast){
                ToastUtils.showShortToast(getContext(),"请输入正确电话号码");
            }
            return false;
        }

        return false;
    }

    public void setEnableEditor(boolean enableEditor) {
        this.enableEditor = enableEditor;
        updataView();
    }

    public void setMust(boolean must) {
        isMust = must;
        updataView();
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
        title.setText(titleStr);
    }

    public String getInputHintStr() {
        return inputHintStr;
    }

    public EditText getInputEdit() {
        return inputEdit;
    }

    @BindingAdapter("single_input_text")
    public static void setInputTextValue(InputTextLine inputTextLine,String inputTxt){
        if(inputTextLine!=null){
            String edTextStrng = inputTextLine.getInputStr() == null? "" :inputTextLine.getInputStr();
            inputTxt  =  inputTxt == null ?"":inputTxt;
            if(edTextStrng.equalsIgnoreCase(inputTxt)){
                return;
            }
            inputTextLine.setInputStr(inputTxt);
        }
    }


    @InverseBindingAdapter(attribute = "single_input_text", event = "valueAttrChanged")
    public static String getInputTextValue(InputTextLine inputTextLine) {
        return inputTextLine.getInputStr();
    }

    @BindingAdapter(
            value = {"android:beforeTextChanged",
                    "android:onTextChanged",
                    "android:afterTextChanged",
                    "valueAttrChanged"},
            requireAll = false)
    public static void setTextWatcher(
            InputTextLine view,
            final TextViewBindingAdapter.BeforeTextChanged before,
            final TextViewBindingAdapter.OnTextChanged on,
            final TextViewBindingAdapter.AfterTextChanged after,
            final InverseBindingListener valueAttrChanged) {
        TextWatcher newWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (before != null) {
                    before.beforeTextChanged(s, start, count, after);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (on != null) {
                    on.onTextChanged(s, start, before, count);
                }
                if (valueAttrChanged != null) {
                    valueAttrChanged.onChange();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (after != null) {
                    after.afterTextChanged(s);
                }
            }
        };
        TextWatcher oldWatcher = ListenerUtil.trackListener(view, newWatcher, R.id.textWatcher);
        if (oldWatcher != null) {
            view.inputEdit.removeTextChangedListener(oldWatcher);
        }
        view.inputEdit.addTextChangedListener(newWatcher);
    }

}

