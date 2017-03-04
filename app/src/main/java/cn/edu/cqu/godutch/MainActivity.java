package cn.edu.cqu.godutch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //每个人需要付的钱数
    String amount;
    //总金额
    EditText totalAmount;
    //人数
    EditText persNbr;
    //显示器
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置小数位控制
        totalAmount = (EditText) findViewById(R.id.etAmount);
        totalAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            //两位小数
            public void afterTextChanged(Editable s) {
                String temp=s.toString();
                int posDot=temp.indexOf(".");
                if(temp.length()-posDot-1>2)
                {
                    s.delete(posDot+3,posDot+4);
                }

            }
        });
        persNbr = (EditText) findViewById(R.id.etNbr);
        display = (TextView) findViewById(R.id.tvAmountPerPerson);
    }

    //对按钮添加事件
    public void btnCompute(View v) {
        Double persons = Double.parseDouble(persNbr.getText().toString());
        if((totalAmount.length()<=0) || (persons==0)){
            Toast.makeText(getBaseContext(),"The Data can't be valid and Number of person can't be zero",Toast.LENGTH_SHORT).show();
        }
        else{
            Double total = Double.parseDouble(totalAmount.getText().toString());
            Double share = total / persons;
            share = Double.valueOf(Math.round(share * 100));
            share = share / 100;
            display.setText(String.valueOf(share));
        }
    }
}
