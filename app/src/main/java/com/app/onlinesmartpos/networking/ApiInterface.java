package com.app.onlinesmartpos.networking;


import com.app.onlinesmartpos.Constant;
import com.app.onlinesmartpos.model.APIRespond;
import com.app.onlinesmartpos.model.Category;
import com.app.onlinesmartpos.model.Customer;
import com.app.onlinesmartpos.model.Expense;
import com.app.onlinesmartpos.model.ExpenseReport;
import com.app.onlinesmartpos.model.Login;
import com.app.onlinesmartpos.model.MonthData;
import com.app.onlinesmartpos.model.OrderDetails;
import com.app.onlinesmartpos.model.OrderList;
import com.app.onlinesmartpos.model.Product;
import com.app.onlinesmartpos.model.SalesReport;
import com.app.onlinesmartpos.model.ShopInformation;
import com.app.onlinesmartpos.model.Suppliers;
import com.app.onlinesmartpos.model.WeightUnit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    //for login
    @FormUrlEncoded
    @POST("login")
    Call<Login> login(
            @Field(Constant.KEY_EMAIL) String email,
            @Field(Constant.KEY_PASSWORD) String password);


    //calling json array , need list
    @POST("order/create")
    Call<Customer> submitOrders(
            @Body RequestBody ordersData
    );


    //get customers data
//    @Headers({"Accept: application/json",
//            "Authorization: Bearer {token}"
//    })
    @GET("customer")
    Call<List<Customer>> getCustomers(
            @Query(Constant.SEARCH_TEXT) String searchText
    );


    //get customers data
    @GET("order")
    Call<List<OrderList>> getOrders(
            @Query(Constant.SEARCH_TEXT) String searchText

    );


    //get customers data
    @GET("product-service/all")
    Call<List<Product>> getProducts(
            @Query(Constant.SEARCH_TEXT) String searchText

    );


    //get product data
    @GET("product-service/{product_id}")
    Call<List<Product>> getProductById(
            @Path(value = Constant.PRODUCT_ID) String productId

    );


    //get order data
    @GET("order_details_by_invoice.php")
    Call<List<OrderDetails>> OrderDetailsByInvoice(
            @Query(Constant.INVOICE_ID) String invoiceId

    );


    //get order data
    @GET("sales_report_list.php")
    Call<List<OrderDetails>> getReportList(
            @Query(Constant.KEY_TYPE) String type

    );


    //get order data
    @GET("company")
    Call<List<ShopInformation>> shopInformation(
            @Query(Constant.SHOP_ID) String shopId

    );


    //get order data
    @GET("get_sales_report.php")
    Call<List<SalesReport>> getSalesReport(

            @Query(Constant.KEY_TYPE) String type
    );


    //get expense data
    @GET("expense")
    Call<List<ExpenseReport>> getExpenseReport(

            @Query(Constant.KEY_TYPE) String type
    );


    //for monthly expense data
    @GET("get_monthly_expense.php")
    Call<List<MonthData>> getMonthlyExpense(
            @Query(Constant.YEARLY) String year
    );


    //for monthly sales data
    @GET("get_monthly_sales.php")
    Call<List<MonthData>> getMonthlySales(
            @Query(Constant.YEARLY) String year
    );


    //for category data
    @GET("category/product")
    Call<List<Category>> getCategory();


    //for product data
    @GET("search_product.php")
    Call<List<Product>> searchProductByCategory(
            @Query(Constant.KEY_CATEGORY_ID) String categoryId
    );


    //add customer data to server
    @FormUrlEncoded
    @POST("customer/create")
    Call<Customer> addCustomers(
            @Field(Constant.CUSTOMER_NAME) String name,
            @Field(Constant.CUSTOMER_CELL) String cell,
            @Field(Constant.CUSTOMER_EMAIL) String email,
            @Field(Constant.CUSTOMER_ADDRESS) String address);


    //add unit data to server
    @FormUrlEncoded
    @POST("unit/create")
    Call<WeightUnit> addUnit(
            @Field(Constant.UNIT_NAME) String address
    );


    //add category data to server
    @FormUrlEncoded
    @POST("add_category.php")
    Call<Category> addCategory(
            @Field(Constant.CATEGORY_NAME) String category
    );



    //add expense data to server
    @FormUrlEncoded
    @POST("add_expense.php")
    Call<Expense> addExpense(
            @Field(Constant.EXPENSE_NAME) String name,
            @Field(Constant.EXPENSE_AMOUNT) String amount,
            @Field(Constant.EXPENSE_NOTE) String note,
            @Field(Constant.EXPENSE_DATE) String date,
            @Field(Constant.EXPENSE_TIME) String time);


    //update expense data to server
    @FormUrlEncoded
    @POST("update_expense.php")
    Call<Expense> updateExpense(
            @Field(Constant.EXPENSE_ID) String id,
            @Field(Constant.EXPENSE_NAME) String name,
            @Field(Constant.EXPENSE_AMOUNT) String amount,
            @Field(Constant.EXPENSE_NOTE) String note,
            @Field(Constant.EXPENSE_DATE) String date,
            @Field(Constant.EXPENSE_TIME) String time);


    //add suppliers data to server
    @FormUrlEncoded
    @POST("supplier/create")
    Call<Suppliers> addSuppliers(
            @Field(Constant.SUPPLIERS_NAME) String name,
            @Field(Constant.SUPPLIERS_CONTACT_PERSON) String contactPerson,
            @Field(Constant.SUPPLIERS_CELL) String cell,
            @Field(Constant.SUPPLIERS_EMAIL) String email,
            @Field(Constant.SUPPLIERS_ADDRESS) String address);


    //add suppliers data to server
    @FormUrlEncoded
    @PUT("supplier/{supplier_id}/edit")
    Call<Suppliers> updateSuppliers(
            //@Field(Constant.SUPPLIERS_ID) String suppliersId,
            @Path(value = Constant.SUPPLIERS_ID) String suppliersId,
            @Field(Constant.SUPPLIERS_NAME) String name,
            @Field(Constant.SUPPLIERS_CONTACT_PERSON) String contactPerson,
            @Field(Constant.SUPPLIERS_CELL) String cell,
            @Field(Constant.SUPPLIERS_EMAIL) String email,
            @Field(Constant.SUPPLIERS_ADDRESS) String address);


    //update customer data to server
    @FormUrlEncoded
    @PUT("customer/{id}/edit")
    Call<Customer> updateCustomers(
            //@Field(Constant.CUSTOMER_ID) String id,
            @Path("id") String Id,
//            @Path(value = Constant.CUSTOMER_ID) String id,
            @Field(Constant.CUSTOMER_NAME) String name,
            @Field(Constant.CUSTOMER_CELL) String cell,
            @Field(Constant.CUSTOMER_EMAIL) String email,
            @Field(Constant.CUSTOMER_ADDRESS) String address);


    //update unit data to server
    @FormUrlEncoded
    @PUT("unit/{unit_id}/edit")
    Call<WeightUnit> updateUnit(
            //@Field(Constant.UNIT_ID) String unitId,
            @Path(value = Constant.UNIT_ID) String unitId,
            @Field(Constant.UNIT_NAME) String unitName);



    //update category data to server
    @FormUrlEncoded
    @POST("update_category.php")
    Call<Category> updateCategory(
            @Field(Constant.CATEGORY_ID) String categoryId,
            @Field(Constant.CATEGORY_NAME) String categoryName);


    //delete customer
