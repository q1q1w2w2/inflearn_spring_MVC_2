package community.demo.web.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MemberJoinForm {

    @NotBlank
    private String name;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @Email
    private String email;

}
