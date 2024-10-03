package com.example.newlab10;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // กำหนดข้อมูลกาแฟ
    private coffee[] coffees = {
            new coffee(R.drawable.coffee1, "เอสเปรสโซ", "100 ฿"),
            new coffee(R.drawable.coffee2, "ลาเต้", "120 ฿"),
            new coffee(R.drawable.coffee3, "อเมริกาโน", "70 ฿"),
            new coffee(R.drawable.coffee4, "ชาเขียว", "70 ฿"),
            new coffee(R.drawable.coffee5, "ม็อกค่า", "100 ฿"),
    };

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // เชื่อมต่อกับ LinearLayout ใน XML
        linearLayout = findViewById(R.id.linearLayout);

        // สร้าง ImageView แบบไดนามิกสำหรับแต่ละกาแฟ
        for (final coffee coffee : coffees) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(coffee.getImageResId());

            // กำหนด LayoutParams สำหรับ ImageView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dpToPx(200) // ความสูงเป็น dp
            );
            params.setMargins(0, 0, 0, dpToPx(16)); // มาร์จิ้นล่าง
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // ตั้งค่า OnClickListener สำหรับ ImageView
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCoffeeDetails(coffee);
                }
            });

            // เพิ่ม ImageView ลงใน LinearLayout
            linearLayout.addView(imageView);
        }
    }

    // เมธอดเพื่อแปลง dp เป็น px
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    // เมธอดแสดงรายละเอียดกาแฟใน Dialog และแก้ไขราคา
    private void showCoffeeDetails(final coffee coffee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_coffee_details, null);

        // เชื่อมต่อกับวิวใน Dialog
        ImageView dialogImageView = dialogView.findViewById(R.id.dialogImageView);
        TextView dialogName = dialogView.findViewById(R.id.dialogName);
        final EditText dialogPrice = dialogView.findViewById(R.id.dialogPrice);  // ใช้ EditText

        // ตั้งค่าข้อมูลกาแฟ
        dialogImageView.setImageResource(coffee.getImageResId());
        dialogName.setText(coffee.getName());
        dialogPrice.setText(coffee.getPrice());

        // สร้างและแสดง Dialog
        builder.setView(dialogView)
                .setPositiveButton("บันทึก", (dialog, which) -> {
                    // อัปเดตราคากาแฟตามค่าที่ผู้ใช้ป้อน
                    String newPrice = dialogPrice.getText().toString();
                    coffee.setPrice(newPrice);  // ตั้งราคากาแฟใหม่
                })
                .setNegativeButton("ยกเลิก", null)
                .show();
    }
}
