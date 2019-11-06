package com.myspider.service.impl;

import com.myspider.entity.SpiderTokenEntity;
import com.myspider.repository.SpiderTokenDao;
import com.myspider.service.SpiderTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
@Slf4j
public class SpiderTokenServiceImpl implements SpiderTokenService {

    @Autowired
    private SpiderTokenDao spiderTokenDao;

    /**
     * 字符串池
     */
    private static String[] STR_ARR = new String[] { "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "0" };

    @Override
    @Transactional
    public boolean generateToken() {

        try{
            for(int i=0; i<=1000;i++){
                SpiderTokenEntity entity = SpiderTokenEntity.builder().token(this.getToken(59)).createDate(new Date()).build();
                spiderTokenDao.save(entity);
            }
            return true;
        }
        catch (Exception e){
            log.error("存入pdd token 表失败,{}",e.getMessage());
            return false;
        }
    }

    /**
     *
     * 根据指定的长度生成的含有大小写字母及数字的字符串
     *
     * @param length
     *            指定的长度
     * @return 按照指定的长度生成的含有大小写字母及数字的字符串
     */
    public static String getToken(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(STR_ARR[rand.nextInt(STR_ARR.length)]);
        }
        return sb.toString();
    }

}
