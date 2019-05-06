package com.hellom.petserviceandroid.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.hellom.petserviceandroid.util.GlideUtil;
import com.hellom.petserviceandroid.util.KeyBoardLayoutUtil;
import com.hellom.petserviceandroid.util.TakePhotoUtil;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.io.File;
import java.util.List;

public class RegisterActivity extends BaseActivity {

    private EditText userName;
    private EditText password;
    private EditText masterName;
    private EditText masterAddress;
    private EditText masterPhone;
    private EditText petName;
    private EditText petKind;
    private EditText petAge;

    private PettypeBean pettypeBean;
    private TextView petType;
    private ImageView petImg;
    private Uri targetUri;

    @Override
    public void initView() {
        KeyBoardLayoutUtil.controlKeyboardLayout(findViewById(R.id.content));
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        masterName = findViewById(R.id.user_nickname);
        masterAddress = findViewById(R.id.user_address);
        masterPhone = findViewById(R.id.user_phone);
        petName = findViewById(R.id.pet_name);
        petKind = findViewById(R.id.pet_kind);
        petAge = findViewById(R.id.pet_age);
        petType = findViewById(R.id.pet_type);
        petImg = findViewById(R.id.pet_img);

        userName.setVisibility(View.VISIBLE);
        findViewById(R.id.pet_img_select).setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
        petType.setOnClickListener(this);
        findViewById(R.id.pet_img_select).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPetType();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pet_type:
                showPetTypeList();
                break;
            case R.id.pet_img_select:
                showTakePhotoList();
                break;
            case R.id.btn_register:
                register();
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(masterName.getText().toString())
                || TextUtils.isEmpty(masterAddress.getText().toString()) || TextUtils.isEmpty(masterPhone.getText().toString()) || TextUtils.isEmpty(petName.getText().toString()) ||
                TextUtils.isEmpty(petKind.getText().toString()) || TextUtils.isEmpty(petAge.getText().toString()) || TextUtils.isEmpty(petType.getText().toString()) ||
                targetUri == null || userName.getText().toString().length() < 8 || password.getText().toString().length() < 6 ||
                masterName.getText().toString().length() > 8 || masterPhone.getText().toString().length() != 11
                || petName.getText().toString().length() > 8) {
            ToastUtils.showShort("请按要求将信息填写完整");
            return false;
        }
        return true;
    }

    private void register() {
        if (!checkInput()) {
            return;
        }

        File file = UriUtils.uri2File(targetUri);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Bitmap compressBitmap = ImageUtils.compressBySampleSize(bitmap, 2, true);
        FileUtils.createFileByDeleteOldFile(file);
        ImageUtils.save(compressBitmap, file, Bitmap.CompressFormat.JPEG);

        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.REGISTER)
                .params("userName", userName.getText().toString())
                .params("password", password.getText().toString())
                .params("masterName", masterName.getText().toString())
                .params("masterAddress", masterAddress.getText().toString())
                .params("masterPhone", masterPhone.getText().toString())
                .params("petName", petName.getText().toString())
                .params("petKind", petKind.getText().toString())
                .params("petType", petType.getText().toString())
                .params("petAge", petAge.getText().toString())
                .params("petImg", file)
                .execute(new LoadingCallBack<>(ResponseBean.class, this, new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("注册成功，去登录");
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

    private void showTakePhotoList() {
        new QMUIBottomSheet.BottomListSheetBuilder(this).addItem("相册").addItem("拍照").setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                dialog.dismiss();
                switch (position) {
                    case 0:
                        TakePhotoUtil.openGallery(RegisterActivity.this);
                        break;
                    case 1:
                        targetUri = TakePhotoUtil.openCamera(RegisterActivity.this);
                        break;
                    default:
                        break;
                }
            }
        }).build().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TakePhotoUtil.TAKE_PHOTO_FROM_CAMERA:
                    petImg.setVisibility(View.VISIBLE);
                    GlideUtil.load(this, targetUri, petImg);
                    break;
                case TakePhotoUtil.TAKE_PHOTO_FROM_GALLERY:
                    petImg.setVisibility(View.VISIBLE);
                    targetUri = data.getData();
                    GlideUtil.load(this, targetUri, petImg);
                    break;
                default:
                    break;
            }
        }
    }
}
