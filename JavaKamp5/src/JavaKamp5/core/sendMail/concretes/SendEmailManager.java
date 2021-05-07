package JavaKamp5.core.sendMail.concretes;

import java.util.Scanner;

import JavaKamp5.core.sendMail.abstracts.SendEmailService;

public class SendEmailManager implements SendEmailService {

	@Override
	public boolean sendEmail(String email) {
		System.out.println(email + " adresini dogrulamak icin lutfen eposta adresinize gelen linki cevaplayin.");
		System.out.print("Eposta aktivasyonunu onaylamak icin lutfen 'e/E' tusuna basiniz: ");
		Scanner girdi = new Scanner(System.in);
		String sonuc = girdi.next();

		if (sonuc.trim().toLowerCase().equals("e")) {
			System.out.println("Eposta aktivasyonu tamamlanmistir");
			return true;
		}
		return false;

	}

}
