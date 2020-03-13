package java8test.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UseLambda {
	
	
	
	
	public static void main(String[] args) {
		Connection.execute( con->con.execute("select * from user"));
		for (String string : args) {
			
		}
	}
}

class Connection{
	public void connect(){
		System.out.println("connected");
	}
	
	public void execute(String sql){
		System.out.println("execute:"+sql);
	}	
	
	public void close(){
		System.out.println("closed");
	}
	public static void execute(Consumer<Connection> c){
		Connection con=new Connection();
		con.connect();
		try{
			c.accept(con);
		}catch(Exception e){
			//´íÎó´¦Àí´úÂë
		}finally{
			con.close();
		}
	}
}