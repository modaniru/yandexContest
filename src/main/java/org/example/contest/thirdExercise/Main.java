package org.example.contest.thirdExercise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static Integer PRICE_LESS_THAN;
    private static LocalDate DATE_AFTER;
    private static String NAME_CONTAINS;
    private static Integer PRICE_GREATER_THAN;
    private static LocalDate DATE_BEFORE;

    public static void main(String[] args) throws ParseException {
        class Product {
            private Long id;
            private String name;
            private LocalDate date;
            private Long price;

            public Product(Long id, String name, LocalDate date, Long price) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.date = date;
            }

            public Long getId() {
                return id;
            }

            @Override
            public String toString() {
                return "Product{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", date=" + date +
                        ", price=" + price +
                        '}';
            }
        }
        JSONParser jsonParser = new JSONParser();
        Scanner scanner = new Scanner(System.in);
        String js = scanner.nextLine();

        //init list of products
        Object obj = jsonParser.parse(js);
        JSONArray productList = (JSONArray) obj;
        List<Product> products = new ArrayList<>();
        for (Object o : productList) {
            JSONObject object = (JSONObject) o;
            Product product = new Product(
                    (Long) object.get("id"),
                    (String) object.get("name"),
                    LocalDate.parse((String) object.get("date"), formatter),
                    (Long) object.get("price")
            );
            products.add(product);
        }
        for (int i = 0; i < 5; i++) {
            initFilter(scanner.nextLine());
        }
        products = products.stream().filter(p -> {
            String name = p.name.toLowerCase();
            return name.contains(NAME_CONTAINS)
                    && p.price >= PRICE_GREATER_THAN
                    && p.price <= PRICE_LESS_THAN
                    && (p.date.isAfter(DATE_AFTER) || p.date.equals(DATE_AFTER))
                    && (p.date.isBefore(DATE_BEFORE) || p.date.equals(DATE_BEFORE));
        }).sorted(Comparator.comparingLong(Product::getId)).collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", product.id);
            jsonObject.put("name", product.name);
            jsonObject.put("date", product.date.format(formatter));
            jsonObject.put("price", product.price);
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
    }

    public static void initFilter(String row) {
        String[] arguments = row.split(" ");
        switch (arguments[0]) {
            case "PRICE_LESS_THAN":
                PRICE_LESS_THAN = Integer.parseInt(arguments[1]);
                break;
            case "DATE_AFTER":
                DATE_AFTER = LocalDate.parse(arguments[1], formatter);
                break;
            case "NAME_CONTAINS":
                NAME_CONTAINS = arguments[1].toLowerCase();
                break;
            case "PRICE_GREATER_THAN":
                PRICE_GREATER_THAN = Integer.parseInt(arguments[1]);
                break;
            case "DATE_BEFORE":
                DATE_BEFORE = LocalDate.parse(arguments[1], formatter);
                break;
        }
    }
}
