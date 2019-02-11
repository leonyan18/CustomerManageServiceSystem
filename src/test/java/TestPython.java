import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class TestPython {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost",8080);
            //获取输出流，向服务器端发送信息
            InputStream is=socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String info=null;
            while(!(info=in.readLine()).equals("over")){
                System.out.println("我是客户端，Python服务器说："+info);
            }
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write("我是Java服务器\n");
            pw.flush();
            info=null;
            while(!(info=in.readLine()).equals("over")){
                System.out.println("我是客户端，Python服务器说："+info);
            }
            socket.shutdownOutput();//关闭输出流
            pw.close();
            os.close();
            is.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
