import java.util.Scanner;
import java.util.Calendar; //Import date and time
import java.util.GregorianCalendar;  //Import date and time
import java.text.SimpleDateFormat;  //Import date and time
import java.util.Date;  //Import date and time

class Stocks{
	private String p_name, brand, c_name; 
	private int stock_quantity;
	private double price;
	
	public Stocks(){
		
	}
	
	public Stocks(String p_name, String brand, int stock_quantity, double price){
		this.p_name=p_name;
		this.brand=brand;
		this.stock_quantity=stock_quantity;
		this.price=price;
	}
	
	//getters 
	public String getProduct(){
		return p_name;
	}  
	public String getBrand(){
		return brand;
	}
	public String getCompany(){
		return c_name;
	}
	public double getPrice(){
		return price;
	}
	public int getStock_Quantity(){
		return stock_quantity;
	}
	
	//setters
	public void setProduct(String p_name){
		this.p_name=p_name;
	}
	public void setBrand(String brand){
		this.brand=brand;
	}
	public void setCompany(String c_name){
		this.c_name=c_name;
	}
	public void setStock_Quantity(int stock_quantity){
		this.stock_quantity=stock_quantity;
	}
	public void setPrice(double price){
		this.price=price;
	}
	
	public Stocks(String p_name, int stock_quantity, double price){
		setProduct(p_name);
		setStock_Quantity(stock_quantity);
		setPrice(price);
	}
}
class StockDetails extends Stocks{
	Scanner sc=new Scanner(System.in);
	int products;
	String C_p_name;
	int C_stock_quantity;
	double C_price;
	int cus_Product;
	int cus_quantity;
	double final_price=0.0, p_price=0.0;
	char choice, customer;
	
	StockDetails(){}
	
	StockDetails(String C_p_name,int C_stock_quantity, double C_price){  //child variables
		super(C_p_name,C_stock_quantity,C_price);
	}
	StockDetails(int cus_Product,int cus_quantity, double p_price){
		this.cus_Product=cus_Product;
		this.cus_quantity=cus_quantity;
		this.p_price=p_price;
	}
	
	public void Stock_Input(){
		System.out.print("\nEnter your company name: ");
		String c_name=sc.nextLine();
		setCompany(c_name);
		System.out.print("\n");
		
		System.out.println("-----------  Welcome "+getCompany()+"  -----------");
		System.out.println("\n<==Create Your Own Price List Now==>");
		
		System.out.print("How many products do you have? ");
		products=sc.nextInt();
		System.out.print("-----------------------------------------------");
		System.out.print("\n");
		
		StockDetails St_detail[]=new StockDetails[products];
		
		for(int i=0; i<St_detail.length; i++) {
			System.out.print("Enter the name of product "+(i+1)+" : ");
			this.C_p_name=sc.next();
			System.out.print("Enter the quantity of product "+(i+1)+" : ");
			this.C_stock_quantity=sc.nextInt();
			System.out.print("Enter the price of product "+(i+1)+" : ");
			this.C_price=sc.nextDouble();
			System.out.print("-----------------------------------------------");
			System.out.print("\n\n");
			St_detail[i] = new StockDetails(C_p_name,C_stock_quantity,C_price); //StockDetails array 
		}
		
		for(int z=0; z>=0; z++){
			System.out.println("Welcome to the "+getCompany()+" shopping centre");
			GregorianCalendar time = new GregorianCalendar();
			int hour = time.get(Calendar.HOUR_OF_DAY);
			int min = time.get(Calendar.MINUTE);
			int day = time.get(Calendar.DAY_OF_MONTH);
			int month = time.get(Calendar.MONTH) + 1;
			int year = time.get(Calendar.YEAR);
		  
			Date dt = new Date();
			SimpleDateFormat dateFormat;
			dateFormat = new SimpleDateFormat("hh:mm:ss a");
			
			if (hour < 12)
				System.out.println("------------------------------Good Morning!-----------------------------");
			else if (hour < 17 && !(hour == 12))
				System.out.println("------------------------------Good Afternoon!---------------------------");
			else if (hour == 12)
				System.out.println("------------------------------Good Noon!--------------------------------");
			else
				System.out.println("------------------------------Good Evening!-----------------------------");
			do{
				do{
					System.out.println("------------------------------------------------------------------------");
					System.out.println("                    Please choose your products here                    ");
					System.out.println("------------------------------------------------------------------------");
					System.out.println("Product \t\t Stock_Quantity \t\t Unit Price(Rs.) ");
					
					for(int i=0;i<products;i++){
						System.out.println((i+1)+"-"+St_detail[i].getProduct()+" \t\t\t "+St_detail[i].getStock_Quantity()+" \t\t\t\t "+St_detail[i].getPrice()+" ");
					}
					
					System.out.print("\nEnter the product id that you need to buy: ");
					cus_Product=sc.nextInt();
					if(cus_Product<=products){
						System.out.print("How many "+St_detail[cus_Product-1].getProduct()+" do you want to buy: ");
						cus_quantity=sc.nextInt();
						if(cus_quantity<=St_detail[cus_Product-1].getStock_Quantity()){
							p_price=(St_detail[cus_Product-1].getPrice()*cus_quantity);
							System.out.println("Total Price of "+St_detail[cus_Product-1].getProduct()+": Rs."+p_price);
						}
						else if(cus_quantity>=St_detail[cus_Product-1].getStock_Quantity()){
							System.out.println("There is only "+St_detail[cus_Product-1].getStock_Quantity()+" stock available in this product");
						}
						else if(St_detail[cus_Product-1].getStock_Quantity()<=0){
							St_detail[cus_Product-1].setStock_Quantity(0);
							System.out.println("Sorry! Out of Stock..Available stock is: 0");
							p_price=0.0;
						}
						else if(St_detail[cus_Product-1].getStock_Quantity()==1){
							System.out.println("There is only 1 stock available in this product");
						}
					}
					if(cus_Product>products){
						System.out.println("Something went wrong.\nPlease choose products from above list");
					}
					
					if(St_detail[cus_Product-1].getStock_Quantity()>=1){
						int stockQuantity = St_detail[cus_Product-1].getStock_Quantity();
						stockQuantity = stockQuantity - cus_quantity;
						St_detail[cus_Product-1].setStock_Quantity(stockQuantity);
					}else{
						St_detail[cus_Product-1].setStock_Quantity(0);
					}
					final_price+=p_price;
					System.out.print("\nDo you want to buy something again(Y/N): ");
					choice=sc.next().charAt(0);
				}while(choice=='Y'|| choice=='y');
				System.out.println("|----------------------------------------------------|");
				System.out.println("|---------------------BILL---------------------------|");
				System.out.println("|----------------------------------------------------|");
				System.out.println("|Total Bill Amount: Rs."+final_price+"                          |");
				System.out.println("|Purchase date: " + month + "/" + day + "/"+ year+"                            |");
				System.out.println("|Purchase Time: "+dateFormat.format(dt)+"                          |");
				System.out.println("|Thankyou For shopping..Have a nice day              |");
				System.out.println("|----------------------------------------------------|");
				final_price=0.0;
				p_price=0.0;
			System.out.print("\nDo you want to continue (Y/N) : ");
			customer=sc.next().charAt(0);
			if(customer=='Y'|| customer=='y'){
				System.out.println("Next customer please!");
			}
			}while(customer=='Y'|| customer=='y');
			System.out.println("System Terminated || Come Again!");
			break;
		}
	}			
}
class BillingApp{
	public static void main(String args[]){ 
		StockDetails obj=new StockDetails();
		obj.Stock_Input();
	}
}