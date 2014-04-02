package jcanlas.projects.myfirstapp;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Diner extends MainPage {

	private String name;
	private List<Double> itemCosts = new ArrayList<Double>();
	private List<String> itemNames = new ArrayList<String>();
	private double total = 0;
	private double tax = 0;
	private double tip = 0;
	private double subtotal = 0;
	private double grandtotal = 0;
	
	public Diner(){
		initialize();
	}
	
	public void initialize(){
		setName("Guest");
	}
	
	// get and set for NAME
	public void setName(String nameIn){
		name = nameIn;
	}
	
	public String getName(){
		return name;
	}
	
	// get and set for ITEM NAMES
	public String getItemName(int itemNameIndex){
		return itemNames.get(itemNameIndex);
	}
	public Double getItemCost(int itemCostIndex){
		return itemCosts.get(itemCostIndex);
	}
	
	// ADD an item to arrays
	public void addItem(String itemName, String itemCost){
		itemNames.add(itemName);
		itemCosts.add(Double.parseDouble(itemCost));
	}
	
	// REMOVE an item from arrays
	public void removeItem(String name, double cost){
		for (int i = 0; i < itemCosts.size(); i++){
			if (itemCosts.get(i)==cost && itemNames.get(i)==name){
				itemCosts.remove(i);
				itemNames.remove(i);
				break;
			}
		}
	}
	
	public void clearList(){
		itemCosts.clear();
		itemNames.clear();
	}
	
	public void setTotal(double a){
		total = a;
	}
	
	// calculate cost subtotal
	public double calculateSubtotal(){
		setTotal(0);
		for (int i = 0; i < itemCosts.size(); i++){
			total += itemCosts.get(i);
		}
		total = total * 100;
		total = Math.round(total);
		total = total / 100;
		return total;
	}
	
	// calculate cost tax
	public double calculateTax(double tax){
		setTotal(0);
		for (int i = 0; i < itemCosts.size(); i++){
			total += itemCosts.get(i);
		}
		total = total * (tax/100);
		total = total * 100;
		total = Math.round(total);
		total = total / 100;
		return total;
	}

	// calculate cost tax
	public double calculateTotal(double tax){
		setTotal(0);
		for (int i = 0; i < itemCosts.size(); i++){
			total += itemCosts.get(i);
		}
		total = total + (total * (tax/100));
		total = total * 100;
		total = Math.round(total);
		total = total / 100;
		return total;
	}
	
	// calculate cost tip
	public double calculateTip(double tip){
		setTotal(0);
		for (int i = 0; i < itemCosts.size(); i++){
			total += itemCosts.get(i);
		}
		total = total * (tip/100);
		total = total * 100;
		total = Math.round(total);
		total = total / 100;
		return total;
	}
	
	// CALCULATE cost total
	public double calculateGrandTotal(double tax, double tip){
		setTotal(0);
		double a = 0;
		for (int i = 0; i < itemCosts.size(); i++){
			total += itemCosts.get(i);
		}
		a = total + (total * (tax/100));
		a = a + (total*(tip/100));
		a = a * 100;
		a = Math.round(a);
		a = a / 100;
		return a;
	}
	
}
