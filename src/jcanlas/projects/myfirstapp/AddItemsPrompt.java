package jcanlas.projects.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddItemsPrompt extends MainPage {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_items_prompt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_items_prompt, menu);
		return true;
	}

}
