package edu.upm.midas.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class UniqueId {


    /**
     * @param length
     * @return Una cadena alfanúmerica única (id) de longitud recibida
     */
    public String generate(int length){
        return RandomStringUtils.randomAlphanumeric( length ).toLowerCase();
    }

}
