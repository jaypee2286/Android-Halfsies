package jcanlas.projects.myfirstapp;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainPage extends Activity {

	private EditText textIn;
	private EditText itemName;
	private EditText itemCost;
	private EditText itemName_input;
	private EditText itemCost_input;
	private EditText tax;
	private EditText tip;
	private Button buttonAdd;
	private Button buttonCalculate;
	private Button buttonClear;
	private LinearLayout container;
	private int currentDiner = 0;
	private int currentItem = 0;
	public int counter = 0;
	private List<Integer> itemCosts = new ArrayList<Integer>();
	private int arraycounter = 0;
	private int arraycounter2 = 0;
	private double results;
	private double taxinput;
	private double tipinput;
	public DecimalFormat df = new DecimalFormat("0.00");
	public BigDecimal bd = new BigDecimal(0);

	// tests
	private String test1;
	private int test2 = 0;

	final Context context = this;
	private EditText result;
	ViewGroup contentArea;
	private int icon = android.R.drawable.ic_menu_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);

		tax = (EditText) findViewById(R.id.input_tax);
		tip = (EditText) findViewById(R.id.input_tip);
		tax.setFilters(new InputFilter[] {new DecimalFilter()});
		tip.setFilters(new InputFilter[] {new DecimalFilter()});

		buttonAdd = (Button) findViewById(R.id.diner_buttonAddItem);
		buttonCalculate = (Button) findViewById(R.id.diner_buttonCalculate);
		buttonClear = (Button) findViewById(R.id.diner_buttonClear);

		container = (LinearLayout) findViewById(R.id.container);

		
		// create a Diner object
		final Diner guest1 = new Diner();

		//
		// run when ADD button is pressed
		buttonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addItemPrompt(guest1);
			}
		});

		// when the CALCULATE button is pressed:
		buttonCalculate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculate(guest1);
			}
		});
		// when the CLEAR button is pressed:
		buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				container.removeAllViews();
				guest1.clearList();
			}
		});

	}

	//
	// "calculate(Diner)"
	// CALCULATE TOTAL AND DISPLAY IN MESSAGE BOX
	public void calculate(Diner guest1) {
		results = guest1.calculateSubtotal();
		taxinput = Double.parseDouble(tax.getText().toString());
		tipinput = Double.parseDouble(tip.getText().toString());
		LayoutInflater resultsPopup = LayoutInflater.from(context);
		View resultsView = resultsPopup.inflate(R.layout.display_results, null);
		AlertDialog.Builder resultsPopup_content = new AlertDialog.Builder(
				context);
		
		final TextView results_subtotal = (TextView) resultsView
				.findViewById(R.id.results_displaySubtotal);
		final TextView results_tax = (TextView) resultsView
				.findViewById(R.id.results_displayTax);
		final TextView results_total = (TextView) resultsView
				.findViewById(R.id.results_displayTotal);
		final TextView results_tip = (TextView) resultsView
				.findViewById(R.id.results_displayTip);
		final TextView results_grandtotal = (TextView) resultsView
				.findViewById(R.id.results_displayGrandTotal);

		// format results output
		results_subtotal.setText(df.format(results));
		results_total.setText(df.format((guest1.calculateTotal(taxinput))));
		results_tax.setText(df.format(guest1.calculateTax(taxinput)));
		results_tip.setText(df.format(guest1.calculateTax(tipinput)));
		results_grandtotal.setText(df.format(guest1.calculateGrandTotal(taxinput, tipinput)));
		
		// set promps.xml to builder
		resultsPopup_content.setView(resultsView);

		resultsPopup_content.setCancelable(false).setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		AlertDialog resultsPopup_show = resultsPopup_content.create();
		resultsPopup_show.show();
	}
	
	//
	// "addItemPrompt(Diner)"
	// this creates a popup that prompts user for item data
	public void addItemPrompt(final Diner guest1) {

		LayoutInflater popup = LayoutInflater.from(context);
		View promptsView = popup.inflate(R.layout.activity_add_items_prompt,
				null);

		AlertDialog.Builder popup_content = new AlertDialog.Builder(context);

		// set promps.xml to builder
		popup_content.setView(promptsView);

		final EditText itemName_input = (EditText) promptsView
				.findViewById(R.id.prompt_itemName);
		final EditText itemCost_input = (EditText) promptsView
				.findViewById(R.id.prompt_itemCost);
		itemCost_input.setFilters(new InputFilter[] {new DecimalFilter()});
		
		// set dialog message
		popup_content
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// addLine();

						LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
								.getSystemService(
										Context.LAYOUT_INFLATER_SERVICE);
						final View addView = layoutInflater.inflate(
								R.layout.add_items, null);
						final TextView addItems_itemName = (TextView) addView
								.findViewById(R.id.addItems_itemName);
						final TextView addItems_itemCost = (TextView) addView
								.findViewById(R.id.addItems_itemCost);
						addItems_itemName.setText(itemName_input.getText());
						addItems_itemCost.setText(itemCost_input.getText());
						Button buttonRemove = (Button) addView
								.findViewById(R.id.remove);
						guest1.addItem(addItems_itemName.getText().toString(),
								addItems_itemCost.getText().toString());
						buttonRemove.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								((LinearLayout) addView.getParent())
										.removeView(addView);
								guest1.removeItem(addItems_itemName.getText()
										.toString(), Double
										.parseDouble(addItems_itemCost
												.getText().toString()));
								// container.removeAllViews();
							}
						});
						container.addView(addView);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog popup_show = popup_content.create();
		popup_show.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

}
