package com.example.app_hh;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends Activity implements OnClickListener {
		
	TextView nameView;
	TextView dateView;
	TextView sexView;
	TextView postView;
	TextView salaryView;
	TextView phoneView;
	TextView emailView;
	EditText editMessage;
	Button btnMain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);

		nameView = (TextView) findViewById(R.id.textView1);
		nameView.setText(MainActivity.getInputName().getText().toString());
		
		dateView = (TextView) findViewById(R.id.textView4);
		String text = "Дата рождения ";
		text += MainActivity.getInputDate().getDayOfMonth();
		text += ".";
		text += MainActivity.getInputDate().getMonth();
		text += ".";
		text += MainActivity.getInputDate().getYear();
		dateView.setText(text);
		
		sexView = (TextView) findViewById(R.id.textView5);
		text = "Пол: ";
		text += MainActivity.getSpinner().getSelectedItem().toString();				
		sexView.setText(text);
		
		postView = (TextView) findViewById(R.id.textView7);
		postView.setText(MainActivity.getInputPost().getText().toString());
		
		salaryView = (TextView) findViewById(R.id.textView8);
		salaryView.setText(MainActivity.getInputSalary().getText().toString());
		
		phoneView = (TextView) findViewById(R.id.textView10);
		phoneView.setText(MainActivity.getInputPhone().getText().toString());
		//phoneView.setMovementMethod(LinkMovementMethod.getInstance());
		
		/*emailView = (TextView) findViewById(R.id.textView11);
		emailView.setText(MainActivity.getInputEmail().getText().toString());
		text = "<a href=\"mailto:";
		text += MainActivity.getInputEmail().getText().toString();
		text += "\">Send Feedback</a>";
		emailView.setText(Html.fromHtml(text));
		emailView.setMovementMethod(LinkMovementMethod.getInstance());*/
		
		emailView = (TextView) findViewById(R.id.textView11);
		emailView.setText(MainActivity.getInputEmail().getText().toString());
		
		editMessage = (EditText) findViewById(R.id.editMessage);
		
		btnMain = (Button) findViewById(R.id.button1);
		btnMain.setOnClickListener(this);
		
	}
	

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
			case R.id.textView10:
				String text = "tel:";
				text += MainActivity.getInputPhone().getText().toString();
				intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(text));
				startActivity(intent);
				break;
			case R.id.textView11:
				String text1 = "mailto:";
				text1 += MainActivity.getInputEmail().getText().toString();
				intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(text1));
				startActivity(intent);
				break;
			case R.id.button1:
				MainActivity.getResponse(editMessage.getText().toString());
				onBackPressed();
				break;
			default:
				break;
		}
		
	}
	

}
