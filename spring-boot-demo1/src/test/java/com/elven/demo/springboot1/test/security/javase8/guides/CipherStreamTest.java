/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

/**
 * @author qiusheng.wu
 * @Filename CipherStreamTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/27 0:02</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CipherStreamTest {

    /**
     * 密钥算法 <br>
     * Java 6 只支持56bit密钥 <br>
     * Bouncy Castle 支持64bit密钥
     */
    public static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    /**
     * 转换密钥
     *
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    public Key toKey(String key) throws Exception
    {

        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key.getBytes());

        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        // 生成秘密密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);

        return secretKey;
    }

    /**
     * CipherInputStream.read加/解密
     * 注意：首先创建好需要的文件，它不会自动创建文件。
     * @throws Exception
     */
    @Test
    public void cipherInputStreamTest() throws Exception {
        ///////////////////////////////CipherInputStream加密/////////////////////////////////////////

        // 步骤一：创建输入“源文件”
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_1.txt"));

        // 步骤二：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤四：用FileInputStream对象和Cipher对象创建CipherInputStream对象
        CipherInputStream cis = new CipherInputStream(fis, cipher);

        // 步骤五：CipherInputStream对象read加密操作，并写入到“密文文件”中
        FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_1.txt"));
        byte[] b = new byte[8];
        int i = cis.read(b);
        while (i != -1) {
            fos.write(b, 0, i);
            i = cis.read(b);
        }
        fos.flush();
        fos.close();
        System.out.println("CipherInputStream.read加密结束");

        // 步骤六：查看文件E:\code\idea\elven\elven-demos\spring-boot-demo1\build\resources\test\security\cipherStream\out_1.txt
        // 但你应该看不懂，因为它是“乱码”（二进制文件）

        ///////////////////////////////CipherInputStream解密/////////////////////////////////////////
        // 步骤一：创建输入“源文件”
        fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_1.txt"));

        // 步骤二：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤四：用FileInputStream对象和Cipher对象创建CipherInputStream对象
        cis = new CipherInputStream(fis, cipher);

        // 步骤五：CipherInputStream对象read加密操作，并写入到“密文文件”中
        fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_1_1.txt"));
        b = new byte[8];
        i = cis.read(b);
        while (i != -1) {
            fos.write(b, 0, i);
            i = cis.read(b);
        }
        fos.flush();
        fos.close();
        System.out.println("CipherInputStream.read解密结束");

        // 步骤六：查看E:\code\idea\elven\elven-demos\spring-boot-demo1\build\resources\test\security\cipherStream\out_1_1.txt文件
        // 比较in_1.txt和out_1_1.txt文件内容是否一致。

    }

    /**
     * 将两个CipherInputStream连接起来使用，可以将一个文件复制到另一个文件
     * CipherInputStream1将in_2.txt明文转变为密文
     * CipherInputStream2将密文转变为明文，并输出到out_2.txt文件中
     * @throws Exception
     */
    @Test
    public void cipherInputStreamConnectTest() throws Exception {
        // 步骤一：创建输入“源文件”
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_2.txt"));

        // 步骤二：创建Cipher加密对象，并初始化
        Cipher cipher1 = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher1.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤三：用FileInputStream对象和Cipher对象创建CipherInputStream对象
        CipherInputStream cis1 = new CipherInputStream(fis, cipher1);

        // 步骤四：创建Cipher解密对象，并初始化
        Cipher cipher2 = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher2.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤五：用CipherInputStream1对象和Cipher2对象创建CipherInputStream2对象
        CipherInputStream cis2 = new CipherInputStream(cis1, cipher2);

        // 步骤六：CipherInputStream对象read操作，并写入到输出文件中
        FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_2.txt"));
        byte[] b = new byte[8];
        int i = cis2.read(b);
        while (i != -1) {
            fos.write(b, 0, i);
            i = cis2.read(b);
        }
        fos.flush();
        fos.close();

        System.out.println("CipherInputStream Connect复制文件结束");
    }

    /**
     * CipherInputStream.read加/解密
     * 注意：首先创建好需要的文件，它不会自动创建文件。
     * @throws Exception
     */
    @Test
    public void cipherOutputStreamTest() throws Exception {
        ///////////////////////////////CipherOutputStream加密/////////////////////////////////////////

        // 步骤一：创建输入“源文件”流
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_3.txt"));

        // 步骤二：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤四：创建输出文件流
        FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_3.txt"));

        // 步骤五：用输出文件流和Cipher对象创建CipherOutputStream对象
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);

        // 步骤六：CipherOutputStream对象write加密操作，并写入到“密文文件”中
        byte[] b = new byte[8];
        int i = fis.read(b);
        while (i != -1) {
            cos.write(b, 0, i);
            i = fis.read(b);
        }
        cos.flush();
        cos.close();
        System.out.println("CipherOutputStream.write加密完毕");

        // 步骤七：查看文件E:\code\idea\elven\elven-demos\spring-boot-demo1\build\resources\test\security\cipherStream\out_3.txt
        // 但你应该看不懂，因为它是“乱码”（二进制文件）

        ///////////////////////////////CipherOutputStream解密/////////////////////////////////////////
        // 步骤一：创建输入“源文件”流
        fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_3.txt"));

        // 步骤二：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤四：创建输出文件流
        fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_3_1.txt"));

        // 步骤五：用输出文件流和Cipher对象创建CipherOutputStream对象
        cos = new CipherOutputStream(fos, cipher);

        // 步骤六：CipherOutputStream对象write加密操作，并写入到“密文文件”中
        b = new byte[8];
        i = fis.read(b);
        while (i != -1) {
            cos.write(b, 0, i);
            i = fis.read(b);
        }
        cos.flush();
        cos.close();
        System.out.println("CipherOutputStream.write解密完毕");

        // 步骤七：查看E:\code\idea\elven\elven-demos\spring-boot-demo1\build\resources\test\security\cipherStream\out_3_1.txt文件
        // 比较in_3.txt和out_3_1.txt文件内容是否一致。
    }

    /**
     * CipherOutputStream Connect复制文件
     * 其加密和解密方向与CipherInputStream相反：
     * CipherInputStream先加密再解密
     * CipherOutputStream先解密再加密
     * @throws Exception
     */
    @Test
    public void cipherOutputStreamConnectTest() throws Exception {
        // 步骤一：创建输入“源文件”流
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_4.txt"));

        // 步骤二：创建Cipher加密对象，并初始化
        Cipher cipher1 = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher1.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤三：创建输出文件流
        FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_4.txt"));

        // 步骤三：用FileOutputStream对象和Cipher对象创建CipherOutputStream1对象
        CipherOutputStream cos1 = new CipherOutputStream(fos, cipher1);

        // 步骤四：创建Cipher解密对象，并初始化
        Cipher cipher2 = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher2.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤五：用CipherOutputStream1对象和Cipher2对象创建CipherOutputStream2对象
        CipherOutputStream cos2 = new CipherOutputStream(cos1, cipher2);

        // 步骤六：CipherInputStream对象read操作，并写入到输出文件中
        byte[] b = new byte[8];
        int i = fis.read(b);
        while (i != -1) {
            cos2.write(b, 0, i);
            i = fis.read(b);
        }
        cos2.flush();
        cos2.close();

        System.out.println("CipherOutputStream Connect复制文件结束");
    }

    @Test
    public void cipherInputAndOutputStreamConnectTest() throws Exception {
        ///////////////////////////////CipherInputStream加密/////////////////////////////////////////

        // 步骤一：创建输入“源文件”流
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_5.txt"));

        // 步骤二：创建Cipher对象
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey("12345678"));

        // 步骤四：用FileInputStream对象和Cipher对象创建CipherInputStream对象
        CipherInputStream cis = new CipherInputStream(fis, cipher);

        // 步骤五：CipherInputStream对象read加密操作，并写入到“密文文件”中
        FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_5_1.txt"));
        byte[] b = new byte[8];
        int i = cis.read(b);
        while (i != -1) {
            fos.write(b, 0, i);
            i = cis.read(b);
        }
        fos.flush();
        fos.close();
        System.out.println("CipherInputStream.read加密结束");

        //////////////////////////////CipherOutputStream解密/////////////////////////////////////////
        // 步骤一：创建输入“密文文件”流
        fis = new FileInputStream(ResourceUtils.getFile("classpath:security/cipherStream/in_5_1.txt"));

        // 步骤二：创建Cipher对象
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 步骤三：初始化Cipher对象为加密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey("12345678"));

        // 步骤四：创建输出文件流
        fos = new FileOutputStream(ResourceUtils.getFile("classpath:security/cipherStream/out_5.txt"));

        // 步骤五：用输出文件流和Cipher对象创建CipherOutputStream对象
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);

        // 步骤六：CipherOutputStream对象write加密操作，并写入到“密文文件”中
        b = new byte[8];
        i = fis.read(b);
        while (i != -1) {
            cos.write(b, 0, i);
            i = fis.read(b);
        }
        cos.flush();
        cos.close();
        System.out.println("CipherOutputStream.write解密完毕");

        // 步骤七：查看E:\code\idea\elven\elven-demos\spring-boot-demo1\build\resources\test\security\cipherStream\out_3_1.txt文件
        // 比较in_4.txt和out_5.txt文件内容是否一致。
    }
}