package com.example.app_hh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnActTwo;
	private String[] data = {"мужской", "женский"};
	final int EmployerResponse  = 1;
	private static String textDialog = "";
	
	private static EditText inputName;
	private static DatePicker inputDate;
	private static Spinner spinner;
	private static EditText inputPost;
	private static EditText inputSalary;
	private static EditText inputPhone;
	private static EditText inputEmail;
	
	
	public static EditText getInputName() {
		return inputName;
	}
	
	public static DatePicker getInputDate() {
		return inputDate;
	}
	
	public static Spinner getSpinner() {
		return spinner;
	}
	
	public static EditText getInputPost() {
		return inputPost;
	}

	public static EditText getInputSalary() {
		return inputSalary;
	}

	public static EditText getInputPhone() {
		return inputPhone;
	}

	public static EditText getInputEmail() {
		return inputEmail;
	}


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btnActTwo = (Button) findViewById(R.id.btnActTwo);
		btnActTwo.setOnClickListener(this);
		
		inputName = (EditText) findViewById(R.id.editText1);
		inputPost = (EditText) findViewById(R.id.editText2);
		inputSalary = (EditText) findViewById(R.id.editText3);
		inputPhone = (EditText) findViewById(R.id.editText4);
		inputEmail = (EditText) findViewById(R.id.editText5);
		
		inputDate = (DatePicker) findViewById(R.id.datePicker1);
		
		// адаптер
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		// выделяем элемент
		spinner.setSelection(0);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnActTwo:
				if (checkInputInfo()) {
					Intent intent = new Intent(this, ActivityTwo.class);
					startActivity(intent);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInputInfo() {
		//check editText1-nameField
		String nameTempl = "\\S+\\s+\\S+\\s+\\S+\\s*";
		if (!isMatches(inputName.getText().toString(), nameTempl)) {
			inputName.requestFocus();
			Toast.makeText(getBaseContext(), "Некорректный ввод имени", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (inputPost.getText().toString().equals("")) {
			inputPost.requestFocus();
			Toast.makeText(getBaseContext(), "Введите должность", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (inputSalary.getText().toString().equals("")) {
			inputSalary.requestFocus();
			Toast.makeText(getBaseContext(), "Введете зарплату", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (inputPhone.getText().toString().equals("")) {
			inputPhone.requestFocus();
			Toast.makeText(getBaseContext(), "Введите телефон", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if (inputEmail.getText().toString().equals("")) {
			inputEmail.requestFocus();
			Toast.makeText(getBaseContext(), "Введите почтовый адрес", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return true;
	}
	
	
    //проверка на соответствие шаблону
	private static boolean isMatches(String text, String regexp) {
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(text);
		return m.matches();
	}
	
	protected Dialog onCreateDialog(int id) {
		if (id == EmployerResponse) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		// заголовок
		adb.setTitle(R.string.employer_response);
		// сообщение
		adb.setMessage(textDialog);
		// иконка
		adb.setIcon(android.R.drawable.ic_dialog_info);
		// создаем диалог
		return adb.create();
		}
		return super.onCreateDialog(id);
	}

	public static void getResponse (String text) {
		textDialog = text;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//Log.d(TAG, "MainActivity: onResume()");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (textDialog != "") {
			showDialog(EmployerResponse);
		}		
	}
	
}