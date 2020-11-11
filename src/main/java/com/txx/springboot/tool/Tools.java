package com.txx.springboot.tool;

import com.txx.springboot.entity.UserLogin;
import com.txx.springboot.service.UserLoginRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 淘米水浇花
 */
@Service
public class Tools {
    private final JdbcTemplate jdbcTemplate;

    public Tools(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static String getLocalTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    public String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    public boolean isHasUsername(String username){
        String sql = "select * from t_user_login where username = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {username} , new UserLoginRowMapper());
        return !list.isEmpty();
    }
    public boolean isHasToken(String token){
        String sql = "select * from t_user_login where token = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {token} , new UserLoginRowMapper());
        return !list.isEmpty();
    }
    public List<UserLogin> getUserInfoByToken(String token){
        String sql = "select * from t_user_login where token = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {token} , new UserLoginRowMapper());
        return list;
    }
    public boolean isCompany(String token){
        String sql = "select * from t_user_login where token = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {token} , new UserLoginRowMapper());
        return list.get(0).getIdentity() == 1;
    }
}
