package community.demo.web.form;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MemberJoinForm {

    private String name;

    private String loginId;

    private String password;

    private String email;

}
