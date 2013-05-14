package com.example.exjobb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lstView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		DBAdapter db = new DBAdapter(this);
		try {
			String destPath = "/data/data/" + getPackageName() + "/databases";
			File f = new File(destPath);
			if (!f.exists()) {
				Toast.makeText(getBaseContext(), "File doesn't exist in DBAdapter!", Toast.LENGTH_LONG).show();
				f.mkdirs();
				f.createNewFile();
				CopyDB(getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath + "/MyDB2"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		db.open();
		Toast.makeText(getBaseContext(), "DBOpen!", Toast.LENGTH_LONG).show();
		/**Cursor c = db.getDrugss();
		if (c.moveToFirst()){
			do {
				DisplayDrug(c);
			} while (c.moveToNext());
		}**/
		db.close();
		
		Choice choices[] = new Choice[] {
				new Choice(R.drawable.tablett_ikon, "Hitta l�kemedel"), 
				new Choice(R.drawable.recept_ikon, "Mina recept"), 
				new Choice(R.drawable.apotek_ikon, "Hitta apotek")
				};
		ChoiceArrayAdapter adapter = new ChoiceArrayAdapter(this, R.layout.lstview_item_row, choices);
		
		lstView = (ListView) findViewById(R.id.lstView);
		View header = (View) getLayoutInflater().inflate(R.layout.lstview_header_row, null);
		View footer = (View) getLayoutInflater().inflate(R.layout.lstview_footer_row, null);
		
		lstView.addHeaderView(header);
		//lstView.addFooterView(footer);
		lstView.setAdapter(adapter);
		
		lstView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				Toast.makeText(getBaseContext(), "Has pos " + pos, Toast.LENGTH_LONG).show();
				
				switch(pos) {
				case 1: startActivity(new Intent(MainActivity.this, DrugsActivity.class));
						finish();
						break;
				case 2: startActivity(new Intent(MainActivity.this, LoginActivity.class));
						finish();
						break;
				case 3: break;
				}
			}
		});
	}
	
	public void CopyDB(InputStream inputStream, FileOutputStream outputStream) throws IOException {
		Toast.makeText(getBaseContext(), "File copied in DBAdapter!", Toast.LENGTH_LONG).show();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	/*public void onListItemClick (ListView parent, View v, int pos, long id) {
		Choice item = (Choice) getListAdapter().getItem(pos);
		Toast.makeText(this, "You've selected " + item.title, Toast.LENGTH_SHORT).show();
	}*/
	
	

}
