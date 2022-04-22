package Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(8088)) {  //try会关闭端口
            System.out.println("Client is listening");
            while (true){
                Socket socket = server.accept();//没有客户端时会阻塞，有了就打开；
                System.out.println("客户端连接了" +socket.getInetAddress().getHostAddress());
                InputStream is = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(reader);
                System.out.println(bufferedReader.readLine());
                OutputStreamWriter serverWriter = new OutputStreamWriter(socket.getOutputStream());
                serverWriter.write("Server端已经收到\n");
                serverWriter.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
