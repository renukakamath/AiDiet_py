package com.example.aidiet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Uploadvideo extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener {
    ImageButton  i2;
    Button b1;
Spinner  s1;
    EditText e1,e2;
    SharedPreferences sh;
    String [] video_id,ename,values;

    public static  String vid;
    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    public static String encodedImage = "", path = "", newval = "", newimg = "";
    private Uri mImageCaptureUri;
    byte[] byteArray = null;
    byte[] byteArray2 = null;



    RadioButton r1, r2;
    private final int REQUEST_TAKE_GALLERY_VIDEO = 305;
    String description, count, inc, dietmode, Calorie, recipy_id;
    private static final int CAMERA_CODE = 101, CROPING_CODE = 301, READ_EXTERNAL_STORAGE_PERMISSION = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadvideo);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        i2 = (ImageButton) findViewById(R.id.videobtn);
        s1=(Spinner)findViewById(R.id.spinner);


        s1.setOnItemSelectedListener(this);




        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Uploadvideo.this;
        String q = "/spinner?log_id=" +sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);


//
        e1 = (EditText) findViewById(R.id.count);
        e2 =(EditText) findViewById(R.id.des);

        b1 = (Button) findViewById(R.id.button);

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVideoOption();

            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = e1.getText().toString();
                description = e2.getText().toString();

                sendAttach();
            }
        });
    }

    private void sendAttach() {
        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//	            String uid = sh.getString("uid", "");


//        	latitude=LocationService.lati;
//        	longitude=LocationService.logi;


            String q = "http://" + IPsettings.ip + "/api/Uploadvideo";
//            String q = "http://" +IpSetting.ip+"/api/user_upload_file";
//	            String q = "/user_upload_file/?image="+imagename+"&desc="+des+"&yts="+yt;
//	        	String q = "http://" +IPSetting.ip+"/TeachersHelper/api.php?action=teacher_upload_notes";
//	        	String q= "api.php?action=teacher_upload_notes&sh_id="+Teacher_view_timetable.s_id+"&desc="+des;




            Map<String, byte[]> aa = new HashMap<>();


            aa.put("video", byteArray2);

            aa.put("count", count.getBytes());
            aa.put("description", description.getBytes());

            aa.put("vid", vid.getBytes());

            aa.put("lid",sh.getString("log_id","").getBytes());
            Toast.makeText(getApplicationContext(), "Byte Array:" + byteArray2.length, Toast.LENGTH_LONG).show();




//            aa.put("sid",Parentmanagestudent.sids.getBytes());
//            aa.put("latitude",LocationService.lati.getBytes());
//            aa.put("longitude",LocationService.logi.getBytes());

            //   aa.put("logid", sh.getString("log_id", "").getBytes());
//            aa.put("link",link.getBytes());
//            aa.put("description",description.getBytes());
//            aa.put("house",house.getBytes());
//            aa.put("phone",phone.getBytes());
//            aa.put("email",email.getBytes());
//            aa.put("gender",gender.getBytes());
//            aa.put("dob",dob.getBytes());
//            aa.put("username",user.getBytes());
//            aa.put("password",pass.getBytes());

            FileUploadAsync fua = new FileUploadAsync(q);
            fua.json_response = (JsonResponse) Uploadvideo.this;
            fua.execute(aa);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }
    private void selectVideoOption() {

        /*Android 10+ gallery code
        android:requestLegacyExternalStorage="true"*/

//        final CharSequence[] items = {"Capture Photo", "Choose from Gallery" ,"Choose From Video", "Cancel"};
        final CharSequence[] items = {"Choose From Video", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Uploadvideo.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    newval="image";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    newval="image";
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                }
                else if (items[item].equals("Choose From Video")) {
                    //  ftype = "video";
                    newval="video";
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);


                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
            //   CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
            // Get the path
            //String path = null;
            try {
                path = FileUtils.getPath(this, uri);
//                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();

                File fl = new File(path);
                int ln = (int) fl.length();

                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray2 = bos.toByteArray();

                Bitmap bit = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
//                Toast.makeText(getApplicationContext(), "test case: "+bit, Toast.LENGTH_LONG).show();
                i2.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray2, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                i2.setImageBitmap(thumbnail);
                byteArray2 = baos.toByteArray();

                String str = Base64.encodeToString(byteArray2, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
//	                selectedImagePath = getPaths(selectedImageUri);
//	                if (selectedImagePath != null) {
//
//	                	Toast.makeText(getApplicationContext(), "Helloo Files", Toast.LENGTH_LONG).show();
//	                    Intent intent = new Intent(User_upload_files.this,
//	                            User_upload_files.class);
//	                    intent.putExtra("path", selectedImagePath);
//	                    startActivity(intent);
//	                }
//	                image = decodeFile(selectedImagePath);
                try {
                    InputStream iStream =   getContentResolver().openInputStream(selectedImageUri);
                    byte[] inputData = getBytes(iStream);
                    Toast.makeText(getApplicationContext(), "Len : " + inputData.length, Toast.LENGTH_LONG).show();
                    byteArray2 = inputData;
                } catch(Exception e) {}
            }


        }
    }
    public byte[] getBytes(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    @Override
    public void response(JSONObject jo) {

        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("water")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), UserHome.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewwater"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    video_id=new String[ja1.length()];


                    ename=new String[ja1.length()];

                    values=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        video_id[i]=ja1.getJSONObject(i).getString("video_id");
                        ename[i]=ja1.getJSONObject(i).getString("ename");



                        values[i]=ename[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,values);
                    s1.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override

    public void onBackPressed()
    {


        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),UserHome.class);
        startActivity(b);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        vid=video_id[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}