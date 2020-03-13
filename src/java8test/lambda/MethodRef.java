package java8test.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Holiday{
	String localNum;	//自己的号码
	String divertNum; 	//呼转的号码
	int start;			//开始时间
	int valid;			//有效时长
	public Holiday(String in){
		String[] ins=in.split(" ");
		if(ins.length==4){
			localNum=ins[0];//第一个是自己的号码
			divertNum=ins[1];//第二个是呼转的号码
			start=Integer.parseInt(ins[2]);//第三个是开始时间
			valid=Integer.parseInt(ins[3]);//第四个是有效时长
		}
	}
	//判断开始日期是否在度假起始日期和结束日期之间
	public boolean isInValid(int start){
		return start>=this.start&&start<(this.start+this.valid);
	}
}
class HZ{
	int start;
	Map<String,Holiday> djs=new HashMap<String,Holiday>();
	public HZ(Reader in) throws IOException{
		BufferedReader sr=new BufferedReader(in);
		//第一行是度假安排
		int djSize=Integer.parseInt(sr.readLine());
		//从第二行至第N行是度假安排
		for(int i=0;i<djSize;i++){
			Holiday dj=new Holiday(sr.readLine());
			//判断多路
			if(djs.containsKey(dj.localNum)){
				throw new RuntimeException("出现多路转移！");
			}
			djs.put(dj.localNum,dj);
		}
		//最后一行是开始日期
		start=Integer.parseInt(sr.readLine());
		sr.close();
	}
	//输出结果
	public void printResult(){
		int max=0,cnt=0;
		Set<String> locals=djs.keySet();
		//对号码进行遍历，此算法还可优化
		for(String localNum:locals){
			Holiday dj=djs.get(localNum);
			//此号码在有效期，计数加1
			if(dj.isInValid(start)) cnt++;
			//使用递归方法查找号码的下一个转移号码，此时可以认为转移号码为一个链表
			int temp=findNext(localNum,dj,0);
			if(temp==-1) return;
			if(temp>max) max=temp;
		}
		System.out.println("开始日期是:"+start);
		System.out.println("当天呼叫转移："+cnt);
		System.out.println("当天最长呼叫转移："+max);
	}
	//使用递归遍历链表
	private int findNext(String sn,Holiday dj,int cnt){
		//判断环路
		if(dj.divertNum.equals(sn)){
			System.out.println("有环路！");
			return -1;
		}
		//如果在有效期内，且有转移号码也设置转移，继续递归
		if(dj.isInValid(start)&&djs.containsKey(dj.divertNum)) return findNext(sn,djs.get(dj.divertNum),++cnt);
		//判断在有效期内没有下一个转移，返回计数加1
		else if(dj.isInValid(start)) return cnt+1;
		//如果不在有效期，直接返回
		else return cnt;
	}
}
public class MethodRef {
	public static void main(String[] args) throws IOException {
		StringBuffer in=new StringBuffer();
		in.append("5").append("\r\n");
		in.append("0000 0001 1 3").append("\r\n");
		in.append("0001 4964 2 1").append("\r\n");
		in.append("4964 0014 2 3").append("\r\n");
		in.append("0004 0001 3 3").append("\r\n");
		in.append("1234 0004 2 5").append("\r\n");
		in.append("3");
		System.out.println("输入内容:\r\n"+in);
		HZ hz=new HZ(new StringReader(in.toString()));
		hz.printResult();
	}
}
