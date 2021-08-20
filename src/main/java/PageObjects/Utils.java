package PageObjects;


import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
        public static String generateRandomEmail(){
            String random = RandomStringUtils.randomAlphabetic(10);
            String randomEmail = random + "@gmail.com";
            System.out.println(randomEmail);
            return randomEmail;
        }
}
