package com.javaweb.service.impl;

import com.javaweb.entity.BaseEntity;
import com.javaweb.service.SomeService;
import com.javaweb.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
@Service
public class SomeServiceimpl implements SomeService {
    @Override
    public void SetCreateDate(BaseEntity entity, String dateString) {
        try {
            entity.setCreateDate(DateUtil.convertStringToDate(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
            // log.error("Error parsing date string: " + dateString);
        }
    }
}
