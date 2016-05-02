package jp.co.nirvana0rigin.caraddition;

import java.util.Random;

class GenerateQA{

	String q = null;
	static int target=0;
	static int q1=0;
	static int q2=0;
	static int q0=0;
	static int a1=0;
	static int a2=0;
	static String[] lr= new String[2];
	Random ran = new Random();
	String add = " + ";
	String equ = " = ";
	String min = " - ";
	String x = " ● ";

	GenerateQA(int target){
		this.target = target;
	}

	void generateNextQA(){
		switch(target){

			case R.id.start_add:
                q1 = ran.nextInt(10);
        		q2 = ran.nextInt(10);
        		q0 = ran.nextInt(10);
        		a1 = q1 + q2;
        		a2 = q1 + q0;
        		if(a1 == a2) {
            		a2 += 1;
        		}
                break;

            case R.id.start_add2:
            	q1 = ran.nextInt(10);
        		a1 = ran.nextInt(10);
        		a2 = ran.nextInt(10);
        		q2 = q1 + a1;
        		q0 = q1 + a2;
        		if(a1 == a2) {
            		a2 += 1;
        		}
                break;

            case R.id.start_min:
            	q1 = ran.nextInt(10)+1;
        		q2 = ran.nextInt(10);
        		if(q2>q1){
        			q2 = q1-1;
        		}
        		q0 = ran.nextInt(10);
        		if(q0>q1){
        			q0 = q1-1;
        		}
        		a1 = q1 - q2;
        		a2 = q1 - q0;
        		if(a1 == a2) {
            		a2 += 1;
        		}
                break;

            case R.id.start_min2:
				q1 = ran.nextInt(10)+1;
        		a1 = ran.nextInt(10);
        		if(a1>q1){
        			a1 = q1-1;
        		}
        		a2 = ran.nextInt(10);
        		if(a2>q1){
        			a2 = q1-1;
        		}
        		q2 = q1 - a1;
        		q0 = q1 - a2;
        		if(a1 == a2) {
            		a2 += 1;
        		}
                break;
		}
	}

	String getQ(){
		switch(target){

			case R.id.start_add:
                q =  String.valueOf(q1) + add + String.valueOf(q2) + equ + x;
                break;

            case R.id.start_add2:
            	 q =  String.valueOf(q1) + add + x + equ + String.valueOf(q2) ;
                break;

            case R.id.start_min:
            	q =  String.valueOf(q1) + min + String.valueOf(q2) + equ + x;
				break;

            case R.id.start_min2:
				 q =  String.valueOf(q1) + min + x + equ + String.valueOf(q2);
				break;
		}
		return q;
	}

	String[] getLR(){
       	if (a1 < a2) {
        	lr[0] = Integer.toString(a1);
           	lr[1] = Integer.toString(a2);
        } else {
           	lr[0] = Integer.toString(a2);
           	lr[1] = Integer.toString(a1);
        }
        return lr;
	}

	String getCollectLR() {
		if (a1 < a2) {
			return "L";
		} else {
			return "R";
		}
	}

}