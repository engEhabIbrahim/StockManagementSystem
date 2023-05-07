package eg.edu.bsu.fcai.stockmanagementsystem.configuration;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.*;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static eg.edu.bsu.fcai.stockmanagementsystem.model.UserRole.*;

@Configuration
public class CommandLineRunnerConfiguration {
    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, PasswordEncoder passwordEncoder, ItemStatusService itemStatusService, TypeService typeService, ProductService productService, StocksService stocksService) {
        return args -> {
            User superAdmin =   User.builder().id("00000000000000").email("superadmin@stock.com")   .password(passwordEncoder.encode("superadmin")) .nameEnglish("Super Admin") .nameArabic("سوبر أدمن")    .phone("01234567890").imageUrl("/assets/img/logo.png")      .isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).authorities(SUPER_ADMIN.getGrantedAuthorities()).build();
            User admin =        User.builder().id("00000000000001").email("admin@stock.com")        .password(passwordEncoder.encode("admin"))      .nameEnglish("Admin")       .nameArabic("أدمن")         .phone("01234567891").imageUrl("/assets/img/profile.jpg")   .isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).authorities(ADMIN.getGrantedAuthorities()).build();
            User user =         User.builder().id("00000000000002").email("user@stock.com")         .password(passwordEncoder.encode("user"))       .nameEnglish("User")        .nameArabic("مستخدم")       .phone("01234567892").imageUrl("/assets/img/profile.jpg")   .isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).authorities(USER.getGrantedAuthorities()).build();
            User consumer =     User.builder().id("00000000000003").email("consumer@stock.com")     .password(passwordEncoder.encode("consumer"))   .nameEnglish("Consumer")    .nameArabic("مستهلك")       .phone("01234567893").imageUrl("/assets/img/profile.jpg")   .isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).authorities(CONSUMER.getGrantedAuthorities()).build();

            userService.add(superAdmin);
            admin = userService.add(admin);
            userService.add(user);
            userService.add(consumer);

            itemStatusService.add(ItemStatus.builder().name("جديد").build());
            itemStatusService.add(ItemStatus.builder().name("مستعمل").build());
            itemStatusService.add(ItemStatus.builder().name("مسترجع").build());
            itemStatusService.add(ItemStatus.builder().name("كهنة").build());

            Type pd = Type.builder().name("أضافة من الجامعة").build();
            typeService.add(pd);
            pd = Type.builder().name("هدية").build();
            typeService.add(pd);
            pd = Type.builder().name("فاتورة").build();
            typeService.add(pd);
            pd = Type.builder().name("أسترجاع").build();
            typeService.add(pd);

            Stock stock1 = Stock.builder().name("المستديم").admin(admin).build();
            stocksService.add(stock1);
            Stock stock2 = Stock.builder().name("الكهنة").admin(admin).build();
            stocksService.add(stock2);
            Stock stock3 = Stock.builder().name("المستهلك").admin(admin).build();
            stocksService.add(stock3);


            Product product = Product.builder().stock(stock3).name("رزمة ورق").build();
            productService.add(product);
            product = Product.builder().name("شاشة كمبيوتر").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("لوحة مفاتيح").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("جهاز عرض علي الحائط").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("حبر طابعة").stock(stock1).monthsValidation(12).build();
            productService.add(product);
            product = Product.builder().name("احبار الأختام").stock(stock3).monthsValidation(6).build();
            productService.add(product);
            product = Product.builder().name("مكتب").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("خزنة").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("ماوس").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("طابعة").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("سيرفر").stock(stock1).build();
            productService.add(product);
            product = Product.builder().name("أقلام").stock(stock3).build();
            productService.add(product);
        };
    }
}
