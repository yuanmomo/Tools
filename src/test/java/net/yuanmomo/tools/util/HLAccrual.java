package net.yuanmomo.tools.util;

import java.text.DecimalFormat;

public class HLAccrual {
	private static double amount = 10000;
	private static int months = 12;
	private static int yearRate = 12;
	private static DecimalFormat df=new   java.text.DecimalFormat("#.##");  
	
	public static void main(String[] args) {
		double monthRate = yearRate/12.0/100.0;
		for(int i = 1;i <= months ;i ++){
			double accrual = amount * monthRate * 
						(Math.pow(1 + monthRate , months) - Math.pow(1 + monthRate , i-1))
						/ (double)(Math.pow(1 + monthRate , months)-1);
			
			// subject.amount * monthRate * (Math.Pow(1 + monthRate,subject.Term) - Math.Pow(1 + monthRate, subject.Sequence - 1)) / (Math.Pow(1 + monthRate, subject.Term) - 1)
			System.out.println("第"+i+"期还款利息:" + df.format(accrual));
		}
	}
}
