package com.honglu.quickcall.common.api.util;

import javax.swing.plaf.synth.SynthSpinnerUI;

/** 
 * JAVA 返回随机数，并根据概率、比率 
 * 
 * 
 */  
public class MathRandom {
public  static double rate;//中奖概率
 
 /**
  * 一个奖项
  * @param math 中奖概率
  * @return
  */
 public static boolean smashingEggs(double math) {
	 boolean flag=false;
	  rate=math;
	  double randomNumber;  
	  randomNumber = Math.random();
	 
	  if(randomNumber >= 0 && randomNumber <= rate) {
		 flag=true;
	  }
	//  System.out.println(randomNumber+"----"+flag);
	  return flag;
 }
 
 /**
  * 心动女神抽奖
  * @param math
  * @return
  */
 public static int lotteryDraw() {
	 int flag=0;
	 double rate1=0.01;//10分钟
	 double rate2=0.09;//5分钟
	 double rate3=0.1;//3分钟
	 double rate4=0.5;//1分钟
	  double randomNumber;  
	  randomNumber = Math.random();
	  System.out.println(randomNumber);
	 
	  if(randomNumber >= 0 && randomNumber <= rate1) {
		 flag=1;
	  }
	  if(randomNumber > rate1 && randomNumber <= rate2) {
		  flag=2;
	  }
	  if(randomNumber > rate2 && randomNumber <= rate3) {
		  flag=3;
	  }
	  if(randomNumber > rate3 && randomNumber <= rate4) {
		  flag=4;
	  }
	//  System.out.println(randomNumber+"----"+flag);
	  System.out.println(flag);
	  return flag;
 }
 /**
  * 除夕活动抽奖
  * @return
  */
 public static int hogmanay() {
	 int flag=0;
	 double rate1=0.01;//1000金豆
	 double rate2=0.11;//100金豆
	 double rate3=0.2;//200金豆
	 double rate4=0.5;//800金豆
	 double rate5=1;//500金豆
	 double randomNumber;  
	  randomNumber = Math.random();
	  System.out.println(randomNumber);
	 if(randomNumber >= 0 && randomNumber <= rate1) {
		 flag=1;
	  }
	  if(randomNumber > rate1 && randomNumber <= rate2) {
		  flag=2;
	  }
	  if(randomNumber > rate2 && randomNumber <= rate3) {
		  flag=3;
	  }
	  if(randomNumber > rate3 && randomNumber <= rate4) {
		  flag=4;
	  }
	  if(randomNumber > rate4 && randomNumber <= rate5) {
		  flag=5;
	  }
	 System.out.println(flag);
	 return flag;
 }
 
 
 /** 
  * 测试主程序 
     * @param agrs 
     */  
 @SuppressWarnings("static-access")
public static void main(String[] agrs)  
 {  
	 hogmanay();
 } 
}
