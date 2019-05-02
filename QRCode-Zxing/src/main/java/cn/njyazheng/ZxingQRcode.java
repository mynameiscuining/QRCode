package cn.njyazheng;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ZxingQRcode {
    
    public static void main(String[] args) {
        int width=300;
        int height=300;
        String imgFormat="png";
        String param="www.baidu.com";
        //设置参数
        Map map=new HashMap();
        //设置字符集,如果汉字
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        //纠错等级:L<Q<M<H;纠错的能力越高存储的能力就越少,一般会选用M
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置边距,默认是5
        map.put(EncodeHintType.MARGIN,5);
    
        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(param, BarcodeFormat.QR_CODE,width,height,map);
            File file=new File("F:\\zxing.png");
            OutputStream outputStream=new FileOutputStream(file);
            //MatrixToImageWriter可以写进流,也可以写进路径
            MatrixToImageWriter.writeToStream(bitMatrix,imgFormat,outputStream);
            //web页面显示
            //设置头部
            /**      stream取:ByteArrayOutputStream
             *         HttpHeaders responseHeaders = new HttpHeaders();
             *         responseHeaders.setContentType(MediaType.IMAGE_JPEG);
             *         responseHeaders.setCacheControl("no-store");
             *         responseHeaders.set("Pragma", "no-cache");
             */
            //new ResponseEntity<>(byteArrayOutputStream.toByteArray(), responseHeaders, HttpStatus.OK);
            //<img src="uri"/>
            
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
