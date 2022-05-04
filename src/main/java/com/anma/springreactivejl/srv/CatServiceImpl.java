package com.anma.springreactivejl.srv;

import com.anma.springreactivejl.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private final TransactionTemplate transactionTemplate;

    @Bean
    DataSource devDatasource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    public CatServiceImpl(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Cat> allCats() {

        return null;
    }
}
