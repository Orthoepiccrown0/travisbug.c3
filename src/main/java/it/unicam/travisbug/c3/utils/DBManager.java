package it.unicam.travisbug.c3.utils;

import it.unicam.travisbug.c3.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManager {

    private AddressServiceImpl addressService;

    private AdminRequestsServiceImpl adminRequestsService;

    private CategoryServiceImpl categoryService;

    private ClientServiceImpl clientService;

    private CourierServiceImpl courierService;

    private EmployeeRequestsServiceImpl employeeRequestsService;

    private EmployeeServiceImpl employeeService;

    private MerchantServiceImpl merchantService;

    private OrderDetailsServiceImpl orderDetailsService;

    private OrderServiceImpl orderService;

    private ProductServiceImpl productService;

    private PromotionServiceImpl promotionService;

    private ShippingServiceImpl shippingService;

    private ShopCategoryServiceImpl shopCategoryService;

    private ShopServiceImpl shopService;

    public AddressServiceImpl getAddressService() {
        return addressService;
    }

    public AdminRequestsServiceImpl getAdminRequestsService() {
        return adminRequestsService;
    }

    public CategoryServiceImpl getCategoryService() {
        return categoryService;
    }

    public ClientServiceImpl getClientService() {
        return clientService;
    }

    public CourierServiceImpl getCourierService() {
        return courierService;
    }

    public EmployeeRequestsServiceImpl getEmployeeRequestsService() {
        return employeeRequestsService;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public MerchantServiceImpl getMerchantService() {
        return merchantService;
    }

    public OrderDetailsServiceImpl getOrderDetailsService() {
        return orderDetailsService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public ProductServiceImpl getProductService() {
        return productService;
    }

    public PromotionServiceImpl getPromotionService() {
        return promotionService;
    }

    public ShippingServiceImpl getShippingService() {
        return shippingService;
    }

    public ShopCategoryServiceImpl getShopCategoryService() {
        return shopCategoryService;
    }

    public ShopServiceImpl getShopService() {
        return shopService;
    }

    @Autowired
    public void setAddressService(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setAdminRequestsService(AdminRequestsServiceImpl adminRequestsService) {
        this.adminRequestsService = adminRequestsService;
    }

    @Autowired
    public void setCategoryService(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setClientService(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setCourierService(CourierServiceImpl courierService) {
        this.courierService = courierService;
    }

    @Autowired
    public void setEmployeeRequestsService(EmployeeRequestsServiceImpl employeeRequestsService) {
        this.employeeRequestsService = employeeRequestsService;
    }

    @Autowired
    public void setEmployeeService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setMerchantService(MerchantServiceImpl merchantService) {
        this.merchantService = merchantService;
    }

    @Autowired
    public void setOrderDetailsService(OrderDetailsServiceImpl orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Autowired
    public void setPromotionService(PromotionServiceImpl promotionService) {
        this.promotionService = promotionService;
    }

    @Autowired
    public void setShippingService(ShippingServiceImpl shippingService) {
        this.shippingService = shippingService;
    }

    @Autowired
    public void setShopCategoryService(ShopCategoryServiceImpl shopCategoryService) {
        this.shopCategoryService = shopCategoryService;
    }

    @Autowired
    public void setShopService(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

}
