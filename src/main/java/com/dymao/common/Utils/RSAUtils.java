package com.dymao.common.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAUtils {
	public static final int KEYSIZE = 512;
	
	/**
	 * 加密
	 * @param publicKey 公钥
	 * @param content 需要加密的内容
	 * @return
	 * @throws Exception
	 */
	public String encrypttoStr(Key publicKey,String content) throws Exception{
		RandUtil rand = new RandUtil();
		String endata = rand.parseByte2HexStr(publicEnrypy(publicKey,content));
		return endata;
	}
	
	/**
	 * 解密
	 * @param privateKey 私钥
	 * @param endata 需要解密的内容
	 * @return
	 * @throws Exception
	 */
	public String decrypttoStr(Key privateKey,String endata) throws Exception{
		RandUtil rand = new RandUtil();
		String data = new String(privateEncode(privateKey,rand.parseHexStr2Byte(endata)));
		return data;
	}
	
	public String decrypttoStr_normal(Key privateKey,String endata) throws Exception{
		String data = new String(privateEncode(privateKey,endata.getBytes()));
		return data;
	}
	
	
	 /**
	  * 加密的方法,使用公钥进行加密
	  * @param publicKey 公钥
	  * @param data 需要加密的数据
	  * @throws Exception
	  */
    public static byte[] publicEnrypy(Key publicKey,String data) throws Exception {
 
        Cipher cipher = Cipher.getInstance("RSA",new org.bouncycastle.jce.provider.BouncyCastleProvider());
 
        // 设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
 
        // 对数据进行加密
        byte[] result = cipher.doFinal(data.getBytes());

        
        return result;
    }
 
    /**
     * 解密的方法，使用私钥进行解密
     * privateKey  私钥
     * encoData 需要解密的数据
     * @throws Exception
     */
    public static byte[]  privateEncode(Key privateKey,byte[] encoData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA",new org.bouncycastle.jce.provider.BouncyCastleProvider());
          
        //设置为解密模式，用私钥解密
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         //解密
         byte[] data = cipher.doFinal(encoData);
//         System.out.println("解密后的数据："+data);
         return data;
    }
    
    
    
    /**
	 * 自动生成密钥对
	 * @throws Exception
	 */
	public  Map<String,Object> createKey(){
		
		 	try {
//				Cipher cipher = Cipher.getInstance("RSA");
		        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
				
		        SecureRandom random = new SecureRandom();  
		        keyPairGenerator.initialize(RSAUtils.KEYSIZE, random);  
		 
		        // 生成钥匙对
		        KeyPair keyPair = keyPairGenerator.generateKeyPair();
		 
		        // 得到公钥
		        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		        // 得到私钥
		        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		        
		        Map<String,Object> map = new HashMap<String, Object>();
		        map.put("publicKey", publicKey);
		        map.put("privateKey", privateKey);
		 
		        return map;
		        //把私钥保存到硬盘上
	//	        saveKey(privateKey,"E://private_key");
		      //把公钥保存到硬盘上
	//	        saveKey(publicKey,"E://public_key");
		 	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
		
	}
     
      
    /**
     * 从硬盘中加载私钥
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public  Key loadKey(String keyUrl) throws IOException, FileNotFoundException,
            ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(new File(keyUrl)));
        Key key = (Key) inputStream.readObject();
        return key;
    }
   
     
    /**
     * 把私钥或则公钥保存到硬盘上
     * @param privateKey
     * @throws IOException
     * @throws FileNotFoundException
     */
    private  void saveKey(Key key,String saveUrl) throws IOException,
            FileNotFoundException {
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(new File(saveUrl)));
        outputStream.writeObject(key);
    }
    
    /**  
     * 使用模和指数生成RSA公钥  
     *   
     *   
     * @param modulus  
     *            模  
     * @param exponent  
     *            指数  
     * @return  
     */    
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {    
        try {    
            BigInteger b1 = new BigInteger(modulus);    
            BigInteger b2 = new BigInteger(exponent);    
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());    
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);    
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);    
        } catch (Exception e) {    
            e.printStackTrace();    
            return null;    
        }    
    }  
    
    /**  
     * 使用模和指数生成RSA私钥  
      
     * /None/NoPadding】  
     *   
     * @param modulus  
     *            模  
     * @param exponent  
     *            指数  
     * @return  
     */    
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {    
        try {    
            BigInteger b1 = new BigInteger(modulus);    
            BigInteger b2 = new BigInteger(exponent);    
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());    
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);    
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);    
        } catch (Exception e) {    
            e.printStackTrace();    
            return null;    
        }    
    } 

}