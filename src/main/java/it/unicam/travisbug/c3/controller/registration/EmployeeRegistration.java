package it.unicam.travisbug.c3.controller.registration;

import it.unicam.travisbug.c3.model.Employee;
import it.unicam.travisbug.c3.model.EmployeeRequests;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import it.unicam.travisbug.c3.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class EmployeeRegistration {

    private DBManager dbManager;

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/register/employee")
    public String employeeRegister(Model model, String used_email) {
        List<Shop> shops = dbManager.getShopService().getAll();
        if (shops.size() != 0) {
            model.addAttribute("shops", shops);
        }
        model.addAttribute("used_email", used_email);
        return "accounts/employeeRegistration";
    }

    @PostMapping("/register/employee")
    public String employeeRegister(Model model,
                                   String nameE,
                                   String surnameE,
                                   String emailE,
                                   String passwordE,
                                   String phoneE,
                                   Integer shopE,
                                   HttpServletResponse response,
                                   RedirectAttributes redirectAttrs) {
        Shop s = dbManager.getShopService().findById(shopE);
        if (!appCookies.isUsedEmail(emailE, dbManager)) {
            return registerEmployee(nameE, surnameE, emailE, passwordE, phoneE, s, response, redirectAttrs);
        }
        redirectAttrs.addAttribute("used_email", "used");
        return "redirect:/register/employee";

    }

    private String registerEmployee(String name,
                                    String surname,
                                    String email,
                                    String password,
                                    String phone,
                                    Shop shop,
                                    HttpServletResponse response,
                                    RedirectAttributes redirectAttrs) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID().toString());
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPassword(PasswordTool.getMD5String(password));
        employee.setEmail(email);
        employee.setShop(shop);
        employee.setStatus("Pending");
        if (phone != null) {
            employee.setPhone(phone);
        }
        dbManager.getEmployeeService().saveEmployee(employee);

        redirectAttrs.addAttribute("employee", employee);
        addRequest(employee, shop);

        appCookies.setUserIDCookie(employee.getId(), response);
        appCookies.setRoleCookie(Roles.EMPLOYEE, response);

        return "redirect:/";
    }

    private void addRequest(Employee employee, Shop shop) {
        EmployeeRequests employeeRequests = new EmployeeRequests();
        employeeRequests.setEmployee(employee);
        employeeRequests.setDate(new Date());
        employeeRequests.setShop(shop);
        dbManager.getEmployeeRequestsService().saveEmployeeRequests(employeeRequests);
    }

}
