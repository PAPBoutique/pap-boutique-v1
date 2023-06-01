package com.pap.product.rest.constants;

public class ProductDocMessages {
    public static final String GET_PRODUCTS = "Get Product by page, size and filter";
    public static final String GET_PRODUCT_BY_ID = "Get a product by its id ";
    public static final String GET_PRODUCTS_SUCCESS = "Retrieves a paginated list of products based on the given page number, page size, and filter value" ;
    public static final String GET_PRODUCTS_FAILED = "Failed to retrieve products. The page number must be greater than or equal to 0, and the page size must be less than 6.";
    public static final String ADD_PRODUCTS = "Add list of products";
    public static final String ADD_PRODUCTS_PARAM = "List of ProductDto" ;
    public static final String ADD_PRODUCTS_SUCCESS ="List of products created with success";
    public static final String DELETE_PRODUCTS = "Delete a product by id";
    public static final String DELETE_PRODUCTS_PARAM = "Id of the product" ;
    public static final String DELETE_PRODUCTS_SUCCESS = "The product deleted with success";
    public static final String UPDATE_PRODUCT_SUCCESS = "The product updated";
    public static final String UPDATE_PRODUCT = "Update a product and save it";
}
