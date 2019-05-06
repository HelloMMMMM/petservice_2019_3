package com.hellom.petserviceandroid.editinfo;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.hellom.petserviceandroid.register.PettypeBean;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class EditInfoActivity extends BaseActivity {
    private QMUIRoundButton updateInfo;

    private EditText masterName;
    private EditText masterAddress;
    private EditText masterPhone;
    private EditText petName;
    private EditText petKind;
    private EditText petAge;
    private EditText password;

    private PettypeBean pettypeBean;
    private TextView petType;

    @Override
    public void initView() {
        updateInfo = findViewById(R.id.btn_register);
        updateInfo.setText("更新");

        masterName = findViewById(R.id.user_nickname);
        masterAddress = findViewById(R.id.user_address);
        masterPhone = findViewById(R.id.user_phone);
        petName = findViewById(R.id.pet_name);
        petKind = findViewById(R.id.pet_kind);
        petAge = findViewById(R.id.pet_age);
        petType = findViewById(R.id.pet_type);
        password = findViewById(R.id.user_password);

        SPUtils spUtils = SPUtils.getInstance();
        masterName.setText(spUtils.getString("masterName"));
        masterAddress.setText(spUtils.getString("masterAddress"));
        masterPhone.setText(spUtils.getString("masterPhone"));
        petName.setText(spUtils.getString("petName"));
        petKind.setText(spUtils.getString("petKind"));
        petAge.setText(String.valueOf(spUtils.getInt("petAge")));
        petType.setText(spUtils.getString("petType"));
        password.setText(spUtils.getString("password"));
    }

    @Override
    public void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
        petType.setOnClickListener(this);
        updateInfo.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPetType();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_edit_info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.pet_type:
                showPetTypeList();
                break;
            case R.id.btn_register:
                update();
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        KeyboardUtils.hideSoftInput(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(password.getText().toString())||TextUtils.isEmpty(masterName.getText().toString())
                || TextUtils.isEmpty(masterAddress.getText().toString()) || TextUtils.isEmpty(masterPhone.getText().toString()) || TextUtils.isEmpty(petName.getText().toString()) ||
                TextUtils.isEmpty(petKind.getText().toString()) || TextUtils.isEmpty(petAge.getText().toString()) || TextUtils.isEmpty(petType.getText().toString()) ||
                masterName.getText().toString().length() > 8 || masterPhone.getText().toString().length() != 11
                || petName.getText().toString().length() > 8||password.getText().toString().length()<6) {
            ToastUtils.showShort("请按要求将信息填写完整");
            return false;
        }
        return true;
    }

    private void update() {
        if (!checkInput()) {
            return;
        }

        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.UPDATE_MASTER_INFO)
                .params("id", SPUtils.getInstance().getInt("id"))
                .params("masterName", masterName.getText().toString())
                .params("masterAddress", masterAddress.getText().toString())
                .params("masterPhone", masterPhone.getText().toString())
                .params("petName", petName.getText().toString())
                .params("petKind", petKind.getText().toString())
                .params("petType", petType.getText().toString())
                .params("petAge", petAge.getText().toString())
                .params("password",password.getText().toString())
                .execute(new LoadingCallBack<>(ResponseBean.class, this, new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        Intent intent=new Intent();
                        intent.putExtra("updatePassword",false);
                        if (!password.getText().toString().equals(SPUtils.getInstance().getString("password"))){
                            intent.putExtra("updatePassword",true);
                        }
                        ToastUtils.showShort("更新成功");
                        setResult(RESULT_OK,intent);
                        finish();
                    }

                    @Override
                    public void onError() {

                    }
                }, true));
    }

    private void getPetType() {
        OkGo.<PettypeBean>post(Constant.ROOT_URL + Constant.GET_ALL_PET_TYPE).execute(new LoadingCallBack<>(PettypeBean.class, this, new LoadingCallBack.Callback<PettypeBean>() {
            @Override
            public void onSuccess(PettypeBean response) {
                pettypeBean = response;
            }

            @Override
            public void onError() {

            }
        }, true));
    }

    private void showPetTypeList() {
        if (pettypeBean == null) {
            return;
        }
        final List<PettypeBean.PetTypeListBean> petTypeListBeans = pettypeBean.getPetTypeList();
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (PettypeBean.PetTypeListBean petTypeListBean : petTypeListBeans) {
            builder.addItem(petTypeListBean.getPetTypeName());
        }
        builder.setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                dialog.dismiss();
                petType.setText(petTypeListBeans.get(position).getPetTypeName());
            }
        }).build().show();
    }
}
