package com.dhrs.date.common.utils;

import com.alibaba.fastjson.JSON;
import com.dhrs.date.common.constant.JwtConstant;
import com.dhrs.date.common.entity.user.MemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {



    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public static String createJWT(String id, String subject, MemberEntity memberEntity) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, JwtConstant.KEY.getBytes());
//                .claim("user", memberEntity);
        if (JwtConstant.TTL > 0) {
            builder.setExpiration(new Date(nowMillis + JwtConstant.TTL));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr) {

        return Jwts.parser()
                .setSigningKey(JwtConstant.KEY.getBytes())
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    public static Long getUserId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Claims claims = JwtUtil.parseJWT(authorization.substring(7));
        return Long.valueOf(claims.getId());
    }

//    public static MemberEntity getUser(HttpServletRequest request) {
//        Claims claims = (Claims) request.getAttribute("claims");
//        Object user = claims.get("user");
//        String s = JSON.toJSONString(user);
//        MemberEntity memberEntity = JSON.parseObject(s, MemberEntity.class);
//        return memberEntity;
//    }

}
