package edu.poly.J6ShopNongsan.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO  implements Serializable {
		@NotEmpty(message = "Tên tài khoản không được bỏ trống")
		@Length(min=5,message = "Độ dài tên tài khoản phải lớn hơn 5")
	  	String username;
		@NotEmpty(message = "Mật khẩu không được bỏ trống")
		@Length(min=5,message = "Độ dài mật khẩu phải lớn hơn 5")
	    String password;
		@NotEmpty(message = "Tên người dùng không được bỏ trống")
	    String fullname;
		@NotEmpty(message = "Số điện thoại không được bỏ trống")
		String phone;
		@NotEmpty(message = "Email không được bỏ trống")
		@Email(message = "Không đúng định dạng Email")
	    String email;
	    String photo;
	    Boolean enable;
	    @NotEmpty(message = "Địa chỉ không được bỏ trống")
	    String address;
	    String verification_code;

    Date register_date = new Date();

}
