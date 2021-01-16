package com.afeng.web.common.token;

import com.afeng.web.common.util.AESUtil;
import com.afeng.web.common.util.ServletUtils;
import com.afeng.web.framework.exception.ApiException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.UUID;


/**
 * TOKEN工具类
 * ClassName: JwtUtil
 * Description: JWT信息体只是简单的base64编码，默认情况下会被解码，为了安全最好是放入加密后的信息。
 * JWT的加密算法只是针对JWT签名不被伪造
 * date: 2020/1/22 10:12
 *
 * @author afeng
 * @since JDK 1.8
 */
public class JwtUtil {

    /**
     * 获取请求头中key为“Authorization”的value == sessionId
     */
    private static final String AUTHORIZATION = "Authorization";
    /**
     * TOKEN规范 Bearer TOKEN
     */
    private static final String TOKEN_START = "Bearer";
    /**
     * token加密key
     */
    private static final String TOKEN_KEY = "userId";
    /**
     * token密钥
     */
    private static final String TOKEN_SECRET = "free2020";
    /**
     * token发布者
     */
    private static final String ISSUER = "afeng";
    /**
     * token有效时间，单位毫秒
     */
    private static final long TOKEN_EXPIRE_TIME = 7 * 24 * 3600 * 1000;


    /**
     * 根据token获取用户id，如果不存在则抛出异常
     */
    public static String getUserId() {
        String token = getToken();
        String userId = queryByToken(token);
        userId = AESUtil.decrypt(userId);//解密
        if (StringUtils.isEmpty(userId)) {
            throw ApiException.toAccessDenied("token非法");
        }
        return userId;
    }

    /**
     * 获取客户端TOKEN
     */
    public static String getToken() {
        try {
            String header = ServletUtils.getRequest().getHeader(AUTHORIZATION);
            if (StringUtils.isNotEmpty(header) && header.startsWith(TOKEN_START)) {
                return header.substring(7);//包括一个空格，所以7个字符
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验token是否正确
     *
     * @param value 加密的值
     * @param token 密钥
     * @return 是否正确
     */
    public static int verifyToken(String token, String value) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(TOKEN_KEY, value)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //直接输出是 类名 + 哈希码 (默认执行了 `toString`方法)
//            System.out.println(jwt);// => com.auth0.jwt.JWTDecoder@212b5695
            // 获取withIssuer 设置的值
//            System.out.println(jwt.getIssuer());
            // 获取开始生效时间/创建时时间
//            System.out.println(DateFormatUtils.format(jwt.getIssuedAt(), "yyyy-MM-dd HH:mm:ss"));
            // 获取过期时间，
//            System.out.println(DateFormatUtils.format(jwt.getExpiresAt(), "yyyy-MM-dd HH:mm:ss"));
            //获取过期时间的一半
            Date date = DateUtils.addMilliseconds(jwt.getExpiresAt(), -(int) (TOKEN_EXPIRE_TIME * 0.5d));
//            System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));

            // 获取Claim中的值
//            Claim claim = jwt.getClaim(TOKEN_KEY);
//            System.out.println(claim.asString());

            //过期时间已经过了一半
            if (new Date().after(date)) {
                return 2;
            }

            return 1;
        } catch (TokenExpiredException e) {
            //TOKEN过期
            return -1;
        } catch (Exception e) {
            //解密失败
            return 0;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @param token token
     * @return token中包含的信息
     */
    public static String queryByToken(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                throw ApiException.toAccessDenied("token不可为空");
            }
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(TOKEN_KEY).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            //解码失败
        }
        return null;
    }

    /**
     * 生成签名
     *
     * @param value 需要加密的值
     * @return 加密的token
     */
    public static String createToken(String value) {
        try {
            String encrypt = AESUtil.encrypt(value);//加密
            Date date = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 附带加密信息，尽量减少体积
            return JWT.create()
                    .withIssuedAt(new Date()) //生成签名的时间
                    .withExpiresAt(date) //过期时间
                    .withClaim(TOKEN_KEY, encrypt) //携带数据
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //使用RS256（公私钥）加密算法签名

    /*获取签发的token，返回给前端*/
    public static String generTokenByRS256(String value) {
        try {
            String encrypt = AESUtil.encrypt(value);//加密
            Date dateExpires = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
            RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key(); // 获取公钥/私钥
            Algorithm algorithm = Algorithm.RSA256(
                    rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
            String[] audience = {"app", "web"};
            return JWT.create()
                    .withIssuer(ISSUER)        //发布者
                    .withAudience(audience)     //观众，相当于接受者
                    .withIssuedAt(new Date())   // 生成签名的时间
                    .withExpiresAt(dateExpires)    //生成签名的有效期
                    .withClaim(TOKEN_KEY, encrypt) //存数据
                    .withNotBefore(new Date())  //生效时间
                    .withJWTId(UUID.randomUUID().toString()) //编号
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*验证token*/
    public static DecodedJWT verifierTokenByRS256(String token) {
        try {
            RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key(); // 获取公钥/私钥
            //其实按照规定只需要传递 publicKey 来校验即可，这可能是auth0 的缺点
            Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build(); //Reusable verifier instance 可复用的验证实例
            DecodedJWT jwt = verifier.verify(token);
            //直接输出是 类名 + 哈希码 (默认执行了 `toString`方法)
//            System.out.println(jwt);
            // 获取withIssuer 设置的值
//            System.out.println(jwt.getIssuer());
            // 获取开始生效时间/创建时时间
//            System.out.println(jwt.getIssuedAt());
            // 获取过期时间，
//            System.out.println(jwt.getExpiresAt());
            // 获取Claim中的值
//            Claim claim = jwt.getClaim(TOKEN_KEY);
//            System.out.println(claim.asString());
            return jwt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO 通过AES创建简单token

    /**
     * 生成token
     * createTokenByAES与authTokenByAES对应
     *
     * @param userId 用户ID
     * @author AFeng
     * @createDate 2020/8/4 14:06
     **/
    public static String createTokenByAES(String userId) {
        try {
            if (StringUtils.isEmpty(userId)) {
                return null;
            }
            String token = userId + "_" + System.currentTimeMillis();
            token = AESUtil.encrypt(token);
            return token;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 验证token，成功则返回userId，失败返回null
     *
     * @param token token
     * @author AFeng
     * @createDate 2020/8/4 14:07
     **/
    public static String authTokenByAES(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            if (StringUtils.length(token) < 16) {
                return null;
            }
            String token_decrypt = AESUtil.decrypt(token);
            if (StringUtils.isNotEmpty(token_decrypt)) {
                String[] arr = token_decrypt.split("_");
                if (arr != null && arr.length > 1) {
                    String userId = arr[0];
                    String loginTime = arr[1];
                    Date date = DateUtils.addMilliseconds(new Date(Long.parseLong(loginTime)), (int) (TOKEN_EXPIRE_TIME));
                    if (new Date().after(date)) {
                        //token过期
                        return null;
                    }
                    return userId;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
