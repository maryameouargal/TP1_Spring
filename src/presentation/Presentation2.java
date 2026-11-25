package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new File("config.txt"));

		// 1. Charger la DAO par réflexion
		String daoClassName = scanner.nextLine().trim();
		Class<?> cDao = Class.forName(daoClassName);
		IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

		// 2. Charger le Métier par réflexion
		String metierClassName = scanner.nextLine().trim();
		Class<?> cMetier = Class.forName(metierClassName);
		IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

		// 3. Injecter la DAO dans le Métier
		Method setDao = cMetier.getMethod("setDao", IDao.class);
		setDao.invoke(metier, dao);

		// 4. Appeler le calcul
		System.out.println("le Résultat est  = " + metier.calcul());

		scanner.close();
	}
}