package com.wischase.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.wischase.Category;
import com.wischase.Question;
import com.wischase.R;
import com.wischase.db.DBHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UploadAFile extends UpdateTable {
    Category userInput;
    int grade;
    int categoryIdInput;
    private File dir;
    Question upload = new Question();
    private static final int REQUEST_PICK_FILE = 1;

    private TextView mFilePathTextView;
    private Button mStartActivityButton;
    private File selectedFile;

    String s = "";
    File sdcard1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    File internal = Environment.getDownloadCacheDirectory().getAbsoluteFile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_afile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (getIntent().hasExtra(ActivityConstants.USER_INPUT)) {
            userInput = (Category) (intent.getParcelableExtra(ActivityConstants.USER_INPUT));
            grade = intent.getIntExtra(ActivityConstants.GRADE_INPUT, 0);
            super.updateCategoryTable(userInput, grade);
            categoryIdInput = userInput.getSubCategory().get(0).getCategoryId();
        }
        // mFilePathTextView = (TextView)findViewById(R.id.filena);
        //   mFilePathTextView.setText(s);
        /*if (getIntent().hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
            s = getIntent().getStringExtra(FilePickerActivity.EXTRA_FILE_PATH);

            checkAvailablity();
        }*/    }

    public void checkAvailablity(View view) {
        try {
            // s is the absolute file path
            FileInputStream fIn = new FileInputStream("/storage/emulated/0/Download/upload.xls");
             Workbook wb = Workbook.getWorkbook(fIn);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();
            int failures=0;
            String rowNotUploaded="Rows that are not uploaded: ";
            String xx = " ";
            for (int i = 1; i < row; i++) {
                Cell z = s.getCell(0,i);
                xx = z.getContents();
                //checking Question is given
               quest:
                if (!xx.equals("")) {
//                Toast.makeText(getApplicationContext(), xx, Toast.LENGTH_LONG).show();
                    upload.setQuestionText(xx);
                    z = s.getCell(1, i);
                    xx = z.getContents();
                    //Checking option1 is given
                           if (!xx.equals("")) {
                        upload.setOptionOne(xx);
                        z = s.getCell(2,i);
                        xx = z.getContents();
                        //Checking option2 is given
                        if (!xx.equals("")) {
                            upload.setOptionTwo(xx);
                            z = s.getCell(3,i);
                            xx = z.getContents();
                            upload.setOptionThree(xx);
                            z = s.getCell(4,i);
                            xx = z.getContents();
                            upload.setOptionFour(xx);
                            z = s.getCell(5,i);
                            xx = z.getContents();
                            //Checking answer is given
                            if (!xx.equals("")) {
                                int answer = Integer.parseInt(xx);
                                upload.setCorrectAnswer(answer);
                                z = s.getCell(6,i);
                                xx = z.getContents();
                                upload.setExplanation(xx);
                                upload.setCategoryId(categoryIdInput);
                                upload.setUserid(1);
                                upload.setGrade(grade);
                                DBHandler dbHandler = new DBHandler(this);
                                long questNo = dbHandler.insertQuestion(upload);
                                dbHandler.close();
                            }
                            //Row numbers are saved and displayed at last.
                            else
                            {
                                rowNotUploaded= rowNotUploaded+i+" ";
                                failures=1;
                                break quest;
                            }

                        }//Row numbers are saved and displayed at last.
                        else
                        {
                            rowNotUploaded= rowNotUploaded+i+" ";
                            failures=1;
                            break quest;
                        }

                    }//Row numbers are saved and displayed at last.
                    else
                    {
                        rowNotUploaded= rowNotUploaded+i+" ";
                        failures=1;
                        break quest;
                    }

                }
                //Row numbers are saved and displayed at last.
                else
                {
                    rowNotUploaded= rowNotUploaded+i+" ";
                    failures=1;

                }
            }
            Toast.makeText(getBaseContext(), "Completed Uploading", Toast.LENGTH_LONG).show();

            if(failures==1){
                Toast.makeText(getBaseContext(),rowNotUploaded,Toast.LENGTH_LONG).show();
            }
        }catch (FileNotFoundException e1) {
            // e1.printStackTrace();
            Toast.makeText(getApplicationContext(), "file not found exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "io exception", Toast.LENGTH_LONG).show();
        } catch (BiffException e) {
            // e.printStackTrace();
            Toast.makeText(getApplicationContext(), "biff exception", Toast.LENGTH_LONG).show();
        }
    }


 /*   public void browseFolderSD(View v) {
        if(Environment.isExternalStorageRemovable()) {
            // Create a new Intent for the file picker activity
            Intent intent = new Intent(this, FilePickerActivity.class);
            // Only make .xls files visible
            ArrayList<String> extensions = new ArrayList<String>();
            extensions.add(".xls");
            intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);
            //intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH, Environment.getExternalStorageDirectory());
           // intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH,"/sdcard/");
            // Start the activity
            // startActivityForResult(intent, REQUEST_PICK_FILE);
            //  break;
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(),"SD card is not available",Toast.LENGTH_LONG).show();

    }
    public void browseFolderPhone(View view)
    {
        Intent intent = new Intent(this, FilePickerActivity.class);
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add(".xls");
        intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);
        //these version needs checking for download directory alone
        if((android.os.Build.VERSION.RELEASE.equals("4.3"))||(android.os.Build.VERSION.RELEASE.startsWith("4.2"))||(android.os.Build.VERSION.RELEASE.startsWith("4.1"))||(android.os.Build.VERSION.RELEASE.startsWith("4.0")))
        {
            if(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).isDirectory())
            {
                intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH,  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));}
        }
        else
        {
            intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH,Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        }startActivity(intent);

    }
    public void browseOneDrive(View view)
    {

    }*/
}
   // @Override
 /*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case REQUEST_PICK_FILE:
                    if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
                        s=data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH);
                        // Get the file path
                       // selectedFile = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
                        // Set the file path text view
                        mFilePathTextView.setText(s);
mFilePathTextView.setVisibility(View.VISIBLE);
                        //Now you have your selected file, You can do your additional requirement with file.
                    }
                    break;
            }
        }
    }*/

