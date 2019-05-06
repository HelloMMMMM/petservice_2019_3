package com.hellom.petserviceandroid.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;

public class TakePhotoUtil {
    public static final int TAKE_PHOTO_FROM_GALLERY = 1;
    public static final int TAKE_PHOTO_FROM_CAMERA = 2;
    public static final int CLIP_PHOTO = 3;

    public static void openGallery(Activity activity) {
        //调用系统图库，选择图片
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        //返回结果和标识
        activity.startActivityForResult(intent, TAKE_PHOTO_FROM_GALLERY);
    }

    public static Uri openCamera(Activity activity) {
        // 启动系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mImageCaptureUri;
        // 判断7.0android系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //临时添加一个拍照权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //通过FileProvider获取uri
            mImageCaptureUri = FileProvider.getUriForFile(activity, "com.hellom.petserviceandroid.provider", new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        } else {
            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        }
        //  返回结果和标识
        activity.startActivityForResult(intent, TAKE_PHOTO_FROM_CAMERA);
        return mImageCaptureUri;
    }

    public static void openGallery(Fragment fragment) {
        //调用系统图库，选择图片
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        //返回结果和标识
        fragment.startActivityForResult(intent, TAKE_PHOTO_FROM_GALLERY);
    }

    public static Uri openCamera(Fragment fragment) {
        // 启动系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mImageCaptureUri;
        // 判断7.0android系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //临时添加一个拍照权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //通过FileProvider获取uri
            mImageCaptureUri = FileProvider.getUriForFile(fragment.getActivity(), "com.hellom.petserviceandroid.provider", new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        } else {
            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        }
        //  返回结果和标识
        fragment.startActivityForResult(intent, TAKE_PHOTO_FROM_CAMERA);
        return mImageCaptureUri;
    }
}
