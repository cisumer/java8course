package java8test.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Holiday{
	String localNum;	//�Լ��ĺ���
	String divertNum; 	//��ת�ĺ���
	int start;			//��ʼʱ��
	int valid;			//��Чʱ��
	public Holiday(String in){
		String[] ins=in.split(" ");
		if(ins.length==4){
			localNum=ins[0];//��һ�����Լ��ĺ���
			divertNum=ins[1];//�ڶ����Ǻ�ת�ĺ���
			start=Integer.parseInt(ins[2]);//�������ǿ�ʼʱ��
			valid=Integer.parseInt(ins[3]);//���ĸ�����Чʱ��
		}
	}
	//�жϿ�ʼ�����Ƿ��ڶȼ���ʼ���ںͽ�������֮��
	public boolean isInValid(int start){
		return start>=this.start&&start<(this.start+this.valid);
	}
}
class HZ{
	int start;
	Map<String,Holiday> djs=new HashMap<String,Holiday>();
	public HZ(Reader in) throws IOException{
		BufferedReader sr=new BufferedReader(in);
		//��һ���Ƕȼٰ���
		int djSize=Integer.parseInt(sr.readLine());
		//�ӵڶ�������N���Ƕȼٰ���
		for(int i=0;i<djSize;i++){
			Holiday dj=new Holiday(sr.readLine());
			//�ж϶�·
			if(djs.containsKey(dj.localNum)){
				throw new RuntimeException("���ֶ�·ת�ƣ�");
			}
			djs.put(dj.localNum,dj);
		}
		//���һ���ǿ�ʼ����
		start=Integer.parseInt(sr.readLine());
		sr.close();
	}
	//������
	public void printResult(){
		int max=0,cnt=0;
		Set<String> locals=djs.keySet();
		//�Ժ�����б��������㷨�����Ż�
		for(String localNum:locals){
			Holiday dj=djs.get(localNum);
			//�˺�������Ч�ڣ�������1
			if(dj.isInValid(start)) cnt++;
			//ʹ�õݹ鷽�����Һ������һ��ת�ƺ��룬��ʱ������Ϊת�ƺ���Ϊһ������
			int temp=findNext(localNum,dj,0);
			if(temp==-1) return;
			if(temp>max) max=temp;
		}
		System.out.println("��ʼ������:"+start);
		System.out.println("�������ת�ƣ�"+cnt);
		System.out.println("���������ת�ƣ�"+max);
	}
	//ʹ�õݹ��������
	private int findNext(String sn,Holiday dj,int cnt){
		//�жϻ�·
		if(dj.divertNum.equals(sn)){
			System.out.println("�л�·��");
			return -1;
		}
		//�������Ч���ڣ�����ת�ƺ���Ҳ����ת�ƣ������ݹ�
		if(dj.isInValid(start)&&djs.containsKey(dj.divertNum)) return findNext(sn,djs.get(dj.divertNum),++cnt);
		//�ж�����Ч����û����һ��ת�ƣ����ؼ�����1
		else if(dj.isInValid(start)) return cnt+1;
		//���������Ч�ڣ�ֱ�ӷ���
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
		System.out.println("��������:\r\n"+in);
		HZ hz=new HZ(new StringReader(in.toString()));
		hz.printResult();
	}
}
