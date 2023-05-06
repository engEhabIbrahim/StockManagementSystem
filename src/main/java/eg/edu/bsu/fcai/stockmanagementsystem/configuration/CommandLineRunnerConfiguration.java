package eg.edu.bsu.fcai.stockmanagementsystem.configuration;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.*;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommandLineRunnerConfiguration {
    @Bean public CommandLineRunner commandLineRunner(UserService userService,
                                                     PasswordEncoder passwordEncoder,
                                                     ItemStatusService itemStatusService,
                                                     TypeService typeService,
                                                     ProductService productService,
                                                     StocksService stocksService
    ) {
        return args -> {
            userService.add(User.builder()
                    .id("30208182202198")
                    .email("auk@outlook.com")
                    .password(passwordEncoder.encode("ahmed"))
                    .nameArabic("أحمد حاتم محمدلطفي كامل")
                    .nameEnglish("Ahmed Hatem Mohammed-Loutfy Kamel")
                    .phone("01553245191")
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .imageUrl("/assets/img/logo.png")
//                    .authorities(Sets.newHashSet())
                    .build());

            User user = User.builder().isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("10000000000000").email("guest@example.com").password(passwordEncoder.encode("123")).nameEnglish("Guest User").nameArabic("مستخدم تجريبي").phone("01234567891").imageUrl("/assets/img/profile.jpg").build();
            userService.add(user);
            user = User.builder().isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000000").email("name1@example.com").password(passwordEncoder.encode("123")).nameEnglish("Beni Suef University").nameArabic("جامعة بني سويف").phone("01234567881").imageUrl("/assets/img/profile.jpg").build();
            userService.add(user);
            user = User.builder().isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000001").email("name2@example.com").password(passwordEncoder.encode("123")).nameEnglish("Hesham Mohammed Ahmed").nameArabic("هشام محمد أحمد").phone("01234567871").imageUrl("/assets/img/profile.jpg").build();
            userService.add(user);
            user = User.builder().isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000002").email("name3@example.com").password(passwordEncoder.encode("123")).nameEnglish("Safwat Abdelazem").nameArabic("صفوت عبدالعظيم").phone("01234567861").imageUrl("/assets/img/profile.jpg").build();
            userService.add(user);
            user = User.builder().isEnabled(false).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000003").email("name4@example.com").password(passwordEncoder.encode("123")).nameEnglish("Maisara Moahmmed Moahmmed").nameArabic("ميسرة محمد محمد").imageUrl("/assets/img/profile.jpg").phone("01234567851").build();
            userService.add(user);
            user = User.builder().isEnabled(false).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000004").email("name5@example.com").password(passwordEncoder.encode("123")).nameEnglish("Mostafa Ahmed Ali").nameArabic("مصطفي أحمد علي").imageUrl("/assets/img/profile.jpg").phone("01234567841").build();
            userService.add(user);
            user = User.builder().isEnabled(false).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).id("00000000000005").email("name6@example.com").password(passwordEncoder.encode("123")).nameEnglish("Eman Eldin Metwally").nameArabic("عماد الدين متولي").imageUrl("/assets/img/profile.jpg").phone("01234567831").build();
            userService.add(user);


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

            Stock stock1 = Stock.builder().name("المستديم").admin(userService.findById("00000000000002")).build();
            stocksService.add(stock1);
            Stock stock2 = Stock.builder().name("الكهنة").admin(userService.findById("00000000000003")).build();
            stocksService.add(stock2);
            Stock stock3 = Stock.builder().name("المستهلك").admin(userService.findById("00000000000004")).build();
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
