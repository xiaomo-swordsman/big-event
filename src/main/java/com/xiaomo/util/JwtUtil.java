package com.xiaomo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final String KEY = "itheima";

    // 对称加密算法 HMAC
	//接收业务数据,生成token并返回
    public static String genHMACToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .sign(Algorithm.HMAC256(KEY));
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseHMACToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

    // 非对称加密 RSA 私钥 进行签名加密数据
    /*
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.19.1</version>
        </dependency>
     */
    public static String genRSAToken(Map<String,Object> claim) throws Exception {
        ClassLoader classLoader = JwtUtil.class.getClassLoader();
        // 1、从resources中读取私钥
        InputStream privateKeyStream =  classLoader.getResourceAsStream("rsakey/rsa");
        if (privateKeyStream == null) {
            throw new IOException("Private key file not found in resources");
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = privateKeyStream.read(data, 0, data.length))!= -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] privateKeyBytes =  buffer.toByteArray();
        privateKeyStream.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = factory.generatePrivate(spec);

        // 2、基于私钥，生成token
        // 2.1设置header，第一部分数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg","RS256");
        map.put("typ","JWT");

        // 2.2 设置payload，第二部分数据
//        Map<String,Object> claim = new HashMap<String,Object>();
//        claim.put("name","xiaomo");
//        claim.put("age","34");

        // 2.3 私钥进行签名，第三部分
        Algorithm algorithm = Algorithm.RSA256(null,(RSAPrivateKey)privateKey);

        // 2.4 要包含在 JWT 中的信息
        String issuer = "your_issuer";
        String subject = "your_subject";
        String audience = "your_audience";
        long iat = System.currentTimeMillis() - 1000 * 60 ; //设置生效时间，提前一分钟

        // 2.5 执行，生成token
        return JWT.create()
                .withHeader(map)
                .withClaim("claim",claim)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 设置有效期 1分钟
                .withNotBefore(new Date(iat))
                .withJWTId(UUID.randomUUID().toString()) // JWT的唯一标识符
                .withIssuer(issuer)
                .withSubject(subject)
                .withAudience(audience)
                .sign(algorithm);
    }

    // 基于RSA 公钥验证签名
    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseRSAToken(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ClassLoader classLoader = JwtUtil.class.getClassLoader();
        // 1、读取公钥
        InputStream publicKeyStream =  classLoader.getResourceAsStream("rsakey/rsa.pub");
        if (publicKeyStream == null) {
            throw new IOException("Public key file not found in resources");
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = publicKeyStream.read(data, 0, data.length))!= -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] publicKeyBytes =  buffer.toByteArray();
        publicKeyStream.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(spec);

        // 2、基于公钥，验证签名
        //接收token,验证token,并返回业务数据
        return JWT.require(Algorithm.RSA256((RSAPublicKey)publicKey, null))
                .build()
                .verify(token)
                .getClaim("claim")
                .asMap();
    }

    // 非对称加密 ECDSA 私钥 进行签名加密数据
    public static String genECDSAToken(Map<String,Object> claim) throws Exception {
        InputStream privateKeyStream =  JwtUtil.class.getClassLoader().getResourceAsStream("rsakey/ecsda");
        if (privateKeyStream == null) {
            throw new IOException("Private key file not found in resources");
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = privateKeyStream.read(data, 0, data.length))!= -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] privateKeyBytes =  buffer.toByteArray();
        privateKeyStream.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = factory.generatePrivate(spec);

        // 2、基于私钥，生成token
        // 2.1设置header，第一部分数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg","ES256");
        map.put("typ","JWT");

        // 2.2 设置payload，第二部分数据
//        Map<String,Object> claim = new HashMap<String,Object>();
//        claim.put("name","xiaomo");
//        claim.put("age","34");

        // 2.3 私钥进行签名，第三部分
        Algorithm algorithm = Algorithm.ECDSA256(null,(ECPrivateKey) privateKey);

        // 2.4 要包含在 JWT 中的信息
        String issuer = "your_issuer";
        String subject = "your_subject";
        String audience = "your_audience";
        long iat = System.currentTimeMillis() - 1000 * 60 ; //设置生效时间，提前一分钟

        // 2.5 执行，生成token
        return JWT.create()
                .withHeader(map)
                .withClaim("claim",claim)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60)) // 设置有效期 1分钟
                .withNotBefore(new Date(iat))
                .withJWTId(UUID.randomUUID().toString()) // JWT的唯一标识符
                .withIssuer(issuer)
                .withSubject(subject)
                .withAudience(audience)
                .sign(algorithm);
    }

    // 基于ECDSA 公钥验证签名
    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseECDSAToken(String token) throws Exception {
        ClassLoader classLoader = JwtUtil.class.getClassLoader();
        // 1、读取公钥
        InputStream publicKeyStream =  classLoader.getResourceAsStream("rsakey/ecsda.pub");
        if (publicKeyStream == null) {
            throw new IOException("Public key file not found in resources");
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = publicKeyStream.read(data, 0, data.length))!= -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] publicKeyBytes =  buffer.toByteArray();
        publicKeyStream.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("EC");
        PublicKey publicKey = factory.generatePublic(spec);

        // 2、基于公钥，验证签名
        //接收token,验证token,并返回业务数据
        return JWT.require(Algorithm.ECDSA256((ECPublicKey)publicKey, null))
                .build()
                .verify(token)
                .getClaim("claim")
                .asMap();
    }
}
