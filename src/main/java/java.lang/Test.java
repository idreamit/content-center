package java.lang;

import com.itcrazy.contentcenter.domain.dto.user.UserDTO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class Test {

    public static void main(String[] args) {
        System.out.println(new UserDTO().getClass().getClassLoader().getParent());
    }
}
