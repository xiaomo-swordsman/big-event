package com.xiaomo.test;

import com.xiaomo.util.JwtUtil;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenHMACToken(){
        Map<String,Object> claim = new HashMap<String,Object>();
        claim.put("name","xiaomo");
        claim.put("age","34");
        System.out.println(JwtUtil.genHMACToken(claim));
    }

//    @Test
    public void testParseHMACToken(){
        Map<String, Object> claim = JwtUtil.parseHMACToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsibmFtZSI6InhpYW9tbyIsImFnZSI6IjM0In0sImV4cCI6MTczNzAxNjAxMX0.BoMVwiaYAPxIGmqhWm-N9uKZXXiufPDptvPaPMLeu2M");
        System.out.println(claim);
    }

//    @Test
    public void testGenRSAToken() throws Exception {
        Map<String,Object> claim = new HashMap<String,Object>();
        claim.put("name","xiaomo");
        claim.put("age","34");
        System.out.println(JwtUtil.genRSAToken(claim));
    }

//    @Test
    public void testParseRSAToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Map<String, Object> claim = JwtUtil.parseRSAToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJjbGFpbSI6eyJuYW1lIjoieGlhb21vIiwiYWdlIjoiMzQifSwiZXhwIjoxNzM3MDEzMTQ3LCJqdGkiOiJhYTRkYTI1NC1mY2JmLTRhMDYtYWRkYy1lYmUyYjBkNmFjZjQiLCJpc3MiOiJ5b3VyX2lzc3VlciIsInN1YiI6InlvdXJfc3ViamVjdCIsImF1ZCI6InlvdXJfYXVkaWVuY2UifQ.NrJebtmHtE2edU1cuPCA7KF3D_OHccOrTny5AnE4ATLSn3QI5WJkIcbxQha6sBTeqzPByZmMWDqSg6wG06dV0C2R-zTN94yWL1uqIJ8jMkp77wTxFESLq-SIi3uYFFhZKjJdy3bHJzbHys7HSBepFyDcsZPg7FH6EZqMQpi6NUM");
        System.out.println(claim);
    }

//    @Test
    public void testGenECDSAToken() throws Exception {
        Map<String,Object> claim = new HashMap<String,Object>();
        claim.put("name","xiaomo");
        claim.put("age","34");
        System.out.println(JwtUtil.genECDSAToken(claim));
    }

//    @Test
    public void testParseECDSAToken() throws Exception {
        Map<String, Object> claim = JwtUtil.parseECDSAToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJjbGFpbSI6eyJuYW1lIjoieGlhb21vIiwiYWdlIjoiMzQifSwiZXhwIjoxNzM3MDE2NDYyLCJuYmYiOjE3MzcwMTYzNDEsImp0aSI6IjVkYjJkYTI1LWUzOWMtNDQyYi04YmFlLWI1MjQzN2I2ZDA4OCIsImlzcyI6InlvdXJfaXNzdWVyIiwic3ViIjoieW91cl9zdWJqZWN0IiwiYXVkIjoieW91cl9hdWRpZW5jZSJ9.mKRNv8aP6QNCgGc4IkISTyUydKrYgimWkyUs0yPLwz8VlZ20hcYzV9KbR-k1mGgazRFWlR2pnMx5sOhI1TPG1A");
        System.out.println(claim);
    }
}
