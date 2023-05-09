package com.example.atif.pxintegration;

import android.app.Application;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AnotherActivity extends AppCompatActivity {

    /*
    private void fillTable(final int n, final double[][] matrix, TableLayout table) {
        table.removeAllViews();
        for (int i = 0; i < n; i++) {
            TableRow row = new TableRow(AnotherActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < n; j++) {
                EditText edit = new EditText(AnotherActivity.this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                edit.setText(Double.toString(matrix[i][j]));

                edit.setKeyListener(null);
                row.addView(edit);
            }
            table.addView(row);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final int rows = 3;
        final int cols = 3;

        double[][] matrix = new double[rows][cols];

        int max = 9;
        int min = 1;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                matrix[i][j] = new Random().nextInt(max - min + 1) + min;
            }
        }


        System.out.println("Array is " + Arrays.deepToString(matrix));

//        for(int i = 0; i < rows; i++){
//            for(int j = 0; j < cols; j++){
//                Log.d("matrix-","- " + matrix[i][j] + " ");
//            }
//            Log.d("matrix-", "\n");
//        }


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                TableLayout tb = new TableLayout(getApplicationContext());
                fillTable(rows, matrix, tb);
            }
        });
   }*/
}
