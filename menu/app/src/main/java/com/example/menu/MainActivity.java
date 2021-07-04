package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.Adapter;
import com.example.model.Model;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayList<Model> modelArrayList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();

    }

    private void addViews() {

        lvProduct = findViewById(R.id.lvProduct);
        modelArrayList = new ArrayList<>();
        adapter = new Adapter(this, R.layout.item_row, modelArrayList);
        lvProduct.setAdapter(adapter);
    }

    private  void addData(){
        EditText edtAddProduct = findViewById(R.id.edtAddProduct);
        EditText edtAddPrice = findViewById(R.id.edtAddPrice);
        String addProduct = edtAddProduct.getText().toString();
        int addPrice = Integer.parseInt(edtAddPrice.getText().toString());
        modelArrayList.clear();
        modelArrayList.add(new Model(addProduct, addPrice));
//        Model m = new Model();
//        m.setName(addProduct);
//        m.setPrice(addPrice);
//        modelArrayList.add(m);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAdd)
        {
            openDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_add);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtAddProduct = dialog.findViewById(R.id.edtAddProduct);
        final EditText edtAddPrice = dialog.findViewById(R.id.edtAddPrice);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addProduct = edtAddProduct.getText().toString();
                int addPrice = Integer.parseInt(edtAddPrice.getText().toString());
                if (addProduct.equals("") )
                {
                    Toast.makeText(MainActivity.this, "Please enter product name!", Toast.LENGTH_SHORT).show();
                }
                else if (addPrice == 0){
                    Toast.makeText(MainActivity.this, "Please enter price !", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(MainActivity.this, "Added product successful!", Toast.LENGTH_LONG).show();
                    modelArrayList.clear();
                    modelArrayList.add(new Model(addProduct, addPrice));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

               // addData();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnEdit)
        {

        }
        if (item.getItemId() == R.id.mnDelete)
        {

        }
        return super.onContextItemSelected(item);
    }

    public void openDialogEditProduct(String name, String price)
    {

    }

    public void deleteProduct(String name, int price)
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure you want to delete this task: " + name + "?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Deleted task successful!",
                        Toast.LENGTH_LONG).show();
            }
        });
        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}