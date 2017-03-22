package com.nb.daipengfei.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by daipengfei
 * on 2017/3/16.
 */
public class MongoClientTest {
    public static void main(String[] args) {
        MongoCredential credential = MongoCredential.createCredential("super",
                "test",
                "5446655".toCharArray());
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("order");
        List<Integer> list = Arrays.asList(0, 3);
        Set<Long> set = new HashSet<>();
        set.add(0L);
        set.add(3L);
        collection.insertOne(new Document().append("point", set));

    }

    @Test
    public void testMongo() {
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(
                new MongoClient(), "test");
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(factory), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.afterPropertiesSet();
//        converter.setCustomConversions(new CustomConversions(Collections.emptyList()));
        MongoOperations mongoOps = new MongoTemplate(factory, converter);
//        mongoOps.insert(new Order("ORDER10006", new Date(),new GeoJsonPoint(0,3)));
        Order one = mongoOps.findOne(new Query(Criteria.where("_id").is("ORDER10006")), Order.class);
        System.out.println(one);
        List<Order> all = mongoOps.findAll(Order.class);
        System.out.println(all);
    }
}
