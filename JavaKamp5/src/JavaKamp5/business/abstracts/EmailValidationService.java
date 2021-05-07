package JavaKamp5.business.abstracts;

public interface EmailValidationService {
	boolean emailValidation(String email);
	boolean checkEmail(String email);
	void confirmMail(String email);
}
