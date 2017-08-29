package com.mr9.calculator;


import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.TextView;
import android.util.FloatMath;

public class MainActivity extends AppCompatActivity {
    private static Button dot, zero, equal, plus, minus, multiply, divide, power, root, op_brack, cl_brack, e, log, ln, fact, sin, cos, tan, erase, one, two, three, four, five, six, seven, eight, nine;
    private static String op1="", op2="",op;
    private static char end,operator;
    private static TextView output, string;
    private static int op_flag=0,equal_flag=0,dot_flag=0;

    private void sum() {
        float z=0;
        try {
            float x = Float.valueOf(op1), y;
            y = Float.valueOf(op2);
            z = x + y;
            output.setText(String.valueOf(z));
        }
        catch (NumberFormatException ex) {
        }
    }
    private void sub() {
        float z=0;
        try {if(op2=="") {
            output.setText(op1);
            return;
        }
        else {
            float x = Float.valueOf(op1), y;
            y = Float.valueOf(op2);
            z = x - y;
            output.setText(String.valueOf(z));
        }
        }
        catch (NumberFormatException ex) {
        }
    }
    private void mul() {
        float z=1;
        try {
            if(op2=="") {
                output.setText(op1);
                return;
            }
            else {
                float x = Float.valueOf(op1), y;
                y = Float.valueOf(op2);
                z = x * y;
                output.setText(String.valueOf(z));
            }
        }
        catch (NumberFormatException ex) {
        }
    }
    private void div() {
        float z=1;
        try {if(op2=="") {
            output.setText(op1);
            return;
        }
        else {
            float x = Float.valueOf(op1), y;
            y = Float.valueOf(op2);
            z = x / y;
            output.setText(String.valueOf(z));
        }
        }
        catch (NumberFormatException ex) {
        }
    }
    private void setPower() {
        double z=1;
        try {if(op2=="") {
            output.setText(op1);
            return;
        }
        else {
            double x = Float.valueOf(op1), y;
            y = Float.valueOf(op2);
            for(int i=0;i<Integer.valueOf(op2);i++)
                z=z*x;
            output.setText(String.valueOf(z));
        }
        }
        catch (NumberFormatException ex) {
        }
    }
    private void setfact() {
        float z=1;
        try {
            if(op2=="") {
                //  output.setText("hereWeGo");
                String f=factorial(Integer.valueOf(op1),300);
                output.setText(f);
                return;
            }
            else {
                float x = Float.valueOf(op1), y;
                y = Float.valueOf(op2);
                String f=factorial(Integer.valueOf(op1),300);
                z = Integer.valueOf(f) * y;
                output.setText(String.valueOf(z));
            }
        }
        catch (NumberFormatException ex) {
        }
    }
    private static String factorial(int n, int maxSize) {
        StringBuffer buff = new StringBuffer();
        try {
            int res[] = new int[maxSize];
            res[0] = 1; // Initialize result
            int res_size = 1;

            // Apply simple factorial formula n! = 1 * 2 * 3 * 4... * n
            for (int x = 2; x <= n; x++) {
                res_size = multiply(x, res, res_size);
            }
            for (int i = res_size - 1; i >= 0; i--) {
                buff.append(res[i]);
            }
        }
        catch (Exception e){};
        return buff.toString();
    }
    private static int multiply(int x, int res[], int res_size) {
        int carry = 0; // Initialize carry.
        try {
            // One by one multiply n with individual digits of res[].
            for (int i = 0; i < res_size; i++) {
                int prod = res[i] * x + carry;
                res[i] = prod % 10; // Store last digit of 'prod' in res[]
                carry = prod / 10;  // Put rest in carry
            }
            // Put carry in res and increase result size.
            while (carry != 0) {
                res[res_size] = carry % 10;
                carry = carry / 10;
                res_size++;
            }
        }
        catch (Exception e){};
        return res_size;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = (Button) findViewById(R.id.button_dot);
        zero = (Button) findViewById(R.id.button_zero);
        equal = (Button) findViewById(R.id.button_equal);
        plus = (Button) findViewById(R.id.button_plus);
        minus = (Button) findViewById(R.id.button_minus);
        multiply = (Button) findViewById(R.id.button_multiply);
        divide = (Button) findViewById(R.id.button_devide);
        power = (Button) findViewById(R.id.button_power);
        fact = (Button) findViewById(R.id.button_factorial);
        erase = (Button) findViewById(R.id.button_erase);
        one = (Button) findViewById(R.id.button_one);
        two = (Button) findViewById(R.id.button_two);
        three = (Button) findViewById(R.id.button_three);
        four = (Button) findViewById(R.id.button_four);
        five = (Button) findViewById(R.id.button_five);
        six = (Button) findViewById(R.id.button_six);
        seven = (Button) findViewById(R.id.button_seven);
        eight = (Button) findViewById(R.id.button_eight);
        nine = (Button) findViewById(R.id.button_nine);
        output = (TextView) findViewById(R.id.output);
        string = (TextView) findViewById(R.id.input_string);
        final TextWatcher text_watch = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(op2!="") {
                    switch (operator) {
                        case '+':
                            sum();
                            break;
                        case '-':
                            sub();
                            break;
                        case '*':
                            mul();
                            break;
                        case '/':
                            div();
                            break;
                        case '!':
                            setfact();
                            break;
                        case '^':
                            setPower();
                            break;
                        default:
                            break;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        string.addTextChangedListener(text_watch);
        plus.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (op1 == "") {
                        }
                        else {
                            if (op_flag > 0) {
                                try {
                                    op = string.getText().toString();
                                    end = op.charAt(op.length() - 1);
                                    if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^' || end == '!')
                                        string.setText(op.substring(0, op.length() - 1));
                                } catch (Exception e) {
                                }
                                op1 = output.getText().toString();
                                op2 = "";
                            }
                            equal_flag = 0;
                            dot_flag = 0;
                            op_flag = 1;
                            operator = '+';
                            string.append("+");
                        }
                    }
                }
        );
        minus.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (op1 == "") {
                        }
                        else {
                            if(op_flag>0) {
                                try {
                                    op = string.getText().toString();
                                    end = op.charAt(op.length() - 1);
                                    if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^'|| end == '!')
                                        string.setText(op.substring(0, op.length() - 1));
                                }
                                catch (Exception e){};
                                op1=output.getText().toString();
                                op2="";
                            }
                            equal_flag=0;
                            dot_flag=0;
                            op_flag=1;
                            operator='-';
                            string.append("-");
                        }
                    }
                }
        );
        multiply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (op1 == "") {
                        } else {
                            if (op_flag > 0) {
                                try {
                                    op = string.getText().toString();
                                    end = op.charAt(op.length() - 1);
                                    if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^' || end == '!')
                                        string.setText(op.substring(0, op.length() - 1));
                                } catch (Exception e) {
                                }
                                op1 = output.getText().toString();
                                op2 = "";
                            }
                            equal_flag = 0;
                            dot_flag = 0;
                            op_flag = 1;
                            operator = '*';
                            string.append("*");
                        }
                    }
                }
        );
        divide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (op1 == "") {
                        } else {
                            if (op_flag > 0) {
                                try {
                                    op = string.getText().toString();
                                    end = op.charAt(op.length() - 1);
                                    if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^' || end == '!')
                                        string.setText(op.substring(0, op.length() - 1));
                                } catch (Exception e) {
                                }
                                op1 = output.getText().toString();
                                op2 = "";
                            }
                            equal_flag = 0;
                            dot_flag = 0;
                            op_flag = 1;
                            operator = '/';
                            string.append("/");
                        }
                    }
                }
        );
        equal.setOnClickListener(

                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==0) {
                            String st=string.getText().toString();
                            if(st.contains(".")) {
                                dot_flag = 1;
                            }
                            else
                                dot_flag=0;
                            op = st;
                            end = op.charAt(op.length() - 1);
                            if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^'|| end == '!')
                                op_flag = 1;
                            else
                                op_flag = 0;
                            operator = 'q';
                            if(output.getText().toString()!="") {
                                string.setText(output.getText().toString());
                                op1 = output.getText().toString();
                                op2 = "";
                                output.setText("");
                                equal_flag = 1;
                            }
                        }
                    }
                }
        );
        dot.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(dot_flag==0) {
                            if (equal_flag == 1) {
                                if (operator == 'q') {
                                    string.setText("");
                                    op1 = "";
                                }
                                equal_flag = 0;
                            }
                            try {
                                op = string.getText().toString();
                                end = op.charAt(op.length() - 1);
                                if (end == '.')
                                    string.setText(op.substring(0, op.length() - 1));
                            } catch (Exception e) {
                            }
                            if (op_flag == 0) {
                                op1 = op1 + ".";
                                string.append(".");
                            } else {
                                op2 = op2 + ".";
                                string.append(".");
                            }
                            dot_flag=1;
                        }
                    }
                }
        );
        erase.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        equal_flag = 0;
                        op = string.getText().toString();
                        if (op_flag == 0) {
                            try {
                                op1 = op1.substring(0, op1.length() - 1);
                                output.setText(op1);
                                string.setText(op.substring(0, op.length() - 1));
                                if (op1.contains(".")) {
                                    dot_flag = 1;
                                } else
                                    dot_flag = 0;
                            }
                            catch (Exception e) {
                            }
                        } else {
                            try {
                                end = op.charAt(op.length() - 1);
                                if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^' || end == '!') {
                                    output.setText("");
                                    operator = 'q';
                                    op_flag = 0;
                                    string.setText(op.substring(0, op.length() - 1));
                                    String st = string.getText().toString();
                                    if (op1.contains(".")) {
                                        dot_flag = 1;
                                    } else
                                        dot_flag = 0;
                                    output.setText(op1);
                                }
                                else {
                                    op2 = op2.substring(0, op2.length() - 1);
                                    string.setText(op.substring(0, op.length() - 1));
                                    if (op2.contains(".")) {
                                        dot_flag = 1;
                                    } else
                                        dot_flag = 0;
                                    output.setText(op2);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
        );
        erase.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        equal_flag = 0;
                            try {
                                op1 = "";
                                op2 = "";
                                output.setText(op1);
                                string.setText("");
                                dot_flag = 0;
                                op_flag=0;
                                equal_flag=0;
                            }
                            catch (Exception e) {
                            }
                            return true;
                    }
                }
        );
        power.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(op_flag>0) {
                            try {
                                op = string.getText().toString();
                                end = op.charAt(op.length() - 1);
                                if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^'|| end == '!')
                                    string.setText(op.substring(0, op.length() - 1));
                            }
                            catch (Exception e){};
                            op1=output.getText().toString();
                            op2="";
                        }
                        equal_flag=0;
                        dot_flag=0;
                        op_flag=1;
                        operator='^';
                        string.append("^");
                    }
                }
        );
        fact.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(op_flag>0) {
                            try {
                                op = string.getText().toString();
                                end = op.charAt(op.length() - 1);
                                if (end == '+' || end == '-' || end == '*' || end == '/' || end == '^'|| end == '!')
                                    string.setText(op.substring(0, op.length() - 1));
                            }
                            catch (Exception e){};
                            op1=output.getText().toString();
                            op2="";
                        }
                        equal_flag=0;
                        dot_flag=0;
                        op_flag=1;
                        operator='!';
                        string.append("!");
                    }
                }
        );
        one.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                                op2 = "";
                            }
                            equal_flag = 0;
                        }
                        if(op_flag==0) {
                            op1=op1+"1";
                            string.append("1");
                        }
                        else{
                            op2=op2+"1";
                            string.append("1");
                        }
                    }
                }
        );
        two.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if(op_flag==0) {
                            op1=op1+"2";
                            string.append("2");
                        }
                        else{
                            op2=op2+"2";
                            string.append("2");
                        }
                    }
                }
        );
        three.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if(op_flag==0) {
                            op1=op1+"3";
                            string.append("3");
                        }
                        else{
                            op2=op2+"3";
                            string.append("3");
                        };
                    }
                }
        );
        four.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if(op_flag==0) {
                            op1=op1+"4";
                            string.append("4");
                        }
                        else{
                            op2=op2+"4";
                            string.append("4");
                        }
                    }
                }
        );
        five.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if (op_flag == 0) {
                            op1 = op1 + "5";
                            string.append("5");
                        } else {
                            op2 = op2 + "5";
                            string.append("5");
                        }
                    }
                }
        );
        six.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (equal_flag == 1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if (op_flag == 0) {
                            op1 = op1 + "6";
                            string.append("6");
                        } else {
                            op2 = op2 + "6";
                            string.append("6");
                        }
                    }
                }
        );
        seven.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if (op_flag == 0) {
                            op1 = op1 + "7";
                            string.append("7");
                        } else {
                            op2 = op2 + "7";
                            string.append("7");
                        }
                    }
                }
        );
        eight.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if(op_flag==0) {
                            op1=op1+"8";
                            string.append("8");
                        }
                        else{
                            op2=op2+"8";
                            string.append("8");
                        }
                    }
                }
        );
        nine.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(equal_flag==1) {
                            if(operator=='q'){
                                string.setText("");
                                op1="";
                            }
                            else
                                string.setText(op1);
                            equal_flag=0;
                        }
                        if(op_flag==0) {
                            op1=op1+"9";
                            string.append("9");
                        }
                        else{
                            op2=op2+"9";
                            string.append("9");
                        }
                    }

                }
        );
        zero.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        if(equal_flag==1) {
                            if (operator == 'q') {
                                string.setText("");
                                op1 = "";
                            }
                            equal_flag = 0;
                        }
                        if (op_flag == 0) {
                            op1 = op1 + "0";
                            string.append("0");
                        } else {
                            op2 = op2 + "0";
                            string.append("0");
                        }
                    }
                }
        );
    }
}

