package threads;

import java.util.Scanner;

class Seats{
	int availseat=10;		// initially assign num of seats in bus to be 10 === available seats 10
	public int getseats() {
		
		return availseat;		// fetching available seats
	}
	public void booked(int reqseat)
	{	
		availseat=availseat-reqseat;	//setting available seats when a booking complete
	
	}
}


class Bus implements Runnable{
	int reqseat;	//number of seats for booking
	int n;			//switch variable
	Scanner sc=new Scanner(System.in);//scanner object
	String[] passenger=new String[100];	//string array to store passenger name
	Seats s=new Seats();//Seats class object to access its property 
	public void run() {					//run method logic of program
		do {
		System.out.println("press 1 for booking 2 for exiting.........");//menu for booking
		n=sc.nextInt();
		switch(n)
		{
		case 1:													//to book seats
			System.out.println("enter the req seats");
			int reqseat=sc.nextInt();
			if(s.getseats()>=reqseat)							//checking for seats availability
			 {
				
				booking(s.availseat,reqseat);				//calling synchronized booking function
			}
		else
			System.out.println("no sufficent seats...num of availiable seats are :"+s.availseat);
		break;
		default:System.out.println("existing");		//existing in the menu
		}
		}while(n==1);
		
		
	}
	private synchronized void booking(int availseat,int reqseat) //Synchronized method  to overcome race condition
	{	if(availseat>=reqseat)									//checking available seats
	{	
		System.out.println("enter the passengers names");		//reading passenger details anme
		for(int j=0;j<=reqseat;j++)
		{
			passenger[j]=sc.nextLine();
		}
		System.out.println(Thread.currentThread().getName()+" start to try to book seats :"+reqseat);
		try {
			Thread.sleep(10);									//sleep method
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" sucussfully book the seats");
		s.booked(reqseat);				//setting available seats
		System.out.println("seats booked passengers names are ");
		for(int j=0;j<=reqseat;j++)
		{
			System.out.println(passenger[j]);	//displaying passengers names
		}
	}
	else
		System.out.println("no sufficent seats...num of availiable seats are :"+availseat);
	}	
	
	public static void main(String args[])
	{	
		Bus obj=new Bus();
		Thread t1=new Thread(obj);	//creating threads
		t1.setName("user1");
		t1.start(); //calling run method 
		
		
	}
}


