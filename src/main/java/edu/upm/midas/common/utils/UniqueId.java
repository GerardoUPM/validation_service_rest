package edu.upm.midas.common.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class UniqueId {

    public String generate(int length){
        return RandomStringUtils.randomAlphanumeric( length ).toLowerCase();
    }

}
