package com.anma.springreactivejl.srv;

import com.anma.springreactivejl.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    @Override
    public List<Cat> allCats() {

        return null;
    }
}