//    @FormUrlEncoded
    @DELETE("customer/{id}/delete")
    Call<Customer> deleteCustomer(
            //@Field(Constant.CUSTOMER_ID) String customerId
            @Path("id") String Id
    );


    //delete unit
    @FormUrlEncoded
    @DELETE("unit/{unit_id}/delete")
    Call<WeightUnit> deleteUnit(
            //@Field(Constant.UNIT_ID) String unitId
            @Path(value = Constant.UNIT_ID) String unitId
    );


    //delete category
    @FormUrlEncoded
    @DELETE("delete_category.php")
    Call<Category> deleteCategory(
            @Field(Constant.CATEGORY_ID) String categoryId
    );


    //delete customer
    @FormUrlEncoded
    @DELETE("order/{id}/delete")
    Call<OrderList> deleteOrder(
            //@Field(Constant.INVOICE_ID) String invoiceId
            @Path(value = Constant.INVOICE_ID) String invoiceId
    );


    //delete product
    @FormUrlEncoded
    @DELETE("product-service/{product_id}/delete")
    Call<Product> deleteProduct(
            //@Field(Constant.PRODUCT_ID) String productId
            @Path(value = Constant.PRODUCT_ID) String productId
    );


    //delete customer
    @FormUrlEncoded
    @DELETE("expense/{expense_id}/delete")
    Call<Expense> deleteExpense(
            //@Field(Constant.EXPENSE_ID) String expenseId
            @Path(value = Constant.EXPENSE_ID) String expenseId
    );


    //delete supplier
    @FormUrlEncoded
    @DELETE("supplier/{supplier_id}/delete")
    Call<Suppliers> deleteSupplier(
            //@Field(Constant.SUPPLIERS_ID) String suppliersId
            @Path(value = Constant.SUPPLIERS_ID) String suppliersId
    );


    //get suppliers data
    @GET("supplier/all")
    Call<List<Suppliers>> getSuppliers(
            @Query(Constant.SEARCH_TEXT) String searchText

    );


    //get weight unit
    @GET("get_weight_units.php")
    Call<List<WeightUnit>> getWeightUnits(
            @Query(Constant.SEARCH_TEXT) String searchText

    );


    //for upload image and info
    @Multipart
    @POST("product-service/create")
    Call<Product> addProduct(@Part MultipartBody.Part file,
                             @Part(Constant.KEY_FILE) RequestBody name,
                             @Part(Constant.PRODUCT_NAME) RequestBody productName,
                             @Part(Constant.PRODUCT_CODE) RequestBody productCode,
                             @Part(Constant.CATEGORY_ID) RequestBody categoryId,
                             @Part(Constant.PRODUCT_DESCRIPTION) RequestBody description,
                             @Part(Constant.PRODUCT_BUY_PRICE) RequestBody buyPrice,
                             @Part(Constant.PRODUCT_SELL_PRICE) RequestBody sellPrice,
                             @Part(Constant.PRODUCT_WEIGHT) RequestBody weight,
                             @Part(Constant.PRODUCT_WEIGHT_UNIT_ID) RequestBody weightUnitId,
                             @Part(Constant.SUPPLIERS_ID) RequestBody supplierId,
                             @Part(Constant.PRODUCT_STOCK) RequestBody stock);



    //for upload image and info
    @Multipart
    @PUT("product-service/{product_id}/edit")
    Call<Product> updateProduct(@Part MultipartBody.Part file,
                                @Part(Constant.KEY_FILE) RequestBody name,
                                @Part(Constant.PRODUCT_NAME) RequestBody productName,
                                @Part(Constant.PRODUCT_CODE) RequestBody productCode,
                                @Part(Constant.CATEGORY_ID) RequestBody categoryId,
                                @Part(Constant.PRODUCT_DESCRIPTION) RequestBody description,
                                @Part(Constant.PRODUCT_BUY_PRICE) RequestBody buyPrice,
                                @Part(Constant.PRODUCT_SELL_PRICE) RequestBody sellPrice,
                                @Part(Constant.PRODUCT_WEIGHT) RequestBody weight,
                                @Part(Constant.PRODUCT_WEIGHT_UNIT_ID) RequestBody weightUnitId,
                                @Part(Constant.SUPPLIERS_ID) RequestBody supplierId,
                                @Part(Constant.PRODUCT_STOCK) RequestBody stock,
                                @Part(Constant.PRODUCT_ID) RequestBody product_id);


    //for upload image and info
    @Multipart
    @POST("update_product_without_image.php")
    Call<Product> updateProductWithoutImage(

            @Part(Constant.PRODUCT_NAME) RequestBody productName,
            @Part(Constant.PRODUCT_CODE) RequestBody productCode,
            @Part(Constant.CATEGORY_ID) RequestBody categoryId,
            @Part(Constant.PRODUCT_DESCRIPTION) RequestBody description,
            @Part(Constant.PRODUCT_BUY_PRICE) RequestBody buyPrice,
            @Part(Constant.PRODUCT_SELL_PRICE) RequestBody sellPrice,
            @Part(Constant.PRODUCT_WEIGHT) RequestBody weight,
            @Part(Constant.PRODUCT_WEIGHT_UNIT_ID) RequestBody weightUnitId,
            @Part(Constant.SUPPLIERS_ID) RequestBody supplierId,
            @Part(Constant.PRODUCT_STOCK) RequestBody stock,
            @Part(Constant.PRODUCT_ID) RequestBody productId);


    //get expense data
    @GET("expense")
    Call<List<Expense>> getExpense(
            @Query(Constant.SEARCH_TEXT) String searchText

    );


    //get expense data
    @GET("expense")
    Call<List<Expense>> getAllExpense(
            @Query(Constant.KEY_TYPE) String type

    );


}