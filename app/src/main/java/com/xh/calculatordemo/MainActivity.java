package com.xh.calculatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    Button btn_0;//数字0按钮
    Button btn_1;//数字1按钮
    Button btn_2;//数字2按钮
    Button btn_3;//数字3按钮
    Button btn_4;//数字4按钮
    Button btn_5;//数字5按钮
    Button  btn_6;//数字6按钮
    Button btn_7;//数字7按钮
    Button btn_8;//数字8按钮
    Button btn_9;//数字9按钮

    Button btn_clear;//清零按钮
    Button btn_del;//删除按钮

    Button btn_minus;//-
    Button btn_plus;//+
    Button btn_multiply;//×
    Button btn_divide;//÷

    Button btn_point;//.
    Button btn_equal;//=

    EditText et_showview;//显示输入内容的显示屏
    Boolean clear_flag=true;//清除标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);//控制xml为content_main

        btn_0=(Button)findViewById(R.id.btn_0);
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        btn_4=(Button)findViewById(R.id.btn_4);
        btn_5=(Button)findViewById(R.id.btn_5);
        btn_6=(Button)findViewById(R.id.btn_6);
        btn_7=(Button)findViewById(R.id.btn_7);
        btn_8=(Button)findViewById(R.id.btn_8);
        btn_9=(Button)findViewById(R.id.btn_9);

        btn_clear= (Button) findViewById(R.id.btn_clear);
        btn_del= (Button) findViewById(R.id.btn_del);

        btn_plus= (Button) findViewById(R.id.btn_plus);
        btn_minus= (Button) findViewById(R.id.btn_minus);
        btn_multiply= (Button) findViewById(R.id.btn_multiply);
        btn_divide= (Button) findViewById(R.id.btn_divide);

        btn_point=(Button) findViewById(R.id.btn_point);
        btn_equal=(Button)findViewById(R.id.btn_equal);
        //以上是实例化按钮

        et_showview=(EditText)findViewById(R.id.et_showview);
        //实例化输入框

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        //设置按钮的点击事件
    }

    @Override
    public void onClick(View v) {
        String str=et_showview.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag=false;
                    str="";//计算下一个的时候，应该将原来的设置为空
                    et_showview.setText("");
                }
                et_showview.setText(str+((Button)v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    et_showview.setText("");
                }
                et_showview.setText(str + " " + ((Button) v).getText() + " ");//将点击的运算符添加到输入框前后有“ ”用于区别
                break;
            case R.id.btn_equal:
                getResult();
                break;
            case R.id.btn_del:
                if(clear_flag){
                    clear_flag=false;
                    str="";//计算下一个的时候，应该将原来的设置为空
                    et_showview.setText("");
                }
                else if(str!=null&& !str.equals(""))
                {
                    et_showview.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_clear:
                clear_flag=false;
                str="";//计算下一个的时候，应该将原来的设置为空
                et_showview.setText("");
                break;
        }
    }
    /**
     * 四则运算求计算结果
     */
    private void getResult() {
        String exp = et_showview.getText().toString();
        if(clear_flag){
            clear_flag=false;
            return;
        }
        if (exp == null || exp.equals("")) {
            return;
        }
        //没有输入运算符（运算符前后都手动加入了空格）
        if (!exp.contains(" ")) {//如果不包含空格（运算符前面有空格），直接返回（比如点了数字，没有运算符）
            return;
        }
        clear_flag = true;
        double result = 0;
        String s1 = exp.substring(0, exp.indexOf(" "));//运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//运算符
        String s2 = exp.substring(exp.indexOf(" ") + 3);//运算符后面的字符串
        //s1、s2非空
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("－")) {
                result = d1 - d2;
            } else if (op.equals("×")) {
                result = d1 * d2;
            } else if (op.equals("÷")) {
                if (d2 == 0) {
                    Toast.makeText(MainActivity.this, "除数不能为0！！！", Toast.LENGTH_LONG).show();
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            }
                if (!s1.contains(".") && !s2.contains(".")&&!op.equals("÷")) {
                    int r = (int)result;
                    et_showview.setText(r + "");
                } else {
                    et_showview.setText(result + "");
                }
        }else if(!s1.equals("") && s2.equals("")){
            Toast.makeText(MainActivity.this, "不具备运算",Toast.LENGTH_LONG).show();
            et_showview.setText(exp);
        }
     //s1为空，s2非空
        else if(s1.equals("") && !s2.equals("")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = 0 + d2;
            } else if (op.equals("－")) {
                result = 0 - d2;
            } else if (op.equals("×")) {
                result = 0 ;
            } else if (op.equals("÷")) {
                result = 0;
            }
            if (!s1.contains(".") && !s2.contains(".")&&!op.equals("÷")) {
                int r = (int)result;
                et_showview.setText(r + "");
            } else {
                et_showview.setText(result + "");
            }
        }
        //s1、s2都是空
        else{
            et_showview.setText("");
        }
    }

}
