package com.example.root.videodemo.upload;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Build;
import android.widget.Toast;

import com.example.root.videodemo.VideoAdapter.VideoLab;

import java.io.*;
import java.net.Socket;

public class Push extends Thread{
    String mFileName;
    String serverURL = "192.168.43.145";//服务器地址
    int PORT = 9090;

    public void setFile(String fileName){
        this.mFileName =fileName;
    }
    public void run(){
        Log.i("Push", "进入push");
        try{
            File file = new File(mFileName);
            Socket socket = new Socket(serverURL, PORT);
            Log.i("Push", "套接字");
            System.out.println(socket.getInetAddress());

            OutputStream outputStream = socket.getOutputStream();
            //使用DataOutputStream
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            //向服务器端传文件名
            dataOutputStream.writeUTF(file.getName());
            Log.i("Push", file.getName());
            dataOutputStream.flush();//刷新流，传输到服务端
            Log.i("Push", "刷新流，传输到服务端");
            //向服务器端传文件，通过字节流
            //字节流先读取硬盘文件
//            if (Build.VERSION.SDK_INT >= 23) {
//                int REQUEST_CODE_CONTACT = 101;
//                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                //验证是否许可权限
//                for (String str : permissions) {
//                    if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                        //申请权限
//                        this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
//                    }
//                }
//            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            int c = -1;
            //System.out.println("Client 准备上传！");
            Log.i("Push", "Client 准备上传！ ");
            while ((c=bufferedInputStream.read())!=-1) {
                //System.out.println("Client 上传中！");
                //将读取的文件以字节方式写入DataOutputStream，之后传输到服务端
                //这里也可以使用byte[]数据进行读取和写入
                dataOutputStream.write(c);
                dataOutputStream.flush();
            }
            Log.i("Puah", "Client 文件上传结束 ");
            VideoLab.newInstance().addVideo("http://"+serverURL+":8080/examples/"+file.getName());
            bufferedInputStream.close();
            dataOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
