package com.dekankilic.cqrswithaxonserverandelasticsearch.query.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.math.BigDecimal;

@Document(indexName = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Setting(settingPath = "static/es-settings.json") // kendi analizlerimizin configlerini yapabileceğimizi söylemiştik. Onu Json olarak import ediyoruz. Eğer istersek bunları querylerimizin içerisinde de yapabilirdik.
public class Product {
    @Id
    private String id;

    @Field(name = "name", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "customer_search")
    private String name;
    //@Field(name = "name", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "customer_search")
    private String description;
    //@Field(name = "price", type = FieldType.Double)
    private BigDecimal price;
    private int stock;
}
