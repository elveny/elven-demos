/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.security.pkcs.PKCS8Key;
import sun.security.util.DerValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author qiusheng.wu
 * @Filename RSACryptTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/25 10:15</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RSACryptTest {

    @Test
    public void sign() throws Exception {

        byte[] keyBytes =  Base64.decodeBase64("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPfYiYGSqD2olvLGaB9C7hiDlJiSNwC/vw4E34+M7XPBPcqjyvciiDcaLGU7R35/qH6Nzm1Rfz6lhEzG3InWRnMSDs/AISrIjEUtlvkyDDdnzf1ehov+NVpodGY+gnnGxw64i2fUGKsROL0HVvdTTMMi3J/aX/h4PH97H4+90+LAgMBAAECgYACOsa5PBUZY/VRKiyugP3jKWqUUX4W0wnoARNnunmINkUOMzPSAf6ohRnD+7f/QVs+qMR8Ka4URR30MCq9z+jZtzbVFpV3HcF7SbHhicQtfPWqqov0laxMWWdVlWfxJYJvwJ/6LJ7bqssPZXJ9+NceXL7F8SbobXFZvSfuhDCF4QJBAPFa+KNTQIhMuGzKffsbdjpYe9NttZmZ/Z1swfY4NQZf8Y36QpqVW51z8cxzWxyNnthufvpFJdCqD9nnvSCv4sMCQQDPwer1ZU4DDsO/iqvuk5c+rSBDdxJbCxp5RTGytJoKvXqcht8lUeu5dkgk9QMKf1Z7GfhZnJp2Lj3vSNAtBYOZAkEApA4FAed97ufPWEuPtJbnFyO8D2v8S4sro80gTn/IMywWIj6g9ThezLjZ+/HRVahB97Wr4+wKlzpxidmGaeiERwJBAKpc/Pvf55nQSKpP328S7gpCU1ufT5kCwOHC4N8HA+5ctCeY3XEv/RmnKb/MfoLkKpllkWaCaZMRlk4aqkLQ67kCQHbzf87PKaqKHCOoCM5SMXC3unI6Pp3Bv2MQTacFEPQ8l00dzkqUVTYP0estYNOR7PZNh0mn7p+HbyTDJKivKiU=".getBytes());
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void decode() throws InvalidKeyException {
        byte[] keyBytes =  Base64.decodeBase64("ENC(GI4OlOp/4/oiXFbZHfDzISAWTHBb20pkMmnqoLJ3C6c+8a6INRtoaH82KQhf1ZW9DFHlfO01O5vEz6OFU2OmcRFF9jxyA40A4evTClUBv+JaXFwf7JSuSWFDS9jKwa8qR20BHsix3xk+e7xdcKdbqAdQiYPgoB2mT8rwk4Yf8wffOqd8ehH/WDjByC+EnZ9FFIqH36INjIImNjESW4QoOfm0l/nhsGFzoY1WZaX9M1TgpMYTZp7pPCAFtrtgzLOhBtxNCUQcPzKRdm2rf0gUxWB/7guSGHPeoc8PoNAFsdhS9zNs1qgU7dJC4DnaZZoz1hyWmsNV446trbKA4UhxzMZFwMxRDG2ZtRepiJIriTGmUEwG+YcR9/ShvQbkT9IPkNKMWSux935Kj9+1EFn4Jz5hKCE34d0dGxwfoy9vwCIu1LZa01xlRUv84uRCPoXuT3iUisuiwTfI8LW2LHcbLYYBx7yhgzfumAnPzm0cJKwnfiJOysYHQOk5GNKXxHyHNdIqnw+bisNgFfFmvxCwpX91/aCrIjP2cTJevJZew/e2ASVwNfnI1rmR253zzmtJFNTQZwurZph3IpweuTYdyh4yQV86aEWjWK/0vPUMZ69Xnglcc2EwiHzu/5lB2k3+6BqV4U83uHCQ5Kk9Py4zUDTpA/00XGrRKMQwly5LPK7slLPHYcYafXX0Wqy/CQYrMYrchEb/g0H9g6vG936nQ8ZEQnC/8YT+l6u+qYrsMtAwWFf6B/8HlkEooqM4aQ4nGVCHbMSVCtzClYRDldzd1tarc06uE2efGFQVLGfJYtWx5gU/Zt2XMh9wnz3Ye9gOs078P9tzhllsWmgxhN0RgicSfdqur35ajJoB1dUYTWDtQiaw21QhZD8f+bprq5cpBb9Z2rfWJSXeSufByQAeTQG2gDZg9LZtyIUIE2Bj0tAEqLspmjxhuXdP9Jp/E8lmTlRH17Q6PTmhrivoqN65xtIupkJwTT8zvZpX2s4V5ogxbo9hF3+pae4KYHJD2qOEWvZQD/dVxq711Arsr+2A1yfKah7g5GGYbqjBOJusD9ytPE6gzLwMgdZKzNHHEMvrLIWslTQHnC3DrIhbOnEe1fB5VZomIZg+wtU0QZJc15AEGyy/DXeDA7ME6B4ebuVD)".getBytes());
        new PKCS8Key().decode((InputStream)(new ByteArrayInputStream(keyBytes)));
    }

    @Test
    public void DerValue() throws IOException {
        byte[] keyBytes =  Base64.decodeBase64("ENC(GI4OlOp/4/oiXFbZHfDzISAWTHBb20pkMmnqoLJ3C6c+8a6INRtoaH82KQhf1ZW9DFHlfO01O5vEz6OFU2OmcRFF9jxyA40A4evTClUBv+JaXFwf7JSuSWFDS9jKwa8qR20BHsix3xk+e7xdcKdbqAdQiYPgoB2mT8rwk4Yf8wffOqd8ehH/WDjByC+EnZ9FFIqH36INjIImNjESW4QoOfm0l/nhsGFzoY1WZaX9M1TgpMYTZp7pPCAFtrtgzLOhBtxNCUQcPzKRdm2rf0gUxWB/7guSGHPeoc8PoNAFsdhS9zNs1qgU7dJC4DnaZZoz1hyWmsNV446trbKA4UhxzMZFwMxRDG2ZtRepiJIriTGmUEwG+YcR9/ShvQbkT9IPkNKMWSux935Kj9+1EFn4Jz5hKCE34d0dGxwfoy9vwCIu1LZa01xlRUv84uRCPoXuT3iUisuiwTfI8LW2LHcbLYYBx7yhgzfumAnPzm0cJKwnfiJOysYHQOk5GNKXxHyHNdIqnw+bisNgFfFmvxCwpX91/aCrIjP2cTJevJZew/e2ASVwNfnI1rmR253zzmtJFNTQZwurZph3IpweuTYdyh4yQV86aEWjWK/0vPUMZ69Xnglcc2EwiHzu/5lB2k3+6BqV4U83uHCQ5Kk9Py4zUDTpA/00XGrRKMQwly5LPK7slLPHYcYafXX0Wqy/CQYrMYrchEb/g0H9g6vG936nQ8ZEQnC/8YT+l6u+qYrsMtAwWFf6B/8HlkEooqM4aQ4nGVCHbMSVCtzClYRDldzd1tarc06uE2efGFQVLGfJYtWx5gU/Zt2XMh9wnz3Ye9gOs078P9tzhllsWmgxhN0RgicSfdqur35ajJoB1dUYTWDtQiaw21QhZD8f+bprq5cpBb9Z2rfWJSXeSufByQAeTQG2gDZg9LZtyIUIE2Bj0tAEqLspmjxhuXdP9Jp/E8lmTlRH17Q6PTmhrivoqN65xtIupkJwTT8zvZpX2s4V5ogxbo9hF3+pae4KYHJD2qOEWvZQD/dVxq711Arsr+2A1yfKah7g5GGYbqjBOJusD9ytPE6gzLwMgdZKzNHHEMvrLIWslTQHnC3DrIhbOnEe1fB5VZomIZg+wtU0QZJc15AEGyy/DXeDA7ME6B4ebuVD)".getBytes());

        new DerValue((InputStream)(new ByteArrayInputStream(keyBytes)));
    }

    @Test
    public void getLength() throws IOException {
        byte[] keyBytes =  Base64.decodeBase64("ENC(GI4OlOp/4/oiXFbZHfDzISAWTHBb20pkMmnqoLJ3C6c+8a6INRtoaH82KQhf1ZW9DFHlfO01O5vEz6OFU2OmcRFF9jxyA40A4evTClUBv+JaXFwf7JSuSWFDS9jKwa8qR20BHsix3xk+e7xdcKdbqAdQiYPgoB2mT8rwk4Yf8wffOqd8ehH/WDjByC+EnZ9FFIqH36INjIImNjESW4QoOfm0l/nhsGFzoY1WZaX9M1TgpMYTZp7pPCAFtrtgzLOhBtxNCUQcPzKRdm2rf0gUxWB/7guSGHPeoc8PoNAFsdhS9zNs1qgU7dJC4DnaZZoz1hyWmsNV446trbKA4UhxzMZFwMxRDG2ZtRepiJIriTGmUEwG+YcR9/ShvQbkT9IPkNKMWSux935Kj9+1EFn4Jz5hKCE34d0dGxwfoy9vwCIu1LZa01xlRUv84uRCPoXuT3iUisuiwTfI8LW2LHcbLYYBx7yhgzfumAnPzm0cJKwnfiJOysYHQOk5GNKXxHyHNdIqnw+bisNgFfFmvxCwpX91/aCrIjP2cTJevJZew/e2ASVwNfnI1rmR253zzmtJFNTQZwurZph3IpweuTYdyh4yQV86aEWjWK/0vPUMZ69Xnglcc2EwiHzu/5lB2k3+6BqV4U83uHCQ5Kk9Py4zUDTpA/00XGrRKMQwly5LPK7slLPHYcYafXX0Wqy/CQYrMYrchEb/g0H9g6vG936nQ8ZEQnC/8YT+l6u+qYrsMtAwWFf6B/8HlkEooqM4aQ4nGVCHbMSVCtzClYRDldzd1tarc06uE2efGFQVLGfJYtWx5gU/Zt2XMh9wnz3Ye9gOs078P9tzhllsWmgxhN0RgicSfdqur35ajJoB1dUYTWDtQiaw21QhZD8f+bprq5cpBb9Z2rfWJSXeSufByQAeTQG2gDZg9LZtyIUIE2Bj0tAEqLspmjxhuXdP9Jp/E8lmTlRH17Q6PTmhrivoqN65xtIupkJwTT8zvZpX2s4V5ogxbo9hF3+pae4KYHJD2qOEWvZQD/dVxq711Arsr+2A1yfKah7g5GGYbqjBOJusD9ytPE6gzLwMgdZKzNHHEMvrLIWslTQHnC3DrIhbOnEe1fB5VZomIZg+wtU0QZJc15AEGyy/DXeDA7ME6B4ebuVD)".getBytes());
        ByteArrayInputStream var2 = new ByteArrayInputStream(keyBytes);
        byte tag = (byte)((InputStream)var2).read();
        byte var3 = (byte)((InputStream)var2).read();

        int length = getLength(var3 & 255, (InputStream)var2);
        System.out.println(length);
    }

    private int getLength(int var0, InputStream var1) throws IOException {
        int var2;
        if((var0 & 128) == 0) {
            var2 = var0;
        } else {
            int var3 = var0 & 127;
            if(var3 == 0) {
                return -1;
            }

            if(var3 < 0 || var3 > 4) {
                throw new IOException("DerInputStream.getLength(): lengthTag=" + var3 + ", " + (var3 < 0?"incorrect DER encoding.":"too big."));
            }

            for(var2 = 0; var3 > 0; --var3) {
                var2 <<= 8;
                var2 += 255 & var1.read();
            }

            if(var2 < 0) {
                throw new IOException("DerInputStream.getLength(): Invalid length bytes");
            }
        }

        return var2;
    }

}