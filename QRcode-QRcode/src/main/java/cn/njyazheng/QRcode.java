package cn.njyazheng;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRcode {
    public static void main(String[] args) throws IOException {
        
        String params="www.baidu.com";
        int width=300;
        int height=300;
    
    
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D gs=bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0,0,width,height);
        //添加偏移量,有问题的时候使用
        int pixoff=2;
        
        Qrcode x=new Qrcode();
        //纠错等级:L<Q<M<H;纠错的能力越高存储的能力就越少,一般会选用M
        x.setQrcodeErrorCorrect('M');
        //N->数字,A->a-Z,B->其他字符
        //中文,日文可选择B
        x.setQrcodeEncodeMode('B');
        //版本号
        x.setQrcodeVersion(7);
        byte[] d =params.getBytes();
        if (d.length>0 && d.length <120){
            boolean[][] s = x.calQrcode(d);
        
            for (int i=0;i<s.length;i++){
                for (int j=0;j<s.length;j++){
                    if (s[j][i]) {
                        gs.fillRect(j*3+pixoff,i*3+pixoff,3,3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
    
        ImageIO.write(bufferedImage,"png",new File("F:\\qrcode.png"));
    
    }
}
