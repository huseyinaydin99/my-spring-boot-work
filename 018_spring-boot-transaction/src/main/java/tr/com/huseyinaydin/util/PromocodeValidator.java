package tr.com.huseyinaydin.util;

import java.util.Arrays;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class PromocodeValidator {

    public static void validatePromoCode(String promoCode) {
        List<String> promoCodes = Arrays.asList("3467hjdbf", "jdfhjke3786t", "7846hbfdh");
        if (!promoCodes.contains(promoCode)) {
            throw new RuntimeException("Geçersiz promosyon kodu !! Lütfen geçerli bir promosyon kodu giriniz.");
        }
    }
}