package com.zt.mala3b.SharedPackage.ClassesPackage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage.AddStadiumFragment;
import com.zt.mala3b.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraHelper {

    private AddStadiumFragment context;
    private Uri uriFilePath=null;
    private String mCurrentPhotoPath;
    private Bitmap bitmap;


    public CameraHelper(AddStadiumFragment addStadiumFragment) {
        this.context=addStadiumFragment;
    }

    public void SelectPhotoDialog() {

        final CharSequence[] items = {
                context.getResources().getString(R.string.takephoto),
                context.getResources().getString(R.string.choosegallery),
                context.getResources().getString(R.string.cancel_dialog)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context.getContext());
        builder.setTitle(context.getResources().getString(R.string.add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(context.getResources().getString(R.string.takephoto))) {

                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion >= Build.VERSION_CODES.M) {

                        int permissionCheck = ContextCompat.checkSelfPermission(context.getContext(),
                                Manifest.permission.CAMERA);


                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            ChooseImageCamera();

                        } else {  //  PERMISSION_DENIED


                            ActivityCompat.requestPermissions((Activity) context.getContext(),
                                    new String[]{Manifest.permission.CAMERA},0);
                        }
                    } else {

                        ChooseImageCamera();
                    }

                } else if (items[item].equals(context.getResources().getString(R.string.choosegallery))) {
                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion >= Build.VERSION_CODES.M) {

                        int permissionCheck = ContextCompat.checkSelfPermission(context.getContext(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);


                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            ChooseImageGallery();


                        } else {  //  PERMISSION_DENIED
                            ActivityCompat.requestPermissions((Activity) context.getContext(),
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        }

                    } else {
                        ChooseImageGallery();
                    }

                } else if (items[item].equals(context.getResources().getString(R.string.cancel_dialog))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void ChooseImageGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(Intent.createChooser(intent, context.getResources().getString(R.string.add_photo)), Constant.Gallery);
    }

    private void ChooseImageCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                uriFilePath = FileProvider.getUriForFile(context.getContext(),
                        "com.zt.mala3b.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriFilePath);
                context.startActivityForResult(takePictureIntent, Constant.Camera);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private String getImagePathFromInputStreamUri(Uri uri) {
        InputStream inputStream = null;
        String filePath = null;

        if (uri.getAuthority() != null) {
            try {
                inputStream = context.getContext().getContentResolver().openInputStream(uri); // context needed
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                // log
            } catch (IOException e) {
                // log
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;
    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        File targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }

    private File createTemporalFile() {
        return new File(context.getContext().getExternalCacheDir(), "tempFile.jpg"); // context needed
    }

    private Bitmap fixRotate(String photoPath) throws IOException {
        ExifInterface ei = new ExifInterface(photoPath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                bitmap = rotateImage(90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                bitmap = rotateImage(180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                bitmap = rotateImage(270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                break;
        }
        return bitmap;
    }

    private Bitmap rotateImage(float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Bitmap onResult(int requestCode, Intent data){
        if (requestCode == Constant.Gallery) {
            if (data != null) {
                uriFilePath = data.getData();
                mCurrentPhotoPath = getImagePathFromInputStreamUri(uriFilePath);
                return setImage();
            }else
                return null;

        } else if (requestCode == Constant.Camera) {
            return setImage();
        }else
            return null;
    }

    private Bitmap setImage() {
        bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, new BitmapFactory.Options());
        try {
            bitmap=fixRotate(mCurrentPhotoPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
